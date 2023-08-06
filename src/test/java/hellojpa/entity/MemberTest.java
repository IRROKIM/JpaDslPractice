package hellojpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void givenCustomRepository_whenInvokeCustomFindMethod_thenEntityIsFound() {
        Member member = new Member();
        member.setEmail("foo@gmail.com");
        member.setName("userName");

        Member persistedUser = MemberRepository.save(member);

        assertEquals(persistedUser, MemberRepository.findMethod(member.getId()));
    }
}