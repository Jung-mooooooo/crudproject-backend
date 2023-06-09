package com.crud.btt.cs.entity;

import com.crud.btt.common.SearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.crud.btt.cs.entity.QNoticeEntity.noticeEntity;
@RequiredArgsConstructor
@Repository
//@ComponentScan(basePackages = "com.crud.btt.cs.entity")
public class NoticeRepositoryCustom {


    private final JPAQueryFactory queryFactory;

    public Page<NoticeEntity> findAllBySearchCondition(Pageable pageable, SearchCondition searchCondition){

        JPAQuery<NoticeEntity> query = queryFactory.selectFrom(noticeEntity)
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()));

        long total = query.stream().count();


        // int customOffset = pageable.getPageNumber() == 0 ? 0 : (pageable.getPageNumber()-1) * pageable.getPageSize();
        // System.out.println("customOffset : "+customOffset);

        List<NoticeEntity> results = query
                .where(searchKeywords(searchCondition.getSk(), searchCondition.getSv()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(noticeEntity.noticeNo.desc())
                .fetch();
        System.out.println("offset : " + pageable.getOffset());
        return new PageImpl<>(results, pageable, total);
    }

    private BooleanExpression searchKeywords(String sk, String sv) {
        if ("noticeTitle".equals(sk)) {
            if (StringUtils.hasLength(sv)) {
                return noticeEntity.noticeTitle.contains(sv);
            }
        } else if ("noticeContent".equals(sk)) {
            if (StringUtils.hasLength(sv)) {
                return noticeEntity.noticeContent.contains(sv);
            }
        }
            return null;
    }
}

