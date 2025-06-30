package com.example.board.inquiry.service;

import com.example.board.inquiry.domain.Inquiry;
import com.example.board.inquiry.dto.InquiryCreateDto;
import com.example.board.inquiry.dto.InquiryResponseDto;
import com.example.board.inquiry.repository.InquiryMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    // 문의 사항 단건 조회
    public InquiryResponseDto getInquiryById(Long id) {
        Inquiry inquiry = inquiryMapper.findInquiryById(id);
        return InquiryResponseDto.from(inquiry);
    }

    // 문의 사항 전체 조회
    public List<InquiryResponseDto> getAllInquiries() {
        //1. 문의 사항 엔티티 리스트 조회
        List<Inquiry> inquiries = inquiryMapper.findAllInquiries();
        //2. dto 저장할 빈 리스트 생성
        List<InquiryResponseDto> result = new ArrayList<>();
        //3. 문의 사항 하나씩 꺼내서
        for (Inquiry inquiry : inquiries) {
            //4. Inquiry -> InquiryResponseDto로 변환 후 result에 추가
            result.add(InquiryResponseDto.from(inquiry));
        }
        //5. 모든 dto 리스트 반환
        return result;
    }


}
