package project.bcvita.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.WebSocketNamespaceHandler;
import org.springframework.web.socket.config.annotation.*;
import project.bcvita.HttpHandshakeInterceptor;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");
        registry.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/vita")
                .setAllowedOriginPatterns("http://localhost:8004", "*")
                .withSockJS();
        registry.addEndpoint("/vita").setAllowedOrigins("*");
    }
}
