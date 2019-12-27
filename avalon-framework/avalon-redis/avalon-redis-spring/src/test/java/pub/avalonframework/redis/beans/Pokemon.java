package pub.avalonframework.redis.beans;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by 白超 on 2019/11/27.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public interface Pokemon {

    String getName();

    Attribute[] getAttributes();
}