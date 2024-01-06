package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // 회원이 저장소에 저장이됌
    Optional<Member> findById(Long id);
    // findById로 값을 가져올때 null 값이 들어올 수 도 있는데
    // 이처럼 null이 들어올 수 있는 경우가 있으면 Optional 을 사용합니다.
    Optional<Member> findByName(String name);

    // id 와 name 을 가져옴

    List<Member> findAll();
    // 지금까지 저장된 모든 리스트를 반환해줍니다.
}
