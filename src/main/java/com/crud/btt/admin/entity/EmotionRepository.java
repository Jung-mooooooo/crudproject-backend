package com.crud.btt.admin.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface EmotionRepository extends JpaRepository<EmotionEntity, Long> {

    //감정 업데이트
    @Modifying
    @Transactional
    @Query(value = "update Emotion set emotion_cat = :emotionCat, emotion_date = sysdate where user_code = :userCode and substr(emotion_date, 1, 10) = substr(sysdate, 1, 10)" , nativeQuery = true)
//    @Query(value = "update Emotion set emotion_cat = :emotionCat, emotion_date = :emotionDate where user_code = :userCode and to_char(emotion_date, 'yyyy-MM-dd') = to_char(:emotionDate, 'yyyy-MM-dd')" , nativeQuery = true)
    void patch(@Param("userCode") Long userCode, @Param("emotionCat") String emotionCat);

    //유저별 감정 확인
    @Query(value = "select * from Emotion where user_code = :userCode and substr(emotion_date, 1, 10) = substr(sysdate, 1, 10)", nativeQuery = true)
    EmotionEntity findByUserCode(Long userCode);

    //@Query(value = "SELECT q FROM QnAEntity q WHERE q.qnaNo = q.qnaRef ORDER BY q.qnaNo DESC", nativeQuery = false)

    //@Query("select emotionCat, round((count(*) / (select count(*) from EmotionEntity) * 100), 2) || '%' as ratioPercentage" +
    //           "from EmotionEntity group by emotionCat")

//    @Query(value = "SELECT emotionCat, ROUND((COUNT(*) / (SELECT COUNT(*) FROM emotion) * 100), 2) || '%' AS ratioPercentage FROM emotion GROUP BY emotionCat", nativeQuery = true)
//    List<EmotionEntity> countEmotionCat();

    //월 감정클릭 (angry)
    @Query(value = "select count(e) from EmotionEntity e where substr(e.emotionDate, 1, 6) = substr(current_timestamp, 1, 6) AND e.emotionCat = 'angry'")
    Long countByMonthAngry();

    //월 감정클릭 (anxious)
    @Query(value = "select count(e) from EmotionEntity e where substr(e.emotionDate, 1, 6) = substr(current_timestamp, 1, 6) AND e.emotionCat = 'anxious'")
    Long countByMonthAnxious();

    //월 감정클릭 (excited)
    @Query(value = "select count(e) from EmotionEntity e where substr(e.emotionDate, 1, 6) = substr(current_timestamp, 1, 6) AND e.emotionCat = 'excited'")
    Long countByMonthExcited();

    //월 감정클릭 (happy)
    @Query(value = "select count(e) from EmotionEntity e where substr(e.emotionDate, 1, 6) = substr(current_timestamp, 1, 6) AND e.emotionCat = 'happy'")
    Long countByMonthHappy();

    //월 감정클릭 (sad)
    @Query(value = "select count(e) from EmotionEntity e where substr(e.emotionDate, 1, 6) = substr(current_timestamp, 1, 6) AND e.emotionCat = 'sad'")
    Long countByMonthSad();

    //월 감정클릭 (scary)
    @Query(value = "select count(e) from EmotionEntity e where substr(e.emotionDate, 1, 6) = substr(current_timestamp, 1, 6) AND e.emotionCat = 'scary'")
    Long countByMonthScary();

    //월 감정클릭 (tired)
    @Query(value = "select count(e) from EmotionEntity e where substr(e.emotionDate, 1, 6) = substr(current_timestamp, 1, 6) AND e.emotionCat = 'tired'")
    Long countByMonthTired();

    //월 감정클릭 (lonely)
    @Query(value = "select count(e) from EmotionEntity e where substr(e.emotionDate, 1, 6) = substr(current_timestamp, 1, 6) AND e.emotionCat = 'lonely'")
    Long countByMonthLonely();

    @Query(value = "SELECT emotionCat, ROUND((COUNT(*) / (SELECT COUNT(*) FROM emotion) * 100), 2) || '%' AS ratioPercentage FROM emotion GROUP BY emotionCat", nativeQuery = true)
    List<EmotionEntity> countEmotionCat();


}
