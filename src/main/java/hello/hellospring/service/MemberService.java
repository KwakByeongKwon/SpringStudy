package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


public class MemberService {


    /*
    기존
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    에서 Test를 계속 돌리면 새로운 값으로 test를 돌리는것이기 때문에 아무 의미가 없습니다.

    그러므로 생성자를 하나 만들어줍니다.
     */

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    memberRepository 를 내가 직접 new에서 생성하는게 아닌 외부에서 값을 넘겨받도록 해줍니다.
    내가 직접 생성하지 않고 외부에서 값을 받아 사용하는것을
    Dependency Injection 라고 합니다.
     */

    /**
     * 회원 가입
     */
    public Long join(Member member){

//        memberRepository.findByName(member.getName())
//                .ifPresent(m -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });
        /*
        result에서 값을 result.get() 으로 바로 꺼낼 수도 있지만, java에서 별로 권장하지 않고
        만약에 값이 있으면 이라는 .ifPresent 로 throw 로 에러처리를 해줍니다.
        null 이 아닌 값이 있으면 동작합니다.

        위의 코드를 하나로 합쳐 굳이 값을 할당한 이후에 실행하지 않고 바로
        밑에 코드처럼 이렇게 하나로 합칠 수 도 있습니다.

        이후에 Ctrl + Alt + M 을 이용해서 메서드로 바꾸어주어 언제든 사용할 수 있게 해주었습니다.
         */

        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
               .ifPresent(m -> {
                   throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    // memberRepository에 member를 save하고 그 member의 ID값을 반환하게 했습니다.

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}

/**
 * Ctrl + Shift + T 를 해당 클래스에서 한다면 자동으로 테스트 파일을 생성해줍니다.
 */