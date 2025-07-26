package com.LinkUp.controller;

import com.LinkUp.Config.JwtProvider;
import com.LinkUp.model.User;
import com.LinkUp.repository.UserRepository;
import com.LinkUp.request.LoginRequest;
import com.LinkUp.response.AuthResponse;
import com.LinkUp.service.CustomUserDetailsService;
import com.LinkUp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @PostMapping("signup")
    public AuthResponse createUser(@RequestBody User thisuser) throws Exception
    {
        User isExit=userRepository.findByEmail(thisuser.getEmail());
        if(isExit!=null)
        {
            throw new Exception("this email is used with another account");
        }
        User newuser=new User();
        newuser.setEmail(thisuser.getEmail());
        newuser.setFirstName(thisuser.getFirstName());
        newuser.setLastName(thisuser.getLastName());
        newuser.setPassword(passwordEncoder.encode(thisuser.getPassword()));
        User savedUser = userRepository.save(newuser);
        Authentication authentication= new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse res=new AuthResponse(token,"Register success");
        return res;

    }
    @PostMapping("signin")
    public AuthResponse signIn(@RequestBody LoginRequest loginRequest )
    {
     Authentication authentication= authenticate(loginRequest.getEmail(),loginRequest.getPassword());


        String token = JwtProvider.generateToken(authentication);
        AuthResponse res=new AuthResponse(token,"login success");
        return res;

    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        if (userDetails == null) {
            throw new BadCredentialsException("invalid username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("wrong password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());


    }
}
