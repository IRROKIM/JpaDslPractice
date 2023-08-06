package hellojpa;

import hellojpa.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberRepository memberRepository;

    @PostMapping("/members")
    public ResponseEntity<Long> createMember (@RequestBody MemberParam param){
        log.info(param.toString());
        Member member = new Member();
        member.setId(param.getId());
        member.setName(param.getName());

        memberRepository.save(member);

        return new ResponseEntity<Long>(member.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/members/{id}")
    public  ResponseEntity<Member> memberList(@PathVariable Long id){
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()){
            return  new ResponseEntity<Member>(member.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<Member>(HttpStatus.NOT_FOUND);
        }
    }
}
