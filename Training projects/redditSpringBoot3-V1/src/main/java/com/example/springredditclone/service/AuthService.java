package com.example.springredditclone.service;

import com.example.springredditclone.dto.AuthenticationResponse;
import com.example.springredditclone.dto.LoginRequest;
import com.example.springredditclone.dto.RefreshTokenRequest;
import com.example.springredditclone.dto.RegisterRequest;
import com.example.springredditclone.exceptions.SpringRedditException;
import com.example.springredditclone.model.NotificationEmail;
import com.example.springredditclone.model.User;
import com.example.springredditclone.model.VerificationToken;
import com.example.springredditclone.repository.UserRepository;
import com.example.springredditclone.repository.VerificationTokenRepository;
import com.example.springredditclone.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Builder
@Slf4j
@AllArgsConstructor
@Transactional
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;





    public void signup(RegisterRequest registerRequest){
        User user= new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail
                (
                "Please Activate your Account",                                 //subject
                user.getEmail(),                                                       // recipient
                "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token          // body
                )
        );
    }


    private String generateVerificationToken(User user){
       String token =  UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setExpiryDate(Instant.now());
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;

    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new SpringRedditException("Invalid Token"));
        fetchUserAndEnable(verificationToken.get());
    }


    private void fetchUserAndEnable(VerificationToken verificationToken) {
        long userId = verificationToken.getUser().getUserId();
        User user = userRepository.findByUserId(userId).orElseThrow(() ->
                    new SpringRedditException("User not found with name - " + userId));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(  loginRequest.getUsername()  ,
                                                          loginRequest.getPassword()  )
                                                                         );
        // بخزن ال authenticate للتأكد من انا المستخدم قد سجل الدخول ام لا
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);


        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))  // بيحدد وقت انتهاء الصلاحيه
                .username(loginRequest.getUsername())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {

        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken()); // بعرف هيه موجوده عندي في الداتا بيز ولا لا

        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());

        return AuthenticationResponse.builder() 
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // authentication instanceof AnonymousAuthenticationToken  means the user is anonymous
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;
        org.springframework.security.core.userdetails.User user =  (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        User principal = userRepository.findByUsername(user.getUsername()).orElseThrow(() ->
                new SpringRedditException("User not found with name - " + user.getUsername()));

        return principal;
    }

}

