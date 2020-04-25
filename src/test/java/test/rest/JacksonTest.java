package test.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;





public class JacksonTest {


//    @Data
//    @NonNull
//    @Builder
//    public static class Person {
//        private String name;
//        private int age;
//    }
//
//    public class Person2 {
//        private String name;
//        private int age;
//
//        public Person2(){}
//
//        public Person2(String name, int age){
//            this.name = name;
//            this.age = age;
//        }
//    }


    public static void main(String[] args) throws Exception {
        JacksonTest j = new JacksonTest();
        j.objToJson01();
    }

    public void objToJson01() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        Person person = Person.builder().name("aa").age(11).build();
        String personJson = objectMapper.writeValueAsString(person);
        System.out.println(personJson);

        Person jsonToPerson = objectMapper.readValue(personJson, Person.class);
        //System.out.println(jsonToPerson.getName());
        //System.out.println(jsonToPerson.getAge());

    }

    public static void jsonToMap01() throws Exception {
        try {
            System.out.println(
                    "-------------------JSON String 을 MAP 으로 변환-----------------------");
            ObjectMapper mapper = new ObjectMapper();
            String json = "{ \"name\" : \"mkgil\" , \"age\" : 25 }";
            Map<String, Object> map = new HashMap<String, Object>();
            map = mapper.readValue(json,
                    new TypeReference<Map<String, String>>() {
                    });
            System.out.println(map);
            System.out.println(map.get("name"));
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void jsonToMap02() throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = "{\"name\":\"mkyong\", \"age\":29, \"messages\" : [\"msg 1\",\"msg 2\",\"msg 3\"] }";
            Map<Object, Object> map = new HashMap<Object, Object>();
            // convert JSON string to Map
            map = mapper.readValue(json,
                    new TypeReference<Map<Object, Object>>() {
                    });
            System.out.println(map);
            ArrayList aL = (ArrayList) map.get("messages");
            System.out.println(aL.get(2));
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
