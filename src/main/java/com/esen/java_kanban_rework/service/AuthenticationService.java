package com.esen.java_kanban_rework.service;

import com.esen.java_kanban_rework.config.JwtService;
import com.esen.java_kanban_rework.dto.*;
import com.esen.java_kanban_rework.entity.User;
import com.esen.java_kanban_rework.exception_handlers.IncorrectPasswordException;
import com.esen.java_kanban_rework.exception_handlers.VerificationNotPassed;
import com.esen.java_kanban_rework.repository.UserRepository;
import com.esen.java_kanban_rework.utils.UserRole;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final OTPService otpService;

    public AuthenticationResponseDTO register(RegisterRequestDTO request)
    {
        var userAlreadyRegistered = repository.findByEmail(request.getEmail()).isPresent();

        if (!userAlreadyRegistered) {
            var user = User.builder()
                    .firstname(request.getFirstName())
                    .lastname(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(UserRole.USER)
                    .isVerified(false)
                    .build();
            repository.save(user);

            otpService.sendOTP(OTPRequest.builder().email(request.getEmail()).build());

            return AuthenticationResponseDTO.builder()
                    .message("Verification email has been sent")
                    .build();
        } else {
            return AuthenticationResponseDTO.builder()
                    .message("This email is already registered")
                    .build();
        }
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        if (!user.getIsVerified()) {
            throw new VerificationNotPassed("This email is not verified");
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    public String verify(VerifyDTO request) {
        if (otpService.isOtpValid(request)) {
            var user = repository.findByEmail(request.getEmail())
                    .orElseThrow();
            user.setIsVerified(true);
            repository.save(user);
            return "You account has been verified, you can login now";
        }
        return "Get a new token";
    }

//    public ResetPasswordResponse resetPassword(ResetPasswordRequest request) {
//        Optional<User> optUser = repository.findByEmail(request.getEmail());
//
//        if (!optUser.isPresent()) {
//            return ResetPasswordResponse.builder()
//                    .status(HttpStatus.NOT_FOUND.value())
//                    .message("Email doesn't exist")
//                    .build();
//        }
//
//        User user = optUser.get();
//
//        try {
//            if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
//                throw new IncorrectPasswordException("Old password is incorrect");
//            }
//            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//            repository.save(user);
//            return ResetPasswordResponse.builder()
//                    .status(HttpStatus.OK.value())
//                    .message("Password updated successfully")
//                    .build();
//        } catch (IncorrectPasswordException e) {
//            return ResetPasswordResponse.builder()
//                    .status(HttpStatus.BAD_REQUEST.value())
//                    .message(e.getMessage())
//                    .build();
//        } catch (Exception e) {
//            return ResetPasswordResponse.builder()
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                    .message("An error occurred while updating the password " + e.getMessage())
//                    .build();
//        }
//    }

    public ResetPasswordResponseDTO resetPassword(ResetPasswordRequestDTO request) {
        try {
            User user = repository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Email doesn't exist"));

            if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
                throw new IncorrectPasswordException("Old password is incorrect");
            }

            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            repository.save(user);
            return ResetPasswordResponseDTO.builder()
                    .status(HttpStatus.OK.value())
                    .message("Password updated successfully")
                    .build();
        } catch (UsernameNotFoundException e) {
            return ResetPasswordResponseDTO.builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message(e.getMessage())
                    .build();
        } catch (IncorrectPasswordException e) {
            return ResetPasswordResponseDTO.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
        } catch (Exception e) {
            return ResetPasswordResponseDTO.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("An error occurred while updating the password " + e.getMessage())
                    .build();
        }
    }
}
