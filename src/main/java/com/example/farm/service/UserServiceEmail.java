/*
package com.example.farm.service;

import com.example.farm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    public boolean generateAndSendVerificationToken(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        String token = UUID.randomUUID().toString();
        TokenVerification tokenVerification = new TokenVerification(token, user);
        tokenRepository.save(tokenVerification);
        emailService.sendVerificationEmail(user.getEmail(), token);
        return true;
    }

    public boolean verifyEmailToken(String token) {
        TokenVerification tokenVerification = tokenRepository.findByToken(token);
        if (tokenVerification == null || tokenVerification.isExpired()) {
            return false;
        }
        User user = tokenVerification.getUser();
        user.setVerified(true);
        userRepository.save(user);
        tokenRepository.delete(tokenVerification);
        return true;
    }
}

*/
