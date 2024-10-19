package com.springsecurity.reposiroty;

import com.springsecurity.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<MyUser,Long> {

    Optional<MyUser> findByEmail(String email);
}
