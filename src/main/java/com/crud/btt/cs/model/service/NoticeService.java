package com.crud.btt.cs.model.service;

import com.crud.btt.common.CustomPageable;
import com.crud.btt.common.Header;
import com.crud.btt.common.Pagination;
import com.crud.btt.common.SearchCondition;
import com.crud.btt.cs.entity.NoticeEntity;
import com.crud.btt.cs.entity.NoticeRepository;
import com.crud.btt.cs.entity.NoticeRepositoryCustom;
import com.crud.btt.cs.model.dto.NoticeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class  NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeRepositoryCustom noticeRepositoryCustom;
    private static final Logger logger = LoggerFactory.getLogger(NoticeService.class);

    //목록보기
    public Header<List<NoticeDto>> getNoticeList(Pageable oriPageable, SearchCondition searchCondition) {
        CustomPageable pageable = new CustomPageable(oriPageable);
        List<NoticeDto> list = new ArrayList<>();
//        logger.info(pageable.toString());
//        logger.info(searchCondition.toString());
        Page<NoticeEntity> noticeEntities =
                noticeRepositoryCustom.findAllBySearchCondition(
                        pageable, searchCondition);
//        logger.info("noticeEntities.getTotalPages() : "+noticeEntities.getTotalPages());
//        logger.info("noticeEntities.getNumber() : "+noticeEntities.getNumber());
        logger.info("noticeEntities.getPageable().getPageNumber() : "+noticeEntities.getPageable().getPageNumber());
        for (NoticeEntity entity : noticeEntities) {
            NoticeDto dto = NoticeDto.builder()
                    .noticeNo(entity.getNoticeNo())
                    .createAt(entity.getCreateAt())
                    .noticeTitle(entity.getNoticeTitle())
                    .noticeContent(entity.getNoticeContent())
                    .noticeReadCount(entity.getNoticeReadCount())
                    .adminCode(entity.getAdminCode())
                    .noticeOriginalFile(entity.getNoticeOriginalFile())
                    .noticeRenameFile(entity.getNoticeRenameFile())
                    .build();

            list.add(dto);
        }

        Pagination pagination = new Pagination(
                (int) noticeEntities.getTotalElements()
                , pageable.getPageNumber() == 0 ? 1 : pageable.getPageNumber()
                , pageable.getPageSize() + 1
                , 10
        );
        // pagination에서는 현재페이지가 0이 되면 ( 0 - 1 ) * pageFetch 이 음수가나옴.
        // logger.info("======================Service NoticeList pagination======================" + pagination.toString());
        return Header.OK(list, pagination);
    }

    // 상세보기
        public NoticeDto getNotice(Long noticeNo) {
            NoticeEntity noticeEntity = noticeRepository.findById(noticeNo).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
            noticeEntity.setNoticeReadCount(noticeEntity.getNoticeReadCount() + 1);
            noticeEntity = noticeRepository.save(noticeEntity);

            return NoticeDto.builder()
                    .noticeNo(noticeEntity.getNoticeNo())
                    .noticeTitle(noticeEntity.getNoticeTitle())
                    .noticeContent(noticeEntity.getNoticeContent())
                    .noticeReadCount(noticeEntity.getNoticeReadCount())
                    .createAt(noticeEntity.getCreateAt())
                    .build();
        }

//    // 글작성
//       public NoticeDto noticeCreate(NoticeDto noticeDto) {
//
//           TimeZone timeZone = TimeZone.getTimeZone("Asia/Seoul");
//           Date now = new Date();
//
//           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//           formatter.setTimeZone(timeZone);
//           String formattedDate = formatter.format(now);
//
//           try {
//               now = formatter.parse(formattedDate);
//           } catch (ParseException e) {
//               e.printStackTrace();
//           } catch (Exception e) {
//               e.printStackTrace();
//           }
//
//        NoticeEntity noticeEntity = NoticeEntity.builder()
//                .noticeTitle(noticeDto.getNoticeTitle())
//                .noticeContent(noticeDto.getNoticeContent())
//                .adminCode(noticeDto.getAdminCode())
//                .createAt(now)
//                .noticeReadCount(noticeDto.getNoticeReadCount())
//                .noticeOriginalFile(noticeDto.getNoticeOriginalFile())
//                .noticeRenameFile(noticeDto.getNoticeRenameFile()).build();
//        noticeEntity = noticeRepository.save(noticeEntity);
//        // return new NoticeDto(noticeRepository.save(new NoticeEntity(noticeDto)));
//
//      return NoticeDto.builder()
//              .noticeNo(noticeEntity.getNoticeNo())
//              .noticeTitle(noticeEntity.getNoticeTitle())
//              .noticeContent(noticeEntity.getNoticeContent())
//              .adminCode(noticeEntity.getAdminCode())
//              .createAt(noticeEntity.getCreateAt())
//              .noticeReadCount(noticeEntity.getNoticeReadCount())
//              .noticeOriginalFile(noticeEntity.getNoticeOriginalFile())
//              .noticeRenameFile(noticeEntity.getNoticeRenameFile())
//              .build();
//    }
//
//
//    //수정
//    public NoticeDto update(NoticeDto noticeDto) {
//        TimeZone timeZone = TimeZone.getTimeZone("GMT+9");
//        Date now = new Date();
//
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        formatter.setTimeZone(timeZone);
//        String formattedDate = formatter.format(now);
//
//        try {
//            now = formatter.parse(formattedDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        NoticeEntity existingNoticeEntity = noticeRepository.findById(noticeDto.getNoticeNo())
//                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
//
//        NoticeEntity noticeEntity = NoticeEntity.builder()
//                .noticeNo(noticeDto.getNoticeNo())
//                .noticeTitle(noticeDto.getNoticeTitle())
//                .noticeContent(noticeDto.getNoticeContent())
//                .noticeReadCount(existingNoticeEntity.getNoticeReadCount()-1)
//                .adminCode(noticeDto.getAdminCode())
//                .createAt(now)
//                .build();
//
//        return new NoticeDto(noticeRepository.save(noticeEntity));
//    }
//
//
//    //삭제 (삭제는 반환타입이 Long, 값은 삭제된 행 )
//    public Long noticeDelete(Long noticeNo){
//         noticeRepository.deleteByNoticeNo(noticeNo);
//         return 1L;
//    }

}

