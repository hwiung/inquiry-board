package com.example.board.inquiry.service;

import com.example.board.exception.NotFoundException;
import com.example.board.inquiry.domain.Inquiry;
import com.example.board.inquiry.repository.InquiryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminInquiryServiceTest {

    @Mock
    private InquiryMapper inquiryMapper;

    @InjectMocks
    private AdminInquiryService adminInquiryService;

    @Test
    void updateInquiryAsAdmin() {
        // given: 기존 문의가 존재할 때
        Long inquiryId = 1L;
        Inquiry inquiry = new Inquiry(inquiryId, "old title", "old content");

        // when: 문의를 업데이트하면
        when(inquiryMapper.findInquiryById(inquiryId)).thenReturn(inquiry);
        adminInquiryService.updateInquiryAsAdmin(inquiryId, "new title", "new content");

        // then: 내용이 정상적으로 변경되고, 업데이트가 호출된다.
        verify(inquiryMapper).updateInquiry(inquiry);
        assertEquals("new title", inquiry.getTitle());
        assertEquals("new content", inquiry.getContent());

        // given: 조회하려는 문의가 존재하지 않을 때
        when(inquiryMapper.findInquiryById(2L)).thenReturn(null);

        // when & then: 존재하지 않는 문의를 업데이트하면 NotFoundException이 발생한다.
        assertThrows(NotFoundException.class, () -> {
            adminInquiryService.updateInquiryAsAdmin(2L, "title", "content");
        });
    }
}
