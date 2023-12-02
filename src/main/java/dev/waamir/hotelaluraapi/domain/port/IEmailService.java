package dev.waamir.hotelaluraapi.domain.port;

import dev.waamir.hotelaluraapi.application.model.EmailDetails;
import dev.waamir.hotelaluraapi.domain.model.Guest;

public interface IEmailService {
    void sendEmail(EmailDetails emailDetails);
    String getConfirmationMessage(String url);
    String getRegistrationMessage(Guest guest, String url);
}
