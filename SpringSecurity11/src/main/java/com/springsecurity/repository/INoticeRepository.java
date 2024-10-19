package com.springsecurity.repository;

import com.springsecurity.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INoticeRepository extends JpaRepository<Notice,Long> {

    @Query(value = "FROM Notice n WHERE CURDATE() BETWEEN noticBegDt AND noticEndDt")
    List<Notice> findAllActiveNotices();
}
