/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function addComment() {
    let commentText = document.getElementById("commentInput").value.trim();
    if (commentText === "") {
        alert("Vui lòng nhập bình luận!");
        return;
    }

    fetch('/add-comment', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({user_id: userId, product_id: productId, content: commentText})
    })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    let newComment = createCommentElement(data.fullname, commentText, data.created_at);
                    document.getElementById("commentsList").prepend(newComment); // Thêm vào giao diện
                    document.getElementById("commentInput").value = ""; // Xóa nội dung input
                } else {
                    alert("Lỗi khi gửi bình luận!");
                }
            })
            .catch(error => console.error('Lỗi:', error));
}
function toggleLike(commentId, likeButton) {
    fetch('/toggle-like', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ user_id: adminId, comment_id: commentId })
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

function loadComments() {
    fetch(`/get-comments?product_id=${productId}`)
    .then(response => response.json())
    .then(data => {
        document.getElementById("commentsList").innerHTML = "";
        let commentMap = new Map();
        data.comments.forEach(comment => {
            let commentElement = createCommentElement(comment.fullname, comment.content, comment.created_at, comment.id);
            commentMap.set(comment.id, commentElement);

            if (comment.parent_id) {
                let parentComment = commentMap.get(comment.parent_id);
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
