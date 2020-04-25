package test.rest;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
@Builder
public class Person {
    private String name;
    private int age;
}
