package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(findExtendBeanConfig.class);

    @Test
    @DisplayName("부모타입으로 조회 시, 자식이 둘 이상이면 오류 발생")
    void findByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모타입으로 조회 시, 자식 둘 이상일시 이름으로 조회")
    void findByParentTypeBeanName(){
        DiscountPolicy findBean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(findBean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("하위타입으로 조회")
    void findBeanBySubType(){
        RateDiscountPolicy findBean = ac.getBean(RateDiscountPolicy.class);
        assertThat(findBean).isInstanceOf(RateDiscountPolicy.class);
    }


    @Test
    @DisplayName("부모타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> findBeans = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(findBeans.size()).isEqualTo(2);
        for (String key : findBeans.keySet()) {
            System.out.println("key = " + key + "  value " + findBeans.get(key));
        }
        System.out.println("findBeans = " + findBeans);
    }

    @Configuration
    static class findExtendBeanConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }
}
