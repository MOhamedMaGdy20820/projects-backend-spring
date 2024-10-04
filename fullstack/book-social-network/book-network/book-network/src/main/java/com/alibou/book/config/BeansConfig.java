package com.alibou.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // يستخدم لتحميل تفاصيل المستخدم من قاعدة البيانات أو مصدر البيانات.
        authProvider.setPasswordEncoder(passwordEncoder());     // تُرجع كائنًا من نوع PasswordEncoder، وهو المسؤول عن تشفير كلمات المرور و التأكد من أن كلمة المرور المدخلة تتطابق مع النسخة المشفرة المخزنة في قاعدة البيانات.
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public AuditorAware<Integer> auditorAware() {
        return new ApplicationAuditAware();
    }


    // إعداد فلتر CORS في تطبيق Java Spring Boot.
    // هذا الفلتر يسمح للمتصفح بإجراء طلبات عبر نطاقات (Cross-Origin Requests)
    // من واجهة أمامية (مثل Angular) إلى الخادم الخلفي (مثل Spring Boot).
    @Bean
    public CorsFilter corsFilter() {
        // يتم استخدامه لتسجيل تكوين CORS المرتبط بنمط URL معين.
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // يمثل إعدادات التكوين الخاصة بـ CORS، مثل السماح بالأصول (origins)
        // ، الرؤوس (headers)،
        // والطرق المسموح بها (HTTP methods).
        final CorsConfiguration config = new CorsConfiguration();

        // هذا الخيار يسمح بتضمين معلومات المصادقة (مثل ملفات تعريف الارتباط أو التوكنات) في الطلبات عبر النطاقات
        // هذا مفيد إذا كنت ترغب في السماح للمتصفح بإرسال هذه البيانات مع الطلبات
        config.setAllowCredentials(true);

        //هذا التكوين يسمح فقط للطلبات الواردة من النطاق http://localhost:4200
        // (واجهة أمامية محلية مثل Angular) بالوصول إلى الخادم.
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));


        // هنا يتم تحديد الرؤوس المسموح للمتصفح بإرسالها في الطلبات. على سبيل المثال،
        // ORIGIN, CONTENT_TYPE, ACCEPT, و AUTHORIZATION تُعد رؤوساً شائعة تستخدم في طلبات HTTP.
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.ORIGIN,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT,
                HttpHeaders.AUTHORIZATION
        ));



        // هنا يتم تحديد الطرق (HTTP methods) المسموح بها، مثل GET, POST, DELETE, PUT, و PATCH.
        // هذا يضمن أن المتصفح يمكنه إرسال طلبات بهذه الطرق فقط إلى الخادم
        config.setAllowedMethods(Arrays.asList(
                "GET",
                "POST",
                "DELETE",
                "PUT",
                "PATCH"
        ));

        // ي أن أي طلب يتم إرساله إلى أي مسار سيتم فحصه بواسطة هذا الفلتر
        source.registerCorsConfiguration("/**", config);


        return new CorsFilter(source);

    }

}

//الـ Headers (الرؤوس) في HTTP هي أجزاء من البيانات يتم إرسالها مع طلبات واستجابات HTTP
// توفر هذه الرؤوس معلومات إضافية حول الطلب أو الاستجابة، مثل نوع المحتوى الذي يتم إرساله
// أسلوب المصادقة، معلومات عن العميل (مثل المتصفح)، وغير ذلك.
