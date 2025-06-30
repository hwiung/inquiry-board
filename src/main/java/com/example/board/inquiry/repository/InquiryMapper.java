package com.example.board.inquiry.repository;

import com.example.board.inquiry.domain.Inquiry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryMapper {

    void insertInquiry(Inquiry inquiry);

    Inquiry findInquiryById(Long id);

    List<Inquiry> findAllInquiries();

}
