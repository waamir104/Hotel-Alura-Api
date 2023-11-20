package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.application.model.EmailDetails;

public interface IEmailService {
    void sendEmail(EmailDetails emailDetails);
    String getConfirmationMessage(String url);
}
