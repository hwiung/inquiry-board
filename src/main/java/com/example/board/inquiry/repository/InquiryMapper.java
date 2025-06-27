package com.example.board.inquiry.repository;

import com.example.board.inquiry.domain.Inquiry;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InquiryMapper {

    void insertInquiry(Inquiry inquiry);

}
