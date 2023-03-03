package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPoicy implements DiscountPolicy{

    private int discountFixAmount = 1000; // 1000원 할인
    @Override
    public int discount(Member member, int price) {
        // VIP 1000원 할인. 그 외 할인 없음
        return member.getGrade() == Grade.VIP ? discountFixAmount : 0 ;
    }
}
