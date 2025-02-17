/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

export function showToast(message, type) {
    Toastify({
        text: message,
        duration: 3000,
        destination: "http://localhost:8080/Astatine/Checkout",
        newWindow: true,
        close: true,
        stopOnFocus: true,
        gravity: "top",
        position: "center",
        style: {
            background: type === 'success' ? "#21A691" : "#EE4E4E",
            padding: "10px 30px",
            cursor: "pointer"
        },
        onclick: function(){}
    }).showToast();
}

