package com.crud.btt.cs.entity;

import com.crud.btt.common.SearchCondition;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.crud.btt.cs.entity.QFAQEntity.fAQEntity;

@RequiredArgsConstructor
@Repository
public class FAQRepositoryCustom {
    private final  JPAQueryFactory queryFactory;
    public Page<FAQEntity> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition){

        JPAQuery<FAQEntity> query = queryFactory.selectFrom(fAQEntity)
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()));

        long total = query.stream().count();

        // pageable 의 offset 기본값이 10으로 돼 있어서, 해당페이지의 첫번째 글순서를 가져올 수 있도록 변수 생성
        // pageable 은 setter 가 없어서 따로 생성함.
        int customOffset = pageable.getPageNumber() < 0 ? 0 : (pageable.getPageNumber()-1) * pageable.getPageSize();
        System.out.println("customOffset : "+customOffset);

        List<FAQEntity> results = query
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()))
                .offset(customOffset)
                .limit(pageable.getPageSize())
                .orderBy(fAQEntity.faqNo.desc())
                .fetch();
        return new PageImpl<>(results, pageable, total);
    }

    private Predicate searchKeywords(String sk, String sv) {

        if("faqTitle".equals(sk)){
            if(StringUtils.hasLength(sv)){
                return fAQEntity.faqTitle.contains(sv);
            }
        } else if ("faqContent".equals(sk)) {
            if(StringUtils.hasLength(sv)){
                return fAQEntity.faqContent.contains(sv);
            }
        }
        return null;

    }
}
