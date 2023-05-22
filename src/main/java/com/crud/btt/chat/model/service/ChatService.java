package com.crud.btt.chat.model.service;


import com.crud.btt.chat.entity.ChatListEntity;
import com.crud.btt.chat.model.dto.ChatListDto;
//import com.crud.btt.chat.model.dto.ChatListRepository;
import com.crud.btt.chat.model.dto.ChatRoom;
//import com.crud.btt.chat.model.dto.ChatRoomRepository;
import com.crud.btt.member.entity.MemberEntity;
import com.crud.btt.member.model.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    //채팅방을 생성, 조회, 하나의 세션에 메세지를 발송하는 서비스단
    //채팅방 Map은 서버에 생성된 모든 채팅방의 정보를 모아둔 구조체.
    //채팅방의 정보저장은 일단은 HashMap에 저장하도록 구현(추후 DB로 저장)

    //채팅방 조회 : 채팅방 Map에 담긴 정보를 조회
    //채팅방 생성 : Random UUID로 구별 ID로 가진 채팅방 객체를 생성하고 채팅방 Map에 추가
    // 추후 MatchId + 진영 코드 + 소환사 명을 구별 ID로 변경

    //메세지 발송 : 지정한 Websocket 세션에 메세지 발송

    //JSON 컨텐츠를 java 객체로 deserialization 하거나, java 객체를 JSON으로 serialization할 때
    //사용하는 JSON 라이브러리 클래스
    //=> 제이슨을 자바로, 자바를 제이슨으로 직렬화 가능
//    private final ObjectMapper objectMapper;
//    private Map<String, ChatRoom> chatRooms;   //서버에 생성된 모든 채팅방의 정보를 모아둔 구조체.
//
//    @PostConstruct //의존성 주입이 이루어진 후 초기화를 수행하는 메서드, 다른 리소스에 호출되지 않아도 수행됨
//    private void init(){
//        log.info("service init");
//        chatRooms = new LinkedHashMap<>();
//    }

//    //채팅방 리스트 조회
//    public List<ChatRoom> findAllRoom() {
////        List<ChatRoom> chatRooms = new ArrayList<>();
////        Collections.reverse(chatRooms);
////        return chatRooms;
//        return new ArrayList<>(chatRooms.values());
//    }

//    //채팅방 정보 조회
//    public ChatRoom findById(String roomId){
//
//        System.out.println("chatRoos roodId" + chatRooms.get(roomId).toString());
//        return chatRooms.get(roomId);
//    }
//
//    //채팅방 생성
//    public ChatRoom createRoom(String name) {
//        log.info("create room - service");
//        String randomId = UUID.randomUUID().toString();
//        ChatRoom chatRoom = ChatRoom.builder()
//                .roomId(randomId)
//                .name(name)
//                .build();
//        chatRooms.put(randomId, chatRoom);
//        log.info(chatRooms.toString());
//        log.info("chatRoom - Id : " + chatRoom.getRoomId() + ", name : " + chatRoom.getName());
//        return chatRoom;
//    }
//
//    //메세지 전달
//    public <T> void sendChat(WebSocketSession session, T chat) {
//        try{
//            log.info("sendChat - service");
//            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(chat)));
//        }catch (IOException e){
//            log.error(e.getMessage(), e);
//        }
//    }


//    private final MemberService memberService;
//    private final ChatListRepository chatListRepository;
//
//    //채팅유저리스트 생성
//    public ChatListEntity save(Long userCode) throws Exception {
//        MemberEntity member = memberService.read(userCode);
//        log.info(member.toString());
//        ChatListEntity chatListEntity = ChatListEntity.builder()
//                .userCode(userCode)
//                .userName(member.getUsername())
//                .build();
//              return chatListRepository.save(chatListEntity);
//    }



}
