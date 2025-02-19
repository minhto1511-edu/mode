// Lấy giỏ hàng từ localStorage hoặc khởi tạo giỏ hàng trống nếu chưa có
let cart = JSON.parse(localStorage.getItem('cart')) || [];

// Hàm thêm món vào giỏ hàng
function addToCart(itemName, itemPrice) {
  // Kiểm tra nếu món ăn đã có trong giỏ hàng
  const existingItemIndex = cart.findIndex(cartItem => cartItem.name === itemName);
  
  if (existingItemIndex !== -1) {
    // Nếu món ăn đã tồn tại trong giỏ hàng, tăng số lượng
    cart[existingItemIndex].quantity += 1;
  } else {
    // Nếu món ăn chưa có trong giỏ hàng, thêm món mới vào
    cart.push({ name: itemName, price: itemPrice, quantity: 1 });
  }

  // Cập nhật giỏ hàng trong localStorage
  localStorage.setItem('cart', JSON.stringify(cart));

  alert(`${itemName} đã được thêm vào giỏ hàng!`);
}

// Lắng nghe sự kiện nhấp chuột trên các nút "Thêm vào giỏ hàng"
document.querySelectorAll('.add-to-cart').forEach(button => {
  button.addEventListener('click', function (event) {
    event.preventDefault();
    const itemName = this.getAttribute('data-item');
    const itemPrice = parseInt(this.getAttribute('data-price'));
    addToCart(itemName, itemPrice);
  });
});

// Hàm hiển thị giỏ hàng trong trang cart.html
function displayCart() {
  const cartTable = document.getElementById('cart-table-body');
  const totalPriceElement = document.getElementById('total-price');
  cartTable.innerHTML = ''; // Xóa nội dung cũ của bảng giỏ hàng

  let totalPrice = 0;
  cart.forEach(item => {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${item.name}</td>
      <td>${item.price} VND</td>
      <td>${item.quantity}</td>
      <td>${item.price * item.quantity} VND</td>
    `;
    cartTable.appendChild(row);

    // Tính tổng tiền
    totalPrice += item.price * item.quantity;
  });

  // Hiển thị tổng tiền
  totalPriceElement.textContent = `Tổng tiền: ${totalPrice} VND`;
}

// Hàm xóa toàn bộ giỏ hàng
function clearCart() {
  cart = [];
  localStorage.removeItem('cart');
  displayCart();
  alert('Giỏ hàng đã được xóa!');
}

// Kiểm tra nếu đang ở trang cart.html, hiển thị giỏ hàng
if (window.location.pathname.includes('cart.html')) {
  displayCart();
}

// Gắn sự kiện cho nút "Thanh toán"
document.getElementById('checkout-button')?.addEventListener('click', function () {
  if (cart.length > 0) {
    // Chuyển đổi giỏ hàng thành dữ liệu cần thiết để gửi đến quán
    console.log('Đơn hàng:', cart);
    alert('Đơn hàng của bạn đã được gửi!');
    clearCart(); // Xóa giỏ hàng sau khi thanh toán
  } else {
    alert('Giỏ hàng của bạn đang trống!');
  }
});

// Hiển thị giỏ hàng khi trang cart.html được tải
document.addEventListener('DOMContentLoaded', displayCart);
