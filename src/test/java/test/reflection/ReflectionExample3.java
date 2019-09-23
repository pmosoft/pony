package test.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
 
public class ReflectionExample3 {
    public static void main(String[] args) {
        
        /*
         * 이름을 지정해 특정 메소드를 실행시키는 방법
         */
        
        try {
            TargetClass target = new TargetClass(); //해당 클래스의 인스턴스 생성
            Class targetClass = Class.forName("co.kr.javastudy.reflection.TargetClass");
            Method methods[] = targetClass.getDeclaredMethods();
            
            for(int i=0;i<methods.length;i++) {
                String findMethod = methods[i].getName();
                if(findMethod.equals("secondMethod")) {
                    //secondMethod를 찾아서 실행
                    try {
                        methods[i].invoke(target,"리플렉션");
                        //invoke()를 통해 메소드 호출 가능
                        //첫번째 파라미터는 해당 메소드를 가진 인스턴스, 두번쨰 파라미터는 해당 메소드의 파라미터
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("클래스를 찾을 수 없습니다.");
        }
    }
}

