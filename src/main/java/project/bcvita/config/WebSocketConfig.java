package project.bcvita.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(webSocketHandler, "ws/chat").setAllowedOrigins("*")
                // HttpSession에 저장되어 있는 loginId를 사용하기 위해 websocket과 httpSession을 이어주는 Interceptor 입니다.
                // 이렇게 하면 HttpSession의 속성을 WebSocket 속성으로 복사하게 됩니다.
                .addInterceptors(new HttpSessionHandshakeInterceptor());
    }
}
