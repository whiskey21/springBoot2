package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        //MemberService memberService;
        //OrderService orderService;
        //AppConfig appConfig = new AppConfig();
        //memberService = appConfig.memberService();
        //orderService = appConfig.orderService();

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        Member member = new Member(1L, "spring100", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(1L, "itemSpring", 20000);

        System.out.println("order = " + order);


    }
}
