/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function showToast(message, type) {
    Toastify({
        text: message,
        duration: 3000,
        newWindow: true,
        stopOnFocus: true,
        gravity: "top",
        position: "center",
        style: {
            background: type === 'success' ? "#21A691" : "#EE4E4E",
            padding: "10px 40px",
            fontSize: "16px",
            borderRadius: "10px",
        },
    }).showToast();
}

function checkSubmitOrder(total) {
    let payment1 = document.querySelector('#payment-1')
    let payment2 = document.querySelector('#payment-2')
//    console.log(payment1);
//    console.log(payment2.checked);
    if (!payment1.checked && !payment2.checked) {
        showToast('Please choose a payment method!');
        return;
    }
    let confirmModal = document.getElementById("orderConfirmModal");
    let processingModal = document.querySelector(".processing");
    let confirmButton = document.getElementById("confirmOrder");

    let scrollY = window.scrollY;
    document.body.style.top = `-${scrollY}px`;

    document.body.classList.add("modal-open");
    confirmModal.style.display = "block";

    confirmButton.onclick = function () {
        confirmModal.style.display = "none";
        window.location.href = `Orders?total=${total}`;
        document.body.classList.remove("modal-open");
    };
}

function closeModal() {
    let body = document.body;
    let scrollY = body.style.top;

    body.classList.remove("modal-open");
    body.style.top = "";

    // Cuộn lại vị trí trước đó
    window.scrollTo(0, parseInt(scrollY || '0') * -1);
}

document.getElementById("cancelOrder").addEventListener("click", function () {
    document.getElementById("orderConfirmModal").style.display = "none";
    closeModal();
});

window.onclick = function (event) {
    let modal = document.getElementById("orderConfirmModal");
    if (event.target === modal) {
        modal.style.display = "none";
        closeModal();
    }
};