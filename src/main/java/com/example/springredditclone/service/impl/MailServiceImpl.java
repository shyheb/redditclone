package com.example.springredditclone.service.impl;

import com.example.springredditclone.exceptions.SpringRedditException;
import com.example.springredditclone.model.NotificationEmail;
import com.example.springredditclone.service.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendEmail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("chiheb_mahjoub1993@yahoo.fr");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody(), true);
        };


        try {
            javaMailSender.send(messagePreparator);
            log.info("Activation Email Send");
        } catch (MailException mailException) {
            throw new SpringRedditException("Exception occured when when sending validation mail to " + notificationEmail.getRecipient());
        }
    }
}
