/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function showToast(message, type) {
    Toastify({
        text: message,
        duration: 3000,
        destination: "http://localhost:8080/Checkout",
        newWindow: true,
        stopOnFocus: true,
        gravity: "top",
        position: "center",
        style: {
            background: type === 'success' ? "#21A691" : "#EE4E4E",
            padding: "10px 40px",
            fontSize: "16px",
            borderRadius: "10px",
            cursor: "pointer"
        },
        onclick: function () {}
    }).showToast();
}

const handleAddToCart = (productId, email) => {
    // Kiểm tra nếu phần tử #quantity tồn tại
    let numElement = document.querySelector('#quantity');
    let num = numElement ? numElement.value : 1; // Nếu không tìm thấy phần tử, đặt giá trị mặc định là 1

    console.log(email)
    if (!email) {
        showToast('Please log in to order!');
    } else {
        showToast('Add to Cart Successfull!', 'success')
    }
    fetch('Checkout', {
        method: 'post', // viết chữ thường cho method
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `id=${productId}&num=${num}`
    })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json(); // Giả sử server trả về JSON
            })
            .then(data => {

                console.log(data);
                if (data.success) {
                    // Cập nhật số lượng giỏ hàng hiển thị trên trang
                    document.querySelector('#num-order').textContent = data.numOrder;
                }
                console.log('Product added to cart:', data);
            })
            .catch(error => {
                console.error('Error adding product to cart:', error);
            });
}

// Gọi hàm để gửi dữ liệu khi cần

