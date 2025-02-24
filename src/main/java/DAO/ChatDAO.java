package DAO;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import model.User;
import org.json.JSONObject;

@ServerEndpoint(value = "/chat", configurator = ChatConfigurator.class) // Thêm configurator
public class ChatDAO {

    private static final Map<String, Set<Session>> rooms = new HashMap<>();
    private static final Set<Session> adminSessions = new CopyOnWriteArraySet<>();
    private static final Map<Session, String> userRooms = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        // Lấy HttpSession từ WebSocket
        HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");

        if (httpSession != null) {
            Object userObj = httpSession.getAttribute("User"); // Lấy user từ session
            if (userObj instanceof User) {
                User user = (User) userObj;
                System.out.println("🟢 Kết nối mới: " + session.getId() + " - User: " + user.getEmail());
                try {
                    // Chỉ gửi tin nhắn nếu user đã đăng nhập
                    session.getBasicRemote().sendText("botXin chào " + user.getFullname() + "! Bạn cần hỗ trợ gì?");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    session.close(); // Đóng kết nối nếu user chưa đăng nhập
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("⚠️ Người dùng chưa đăng nhập, không gửi tin nhắn.");
            }
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            JSONObject json = new JSONObject(message);
            String type = json.getString("type"); // "user" hoặc "admin"
            String chatMessage = json.getString("message");
            System.out.println("t" + type);
            System.out.println("c" + chatMessage);
            if ("user".equals(type)) {
                String roomId = json.getString("roomId");
                rooms.putIfAbsent(roomId, new CopyOnWriteArraySet<Session>());
                rooms.get(roomId).add(session);
                userRooms.put(session, roomId);

                // Gửi tin nhắn chào mừng khi user kết nối
                session.getBasicRemote().sendText("botAstatine Xin chào! Bạn cần hỗ trợ gì?");
                System.out.println("📩 Người dùng gửi tin nhắn từ phòng " + roomId + ": " + chatMessage);

                // Gửi tin nhắn đến tất cả admin
                for (Session admin : adminSessions) {
                    admin.getBasicRemote().sendText("bot" + chatMessage);
                }
            } else if ("admin".equals(type)) {
                String roomId = json.getString("roomId");
                if (rooms.containsKey(roomId)) {
                    System.out.println("📩 Admin gửi tin nhắn đến phòng " + roomId + ": " + chatMessage);

                    for (Session user : rooms.get(roomId)) {
                        user.getBasicRemote().sendText("bot" + chatMessage);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        if (adminSessions.contains(session)) {
            adminSessions.remove(session);
            System.out.println("🔴 Admin rời khỏi.");
        } else {
            String roomId = userRooms.remove(session);
            if (roomId != null && rooms.containsKey(roomId)) {
                rooms.get(roomId).remove(session);
                if (rooms.get(roomId).isEmpty()) {
                    rooms.remove(roomId);
                }
            }
            System.out.println("🔴 Người dùng rời khỏi phòng " + roomId);
        }
    }
}
