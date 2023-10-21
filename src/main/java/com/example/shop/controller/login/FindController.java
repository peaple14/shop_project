package com.example.shop.controller.login;


import com.example.shop.dto.loginDTO.FindIdDTO;
import com.example.shop.dto.loginDTO.FindPasswordDTO;
import com.example.shop.dto.loginDTO.MailDto;
import com.example.shop.repository.MemberRepository;
import com.example.shop.service.loginservice.FindService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class FindController {
    //아이디,비밀번호 찾기용


    @Autowired
    private MemberRepository memberRepository;

    private FindService findService;

//    @Autowired
//    private final EmailService emailService;


    @GetMapping("/findId")
    public String findI(@ModelAttribute("FindIdForm") FindIdDTO form) {
        return "/Member/Findaccount/findId";
    }

    @PostMapping("/findId")
    public String findId(@Validated @ModelAttribute("FindIdForm") FindIdDTO form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/Member/Findaccount/findId";
        }
        System.out.println("여기오나");

        String email = form.getFindid_email();
        String foundMember = findService.findIdByuserEmail(email);
        System.out.println("아이디찾기:" + foundMember);

        if (foundMember != null) {
            model.addAttribute("foundId", foundMember);
            return "/Member/Findaccount/findIdResult";
        } else {
            return "redirect:/findId";
        }
    }


    @GetMapping("/find/findIdResult")
    public String findIdResult(Model model, @RequestParam("foundId") String foundId) {
        model.addAttribute("foundId", foundId);
        return "/Member/Findaccount/findIdResult";

    }

    @GetMapping("/findpassword")
    public String findP(@ModelAttribute("FindPasswordForm") FindPasswordDTO form) {
        return "/Member/Findaccount/findpassword";
    }

    @PostMapping("/findpassword")
    public String FindP(@Validated @ModelAttribute("FindIdForm") FindPasswordDTO form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/Member/Findaccount/findpassword";
        }

        if (form == null || (form.getFindpassword_Id() == null || form.getFindpassword_Id().trim().isEmpty()) || (form.getFindpassword_email() == null || form.getFindpassword_email().trim().isEmpty())) {
            return "redirect:/findpassword?error";
        }


        String id = form.getFindpassword_Id();
        String email = form.getFindpassword_email();
        String foundpassword = memberRepository.findPasswordByIdAndEmail(id,email);


        MailDto mailDto = new MailDto();
        mailDto.setTitle("조모씨의 비번찾기");
        mailDto.setAddress(email);
        mailDto.setContent("찾으신 비밀번호는 : "+ foundpassword + "입니다.");

        System.out.println(mailDto);


        if (foundpassword != null) {
            emailService.sendSimpleMessage(mailDto);
            return "/Member/Findaccount/findpasswordresult";
        } else {
            return "redirect:/findpassword";
        }
    }


}
