package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 추상화(인터페이스)에만 의존한다 : 생성자 주입
    private final MemberRepository memberRepository;

    // 생성자를 통해서 구현체가 어떤게 들어갈 지 선택한다.
    public MemberServiceImpl(MemberRepository memoryRepository) {
        this.memberRepository = memoryRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
