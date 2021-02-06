package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP일시 10%할인해주는지 확인")
    void discountProp() {
        Member member = new Member(1L, "spring1", Grade.VIP);
        int discountPrice = discountPolicy.discount(member, 10000);
        Assertions.assertThat(discountPrice).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아닐 시 확인")
    void discountNo(){
        Member member = new Member(1L, "spring2", Grade.Basic);
        int discount = discountPolicy.discount(member, 10000);
        Assertions.assertThat(discount).isEqualTo(1000);
    }
}