package com.example.farm.controller;


import com.example.farm.dto.LoginDto;
import com.example.farm.entity.User;
import com.example.farm.entity.Role;
import com.example.farm.repository.UserRepository;
import com.example.farm.security.CustomUserDetailsService;
import com.example.farm.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.farm.dto.EmailRequest;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;

    /*@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = jwtUtil.generateToken(loginDto.getUsernameOrEmail());
        return ResponseEntity.ok(token);
    }*/
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Long userId = user.getId();
        Set<Role> roles = user.getRoles();
        final String token = jwtUtil.generateToken(loginDto.getUsernameOrEmail(), userId, roles);

        return ResponseEntity.ok(token);
    }


    /*@PostMapping("/email/request")
    public ResponseEntity<?> requestVerification(@RequestBody EmailRequest emailRequest) {
        String email = emailRequest.getEmail();
        boolean sent = userService.generateAndSendVerificationToken(email);
        if (sent) {
            return ResponseEntity.ok(new ApiResponse("Verification email sent.", "success"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("Invalid email address or user not found.", "error"));
        }
    }

    @GetMapping("/email/confirm")
    public ResponseEntity<?> confirmVerification(@RequestParam String token) {
        boolean verified = userService.verifyEmailToken(token);
        if (verified) {
            return ResponseEntity.ok(new ApiResponse("Email verified successfully.", "success"));
        } else {
            return ResponseEntity.badRequest().body(new ApiResponse("Invalid or expired token.", "error"));
        }
    }*/

}

