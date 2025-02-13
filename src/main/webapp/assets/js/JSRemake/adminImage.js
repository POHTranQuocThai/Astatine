/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function validateForm() {
        const form = document.getElementById('productForm');
        const imageInput = document.getElementById('image');

        // Kiểm tra nếu ảnh không được chọn
        if (!imageInput.value) {
            // Thiết lập ảnh mặc định nếu không có ảnh được chọn
            const defaultImage = "default-image.jpg";
            const defaultImageInput = document.createElement("input");
            defaultImageInput.type = "hidden";
            defaultImageInput.name = "defaultImage";
            defaultImageInput.value = defaultImage;
            form.appendChild(defaultImageInput);
        }

        // Kiểm tra tất cả các trường bắt buộc đã được điền
        if (!form.checkValidity()) {
            alert("Please fill in all required fields.");
            return false; // Ngăn việc submit nếu form không hợp lệ
        }

        return true;
    }
