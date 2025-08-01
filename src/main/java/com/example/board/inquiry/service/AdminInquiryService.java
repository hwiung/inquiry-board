package com.example.board.inquiry.service;

import com.example.board.exception.NotFoundException;
import com.example.board.inquiry.domain.Inquiry;
import com.example.board.inquiry.repository.InquiryMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminInquiryService {
    private final InquiryMapper inquiryMapper;

    public AdminInquiryService(InquiryMapper inquiryMapper) {
        this.inquiryMapper = inquiryMapper;
    }

    public void updateInquiryAsAdmin(Long id, String title, String content) {
        Inquiry inquiry = inquiryMapper.findInquiryById(id);
        if (inquiry == null) {
            throw new NotFoundException("문의글이 존재하지 않습니다.");
        }

        // 엔터티에 직접 만든 값 변경 메소드(Setter 지양)
        inquiry.updateInquiry(title, content);
        inquiryMapper.updateInquiry(inquiry);
    }

    public void deleteInquiryAsAdmin(Long id) {
        Inquiry inquiry = inquiryMapper.findInquiryById(id);
        if (inquiry == null) {
            throw new NotFoundException("문의글이 존재하지 않습니다.");
        }

        inquiryMapper.deleteInquiry(id);
    }

}
