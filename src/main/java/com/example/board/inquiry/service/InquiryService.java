package com.example.board.inquiry.service;

import com.example.board.inquiry.domain.Inquiry;
import com.example.board.inquiry.dto.InquiryCreateDto;
import com.example.board.inquiry.repository.InquiryMapper;
import org.springframework.stereotype.Service;

@Service
public class InquiryService {

    public final InquiryMapper inquiryMapper;

    public InquiryService(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }

    // 문의 사항 작성
    public void createInquiry(InquiryCreateDto dto) {
        Inquiry inquiry = new Inquiry(dto.getTitle(), dto.getContent());
        inquiryMapper.insertInquiry(inquiry);
    }
}
