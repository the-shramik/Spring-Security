package com.springsecurity.controller;

import com.springsecurity.model.Notice;
import com.springsecurity.repository.INoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoticesController {

    @Autowired
    private INoticeRepository noticeRepository;

    @GetMapping("/notices")
    public ResponseEntity<?> getNotices() {

        List<Notice> notices = noticeRepository.findAllActiveNotices();

        if (notices != null) {
            return ResponseEntity.ok(notices);
        } else {
            return null;
        }
    }
}
