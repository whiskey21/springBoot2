package hello.core.sigletonpattern;

import hello.core.AppConfig;
import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("memberRepository의 호출 주소값 확인")
    void configTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();


        System.out.println("memberRepository1 = " + memberRepository1 + " memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberRepository1).isEqualTo(memberRepository);
        assertThat(memberRepository2).isEqualTo(memberRepository);

    }


    @Test
    @DisplayName("AppConfig CGLIB 상속받아서 Bean에 등록했는지 확인")
    void appConfigTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //부모class 출력하면 자식까지 줄줄이나옴 AppConfig출력해도 그걸 상속한 CGLIB도 출력됨
        AppConfig appConfig = ac.getBean(AppConfig.class);
        System.out.println("appConfig = " + appConfig);
    }

    @Test
    @DisplayName("AutoAppConfig 설정정보 안붙였을때 싱글톤방식확인")
    void AutoAppConfigCofiguration(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberServiceImpl memberServiceImpl = ac.getBean("memberServiceImpl", MemberServiceImpl.class);
        OrderServiceImpl orderServiceImpl = ac.getBean("orderServiceImpl", OrderServiceImpl.class);

        MemberRepository memberRepository1 = memberServiceImpl.getMemberRepository();
        MemberRepository memberRepository2 = orderServiceImpl.getMemberRepository();

        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);

        Assertions.assertThat(memberRepository1).isEqualTo(memberRepository2);

    }
}
