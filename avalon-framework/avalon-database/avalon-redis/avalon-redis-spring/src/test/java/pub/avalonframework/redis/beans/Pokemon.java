package pub.avalonframework.redis.beans;

import com.fasterxml.jackson.annotation.JsonTypeInfo;


/**
 * @author baichao
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public interface Pokemon {

    String getName();

    Attribute[] getAttributes();
}