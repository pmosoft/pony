package test.reflection;

import java.lang.reflect.Method;

public class ReflectionExample2 {
    public static void main(String[] args) {
        
        /*
         * 클래스의 선언된 메서드이름을 출력
         */
        
        try {
            Class targetClass = Class.forName("co.kr.javastudy.reflection.TargetClass"); 
            Method methods[] = targetClass.getDeclaredMethods(); 
          
            for(int i=0;i<methods.length;i++) {
                String findMethod = methods[i].getName(); //method의 이름 추출
                System.out.println(findMethod);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("클래스를 찾을 수 없습니다.");
        }
    }
}
