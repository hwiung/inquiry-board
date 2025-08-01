package com.example.board.inquiry.controller;

import com.example.board.inquiry.dto.InquiryUpdateRequestDto;
import com.example.board.inquiry.service.AdminInquiryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/inquiries")
public class AdminInquiryController {
    private final AdminInquiryService adminInquiryService;

    public AdminInquiryController(AdminInquiryService adminInquiryService) {
        this.adminInquiryService = adminInquiryService;
    }

    // 모든 문의글 수정(관리자)
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateInquiryAsAdmin(@PathVariable Long id, InquiryUpdateRequestDto dto) {
        adminInquiryService.updateInquiryAsAdmin(id, dto.getTitle(), dto.getContent());
        return ResponseEntity.ok().build();
    }

    // 모든 문의글 삭제(관리자)
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteInquiryByadmin(@PathVariable Long id) {
        adminInquiryService.deleteInquiryAsAdmin(id);
        return ResponseEntity.ok().build();
    }
}
