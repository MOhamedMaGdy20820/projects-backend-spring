package com.alibou.book.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail(String to,                           // عنوان البريد الإلكتروني الذي سيتم إرسال البريد إليه.
                          String username,                     // اسم المستخدم المستهدف بالإيميل.
                          EmailTemplateName emailTemplate,     // اسم المستخدم المستهدف بالإيميل.
                          String confirmationUrl,              // رابط التأكيد المستخدم في البريد.
                          String activationCode,               // كود التفعيل المستخدم في البريد.
                          String subject                       // عنوان البريد الإلكتروني (Subject).
                        ) throws MessagingException {

        String templateName;

        if (emailTemplate == null) {
            templateName = "confirm-email";
        } else {
            templateName = emailTemplate.name();
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(
                mimeMessage,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );

        // إعداد البيانات التي سيتم إرسالها في البريد
        // تحديد تفاصيل الايميل نفسه
        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activation_code", activationCode);

        // نشاء سياق القالب (Thymeleaf context) وتحديد المتغيرات التي سيتم استخدامها داخل القالب HTML.
        Context context = new Context();
        context.setVariables(properties);

        // تحديد بيانات البريد الإلكتروني
        // تحديد بيانات المرسل و المرسل اليه و عنوان الميل
        helper.setFrom("medomagdy7070@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

        // انشاء قالب الايميل
        String template = templateEngine.process(templateName, context);

        // وضع قالب الأيميل دخل الايميل لارساله
        helper.setText(template, true);

        // ارسال الايميل
        mailSender.send(mimeMessage);
    }
}
