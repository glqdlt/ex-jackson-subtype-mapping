package com.glqdlt.ex.jacksonsubtypemapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class ManTest {

    @Test
    void name() throws JsonProcessingException {
        Man m = new Man.Student();
        m.setName("학생");
        m.setOld(20);

        Man.Solder m2 = new Man.Solder();
        m2.setOld(33);
        m2.setName("직장인");
        m2.setWeapon("키보드");

        Assertions.assertEquals("학생", m.getName());
        Assertions.assertEquals("직장인", m2.getName());

        List<Man> mans = new LinkedList<>();
        mans.add(m);
        mans.add(m2);


        ObjectMapper mapper = new ObjectMapper();
        String eee = mapper.writeValueAsString(mans);
        Assertions.assertEquals("[{\"name\":\"학생\",\"old\":20,\"type\":2},{\"name\":\"직장인\",\"old\":33,\"weapon\":\"키보드\",\"type\":1}]", eee);

        String solderJson = mapper.writeValueAsString(m2);

        Man sol = mapper.readValue(solderJson, Man.class);
        Assertions.assertEquals("직장인", sol.getName());
    }

    @Test
    void name2() throws IOException {
        URL ddd = ClassLoader.getSystemResource("simple.json");
        ObjectMapper mapper = new ObjectMapper();
        List ttt = mapper.readValue(ddd, List.class);

//        이걸하는 이유는 JACKSON 에서 콜렉션의 generic 이 안먹는다. ㅡㅡ; 타입래퍼런스라는 객체를 만들어서 넘기는 방법도 있는데.. 이러면 SUBTYPE 맵핑이 안먹으
        List<Man> mans = new LinkedList<>();
        for (Object t : ttt) {

            Man yy = mapper.convertValue(t, Man.class);
            mans.add(yy);

        }


        Assertions.assertEquals(mans.get(0).getName(), "학생");
        Assertions.assertEquals(mans.get(0).getType(), 2);
        Assertions.assertEquals(mans.get(1).getName(), "직장인");
        Assertions.assertEquals(mans.get(1).getType(), 1);
    }
}