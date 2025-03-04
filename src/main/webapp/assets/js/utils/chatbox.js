let socket;

document.getElementById("chatIcon").addEventListener("click", function () {
    const chatBox = document.getElementById("chat-widget");

    if (!chatBox.classList.contains("show")) {
        // Mở chat box
        chatBox.style.display = "flex"; // Hiện chat box trước khi animation
        setTimeout(() => {
            chatBox.classList.add("show"); // Thêm hiệu ứng mở
        }, 10); // Đợi một chút để CSS nhận dạng thay đổi

        // Chỉ kết nối WebSocket nếu chưa có kết nối hoặc đã đóng
        // Chỉ kết nối WebSocket nếu chưa có kết nối hoặc đã đóng
         if (!socket || socket.readyState === WebSocket.CLOSED || socket.readyState === WebSocket.CLOSING) {
            socket = new WebSocket("ws://localhost:8080/chat");
            socket.onopen = function () {
                console.log("✅ Kết nối WebSocket thành công!");
            };
            console.log(socket.readyState);

            socket.onerror = function (error) {
                console.error("❌ Lỗi kết nối WebSocket:", error);
            };

            socket.onclose = function (event) {
                console.log("⚠️ Kết nối WebSocket đã đóng, mã:", event.code);
            };

            socket.onmessage = function (event) {
                console.log("📨 Nhận tin nhắn từ server:", event.data);

                if (!event.data.startsWith("bot"))
                    return;

                const botMess = event.data.replace("bot", "").trim();
                const chatBox = document.querySelector("#chat-box");
                if (!chatBox)
                    return;

                const now = new Date();
                const dateString = now.toLocaleDateString("vi-VN");
                const timeString = now.toLocaleTimeString("vi-VN", {
                    hour: "2-digit",
                    minute: "2-digit",
                });

                const chatDate = document.querySelector(".chat-date");
                chatDate.textContent = dateString;

                const parentMessage = document.createElement("div");
                parentMessage.classList.add("parent-message");

                const botMessage = document.createElement("div");
                botMessage.classList.add("message", "bot");
                botMessage.textContent = botMess;

                const sendTime = document.createElement("span");
                sendTime.classList.add("send-time");
                sendTime.textContent = timeString;

                parentMessage.appendChild(botMessage);
                parentMessage.appendChild(sendTime);

                chatBox.appendChild(parentMessage);
                chatBox.scrollTop = chatBox.scrollHeight;
            };
        }
    } else {
        // Ẩn chat box có hiệu ứng
        chatBox.classList.add("hide");

        // Đợi hiệu ứng chạy xong rồi mới ẩn hẳn
        setTimeout(() => {
            chatBox.classList.remove("show", "hide");
            chatBox.style.display = "none";
        }, 300); // Cùng thời gian với transition trong CSS

        if (socket) {
            socket.close();
            socket = null; // Reset lại để lần sau mở sẽ kết nối lại
        }
    }
});


// Hàm gửi tin nhắn
function sendMessage(id, type) {
    const chatBox = document.querySelector("#chat-box");
    if (!id) {
        return;
    }
    const input = document.getElementById("message");
    const messageText = input.value.trim();

    if (messageText === "") {
        return;
    }
    if (socket.readyState !== WebSocket.OPEN) {
        console.error("❌ WebSocket chưa sẵn sàng để gửi tin nhắn!");
        return;
    }

    // Hiển thị tin nhắn của user
    const userMessage = document.createElement("div");
    userMessage.classList.add("message", "user");
    userMessage.textContent = messageText;

    chatBox.appendChild(userMessage);
    chatBox.scrollTop = chatBox.scrollHeight;

    // Gửi tin nhắn lên server
    const data = JSON.stringify({
        roomId: id + "",
        type: type ? "admin" : "user",
        message: messageText,
    });

    socket.send(data);
    input.value = "";
}
