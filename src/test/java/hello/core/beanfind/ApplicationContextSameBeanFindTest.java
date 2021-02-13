package hello.core.beanfind;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(findBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상일시 오류가 발생")
    void findSameTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입 둘 이상일때 specific name을 지정하면 됨")
    void findSameTypeWithName(){
        MemoryMemberRepository findBean = ac.getBean("memberRepository1", MemoryMemberRepository.class);
        assertThat(findBean).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정타입 모두 조회하기")
    void findAllBeanByType(){
        Map<String, MemberRepository> findBeans = ac.getBeansOfType(MemberRepository.class);
        for (String key : findBeans.keySet()) {
            System.out.println("key = " + key + "  value " + findBeans.get(key));
        }
        System.out.println("findBeans = " + findBeans);
        assertThat(findBeans.size()).isEqualTo(2);
    }


    @Configuration
    static class findBeanConfig {

        //same type no.1
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        //same type no.2
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

}
