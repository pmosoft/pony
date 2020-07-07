package test.variable.collection;

import java.util.HashMap;

public class EnumTest{

    public enum Type {
        WALKING, RUNNING, TRACKING, HIKING
    }

    public static void main(String args[])
    {
        MapEx02();
    }

    static void MapEx02(){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("people", "AAA");
        map.put("people", "AAB");
        map.put("baseball", "BBB");
        map.put("AAA", "11AAA");
        map.put("AAB", "11AAB");
        map.put("AAA", "11AAC");

        //System.out.println(map.get("people"));
        System.out.println(map.get("AAA"));
        //System.out.println(map.containsKey("people"));
        //System.out.println(map.remove("people"));
        //System.out.println(map.size());
    }
}