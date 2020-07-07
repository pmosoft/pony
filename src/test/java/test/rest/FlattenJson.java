package test.rest;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.opendevl.JFlat;

public class FlattenJson {

    public static void main(String[] args) throws Exception {

        System.out.println("start");
        String str = new String(Files.readAllBytes(Paths.get("d:/lge/data/kruser01.json")));

        JFlat flatMe = new JFlat(str);

        //get the 2D representation of JSON document
        flatMe.json2Sheet().headerSeparator("_").getJsonAsSheet();

        //write the 2D representation in csv format
        flatMe.write2csv("d:/lge/data/kruser01.csv");
        System.out.println("end");

    }
}
