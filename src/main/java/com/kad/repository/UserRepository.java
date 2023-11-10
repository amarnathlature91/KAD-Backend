package com.kad.repository;

import com.kad.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User getByUserName(String email);
}
