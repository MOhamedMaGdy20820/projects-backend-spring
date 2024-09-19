package com.alibou.book.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())                                 // يتيح التحكم في سياسات CORS (Cross-Origin Resource Sharing) باستخدام الإعدادات الافتراضية.
                .csrf(AbstractHttpConfigurer::disable)                // يتم تعطيل CSRF (Cross-Site Request Forgery) لأن التطبيق يستخدم المصادقة بـ JWT، وهي آمنة بما يكفي بدون CSRF
                .authorizeHttpRequests(req ->
                        req.requestMatchers(                          //تحدد المسارات (URLs) التي يمكن الوصول إليها بدون مصادقة
                                        "/auth/**",
                                        "/v2/api-docs",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui/**",
                                        "/webjars/**",
                                        "/swagger-ui.html"
                                )
                                .permitAll()                          // يسمح بالوصول إلى هذه المسارات بدون مصادقة
                                .anyRequest().authenticated()         //يتطلب المصادقة لأي طلبات أخرى غير المذكورة
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))      //يضبط إدارة الجلسة إلى الوضع STATELESS، مما يعني أن الجلسات لا تُستخدم وأن المصادقة تعتمد على كل طلب على حدة (بدون حالة session) المصادقه تتم من خلال ال jwt وليس بال sessions.
                .authenticationProvider(authenticationProvider)                              //يحدد مزود المصادقة الذي يتم استخدامه للتحقق من هوية المستخدمين.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // يضيف فلتر JWT قبل فلتر UsernamePasswordAuthenticationFilter في سلسلة الفلاتر لضمان التحقق من الرموز قبل إجراء المصادقة التقليدية.

        return http.build();
    }
}
