package com.kad.security;

import com.kad.entity.User;
import com.kad.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository urp;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = urp.getByUserName(userName);
        if (user==null) {
            throw new RuntimeException("No user exists with this UserName.");
        } else {
            return new CustomUserDetails(user);
        }
    }
}
