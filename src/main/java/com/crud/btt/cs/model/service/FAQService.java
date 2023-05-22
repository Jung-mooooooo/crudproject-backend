
package com.crud.btt.cs.model.service;

import com.crud.btt.common.Header;
import com.crud.btt.common.Pagination;
import com.crud.btt.common.SearchCondition;
import com.crud.btt.cs.controller.FAQController;
import com.crud.btt.cs.entity.FAQEntity;
import com.crud.btt.cs.entity.FAQRepository;
import com.crud.btt.cs.entity.FAQRepositoryCustom;
import com.crud.btt.cs.model.dto.FAQDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Slf4j
@RequiredArgsConstructor
@Service
public class FAQService {
    
    private final FAQRepository faqRepository;
    private final FAQRepositoryCustom faqRepositoryCustom;
    private static final Logger logger = LoggerFactory.getLogger(FAQController.class);
    //목록보기
    public Header<List<FAQDto>> getFAQList(Pageable pageable, SearchCondition searchCondition) {
        List<FAQDto> list = new ArrayList<>();
        Page<FAQEntity> faqEntities =
                faqRepositoryCustom.findAllBySearchCondition(
                        pageable, searchCondition);
        for (FAQEntity entity : faqEntities) {
            FAQDto dto = FAQDto.builder()
                    .faqNo(entity.getFaqNo())
                    .createAt(entity.getCreateAt())
                    .faqTitle(entity.getFaqTitle())
                    .faqContent(entity.getFaqContent())
                    .adminCode(entity.getAdminCode())
                    .build();
            list.add(dto);
        }

        Pagination pagination = new Pagination(
                (int) faqEntities.getTotalElements()
                , pageable.getPageNumber()
                , pageable.getPageSize() + 1
                , 10
        );
        logger.info("======================Service FAQList======================" + list.toArray());
        return Header.OK(list, pagination);
    }

    // 상세보기
    public FAQDto getFAQ(Long faqNo) {
        FAQEntity faqEntities = faqRepository.findById(faqNo).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        return FAQDto.builder()
                .faqNo(faqEntities.getFaqNo())
                .faqTitle(faqEntities.getFaqTitle())
                .faqContent(faqEntities.getFaqContent())
                .createAt(faqEntities.getCreateAt())
                .build();
    }

    // 글작성
    public FAQDto faqCreate(FAQDto faqDto) {

        TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");
        Date now = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(timeZone);
        String formattedDate = formatter.format(now);

        try {
            now = formatter.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FAQEntity faqEntities = FAQEntity.builder()
                .faqNo(faqDto.getFaqNo())
                .faqTitle(faqDto.getFaqTitle())
                .faqContent(faqDto.getFaqContent())
                .adminCode(faqDto.getAdminCode())
                .createAt(now)
                .build();
        faqEntities = faqRepository.save(faqEntities);
        // return new FAQDto(faqRepository.save(new FAQEntity(faqDto)));

        return FAQDto.builder()
                .faqNo(faqEntities.getFaqNo())
                .faqTitle(faqEntities.getFaqTitle())
                .faqContent(faqEntities.getFaqContent())
                .adminCode(faqEntities.getAdminCode())
                .createAt(faqEntities.getCreateAt())
                .build();
    }

    //수정
    public FAQDto update(FAQDto faqDto) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT+9");
        Date now = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(timeZone);
        String formattedDate = formatter.format(now);

        try {
            now = formatter.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FAQEntity faqEntity = faqRepository.findById(faqDto.getFaqNo())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        FAQEntity faqEntities = faqEntity.builder()
                .faqNo(faqDto.getFaqNo())
                .faqTitle(faqDto.getFaqTitle())
                .faqContent(faqDto.getFaqContent())
                .adminCode(faqDto.getAdminCode())
                .createAt(now)
                .build();

        return new FAQDto(faqRepository.save(faqEntities));
    }


    //삭제 (삭제는 반환타입이 Long, 값은 삭제된 행 )
    public Long faqDelete(Long faqNo){
        faqRepository.deleteByFaqNo(faqNo);
        return 1L;
    }

}

