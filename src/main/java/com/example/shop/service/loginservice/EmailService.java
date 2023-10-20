//package com.example.shop.service.loginservice;
//
//
//import com.example.shop.dto.loginDTO.MailDto;
//import lombok.AllArgsConstructor;
//
//import org.springframework.stereotype.Service;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//
//@Service
//@AllArgsConstructor
//public class EmailService {
////https://velog.io/@tjddus0302/Spring-Boot-%EB%A9%94%EC%9D%BC-%EB%B0%9C%EC%86%A1-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0-Gmail
////여기서 환경변수 설정하기
//
//    private JavaMailSender emailSender;
//
//    public void sendSimpleMessage(MailDto mailDto) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("mailtest@gmail.com");
//        message.setTo(mailDto.getAddress());
//        message.setSubject(mailDto.getTitle());
//        message.setText(mailDto.getContent());
//        emailSender.send(message);
//    }
//}