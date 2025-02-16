package DAO;

import jakarta.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class ChatConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        // Kiểm tra nếu request hỗ trợ HttpSession
        Object sessionObj = request.getHttpSession();
        if (sessionObj instanceof HttpSession) {
            HttpSession httpSession = (HttpSession) sessionObj;
            config.getUserProperties().put("httpSession", httpSession);
        }
    }
}
