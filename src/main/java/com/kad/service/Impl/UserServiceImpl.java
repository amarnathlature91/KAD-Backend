package com.kad.service.Impl;

import com.kad.dto.LoginDto;
import com.kad.dto.RegisterDto;
import com.kad.entity.User;
import com.kad.repository.UserRepository;
import com.kad.security.CustomUserDetailsService;
import com.kad.security.JwtHelper;
import com.kad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository urp;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private AuthenticationManager manager;

    public User create(RegisterDto rdt) {
        User savedU = null;
            User u = urp.getByUserName(rdt.getUserName());
            if (u != null) {
                throw new RuntimeException("UserName is Already Registered");
            }
            else {
                User us = new User();
                us.setUserName(rdt.getUserName());
                us.setEmail(rdt.getEmail());
                us.setPassword(rdt.getPassword());
                us.setRole("ROLE_ADMIN");
                us.setEnabled(true);
                savedU = urp.save(us);
            }
        return savedU;
    }

    public User getByUserName(String email) {
        User u=urp.getByUserName(email);
        if (u != null) {
            return u;
        } else {
            throw new RuntimeException("User Not Found with given UserName");
        }
    }


    public String login(LoginDto ld) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(ld.getUserName(), ld.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Bad Credentials");
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(ld.getUserName());
        return jwtHelper.generateToken(userDetails);
    }

    public final User getAuthenticatedUser() {
        String authUser = SecurityContextHolder.getContext().getAuthentication().getName().toString();
        return getByUserName(authUser);
    }

}
