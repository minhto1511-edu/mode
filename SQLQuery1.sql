CREATE DATABASE RestaurantDB
GO
USE RestaurantDB
go
-- 1. Tạo bảng tables
CREATE TABLE tables (
    table_id INT IDENTITY(1,1) PRIMARY KEY,   -- Mã bàn tự tăng
    table_number INT NOT NULL,                -- Số bàn
    status VARCHAR(50) DEFAULT 'available',  -- Trạng thái bàn (available, occupied)
    capacity INT NOT NULL                     -- Sức chứa
);

-- 2. Tạo bảng users
CREATE TABLE users (
    user_id INT IDENTITY(1,1) PRIMARY KEY,    -- Mã user tự tăng
    username VARCHAR(50) NOT NULL,           -- Tên đăng nhập
    password VARCHAR(100) NOT NULL,          -- Mật khẩu
    role NVARCHAR(50) CHECK (role IN ('admin', 'staff')), -- Chỉ admin và staff
    full_name NVARCHAR(100) NOT NULL,         -- Họ tên
    email VARCHAR(100),                      -- Email
    phone VARCHAR(20),                       -- Số điện thoại
    created_at DATETIME DEFAULT GETDATE()     -- Thời gian tạo tài khoản
);

-- 3. Tạo bảng categories
CREATE TABLE categories (
    category_id INT IDENTITY(1,1) PRIMARY KEY,  -- Mã loại món tự tăng
    category_name NVARCHAR(100) NOT NULL        -- Tên loại món
);

-- 4. Tạo bảng menu_items
CREATE TABLE menu_items (
    item_id INT IDENTITY(1,1) PRIMARY KEY,      -- Mã món ăn tự tăng
    item_name NVARCHAR(100) NOT NULL,           -- Tên món ăn
    item_description NVARCHAR(255),             -- Mô tả món ăn
    price DECIMAL(19, 0) NOT NULL,              -- Giá món ăn
    category_id INT,                            -- Loại món (khóa ngoại)
    image_url VARCHAR(255),                    -- URL ảnh món ăn
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

-- 5. Tạo bảng orders (Đã xóa cột customer_id)
CREATE TABLE orders (
    order_id INT IDENTITY(1,1) PRIMARY KEY,     -- Mã đơn hàng tự tăng
    table_id INT NOT NULL,                      -- Liên kết với bàn (khóa ngoại)
    order_status NVARCHAR(50),                  -- Trạng thái đơn hàng (unpaid, paid)
    created_at DATETIME DEFAULT GETDATE(),      -- Thời gian tạo đơn hàng
    total_amount DECIMAL(19, 0),                -- Tổng tiền đơn hàng
    FOREIGN KEY (table_id) REFERENCES tables(table_id)
);

-- 6. Tạo bảng order_items
CREATE TABLE order_items (
    order_item_id INT IDENTITY(1,1) PRIMARY KEY, -- Mã món ăn trong đơn hàng tự tăng
    order_id INT NOT NULL,                       -- Liên kết với đơn hàng (khóa ngoại)
    item_id INT NOT NULL,                        -- Liên kết với món ăn (khóa ngoại)
    quantity INT NOT NULL,                       -- Số lượng món
    item_status NVARCHAR(50) DEFAULT 'unaccepted',  -- Trạng thái món (unaccepted, pending, preparing, served)
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (item_id) REFERENCES menu_items(item_id)
);

-- 7. Tạo bảng customer_requests
CREATE TABLE customer_requests (
    request_id INT IDENTITY(1,1) PRIMARY KEY,   -- Mã yêu cầu tự tăng
    order_item_id INT NOT NULL,                 -- Liên kết với món ăn trong đơn hàng
    request_time DATETIME DEFAULT GETDATE(),    -- Thời gian yêu cầu
    status NVARCHAR(50) DEFAULT 'pending',      -- Trạng thái yêu cầu (pending, completed)
    FOREIGN KEY (order_item_id) REFERENCES order_items(order_item_id)
);

-- 8. Tạo bảng bills
CREATE TABLE bills (
    bill_id INT IDENTITY(1,1) PRIMARY KEY,      -- Mã hóa đơn tự tăng
    order_id INT NOT NULL,                      -- Liên kết với đơn hàng
    bill_time DATETIME DEFAULT GETDATE(),       -- Thời gian xuất hóa đơn
    total_amount DECIMAL(19, 2) NOT NULL,       -- Tổng tiền
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);
INSERT INTO categories (category_name)
VALUES 
(N'Bún'),
(N'Phở'),
(N'Bánh mì'),
(N'Đồ uống');

INSERT INTO menu_items (item_name, item_description, price, category_id, image_url) VALUES 
(N'Bún bò Huế', N'Món bún bò với hương vị đậm đà, cay đặc trưng của Huế', 50000, 1, 'images/bunbohue.png'), 
(N'Bún chả', N'Bún chả Hà Nội với thịt nướng thơm ngon', 45000, 1, 'images/buncha.png'), 
(N'Bún riêu cua', N'Món bún riêu với nước dùng từ cua đồng', 40000, 1, 'images/bunrieu.png'),
(N'Phở bò tái', N'Phở bò với thịt tái và nước dùng trong', 60000, 2, 'images/phobotai.png'), 
(N'Phở gà', N'Phở gà truyền thống với thịt gà mềm', 55000, 2, 'images/phoga.png'), 
(N'Phở bò sốt vang', N'Phở bò với thịt bò sốt vang', 70000, 2, 'images/phobosotvang.png'),
(N'Bánh mì thịt nướng', N'Bánh mì kẹp thịt nướng với rau và nước sốt', 20000, 3, 'images/banhmithitnuong.png'), 
(N'Bánh mì xíu mại', N'Bánh mì kẹp xíu mại với nước sốt đặc biệt', 18000, 3, 'images/banhmixiumai.png'), 
(N'Bánh mì chả cá', N'Bánh mì kẹp chả cá nóng giòn', 22000, 3, 'images/banhmichaca.png'),
(N'Cà phê sữa đá', N'Cà phê sữa đá truyền thống Việt Nam', 25000, 4, 'images/caphesuada.png'), 
(N'Trà sữa', N'Trà sữa với nhiều hương vị khác nhau', 30000, 4, 'images/trasua.png'), 
(N'Nước ép cam', N'Nước ép cam tươi ngon, giàu vitamin C', 35000, 4, 'images/nuocep.png');

INSERT INTO users (username, password, role, full_name, email, phone)
VALUES
    ('admin1', '123', 'admin', N'Nguyễn Văn A', 'admin1@example.com', '0901234567'),
    ('staff1', '123', 'staff', N'Trần Thị B', 'staff1@example.com', '0912345678'),
    ('staff2', '123', 'staff', N'Lê Văn C', 'staff2@example.com', '0923456789');

insert into tables (table_number, capacity) values
(1, 4),
(2, 6),
(3, 2),
(4, 8);

INSERT INTO orders (table_id, order_status, total_amount) 
VALUES 
(1, 'paid', 95000), 
(2, 'paid', 120000), 
(3, 'paid', 80000), 
(4, 'paid', 150000);

INSERT INTO order_items (order_id, item_id, quantity, item_status) 
VALUES 
(15, 1, 2, 'served'),  -- Đơn hàng 1 có 2 phần Bún bò Huế
(15, 4, 1, 'served'),  -- Đơn hàng 1 có 1 phần Phở bò tái
(16, 6, 1, 'served'),-- Đơn hàng 2 có 1 phần Phở bò sốt vang
(16, 11, 2, 'served'),-- Đơn hàng 2 có 2 phần Trà sữa
(17, 7, 1, 'served'),   -- Đơn hàng 3 có 1 phần Bánh mì thịt nướng
(17, 12, 1, 'served'),  -- Đơn hàng 3 có 1 phần Nước ép cam
(18, 2, 2, 'served'), -- Đơn hàng 4 có 2 phần Bún chả
(18, 9, 1, 'served'); -- Đơn hàng 4 có 1 phần Bánh mì chả cá
