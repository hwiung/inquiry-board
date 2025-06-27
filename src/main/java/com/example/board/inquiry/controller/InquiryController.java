package com.example.board.inquiry.controller;

import com.example.board.inquiry.dto.InquiryCreateDto;
import com.example.board.inquiry.service.InquiryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/inquiries")
@RestController
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    // 문의 사항 작성
    @PostMapping
    public void createInquiry(@RequestBody InquiryCreateDto inquiryCreateDto) {
        inquiryService.createInquiry(inquiryCreateDto);
    }
}
