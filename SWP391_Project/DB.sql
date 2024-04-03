
use swp_demo;
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    display_name VARCHAR(255) CHARACTER SET UTF8MB4,
    is_admin bit DEFAULT FALSE, 
    is_verify bit default false,
    is_active bit DEFAULT False, 
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table Category (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) CHARACTER SET UTF8MB4,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	is_delete bit
);

Create table Product (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) CHARACTER SET UTF8MB4,
    price float(10,2),
    categoryID int,
    
    /*FOREIGN KEY (categoryID) REFERENCES category(id),*/
    
    description VARCHAR(255) CHARACTER SET UTF8MB4,
    image1 varchar(500),
    image2 varchar(500),
    image3 varchar(500),
    image4 varchar(500),
    
	transaction_Fees bit,
    contact_Method VARCHAR(255) CHARACTER SET UTF8MB4,
    create_by int,
    /*FOREIGN KEY (create_by) REFERENCES users(id),*/
    hidden_content varchar(255),
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by int,
    
    /*FOREIGN KEY (updated_by) REFERENCES users(id),*/
    
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_delete bit
);


create table intermediate_Orders(
	id INT PRIMARY KEY AUTO_INCREMENT,
    code varchar(255),
    productID int,
    buyer_id int,
	total_received_amount float(10,2),
    total_paid_amount float(10,2),
	intermediary_fee float(10,2),
    status VARCHAR(255) CHARACTER SET UTF8MB4,
    create_by int,
    /*FOREIGN KEY (create_by) REFERENCES users(id),*/
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by int,
    /*FOREIGN KEY (updated_by) REFERENCES users(id),*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_delete bit
	
);

CREATE TABLE Order_Detail (
    id INT PRIMARY KEY AUTO_INCREMENT,
    orderID INT,
    /* FOREIGN KEY (orderID) REFERENCES Intermediate_Orders(id) */
    productID INT,
    /* FOREIGN KEY (productID) REFERENCES Product(id) */
	price float(10,2),
    /* FOREIGN KEY (user_id) REFERENCES Users(id) */
    
    seller_name VARCHAR(255) CHARACTER SET UTF8MB4,
    buyer_name VARCHAR(255) CHARACTER SET UTF8MB4,
    product_name VARCHAR(255) CHARACTER SET UTF8MB4,
    create_by int,
    /*FOREIGN KEY (create_by) REFERENCES users(id),*/
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by int,
    /*FOREIGN KEY (updated_by) REFERENCES users(id),*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_delete bit
);


create table Report(
	id INT PRIMARY KEY AUTO_INCREMENT,
    type_report int,
    orderID int,
    recivedID int,
    status bit,
    description VARCHAR(255) CHARACTER SET UTF8MB4,
    create_by int,
    /*FOREIGN KEY (create_by) REFERENCES users(id),*/
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by int,
    /*FOREIGN KEY (updated_by) REFERENCES users(id),*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_delete bit
);

CREATE EVENT delete_old_report_data
ON SCHEDULE
EVERY 5 MONTH
DO
    DELETE FROM Report WHERE create_at < NOW() - INTERVAL 5 MONTH;

create table Wallet (
	id INT PRIMARY KEY AUTO_INCREMENT,
    balance float(10,2),
    create_by int,
    /*FOREIGN KEY (create_by) REFERENCES users(id),*/
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_by int,
    /*FOREIGN KEY (updated_by) REFERENCES users(id),*/
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



CREATE TABLE feedback (
    id INT PRIMARY KEY auto_increment,
    title VARCHAR(100),
    content VARCHAR(500),
    create_at DATETIME,
    user_id INT,
    intermediary_order_id INT,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    paymentCode varchar(100),
    product_id INT,
    status VARCHAR(50)
);

CREATE TABLE deposit (
  id int NOT NULL,
  wallet_id int DEFAULT NULL,
  amount decimal(10,0) DEFAULT NULL,
  description varchar(500) DEFAULT NULL,
  create_datetime datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table vnpay_Transaction (
id int not null auto_increment,
-- 3 status: pending, fail, success
Status varchar(20),
Wallet_id int,
Payment_code varchar(100),
Time text,
Description text,
BankCode varchar(10),
primary key(id)
);

create table order_history(
	id INT PRIMARY KEY AUTO_INCREMENT,
	orderID int,
	order_status varchar(255),
	description varchar(255),
	create_by int,
	create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table History_Transaction(
  id INT PRIMARY KEY auto_increment,
Money_Transaction double,
Transaction_Type char(1) check(Transaction_Type IN('+','-')) Not Null,
Status boolean,
Note varchar(200),
Create_by int,
Creat_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
nguoinhan int,
Update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);  

CREATE TABLE withdrawals (
    id INT AUTO_INCREMENT PRIMARY KEY,
    withdrawal_code VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    amount DECIMAL(18, 2) NOT NULL,
    account_number VARCHAR(50) NOT NULL,
    account_holder VARCHAR(255) NOT NULL,
    bank_name VARCHAR(255) NOT NULL,
    bank_branch VARCHAR(255) NOT NULL,
    response VARCHAR(255),
    created_by INT NOT NULL,
    updated_by INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

create table cart(
	id INT PRIMARY KEY AUTO_INCREMENT,
	userID int,
	productID int
);

alter table History_Transaction
add foreign key (Create_by) references users(id);

INSERT INTO swp_demo.feedback (id, title, content, create_at, user_id, intermediary_order_id)
VALUES (1, 'Feedback Title', 'Feedback Content', '2024-02-08 12:00:00', 1, 1);
INSERT INTO swp_demo.feedback (id, title, content, create_at, user_id, intermediary_order_id)
VALUES (2, 'huehuehue', 'quaxinhgai', '2024-02-07 12:00:00', 1, 1);



drop table Report

alter table Report
add foreign key (orderID) references intermediate_Orders(id);
alter table Report
add foreign key (create_by) references users(id);
alter table Report
add foreign key (updated_by) references users(id);

alter table Wallet
add foreign key (create_by) references users(id);
alter table Wallet
add foreign key (updated_by) references users(id);

alter table Product
add foreign key (categoryID) references Category(id);
alter table Product
add foreign key (create_by) references users(id);
alter table Product
add foreign key (updated_by) references users(id);

alter table intermediate_Orders
add foreign key (productID) references Product(id);
alter table intermediate_Orders
add foreign key (create_by) references users(id);
alter table intermediate_Orders
add foreign key (updated_by) references users(id);

alter table Order_Detail
add foreign key (orderID) references intermediate_Orders(id);
alter table Order_Detail
add foreign key (productID) references Product(id);
alter table Order_Detail
add foreign key (create_by) references users(id);
alter table Order_Detail
add foreign key (updated_by) references users(id);

INSERT INTO Category (name, is_delete) VALUES ('Account', 0);
INSERT INTO Category (name, is_delete) VALUES ('Quizlet', 0);
INSERT INTO Category (name, is_delete) VALUES ('Netflix', 0);


INSERT INTO users (username, password, email, display_name, is_admin, is_verify, is_active) 
VALUES ('bach', 'UPdqztViNgyMw0QyGVTpe0ud+dw=', 'example@example.com', 'Example User', 1, 1, 1);

INSERT INTO users (username, password, email, display_name, is_admin, is_verify, is_active) 
VALUES ('bach2', 'UPdqztViNgyMw0QyGVTpe0ud+dw=', 'example@example.com', 'buyer', 0, 1, 1);

INSERT INTO users (username, password, email, display_name, is_admin, is_verify, is_active) 
VALUES ('bach3', 'UPdqztViNgyMw0QyGVTpe0ud+dw=', 'example@example.com', 'cus', 0, 1, 1);

INSERT INTO users (username, password, email, display_name, is_admin, is_verify, is_active) 
VALUES ('bach4', 'UPdqztViNgyMw0QyGVTpe0ud+dw=', 'example@example.com', 'cus2', 0, 1, 1);

UPDATE Wallet SET balance = 5000000 WHERE create_by = 2
INSERT INTO Wallet (balance, create_by, updated_by)
VALUES (500000.00, 4, 4)
       
       
update  users 
set email = 'bachnvse@gmail.com'
where id = 1


delete from swp_demo.product where product.id = 5;
delete from swp_demo.intermediate_Orders where product.id = 5;
delete  from swp_demo.Order_Detail
delete from Wallet
delete from transactions
delete from withdrawals where
delete from users where id = 5
delete from Wallet where create_by = 5
select * from Category
select * from Product where is_delete = true
select * from users
select * from intermediate_Orders where productID = 2
select * from swp_demo.Order_Detail
select * from Wallet where create_by = 5
select * from Report 
select *  from transactions
select * from withdrawals
 -- //1 là khiếu nại
--             //2 là mua hàng
--             //3 là đăng đơn hàng
--             //4 là hoàn tất mua hàng
--             //5 là nạp tiền
--             //6 là rút tiền

SELECT p.*
FROM Product p
INNER JOIN intermediate_Orders io ON p.id = io.productID
WHERE io.status = 'Người mua đang kiểm tra đơn hàng';

select * from report where create_by = 3
SELECT * FROM Report where create_by = 3 ORDER BY id LIMIT 3 OFFSET 1 ;

SELECT * FROM Report where recivedID = 1 || create_by = 1 ORDER BY id desc LIMIT 3 OFFSET 0;
