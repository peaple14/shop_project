package com.example.shop.service.loginservice;


import com.example.shop.dto.loginDTO.MailDto;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
@AllArgsConstructor
public class EmailService {
//여기서 환경변수 설정하기

    private JavaMailSender emailSender;

    public void sendSimpleMessage(MailDto mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mailtest@gmail.com");
        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getContent());
        emailSender.send(message);
    }
}