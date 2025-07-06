package com.example.board.inquiry.controller;

import com.example.board.inquiry.dto.InquiryCreateRequestDto;
import com.example.board.inquiry.dto.InquiryResponseDto;
import com.example.board.inquiry.dto.InquiryUpdateRequestDto;
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
    public void createInquiry(@RequestBody InquiryCreateRequestDto inquiryCreateRequestDto) {
        inquiryService.createInquiry(inquiryCreateRequestDto);
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

    // 문의 사항 수정
    @PatchMapping("/{id}")
    public void updateInquiry(@PathVariable Long id, @RequestBody InquiryUpdateRequestDto inquiryUpdateRequestDto) {
        inquiryService.updateInquiry(id, inquiryUpdateRequestDto);
    }

    // 문의 사항 삭제
    @DeleteMapping("/{id}")
    public void deleteInquiry(@PathVariable Long id) {
        inquiryService.deleteInquiry(id);
    }
}
