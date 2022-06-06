package hello.core.singleton;

import hello.core.config.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른걸 확인

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonService() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singleTonService2 = SingletonService.getInstance();


        // isEqualTo 와 isSameAs의 차이 :
        // same == 객체 주소 비교
        // equal  == 값을 비교, overriding
        Assertions.assertThat(singletonService1).isSameAs(singleTonService2);
    }
}
