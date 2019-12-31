package pub.avalonframework.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pub.avalonframework.common.beans.BeanPropertyInfo;

import java.util.*;

/**
 * Bean utils test.
 *
 * @author baichao
 */
public class BeanUtilsTest {

    private static class Pokemon {

        private String name;

        private Boolean dualAttribute;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Boolean getDualAttribute() {
            return dualAttribute;
        }

        public void setDualAttribute(Boolean dualAttribute) {
            this.dualAttribute = dualAttribute;
        }
    }

    private final static class Pikachu extends Pokemon {

        private String character;

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }
    }

    @Test
    void TestGetPropertyNames() {
        List<String> propertyNames = BeanUtils.getPropertyNames(Pikachu.class);
        Assertions.assertEquals(1, propertyNames.size());
        Assertions.assertArrayEquals(new String[]{"character"}, propertyNames.toArray());
        propertyNames = BeanUtils.getPropertyNames(Pikachu.class, true);
        Assertions.assertEquals(3, propertyNames.size());
        Assertions.assertArrayEquals(new String[]{"character", "name", "dualAttribute"}, propertyNames.toArray());
    }

    @Test
    void TestGetProperties() {
        Pikachu pikachu = new Pikachu();
        pikachu.setDualAttribute(false);
        pikachu.setName("皮卡丘");
        Map<String, Object> properties = BeanUtils.getProperties(pikachu);
        Assertions.assertEquals(1, properties.size());
        Assertions.assertTrue(properties.keySet().contains("character"));
        Assertions.assertTrue(properties.values().contains(null));
        properties = BeanUtils.getProperties(pikachu, true);
        Assertions.assertEquals(3, properties.size());
        Assertions.assertTrue(properties.keySet().containsAll(Arrays.asList("character", "name", "dualAttribute")));
        Assertions.assertTrue(properties.values().containsAll(Arrays.asList(null, "皮卡丘", false)));
        properties = BeanUtils.getProperties(pikachu, Objects::nonNull);
        Assertions.assertEquals(0, properties.size());
        properties = BeanUtils.getProperties(pikachu, true, Objects::nonNull);
        Assertions.assertEquals(2, properties.size());
        Assertions.assertTrue(properties.keySet().containsAll(Arrays.asList("name", "dualAttribute")));
        Assertions.assertTrue(properties.values().containsAll(Arrays.asList("皮卡丘", false)));
    }

    @Test
    void TestGetBeanPropertyInfo() {
        Pikachu pikachu = new Pikachu();
        pikachu.setDualAttribute(false);
        pikachu.setName("皮卡丘");
        BeanPropertyInfo beanPropertyInfo = BeanUtils.getBeanPropertyInfo(Pikachu.class, "name");
        Assertions.assertEquals("name", beanPropertyInfo.getName());
        Object value = beanPropertyInfo.getValue(pikachu);
        Assertions.assertEquals("皮卡丘", value);
        beanPropertyInfo.setValue(pikachu, "雷丘");
        value = beanPropertyInfo.getValue(pikachu);
        Assertions.assertEquals("雷丘", value);
    }
}