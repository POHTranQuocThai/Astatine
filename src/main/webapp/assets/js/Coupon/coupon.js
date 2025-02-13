let isOpen = false;

const handleCoupon = () => {
    const couponContainer = document.querySelector('.coupon');
    if (couponContainer) {
        isOpen = !isOpen; // Đảo ngược trạng thái
    }
    // Kiểm tra nếu conponDiv đã tồn tại không
    let conponDiv = document.querySelector('.coupon-option'); // Sử dụng class để tìm kiếm

    if (isOpen) {
        // Nếu conponDiv không tồn tại, tạo mới
        if (!conponDiv) {
            conponDiv = document.createElement('div');
            conponDiv.classList.add('coupon-option'); // Thêm class để dễ dàng tìm kiếm sau này
            // Thêm vào couponContainer
        }

        conponDiv.style.display = "block"; // Hiển thị phần tử
        conponDiv.style.marginTop = "10px"; // Hiển thị phần tử
        conponDiv.innerHTML = ` <!-- Thêm nội dung HTML vào phần tử -->
           <div class="coupon">
        <div class="coupon-left">
          <img alt="Astatine logo" height="50" src="/img/logo.png" width="50" />
          <div>Astatine</div>
        </div>
        <div class="coupon-right">
          <div class="coupon-right-numbers">
            <span>Giảm 20%</span>
          </div>
          <div class="expired">Dùng ngay</div>
          <div class="conditions"><span>Điều kiện</span>
            <div class="conditions-content">
                <p>Áp dụng cho khách hàng lần đầu đặt hàng</p>
                <p>Áp dụng cho khách hàng lần đầu đặt hàng</p>
            </div>
          </div>
        </div>
      </div>
        `;
    } else {
        if (conponDiv) {
            conponDiv.style.display = "none"; // Ẩn phần tử
            conponDiv.innerHTML = ""; // Xóa nội dung khi ẩn

        }
    }
};
