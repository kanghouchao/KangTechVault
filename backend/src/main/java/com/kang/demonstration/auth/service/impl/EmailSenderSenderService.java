package com.kang.demonstration.auth.service.impl;

import com.kang.demonstration.auth.service.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

/**
 * @author kanghouchao
 */
@Service
@RequiredArgsConstructor
public class EmailSenderSenderService implements EmailSenderService {

    @Value("${register.email.verify-baseurl}")
    private String baseURL;

    private final JavaMailSender javaMailSender;

    private final TemplateEngine templateEngine;

    private final MessageSource messageSource;

    @Override
    public void sendEmailForRegister(final String email, final String token) throws MessagingException {
        Locale locale = LocaleContextHolder.getLocale();
        final String subject = this.getEmailSubject(locale);

        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(email);
        messageHelper.setSubject(subject);
        messageHelper.setText(this.getEmailText(subject, token, locale), true);
        this.javaMailSender.send(mimeMessage);
    }

    private String getEmailSubject(final Locale locale) {
        return messageSource.getMessage("email.subject", null, locale);
    }

    private String getEmailText(final String subject, final String token, final Locale locale) {
        final EmailMessage message = new EmailMessage(
            subject,
            messageSource.getMessage("email.greeting", null, locale),
            messageSource.getMessage("email.body", null, locale),
            messageSource.getMessage("email.buttonText", null, locale),
            messageSource.getMessage("email.notice", null, locale),
            messageSource.getMessage("email.footer", null, locale)
        );
        final Context context = new Context();
        context.setVariable("lang", locale.getLanguage());
        context.setVariable("message", message);
        context.setVariable("verificationLink", this.baseURL + token);
        return templateEngine.process("email_template", context);
    }

    record EmailMessage(String subject,
                        String greeting,
                        String body,
                        String buttonText,
                        String notice,
                        String footer) {
    }
}
