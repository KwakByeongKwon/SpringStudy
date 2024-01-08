package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions; 과거에 사용했던 Assertions

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /*
    각 테스트 메서드들이 실행이 끝날때마다 정해놓은 메서드가 실행이 됩니다.
     */
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }
    /*
    @Test 를 사용함으로써
    main에 있는 코드를 실행할 수 있습니다.
     */
    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        /*
        findById의 반환 타입이 Optional이므로 여기서 값을 꺼낼때 get으로 값을 꺼낼 수 있습니다.
        get으로 꺼내는 방식은 비 효율적인 방식이지만, 테스트를할때는 사용해도 무관합니다.
         */

//        Assertions.assertEquals(member, result); 과거에 사용했던 Assertions에서 사용하는 메서드

        /*
        System.out.println("result = " + (result == member));
        이렇게 값을 출력하여 확일 할 수도 있지만

        Assertions.assertEquals()를 이용해서 ()안의 값들이 같은지 확인할 수 있습니다.
        이렇게 값을 확인해서 같으면 오류 없이 지나가고 다르다면 오류가 나옵니다.
         */

        Assertions.assertThat(member).isEqualTo(result);
        /*
        이 전에 사용했을때는 뭐가 앞에올지 헷갈린다면
        이렇게 사용하면 처음 assertThat() 괄호안에 있는 값이 isEqualTo() 이 괄호 안에 있는값과 똑같애 라고 해석되어
        헷갈리지 않고 편하게 사용 가능합니다.
         */

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
//        Member result2 = repository.findByName(member1.getName()).get();

        Assertions.assertThat(result).isEqualTo(member1);
//        Assertions.assertThat(member1).isEqualTo(result2);


    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }

}
