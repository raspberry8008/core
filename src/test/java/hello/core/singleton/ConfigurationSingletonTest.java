package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        // 같은 인스턴스가 공유되어 사용된다.
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        // 순수한 클래스라면 class hello.core.AppConfig 라고 출력되어야 한다.
        // 하지만 CGLIB 바이트코드 조작 라이브러리를 사용해서 AppConfig.class 상속받은 임의의 다른 클래스를 만들고,
        // 다른 클래스를 스프링 빈으로 등록한 것이다.
        // 스프링 컨테이너에 등록되어 있으면 스프링 컨테이너에서 찾아서 반환하고
        // 없다면 기존 로직을 호출해서 스프링 빈을 생성하고 스프링 컨테이너에 등록한다(동적)

        // @Configuration을 사용하지 않아도 스프링 빈으로 등록된다. (AppConfig.class 가 출력된다.)
        // 호출 될 때마다 여러번 호출 된다. (싱글톤을 보장하지 않는다. 스프링 컨테이너에서 관리하지 않는다.)
        System.out.println("bean = " + bean.getClass());
    }
}
