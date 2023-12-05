package dev.waamir.hotelaluraapi.application.service.Email;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import dev.waamir.hotelaluraapi.application.model.EmailDetails;
import dev.waamir.hotelaluraapi.domain.model.Guest;
import dev.waamir.hotelaluraapi.domain.model.User;
import dev.waamir.hotelaluraapi.domain.port.IEmailService;
import dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception.ApiException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${application.front-end.host}")
    private String frontHost;

    @Override
    public void sendEmail(EmailDetails emailDetails) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            mimeMessage.setFrom(new InternetAddress(sender));
            mimeMessage.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(emailDetails.getRecipient()));
            mimeMessage.setContent(emailDetails.getMsgBody(), "text/html; charset=utf-8");
            mimeMessage.setSubject(emailDetails.getSubject());

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new ApiException("An error ocurred sending the email");
        }
    }

    @Override
    public String getConfirmationMessage(String url) {
        try {
            Resource resource = new ClassPathResource("templates/Email/ConfirmationEmailTemplate.html");
            byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String body = new String(bytes, StandardCharsets.UTF_8);
            body = body.replace("${confirmation-url}", url);
            return body;
        } catch (IOException e) {
            throw new ApiException("An error ocurred getting the confirmation message");
        }
    }

    @Override
    public String getRegistrationMessage(Guest guest, String url) {
        try {
            Resource resource = new ClassPathResource("templates/Email/RegistrationEmailTemplate.html");
            byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String body = new String(bytes, StandardCharsets.UTF_8);
            body = body.replace("${guest-name}", String.format("%s %s", guest.getName(), guest.getLastName()));
            body = body.replace("${registration-url}", url);
            return body;
        } catch (IOException e) {
            throw new ApiException("An error ocurred getting the confirmation message");
        }
    }

    @Override
    public String getResetPwdMessage(String url, User user) {
        try {
            Resource resource = new ClassPathResource("templates/Email/ResetPwdEmailTemplate.html");
            byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String body = new String(bytes, StandardCharsets.UTF_8);
            body = body.replace("${username}", user.getUsername());
            body = body.replace("${resetPwd-url}", url);
            return body;
        } catch (IOException e) {
            throw new ApiException("An error ocurred getting the confirmation message");
        }
    }

    @Override
    public String getResetPwdConfirmationMessage() {
        try {
            Resource resource = new ClassPathResource("templates/Email/ConfirmationResetPwdEmailTemplate.html");
            byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String body = new String(bytes, StandardCharsets.UTF_8);
            body = body.replace("${home-url}", frontHost);
            return body;
        } catch (IOException e) {
            throw new ApiException("An error ocurred getting the confirmation message");
        }
    }
    
}
