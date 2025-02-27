/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
const MY_BANK = {
    CONTENT: 'ASTATINE'
};
const API_KEY = 'AK_CS.bf647410eea911efacfaab0600938aa6.mtWr04QyjhSG2YLIZY2wZTJboeVk5RhUFieUcHWAbwxPmoFQUmPO9ytNtr3bw0c33uJG4TiR';
const API_URL = 'https://oauth.casso.vn/v2/transactions';

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

let isSuccess = false;
let intervalId = null;

const handlePaid = (total) => {
    const paymentOnline = (amount) => {
        const checkedRadio = document.querySelector('input[name="payment"]:checked');
        const orderSubmit = document.querySelector('.order-submit');
        const paidMessage = document.querySelector('.paid-message');
        const imgQr = document.querySelector('.qr-code');

        if (checkedRadio && checkedRadio.id === "payment-2") {
            const desc = MY_BANK.CONTENT + randomUppercaseLetters();
            const QR = `https://api.vietqr.io/image/970422-8888331199372-BpFflbu.jpg?accountName=CAO%20TOAN%20THANG&amount=${amount}&addInfo=${desc}`;
            imgQr.src = QR;
            imgQr.style.display = "block";
            orderSubmit.style.pointerEvents = 'none';

            // Xóa interval cũ nếu tồn tại để tránh trùng lặp
            if (intervalId) {
                clearInterval(intervalId);
            }

            // Chờ 10 giây, sau đó kiểm tra giao dịch mỗi 1 giây
            setTimeout(() => {
                intervalId = setInterval(() => {
                    checkPaid(amount, desc, total);
                }, 1000);
            }, 20000);

            paidMessage.innerHTML = 'Please scan the QR code to pay the order!';
            paidMessage.style.color = 'red';
            paidMessage.style.textAlign = 'center';
        } else {
            // Dừng kiểm tra API nếu không chọn payment-2
            if (intervalId) {
                clearInterval(intervalId);
                intervalId = null; // Đặt lại intervalId để tránh chạy lại
            }
            isSuccess = false; // Reset trạng thái để có thể chạy lại nếu chọn payment-2 sau đó

            imgQr.src = "";
            imgQr.style.display = "none";
            orderSubmit.style.pointerEvents = 'auto';
            paidMessage.innerHTML = '';
        }
    };

    // Kiểm tra trạng thái ban đầu
    paymentOnline(total);

    document.querySelectorAll('input[name="payment"]').forEach((radio) => {
        radio.addEventListener("change", function () {
            paymentOnline(total);
        });
    });
};

const checkPaid = async (price, content, total) => {
    if (isSuccess) {
        if (intervalId)
            clearInterval(intervalId);
        return;
    }

    try {
        const response = await fetch(API_URL, {
            headers: {
                Authorization: `apikey ${API_KEY}`,
                "Content-Type": "application/json",
            },
        });

        if (response.status === 429) {
            console.warn("API bị giới hạn, đợi 30 giây trước khi thử lại...");
            clearInterval(intervalId); // Dừng kiểm tra để tránh spam API
            setTimeout(() => {
                intervalId = setInterval(() => checkPaid(price, content, total), 1000); // Kiểm tra lại sau 30 giây
            }, 10000);
            return;
        }

        const data = await response.json();

        if (data.data.records.length > 0) {
            const latestRecord = data.data.records[data.data.records.length - 1]; // Lấy giao dịch mới nhất
            const pricePaided = latestRecord.amount;
            const descPaided = latestRecord.description;
            console.log(data.data.records);
            console.log(pricePaided);
            console.log(descPaided);

            // Kiểm tra nếu thanh toán thành công
            if (pricePaided >= price && descPaided.includes(content)) {
                console.log("Thanh toán thành công!");
                window.location.href = `Orders?total=${total}`;
                isSuccess = true;
            }
        }
    } catch (e) {
        console.error(e);
    }
};

function randomUppercaseLetters(length = 6) {
    return Array.from({length}, () => String.fromCharCode(65 + Math.floor(Math.random() * 26))).join('');
}

