package com.kh.total.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Slf4j
public class ChatRoomResDto {
    private String roomId; // 채팅방 ID, 서버에서 UUID를 통해 자동 생성
    private String name;
    private LocalDateTime regDate;

    @JsonIgnore // 데이터 직렬 방지
    private Set<WebSocketSession> sessions; // 채팅방에 입장한 세션 정보를 담을 set
    public boolean isSessionEmpty() {
        return this.sessions.isEmpty();

    }
    @Builder
    public ChatRoomResDto(String roomId, String name, LocalDateTime regDate) {
        this.roomId = roomId;
        this.name = name;
        this.regDate = regDate;
        this.sessions = Collections.newSetFromMap(new ConcurrentHashMap<>()); // 동시성 문제를 해결하기 위해 ConcurrentHashMap 사용
    }
}
