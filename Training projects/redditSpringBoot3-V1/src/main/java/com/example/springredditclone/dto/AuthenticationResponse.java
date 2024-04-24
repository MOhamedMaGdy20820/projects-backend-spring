package com.example.springredditclone.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {
    private String authenticationToken;   // (minutes or hours) توفير الوصول المؤقت إلى الموارد المحمية على الخادم.
    private String refreshToken;   //الغرض الأساسي منه هو الحصول على new access tokens دون مطالبة المستخدم بإعادةالمصادقة
                                  // (days, weeks, or months) باستخدام بيانات الاعتماد الخاصة به في كل مرة تنتهي فيها صلاحية رمز الوصول
    private Instant expiresAt;
    private String username;
}

/*
تُستخدم authentication tokens :للوصول إلى الموارد المحمية
، بينما تُستخدم refresh tokens :للحصول على access token جديدة دون مطالبة المستخدم بتسجيل الدخول مرة أخرى
*/