package com.kad.service;

import com.kad.dto.LoginDto;
import com.kad.dto.RegisterDto;
import com.kad.entity.User;

public interface UserService {

    public User create(RegisterDto rdt);

    public User getByUserName(String userName);


    public String login(LoginDto ld);

    public User getAuthenticatedUser();


}
