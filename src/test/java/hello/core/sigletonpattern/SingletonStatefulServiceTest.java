package hello.core.sigletonpattern;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class SingletonStatefulServiceTest {

    @Test
    void statefulTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        SingletonStatefulService bean1 = ac.getBean("singletonStatefulService", SingletonStatefulService.class);
        SingletonStatefulService bean2 = ac.getBean("singletonStatefulService", SingletonStatefulService.class);

        int userAPrice = bean1.order("userA", 10000);
        int userBPrice = bean2.order("userB", 20000);

        System.out.println("bean1 = " + userAPrice);

    }
    static class TestConfig {

        @Bean
        public SingletonStatefulService singletonStatefulService(){
            return new SingletonStatefulService();
        }
    }

}