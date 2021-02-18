package hello.core.sigletonpattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonServiceTest {

    @Test
    @DisplayName("싱글톤패턴 객체생성 테스트")
    void singletonServiceTest(){
        //private access
        //SingletonService s3 = new SingletonService();

        SingletonService s1 = SingletonService.getInstance();
        SingletonService s2 = SingletonService.getInstance();

        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
    }
}
