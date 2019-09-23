package test.reflection;

import java.lang.reflect.Method;

public class ReflectionExample {
    public static void main(String[] args) {
        
        /*
         * 클래스의 선언된 메서드를 찾는 방법
         */
        
        try {
            Class targetClass = Class.forName("co.kr.javastudy.reflection.TargetClass");
            //java.lang.Class의 forName()메소드를 통해 클래스를 찾음
            
            Method methods[] = targetClass.getDeclaredMethods();
            //getDeclaredMethods()를 통해 해당 클래스의 메소드들을 찾음
            
            for(int i=0;i<methods.length;i++) {
                System.out.println(methods[i].toString());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("클래스를 찾을 수 없습니다.");
        }
    }
}
