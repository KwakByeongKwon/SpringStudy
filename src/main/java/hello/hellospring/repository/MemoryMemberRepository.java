package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    // id가 long 타입이므로 long 으로 가져옴
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        /*
        Id 값을 하나 증가시켜줌
        회원이 한명 는다는 의미임
         */
        store.put(member.getId(), member);
        /*
        아직 잘 모르지만
        map 형식에 id값과 회원정보를 넣는다는 의미인거 같음
         */
        return member;
        // 저장해논 member가 누구진지 반환해줌
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        /*
        Optional.ofNullable() 로 감싸주면
        () 안의 값으로 null이 들어와도 클라이언트가 뭘 할 수 있게 됍니다
         */
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                    .filter(member -> member.getName().equals(name))
                    .findAny();
        /*
        store의 값을 루프로 돌리면서 filter로 member.getName()이
        파라미터로 넘어온 name이랑 같은지 확인하는 의미입니다.
        같은 경우에만 필터링이 되며
        찾으면 반환을 해줍니다.
        findAny() 란 하나라도 찾는것을 말합니다.
        만약 끝까지 루프를 돌렸는데 나오지 않는다면 Optional에 null이 포함이되어 반환이 되는것입니다.
         */

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        // 새로운 리스트를 생성하여 store 의 values 즉 멤버들을 반환해줍니다.
    }
}
