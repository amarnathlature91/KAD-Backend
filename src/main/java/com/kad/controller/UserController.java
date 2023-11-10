package com.kad.controller;

import com.kad.dto.JwtResponse;
import com.kad.dto.LoginDto;
import com.kad.dto.RegisterDto;
import com.kad.entity.User;
import com.kad.security.CustomUserDetails;
import com.kad.security.JwtHelper;
import com.kad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService ussr;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtHelper jwt;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto rdt) {
        User su = ussr.create(rdt);
        return new ResponseEntity<>(su, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login( @RequestBody LoginDto ldt){
//        JwtResponse jwtResponse = new JwtResponse(ussr.login(ldt));
//        return new ResponseEntity<>(jwtResponse, HttpStatus.OK)
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                ldt.getUserName(), ldt.getPassword())
        );
        User loginUser = ussr.getByUserName(ldt.getUserName());
        CustomUserDetails userPrincipal = new CustomUserDetails(loginUser);
        JwtResponse jw=new JwtResponse(loginUser,jwt.generateToken(userPrincipal));
        return new ResponseEntity<>(jw, HttpStatus.OK);
    }
}
