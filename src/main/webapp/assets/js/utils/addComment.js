/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function addCommentServer(userId, productId, commentText) {
    const now = new Date();
    const dateString = now.toLocaleDateString("vi-VN");
    const timeString = now.toLocaleTimeString("vi-VN", {
        hour: "2-digit",
        minute: "2-digit",
    });
    const createAt = timeString + " " + dateString
    fetch('Comment', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({user_id: userId, product_id: productId, content: commentText})
    })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    // ✅ Lấy commentId từ phản hồi backend
                    let commentId = data.comment_id;

                    // ✅ Tạo comment với ID chính xác
                    var commentDiv = createCommentElement('${User.fullname}', commentText, "",commentId, productId);
                    document.getElementById("commentsList").appendChild(commentDiv);
                    document.getElementById("commentInput").value = "";
                } else {
                    showToast("Error while adding comment!", '');
                }
            })
            .catch(error => {
                console.error("Error:", error);
                showToast("Network error!", '');
            });
}

function toggleLikeServer(likeButton) {

    let commentDiv = likeButton.closest('.comment');
    let commentId = commentDiv.dataset.commentId; // ✅ Lấy ID chính xác
    let commentOwnerId = commentDiv.dataset.userId;

    fetch('LikeComment', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({user_id: commentOwnerId, comment_id: commentId})
    })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    likeButton.textContent = `Like (${data.like_count})`;
                    likeButton.style.color = data.liked ? '#007bff' : 'black';
                } else {
                    alert("Lỗi khi like!");
                }
            });
}

function loadComments(name, productId) {
    fetch(`Comment?product-id=${productId}`)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                document.getElementById("commentsList").innerHTML = "";
                let commentMap = new Map();
                data.comments.forEach(comment => {
                    let commentElement = createCommentElement(name, comment.commentText, comment.createdAt, comment.id, productId);
                    commentMap.set(comment.id, commentElement);
                    if (comment.parentId) {
                        let parentComment = commentMap.get(comment.parentId);
                        parentComment.querySelector(".replies").appendChild(commentElement);
                    } else {
                        document.getElementById("commentsList").appendChild(commentElement);
                    }
                });
            });
}
function replyComment(parentId, commentDiv) {
    let replyInput = document.createElement("input");
    replyInput.placeholder = "Nhập phản hồi...";
    let sendButton = document.createElement("button");
    sendButton.textContent = "Gửi";
    sendButton.onclick = function () {
        if (replyInput.value.trim() !== "") {
            addComment(parentId); // Gửi comment với parentId
            commentDiv.querySelector(".replies").appendChild(createCommentElement(adminName, replyInput.value.trim(), new Date(), parentId));
            replyInput.remove();
            sendButton.remove();
        } else {
            alert("Vui lòng nhập phản hồi!");
        }
    };
    commentDiv.appendChild(replyInput);
    commentDiv.appendChild(sendButton);
}
