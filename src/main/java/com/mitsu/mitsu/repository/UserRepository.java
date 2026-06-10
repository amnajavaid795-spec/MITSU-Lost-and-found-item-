package com.mitsu.mitsu.repository;

import com.mitsu.mitsu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User,Long> {

}