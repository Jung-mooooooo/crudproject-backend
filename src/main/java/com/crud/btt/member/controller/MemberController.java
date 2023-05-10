package com.crud.btt.member.controller;

import com.crud.btt.member.model.dto.MemberDto;
import com.crud.btt.member.model.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@AllArgsConstructor
@CrossOrigin
@RestController
public class MemberController {

    private final MemberService memberService;

    //해당 회원 존재 여부 확인 및 정보 출력용
//    @GetMapping("/member/{userCode}") //mapping은 임의로 작성한것.
//    public MemberDto getMember(@PathVariable Long userCode) {
//        return memberService.getMember(userCode);
//    }

    //탈퇴회원 quit table로 이동
//    @PostMapping
//    public QuitEntity create(@RequestBody MemberDto memberDto) {
//        return create(memberService.create(memberDto));
//    }

    //탈퇴하기
//    @DeleteMapping
//    public void delete(@PathVariable Long userCode) {
//
//        if(getMember(userCode) != null) {
//            //if(만약 quit create가 성공이라면){
//
//            //member table의 user를 삭제한다.
//            memberService.delete(userCode);
//            //}
//        }
//
//    }

    //로그인 처리용
//    @GetMapping("/member/login")
//    public void login(){
//        System.out.println("123");
////        LOG.info("GET successfully called on /login resource");
//    }

    //회원가입
//    @PostMapping("/member/enroll")
//    public String saveMember(@RequestBody MemberDto memberDto) throws Exception{
//        memberService.saveMember(memberDto);
//        return "OK";
//    }
    @PostMapping("/user/signup")
    public String execSignup(MemberDto memberDto) {
        System.out.println("ㅇㅇㅇㅇ");
        System.out.println(memberDto.toString());
        memberService.save(memberDto);
        return "redirect:/member/login";
    }

    //아이디찾기
//    @GetMapping("/member/find/id")
//    public ResponseEntity<String> findId(String email){
//        return null;
//    }

//    //비밀번호찾기
//    @GetMapping("/member/find/id")
//    public String resetPassword(String user_name, String email, RedirectAttributes ra){
//        // 비밀번호를 찾으면 로그인 창으로 이동해 "임시 비밀번호를 이메일로 전송했습니다."라고 출력하려고 한다.
//        // 비밀번호를 못 찾으면 GET /member/reset_password로 이동해서 "비밀번호를 찾지 못했습니다"라고 출력하려고 한다.
////        Boolean result = service.resetPassword(username, email);
////        if(result==true) {
////            ra.addFlashAttribute("msg", "임시비밀번호를 이메일로 전송했습니다");
////            return "redirect:/member/login";
////        } else {
////            ra.addFlashAttribute("msg", "비밀번호를 찾지 못했습니다");
////            return "redirect:/member/reset_password";
////        }
//
//        //임시로 넣은것
//        return null;
//    }
//
//    //이메일인증
//    @PostMapping("/check/findPw/sendEmail")
//    public @ResponseBody void sendEmail(String userEmail, String userName){
////        MailDto dto = sendEmailService.createMailAndChangePassword(userEmail, userName);
////        sendEmailService.mailSend(dto);
//
//    }

    // 이메일 인증
//    @PostMapping("login/mailConfirm")
//    @ResponseBody
//    String mailConfirm(@RequestParam("email") String email) throws Exception {
//
//        String code = registerMail.sendSimpleMessage(email);
//        System.out.println("인증코드 : " + code);
//        return code;
//    }

}