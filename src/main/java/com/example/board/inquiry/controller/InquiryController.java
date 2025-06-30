package com.example.board.inquiry.controller;

import com.example.board.inquiry.dto.InquiryCreateDto;
import com.example.board.inquiry.dto.InquiryResponseDto;
import com.example.board.inquiry.service.InquiryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 문의 사항 단건 조회
    @GetMapping("/{id}")
    public InquiryResponseDto getInquiryById(@PathVariable Long id) {
        return inquiryService.getInquiryById(id);
    }

    // 문의 사항 전체 조회
    @GetMapping
    public List<InquiryResponseDto> getAllInquiries() {
        return inquiryService.getAllInquiries();
    }

}
