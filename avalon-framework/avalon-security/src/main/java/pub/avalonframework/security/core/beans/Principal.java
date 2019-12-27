package pub.avalonframework.security.core.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.Set;

/**
 * @author baichao
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public interface Principal extends Serializable {

    /**
     * get id
     *
     * @return unique identifier
     */
    String getId();

    /**
     * set id
     *
     * @param id unique identifier
     */
    void setId(String id);

    /**
     * get salt
     *
     * @return salt for encryption
     */
    @JsonIgnore
    Object getSalt();

    /**
     * get name
     *
     * @return name for principal
     */
    String getName();

    /**
     * set name
     *
     * @param name for principal
     */
    void setName(String name);

    /**
     * Get role identifier set.
     *
     * @return The role identifier set.
     */
    Set<String> getRoleIdentifierSet();

    /**
     * Set role identifier set.
     *
     * @param roleIdentifierSet The role identifier set.
     */
    void setRoleIdentifierSet(Set<String> roleIdentifierSet);
}