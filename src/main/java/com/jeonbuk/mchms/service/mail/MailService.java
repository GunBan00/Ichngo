package com.jeonbuk.mchms.service.mail;

import com.jeonbuk.mchms.domain.MailDomain;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "icheventboard@gmail.com";

    public void mailSend(MailDomain mailDomain) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);

            // 받는 사람
            mailHandler.setTo(mailDomain.getAddress());
            // 보내는 사람
            mailHandler.setFrom(MailService.FROM_ADDRESS);
            // 제목
            mailHandler.setSubject(mailDomain.getTitle());
            // HTML Layout
            mailHandler.setText(mailDomain.getMessage(), true);
            // 첨부 파일
            //mailHandler.setAttach("newTest.txt", "static/originTest.txt");
            // 이미지 삽입
            //mailHandler.setInline("sample-img", "static/sample1.jpg");

            mailHandler.send();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
