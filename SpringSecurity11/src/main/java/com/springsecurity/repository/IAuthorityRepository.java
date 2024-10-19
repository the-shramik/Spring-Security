package com.springsecurity.repository;

import com.springsecurity.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorityRepository extends JpaRepository<Authority,Long> {

}
