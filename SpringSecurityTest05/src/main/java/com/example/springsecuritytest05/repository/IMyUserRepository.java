package com.example.springsecuritytest05.repository;

import com.example.springsecuritytest05.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMyUserRepository extends JpaRepository<MyUser,Long> {

    Optional<MyUser> findByEmail(String email);
}
