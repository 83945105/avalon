package pub.avalonframework.shiro.session.mgt;

import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import pub.avalonframework.shiro.session.RemoveAttributeHandler;
import pub.avalonframework.shiro.session.SetAttributeHandler;
import pub.avalonframework.shiro.session.StopHandler;
import pub.avalonframework.shiro.session.TouchHandler;

import java.io.Serializable;
import java.util.*;

/**
 * @author baichao
 */
public final class RedisSession extends SimpleSession {

    private Serializable id;
    private Date startTimestamp;
    private Date lastAccessTime;
    private long timeout;
    private boolean expired;
    private String host;
    private Map<Object, Object> attributes;

    /**
     * 除lastAccessTime以外其它字段改变时变为true, 用于解决shiro频繁update session问题, 需要RedisSessionDAO相关代码配合完成
     */
    private transient boolean changed = false;

    @Override
    public Serializable getId() {
        return this.id;
    }

    @Override
    public void setId(Serializable id) {
        this.id = id;
        this.setChanged(true);
    }

    @Override
    public Date getStartTimestamp() {
        return startTimestamp;
    }

    @Override
    public void setStartTimestamp(Date startTimestamp) {
        this.startTimestamp = startTimestamp;
        this.setChanged(true);
    }

    @Override
    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    @Override
    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
        this.setChanged(true);
    }

    @Override
    public long getTimeout() throws InvalidSessionException {
        return timeout;
    }

    @Override
    public void setTimeout(long maxIdleTimeInMillis) throws InvalidSessionException {
        this.timeout = maxIdleTimeInMillis;
        this.setChanged(true);
    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    @Override
    public void setExpired(boolean expired) {
        this.expired = expired;
        this.setChanged(true);
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public void setHost(String host) {
        this.host = host;
        this.setChanged(true);
    }

    private TouchHandler touchHandler;

    public void setTouchHandler(TouchHandler touchHandler) {
        this.touchHandler = touchHandler;
    }

    /**
     * 该方法用于更新session过期时间, 该操作不调用this.setChanged(true),
     *
     * @throws InvalidSessionException
     */
    @Override
    public void touch() throws InvalidSessionException {
        Date lastAccessTime;
        if (this.touchHandler == null) {
            lastAccessTime = new Date();
        } else {
            lastAccessTime = this.touchHandler.touch();
        }
        this.setLastAccessTime(lastAccessTime);
    }

    private StopHandler stopHandler;

    public void setStopHandler(StopHandler stopHandler) {
        this.stopHandler = stopHandler;
    }

    @Override
    public void stop() throws InvalidSessionException {
        Date stopTimestamp;
        if (this.stopHandler == null) {
            stopTimestamp = new Date();
        } else {
            stopTimestamp = this.stopHandler.stop();
        }
        this.setStopTimestamp(stopTimestamp);
        this.setExpired(true);
        this.setChanged(true);
    }

    @Override
    public Map<Object, Object> getAttributes() {
        return attributes;
    }

    @Override
    public void setAttributes(Map<Object, Object> attributes) {
        this.attributes = attributes;
        this.setChanged(true);
    }

    @Override
    public Collection<Object> getAttributeKeys() throws InvalidSessionException {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return Collections.emptySet();
        }
        return attributes.keySet();
    }

    @Override
    public Object getAttribute(Object key) throws InvalidSessionException {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            return null;
        }
        return attributes.get(key);
    }

    private Map<Object, Object> getAttributesLazy() {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            attributes = new HashMap<>(8);
            setAttributes(attributes);
        }
        return attributes;
    }

    private SetAttributeHandler setAttributeHandler;

    public void setSetAttributeHandler(SetAttributeHandler setAttributeHandler) {
        this.setAttributeHandler = setAttributeHandler;
    }

    @Override
    public void setAttribute(Object key, Object value) throws InvalidSessionException {
        if (value == null) {
            removeAttribute(key);
        } else {
            getAttributesLazy().put(key, value);
            if (this.setAttributeHandler != null) {
                this.setAttributeHandler.accept(key, value);
            }
            this.setChanged(true);
        }
    }

    private RemoveAttributeHandler removeAttributeHandler;

    public void setRemoveAttributeHandler(RemoveAttributeHandler removeAttributeHandler) {
        this.removeAttributeHandler = removeAttributeHandler;
    }

    @Override
    public Object removeAttribute(Object key) throws InvalidSessionException {
        Map<Object, Object> attributes = getAttributes();
        if (attributes == null) {
            if (this.removeAttributeHandler == null) {
                this.setChanged(true);
                return null;
            }
            this.removeAttributeHandler.apply(key, null);
            this.setChanged(true);
            return null;
        }
        Object value = attributes.remove(key);
        if (this.removeAttributeHandler == null) {
            this.setChanged(true);
            return value;
        }
        value = this.removeAttributeHandler.apply(key, value);
        this.setChanged(true);
        return value;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}