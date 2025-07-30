package com.example.board.inquiry.service;

import com.example.board.exception.ForbiddenException;
import com.example.board.exception.NotFoundException;
import com.example.board.inquiry.domain.Inquiry;
import com.example.board.inquiry.dto.InquiryCreateRequestDto;
import com.example.board.inquiry.dto.InquiryResponseDto;
import com.example.board.inquiry.dto.InquiryUpdateRequestDto;
import com.example.board.inquiry.repository.InquiryMapper;
import com.example.board.user.repository.UserMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InquiryService {

    public final InquiryMapper inquiryMapper;
    public final UserMapper userMapper;

    public InquiryService(InquiryMapper inquiryMapper, UserMapper userMapper) {

        this.inquiryMapper = inquiryMapper;
        this.userMapper = userMapper;
    }

    // 문의 사항 작성
    public void createInquiry(InquiryCreateRequestDto dto) {
        Inquiry inquiry = new Inquiry(dto.getUserId(), dto.getEmail(), dto.getTitle(), dto.getContent());
        inquiryMapper.insertInquiry(inquiry);
    }

    // 문의 사항 단건 조회
    public InquiryResponseDto getInquiryById(Long id) {
        Inquiry inquiry = inquiryMapper.findInquiryById(id);
        return new InquiryResponseDto(
                inquiry.getId(),
                inquiry.getUsername(),
                inquiry.getEmail(),
                inquiry.getTitle(),
                inquiry.getContent()
        );
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
            result.add(new InquiryResponseDto(
                    inquiry.getId(),
                    inquiry.getUsername(),
                    inquiry.getEmail(),
                    inquiry.getTitle(),
                    inquiry.getContent()
            ));
        }
        //5. 모든 dto 리스트 반환
        return result;
    }

    // 문의 사항 수정
    public void updateInquiry(Long id, InquiryUpdateRequestDto dto, Long userId) {
        // 1. 글 조회(작성자 userId 포함)
        Inquiry inquiry = inquiryMapper.findInquiryById(id);
        if (inquiry == null) {
            throw new NotFoundException("문의 글이 존재하지 않습니다.");
        }

        // 2. 인가(권한) 체크
        if (!inquiry.getUserId().equals(userId)) {
            throw new ForbiddenException("수정 권한이 없습니다.");
        }

        // 3. 커스텀 메소드로 값 변경 (setter 사용 지양)
        inquiry.updateContent(dto.getTitle(), dto.getContent());
        // 4. 실제 DB update 실행
        inquiryMapper.updateInquiry(inquiry);
    }

    // 문의 사항 삭제
    public void deleteInquiry(Long id, Long userId) {
        Inquiry inquiry = inquiryMapper.findInquiryById(userId);
        if (inquiry == null) {
            throw new NotFoundException("문의 글이 존재하지 않습니다.");
        }
        if (!inquiry.getUserId().equals(userId)) {
            throw new ForbiddenException("삭제 권한이 없습니다.");
        }

        inquiryMapper.deleteInquiry(id);
    }

}
