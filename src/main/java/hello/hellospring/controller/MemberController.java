package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * Dependency Injection의 3가지 방법
     * 1. 생성자를 통해 들어온다.
     *
     *    @Autowired
     *     public MemberController(MemberService memberService) {
     *         this.memberService = memberService;
     *     }
     *
     *
     * 2. 필드에 주입한다
     * @Autowired private  MemberService memberService;
     *
     *
     * 3. setter주입
     *    private MemberService memberService;
     *
     *     @Autowired
     *     public void setMemberService(MemberService memberService) {
     *         this.memberService = memberService;
     *     }
     *
     *
     *
     *  요즈음은 생성자를 통해 들어오는게 많이 사용되고 있다.
     */

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
