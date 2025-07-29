package com.example.board.inquiry.service;

import com.example.board.inquiry.domain.Inquiry;
import com.example.board.inquiry.dto.InquiryCreateRequestDto;
import com.example.board.inquiry.dto.InquiryResponseDto;
import com.example.board.inquiry.dto.InquiryUpdateRequestDto;
import com.example.board.inquiry.repository.InquiryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
        InquiryCreateRequestDto dto = new InquiryCreateRequestDto("hwiung@naver.com", "hahaha", "nice day");

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
        // given
        Inquiry inquiry1 = new Inquiry(1L, "hwiung1", "hwiung1@naver.com", "have a nice day", "hahaha");
        Inquiry inquiry2 = new Inquiry(2L, "hwiung2", "hwiung2@naver.com", "cross finger", "huh");
        List<Inquiry> inquiryList = List.of(inquiry1, inquiry2);
        when(inquiryMapper.findAllInquiries()).thenReturn(inquiryList);

        // when
        List<InquiryResponseDto> result = inquiryService.getAllInquiries();

        // then
        assertEquals(2, result.size());

        assertEquals(1L, result.get(0).getId());
        assertEquals("hwiung1", result.get(0).getUsername());
        assertEquals("hwiung1@naver.com", result.get(0).getEmail());

        assertEquals(2L, result.get(1).getId());
        assertEquals("hwiung2", result.get(1).getUsername());
        assertEquals("hwiung2@naver.com", result.get(1).getEmail());

    }

    @Test
    void updateInquiryTest() {
        // given -> 여기서 inquiry 객체와 dto 객체는 서로 별개의 객체임.
        Inquiry inquiry = new Inquiry(1L, 1L, "aiden@naver.com", "hi, i'm aiden", "nice to meet you");
        InquiryUpdateRequestDto dto = new InquiryUpdateRequestDto(1L, "hwiung@naver.com", "hello, i'm hwiung", "good evening");

        // Mock 세팅: findById가 inquiry를 반환하도록
        when(inquiryMapper.findInquiryById(1L)).thenReturn(inquiry);

        // when
        inquiryService.updateInquiry(1L, dto, 1L);  // 서비스 내부에서 새로운 객체를 만듦(dto 값을 받아서 새로운 inquiry 엔터티를 생성). 하지만 테스트에서 이걸 직접 참조할 방법이 없음! 그래서 ArgumentCaptor를 사용!

        // then
        ArgumentCaptor<Inquiry> captor = ArgumentCaptor.forClass(Inquiry.class);    // mock이 updateInquiry를 호출할 때 넘긴 실제 inquiry를 캡처
        verify(inquiryMapper).updateInquiry(captor.capture());
        Inquiry updateInquiry = captor.getValue();  // 여기에 실제 전달된 객체가 들어옴

        // 이제 이 updateInquiry의 각 필드를 assert로 검증할 수 있음
        assertEquals(1L, updateInquiry.getId());
        assertEquals("hello, i'm hwiung", updateInquiry.getTitle());
        assertEquals("good evening", updateInquiry.getContent());

    }

    @Test
    void deleteInquiryTest() {
        // given
        Long inquiryId = 1L;

        // when
        inquiryService.deleteInquiry(inquiryId);

        // then
        verify(inquiryMapper, times(1)).deleteInquiry(inquiryId);


    }

}
