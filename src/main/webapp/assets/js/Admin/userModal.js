/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function openEditModal(userId) {
    // Sử dụng fetch để lấy dữ liệu người dùng bằng cách gửi request đến servlet
    fetch(`User?action=edit&id=${userId}`)
            .then(response => {
                if (!response.ok) {
                    // Nếu không tìm thấy người dùng, thông báo lỗi
                    alert("Không tìm thấy người dùng với ID này!");
                    throw new Error("User not found");
                }
                return response.json();
            })
            .then(data => {
                // Điền dữ liệu vào modal
                document.getElementById('userId').value = data.userId;
                document.getElementById('fullname').value = data.fullname;
                document.getElementById('email').value = data.email;
                document.getElementById('phone').value = data.phone;
                document.getElementById('street').value = data.street;
                document.getElementById('ward').value = data.ward;
                document.getElementById('district').value = data.district;
                document.getElementById('city').value = data.city;
                document.getElementById('country').value = data.country;
                document.getElementById('isAdmin').checked = data.isAdmin;

                document.getElementById('editUserModal').style.display = 'block';
            })
            .catch(error => {
                console.error("Error:", error);
            });
}


window.onclick = function (event) {
    var modal = document.getElementById('editUserModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
}

function closeModal() {
    document.getElementById('editUserModal').style.display = 'none';
}
