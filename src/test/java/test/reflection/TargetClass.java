package test.reflection;

public class TargetClass {
    
    /*
    * reflection 되어질 객체
    */
 
    public void firstMethod() {
        System.out.println("첫번째 매소드");
    }
    
    public void secondMethod(String name) {
        System.out.println("두번째 메소드");
    }
    
    public void thirdMethod(int n) {
        for(int i=0;i<n;i++) {
            System.out.println("세번째 메소드");
        }
    }
}


