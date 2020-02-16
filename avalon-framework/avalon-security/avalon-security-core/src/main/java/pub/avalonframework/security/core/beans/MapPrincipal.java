package pub.avalonframework.security.core.beans;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @author baichao
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public final class MapPrincipal extends LinkedHashMap<String, Object> implements Principal {

    private String id;

    private String name;

    private Set<String> roleIdentifierSet;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Object getSalt() {
        return this.get("salt");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<String> getRoleIdentifierSet() {
        return roleIdentifierSet;
    }

    @Override
    public void setRoleIdentifierSet(Set<String> roleIdentifierSet) {
        this.roleIdentifierSet = roleIdentifierSet;
    }
}