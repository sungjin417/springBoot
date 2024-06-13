package com.kh.total.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.total.dto.ChatMessageDto;
import com.kh.total.dto.ChatRoomResDto;
import com.kh.total.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final ChatService chatService;
    private final Map<WebSocketSession, String> sessionRoomIdMap = new ConcurrentHashMap<>();
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload(); // 클라이언트가 전송한 메시지
        log.warn("{}", payload);
        // JSON 문자열을 ChatMessageDto 객체로 변환
        ChatMessageDto chatMessage = objectMapper.readValue(payload, ChatMessageDto.class);
        String roomId = chatMessage.getRoomId(); // 채팅방 ID
        // 세션과 채팅방 ID를 매핑
        sessionRoomIdMap.put(session, chatMessage.getRoomId()); // 세션과 채팅방 ID를 매핑
        if (chatMessage.getType() == ChatMessageDto.MessageType.ENTER) { // 메시지 타입이 ENTER이면
            chatService.addSessionAndHandleEnter(roomId, session, chatMessage); // 채팅방에 입장한 세션 추가
        } else if (chatMessage.getType() == ChatMessageDto.MessageType.CLOSE) {
            chatService.removeSessionAndHandleExit(roomId, session, chatMessage);
        } else {
            chatService.sendMessageToAll(roomId, chatMessage);
        }

    }
    @Override
    // WebSocket 연결의 생명주기를 관리하는데 있어 중요한 부분을 담당, 연결 종료 직 후 호출
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션과 매핑된 채팅방 ID 가져오기
        log.warn("afterConnectionClosed : {}", session);
        String roomId = sessionRoomIdMap.remove(session);
        if (roomId != null) {
            ChatMessageDto chatMessage = new ChatMessageDto();
            chatMessage.setType(ChatMessageDto.MessageType.CLOSE);
            chatService.removeSessionAndHandleExit(roomId, session, chatMessage);
        }
    }
}
