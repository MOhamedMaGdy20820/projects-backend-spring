package com.alibou.book.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor

@Tag(name = "Authentication") // جميع الـ endpoints التي تحتوي على هذا التصنيف ستظهر تحت هذا القسم في توثيق Swagger، مما يعني أنها مرتبطة بعملية المصادقة (Authentication).

public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    //HTTP 202 هو كود حالة يشير إلى أن الطلب قد تم استلامه ومعالجته بنجاح، لكن العملية لم تكتمل بعد.
    //هذا مفيد في الحالات التي قد تستغرق فيها العملية وقتًا أطول
    //عند استدعاء /register، سيتم إرجاع HTTP 202 Accepted حتى إذا لم تكتمل العملية بعد.
    //يوضح أن النظام قد استلم الطلب وسيقوم بتنفيذه لاحقًا.
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {
        service.register(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/activate-account")
    public void confirm(
            @RequestParam("token") String token
    ) throws MessagingException {
        service.activateAccount(token);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }







}
