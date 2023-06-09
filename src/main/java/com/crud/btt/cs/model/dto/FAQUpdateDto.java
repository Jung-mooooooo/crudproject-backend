package com.crud.btt.cs.model.dto;

import com.crud.btt.cs.entity.FAQEntity;
import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FAQUpdateDto {

    private long faqNo;
    private Date createAt;
    private String faqTitle;
    private String faqContent;
    private String faqCat;
    private long adminCode;

    public FAQUpdateDto(FAQEntity faqEntity){
        this.faqNo = faqEntity.getFaqNo();
        this.createAt = faqEntity.getCreateAt();
        this.faqTitle = faqEntity.getFaqTitle();
        this.faqContent = faqEntity.getFaqContent();
        this.faqCat = faqEntity.getFaqCat();
        this.adminCode = faqEntity.getAdminCode();
    }

    public FAQUpdateDto(String fail_msg){
        this.faqTitle = fail_msg;
    }
}
