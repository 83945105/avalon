package pub.avalonframework.shiro.session.mgt.eis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.CacheManagerAware;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.subject.MutablePrincipalCollection;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.avalonframework.redis.spring.core.RedisTemplate;
import pub.avalonframework.security.core.beans.IncorrectPrincipalTypeException;
import pub.avalonframework.security.core.beans.Principal;
import pub.avalonframework.shiro.session.mgt.RedisSession;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author baichao
 */
public class RedisSessionDAO extends AbstractSessionDAO implements CacheManagerAware {

    private static final Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

    private RedisTemplate<String, Object, String, Object> redisTemplate;

    private final static String DEFAULT_SESSION_KEY_PREFIX = "shiro:session:sessions:";

    private final static String DEFAULT_ATTRIBUTE_KEY_PREFIX = "sessionAttr:";

    private String sessionKeyPrefix = DEFAULT_SESSION_KEY_PREFIX;

    private String attributeKeyPrefix = DEFAULT_ATTRIBUTE_KEY_PREFIX;

    private final static long DEFAULT_EXPIRE = -2;

    private final static long NO_EXPIRE = -1;

    private long expire = DEFAULT_EXPIRE;

    public RedisSessionDAO(RedisTemplate<String, Object, String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String getSessionKey(Serializable sessionId) {
        return this.sessionKeyPrefix + sessionId;
    }

    private String getAttributeKey(Object attributeKey) {
        return this.attributeKeyPrefix + attributeKey;
    }

    private void saveSession(Session session) {
        if (session == null || session.getId() == null) {
            logger.error("RedisSessionDAO saveSession session or session id is null.");
            throw new UnknownSessionException("RedisSessionDAO saveSession session or session id is null.");
        }
        if (session instanceof RedisSession && !((RedisSession) session).isChanged()) {
            return;
        }
        logger.debug("RedisSessionDAO saveSession session sessionId: {}", session.getId());
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", session.getId());
        map.put("startTimestamp", session.getStartTimestamp());
        map.put("lastAccessTime", session.getLastAccessTime());
        map.put("timeout", session.getTimeout());
        map.put("host", session.getHost());
        Collection<Object> keys = session.getAttributeKeys();
        if (keys != null && keys.size() > 0) {
            Object value;
            for (Object key : keys) {
                value = session.getAttribute(key);
                if (value == null) {
                    continue;
                }
                if (DefaultSubjectContext.PRINCIPALS_SESSION_KEY.equals(key)) {
                    map.put(getAttributeKey(key), new RedisMutablePrincipalCollectionWrapper((MutablePrincipalCollection) value));
                    continue;
                }
                map.put(getAttributeKey(key), value);
            }
        }
        try {
            String sessionKey = getSessionKey(session.getId());
            this.redisTemplate.hPutAll(sessionKey, map);
        } catch (Exception e) {
            logger.error("RedisSessionDAO saveSession fail, sessionId: {}", session.getId());
            e.printStackTrace();
        }
    }

    private Session buildSession(Map<String, Object> entries) {
        if (entries == null || entries.size() == 0) {
            return null;
        }
        Object sessionIdObj = entries.get("id");
        if (sessionIdObj == null) {
            logger.error("RedisSessionDAO buildSession sessionId is null.");
            return null;
        }
        String sessionId = String.valueOf(sessionIdObj);
        String sessionKey = getSessionKey(sessionId);
        RedisSession session = new RedisSession();
        String key;
        Object value;
        int len = this.attributeKeyPrefix.length();
        for (Map.Entry<String, Object> entry : entries.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if ("id".equals(key)) {
                session.setId(String.valueOf(value));
                continue;
            }
            if ("startTimestamp".equals(key)) {
                session.setStartTimestamp(new Date(Long.valueOf(value == null ? "0" : value.toString())));
                continue;
            }
            if ("lastAccessTime".equals(key)) {
                session.setLastAccessTime(new Date(Long.valueOf(value == null ? "0" : value.toString())));
                continue;
            }
            if ("stopTimestamp".equals(key)) {
                session.setStopTimestamp(new Date(Long.valueOf(value == null ? "0" : value.toString())));
                continue;
            }
            if ("timeout".equals(key)) {
                session.setTimeout(Long.valueOf(value == null ? "0" : value.toString()));
                continue;
            }
            if ("host".equals(key)) {
                session.setHost(String.valueOf(value));
                continue;
            }
            if (key.startsWith(this.attributeKeyPrefix)) {
                key = key.substring(len);
                if (DefaultSubjectContext.PRINCIPALS_SESSION_KEY.equals(key)) {
                    session.setAttribute(key, new RedisMutablePrincipalCollectionWrapper((Map) value).getMutablePrincipalCollection());
                    continue;
                }
                session.setAttribute(key, value);
            }
        }
        session.setTouchHandler(() -> {
            if (expire != NO_EXPIRE && expire < session.getTimeout()) {
                logger.warn("RedisSessionDAO saveSession session expire time: {} is less than Session timeout: {} , It may cause some problems.", expire, session.getTimeout());
            }
            long timeout;
            if (expire == DEFAULT_EXPIRE) {
                timeout = session.getTimeout();
            } else {
                timeout = expire;
            }
            Date lastAccessTime = new Date();
            // 该字段对于redis无用, 不更新
            // this.redisTemplate.hPut(sessionKey, "lastAccessTime", lastAccessTime);
            this.redisTemplate.expireKey(sessionKey, timeout, TimeUnit.MILLISECONDS);
            logger.debug("RedisSessionDAO update session key: " + sessionKey + " expire time: " + timeout);
            return lastAccessTime;
        });
        session.setStopHandler(() -> {
            Date stopTimestamp = new Date();
            this.redisTemplate.hPut(sessionKey, "stopTimestamp", stopTimestamp);
            this.redisTemplate.hPut(sessionKey, "expired", true);
            return stopTimestamp;
        });
        session.setSetAttributeHandler((k, v) -> this.redisTemplate.hPut(sessionKey, getAttributeKey(k), v));
        session.setRemoveAttributeHandler((k, rv) -> {
            String attributeKey = getAttributeKey(k);
            Object val = this.redisTemplate.hGet(sessionKey, attributeKey);
            if (val == null) {
                return null;
            }
            boolean success = this.redisTemplate.hRemove(sessionKey, attributeKey, val);
            if (!success) {
                logger.error("RedisSessionDAO removeAttribute error from cache, sessionId: {} , attributeName: {}", sessionId, k);
            }
            return rv;
        });
        // 强制将changed改为false
        session.setChanged(false);
        return session;
    }

    private Session getSession(Serializable sessionId) {
        logger.debug("RedisSessionDAO getSession sessionId: {}", sessionId);
        String sessionKey = getSessionKey(sessionId);
        Map<String, Object> entries = this.redisTemplate.hEntries(sessionKey, String.class, Object.class);
        if (entries == null) {
            return null;
        }
        return buildSession(entries);
    }

    @Override
    protected Serializable doCreate(Session session) {
        if (session == null) {
            logger.error("RedisSessionDAO doCreate session is null.");
            throw new UnknownSessionException("RedisSessionDAO doCreate session is null.");
        }
        Serializable sessionId = generateSessionId(session);
        this.assignSessionId(session, sessionId);
        logger.debug("RedisSessionDAO createSession sessionId: {}", sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return getSession(sessionId);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        logger.debug("RedisSessionDAO deleteSession sessionId: {}", session.getId());
        boolean success = this.redisTemplate.deleteKey(getSessionKey(session.getId()));
        if (!success) {
            logger.error("RedisSessionDAO deleteSession fail, sessionId: {}", session.getId());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Session> getActiveSessions() {
        logger.debug("RedisSessionDAO getSessions.");
        Set<String> keys = this.redisTemplate.keys(this.sessionKeyPrefix + "*");
        if (keys == null || keys.size() == 0) {
            return Collections.emptySet();
        }
        return this.redisTemplate.executePipelined(operations -> {
            for (String key : keys) {
                this.redisTemplate.hEntries(key, String.class, Object.class);
            }
        }).stream().map(o -> buildSession((Map<String, Object>) o)).collect(Collectors.toList());
    }

    @Override
    public void setCacheManager(CacheManager cacheManager) {
    }

    public void setSessionKeyPrefix(String sessionKeyPrefix) {
        this.sessionKeyPrefix = sessionKeyPrefix;
    }

    public void setAttributeKeyPrefix(String attributeKeyPrefix) {
        this.attributeKeyPrefix = attributeKeyPrefix;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public final static class RedisMutablePrincipalCollectionWrapper implements MutablePrincipalCollection {

        @JsonIgnore
        private MutablePrincipalCollection mutablePrincipalCollection;

        /**
         * 用户反序列化原始类型
         */
        private String mutablePrincipalCollectionClassName;

        private Map<String, List<Principal>> realmPrincipals;

        @JsonIgnore
        private Map redisPrincipalCollectionWrapperMap;

        public RedisMutablePrincipalCollectionWrapper(MutablePrincipalCollection mutablePrincipalCollection) {
            this.mutablePrincipalCollection = mutablePrincipalCollection;
            mutablePrincipalCollectionClassName = mutablePrincipalCollection.getClass().getName();
            disassembleMutablePrincipalCollection();
        }

        public RedisMutablePrincipalCollectionWrapper(Map redisPrincipalCollectionWrapperMap) {
            this.redisPrincipalCollectionWrapperMap = redisPrincipalCollectionWrapperMap;
            disassembleRedisMutablePrincipalCollectionWrapperMap();
        }

        private void disassembleMutablePrincipalCollection() {
            Set<String> realmNames = this.getRealmNames();
            if (realmNames == null || realmNames.size() == 0) {
                this.realmPrincipals = Collections.emptyMap();
                return;
            }
            this.realmPrincipals = new LinkedHashMap<>(realmNames.size());
            for (String realmName : realmNames) {
                Collection principals = this.fromRealm(realmName);
                if (principals == null || principals.size() == 0) {
                    continue;
                }
                for (Object principal : principals) {
                    if (principal instanceof Principal) {
                        this.realmPrincipals.computeIfAbsent(realmName, k -> new ArrayList<>(principals.size()))
                                .add((Principal) principal);
                        continue;
                    }
                    throw new IncorrectPrincipalTypeException(principal.getClass());
                }
            }
        }

        private void disassembleRedisMutablePrincipalCollectionWrapperMap() {
            if (this.redisPrincipalCollectionWrapperMap == null) {
                throw new NullPointerException("The redisPrincipalCollectionWrapperMap is null.");
            }
            String mutablePrincipalCollectionClassName = String.valueOf(this.redisPrincipalCollectionWrapperMap.get("mutablePrincipalCollectionClassName"));
            if (mutablePrincipalCollectionClassName == null || "null".equals(mutablePrincipalCollectionClassName)) {
                throw new NullPointerException("The mutablePrincipalCollectionClassName is null.");
            }
            try {
                this.mutablePrincipalCollection = (MutablePrincipalCollection) Class.forName(mutablePrincipalCollectionClassName).newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
                return;
            }
            Object realmPrincipals = this.redisPrincipalCollectionWrapperMap.get("realmPrincipals");
            String realmName;
            List principals;
            for (Object entry : ((Map) realmPrincipals).entrySet()) {
                realmName = String.valueOf(((Map.Entry) entry).getKey());
                principals = (List) ((Map.Entry) entry).getValue();
                if (principals == null || principals.size() == 0) {
                    continue;
                }
                for (Object principal : principals) {
                    if (principal instanceof Principal) {
                        this.mutablePrincipalCollection.add(principal, realmName);
                        continue;
                    }
                    if (principal instanceof Map) {
                        this.mutablePrincipalCollection.add(caseValue(principal, Principal.class), realmName);
                        continue;
                    }
                    throw new IncorrectPrincipalTypeException(principal.getClass());
                }
            }
        }

        @Override
        @JsonIgnore
        public void add(Object principal, String realmName) {
            mutablePrincipalCollection.add(principal, realmName);
        }

        @Override
        @JsonIgnore
        public void addAll(Collection principals, String realmName) {
            mutablePrincipalCollection.addAll(principals, realmName);
        }

        @Override
        @JsonIgnore
        public void addAll(PrincipalCollection principals) {
            mutablePrincipalCollection.addAll(principals);
        }

        @Override
        @JsonIgnore
        public void clear() {
            mutablePrincipalCollection.clear();
        }

        @Override
        @JsonIgnore
        public Object getPrimaryPrincipal() {
            return mutablePrincipalCollection.getPrimaryPrincipal();
        }

        @Override
        @JsonIgnore
        public <T> T oneByType(Class<T> type) {
            return mutablePrincipalCollection.oneByType(type);
        }

        @Override
        @JsonIgnore
        public <T> Collection<T> byType(Class<T> type) {
            return mutablePrincipalCollection.byType(type);
        }

        @Override
        @JsonIgnore
        public List asList() {
            return mutablePrincipalCollection.asList();
        }

        @Override
        @JsonIgnore
        public Set asSet() {
            return mutablePrincipalCollection.asSet();
        }

        @Override
        @JsonIgnore
        public Collection fromRealm(String realmName) {
            return mutablePrincipalCollection.fromRealm(realmName);
        }

        @Override
        @JsonIgnore
        public Set<String> getRealmNames() {
            return mutablePrincipalCollection.getRealmNames();
        }

        @Override
        @JsonIgnore
        public boolean isEmpty() {
            return mutablePrincipalCollection.isEmpty();
        }

        @Override
        @JsonIgnore
        public Iterator iterator() {
            return mutablePrincipalCollection.iterator();
        }

        @JsonIgnore
        public MutablePrincipalCollection getMutablePrincipalCollection() {
            return mutablePrincipalCollection;
        }

        public String getMutablePrincipalCollectionClassName() {
            return mutablePrincipalCollectionClassName;
        }

        public void setMutablePrincipalCollectionClassName(String mutablePrincipalCollectionClassName) {
            this.mutablePrincipalCollectionClassName = mutablePrincipalCollectionClassName;
        }

        public Map<String, List<Principal>> getRealmPrincipals() {
            return realmPrincipals;
        }

        public void setRealmPrincipals(Map<String, List<Principal>> realmPrincipals) {
            this.realmPrincipals = realmPrincipals;
        }

        private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

        private static String toJSONString(Object value) {
            try {
                return OBJECT_MAPPER.writeValueAsString(value);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        }

        @SuppressWarnings("unchecked")
        private static <T> T caseValue(Object value, TypeReference<T> typeReference) {
            if (value == null) {
                return null;
            }
            if (value.getClass() == typeReference.getType()) {
                return (T) value;
            }
            try {
                return OBJECT_MAPPER.readValue(toJSONString(value), typeReference);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @SuppressWarnings("unchecked")
        private static <T> T caseValue(Object value, Class<T> valueType) {
            if (value == null) {
                return null;
            }
            if (value.getClass() == valueType) {
                return (T) value;
            }
            try {
                return OBJECT_MAPPER.readValue(toJSONString(value), valueType);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}