package com.crud.btt.member.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
//    MemberEntity save(MemberDto memberDto);

    /* 유효성 검사 - 중복체크
    * 중복 : true
    * not 중복 : false
    * */
    boolean existsByUserId(String userId);  //existsBy~: select count(*)이 아닌, select ~ limit 1;
                                            //중복이 하나라도 발생시, 즉시 쿼리 종료.
    boolean existsByPhone(String phone);
    boolean existsByEmail(String email);

    Optional<MemberEntity> findByUserId(String username);

    List<MemberEntity> findTop5ByOrderByEnrollDateDesc();

//    @EntityGraph(attributePaths = "auth")
//    Optional<MemberEntity> findOneWithAuthoritiesByUsername(String username);
//    Optional<MemberAuth> findByUserAuth(String username);
}
