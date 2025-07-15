package com.example.board.inquiry.service;

import com.example.board.inquiry.domain.Inquiry;
import com.example.board.inquiry.dto.InquiryCreateRequestDto;
import com.example.board.inquiry.dto.InquiryResponseDto;
import com.example.board.inquiry.repository.InquiryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InquiryServiceTest {

    @Mock
    InquiryMapper inquiryMapper;

    @InjectMocks
    InquiryService inquiryService;

    @Test
    void createInquiryTest() {
        // given
        InquiryCreateRequestDto dto = new InquiryCreateRequestDto("hwiung@naver.com","hahaha", "nice day");

        // when
        inquiryService.createInquiry(dto);  // inquiryService 인스턴스로 호출해야 하며, InquiryService(대문자)로 static 호출하면 안됨!

        // then
        ArgumentCaptor<Inquiry> captor = ArgumentCaptor.forClass(Inquiry.class);
        verify(inquiryMapper, times(1)).insertInquiry(captor.capture());

        Inquiry savedInquiry = captor.getValue();
        assertEquals("hwiung@naver.com", savedInquiry.getEmail());
        assertEquals("hahaha", savedInquiry.getTitle());
        assertEquals("nice day", savedInquiry.getContent());
    }

    @Test
    void getInquiryByIdTest() {
        // given
        Inquiry inquiry = new Inquiry(1L, "hwiung", "hwiung@naver.com", "look at me", "hahaha");
        when(inquiryMapper.findInquiryById(1L)).thenReturn(inquiry);

        // when
        InquiryResponseDto result = inquiryService.getInquiryById(1L);

        // then
        assertEquals(1L, result.getId());
        assertEquals("hwiung", result.getUsername());
        assertEquals("hwiung@naver.com", result.getEmail());
        assertEquals("look at me", result.getTitle());
        assertEquals("hahaha", result.getContent());

    }

    @Test
    void getAllInquiriesTest() {

    }

    @Test
    void updateInquiryTest() {

    }

    @Test
    void deleteInquiryTest() {

    }

}
