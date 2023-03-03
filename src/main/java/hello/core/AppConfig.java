package hello.core;

import hello.core.discount.FixDiscountPoicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/*
    애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 구현 객체를 생성하고, 연결하는 책임을 가지는 class
    어떤 구현 객체를 주입할지는 오직 외부 'AppConfig'에서 결정된다.(의존관계에 대한 고민은 외부에 맡기고 실행에만 집중하면 된다.)
    -> 관심사의 분리 : 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확이 분리되었다.(의존관계 주입 DI: Dependency Injection)
 */
public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                new MemoryMemberRepository(),
                new FixDiscountPoicy());
    }
}
