package com.glqdlt.ex.jacksonsubtypemapping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Man.Solder.class, name = "1"),
        @JsonSubTypes.Type(value = Man.Student.class, name = "2")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Man {
    private String name;
    private Integer old;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOld() {
        return old;
    }

    public void setOld(Integer old) {
        this.old = old;
    }

    public abstract Integer getType();

    public static class Solder extends Man {

        private String weapon;

        public String getWeapon() {
            return weapon;
        }

        public void setWeapon(String weapon) {
            this.weapon = weapon;
        }

        @Override
        public Integer getType() {
            return 1;
        }
    }

    public static class Student extends Man {

        @Override
        public Integer getType() {
            return 2;
        }
    }
}
