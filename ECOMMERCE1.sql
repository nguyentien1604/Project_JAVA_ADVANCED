DROP DATABASE ECOMMERCE1;
CREATE DATABASE ECOMMERCE1;
USE ECOMMERCE1;

DROP TABLE IF EXISTS ORDERDETAIL;
DROP TABLE IF EXISTS PRODUCT;
DROP TABLE IF EXISTS `ORDER`;
DROP TABLE IF EXISTS CATEGORY;
DROP TABLE IF EXISTS `Account`;
DROP TABLE IF EXISTS SUPPLIER;
CREATE TABLE SUPPLIER
(
SUPPLIERID TINYINT AUTO_INCREMENT PRIMARY KEY,
SUPPLIERNAME VARCHAR(50) UNIQUE NOT NULL
);
CREATE TABLE CATEGORY
(
CATEGORYID TINYINT AUTO_INCREMENT PRIMARY KEY,
CATEGORYNAME VARCHAR(100)
);
CREATE TABLE PRODUCT
(
PRODUCTID TINYINT AUTO_INCREMENT PRIMARY KEY,
PRODUCTNAME VARCHAR (50) NOT NULL,
CATEGORYID TINYINT NOT NULL references CATEGORY(CATEGORYID),
SUPPLIERID TINYINT NOT NULL REFERENCES SUPPLIER(SUPPLIERID),
UNITQUANTITY TINYINT not null,
UnitPrice DECIMAL(10, 2) NOT NULL,
`DESCRIPTION` VARCHAR(255),
RATING TINYINT NOT NULL,
PICTURE VARCHAR(255),
`TYPE` varchar(50) 
);
drop table if exists  `ORDER`;
CREATE TABLE `ORDER`
(
	ORDERID TINYINT AUTO_INCREMENT PRIMARY KEY,
	PICTURE VARCHAR(255),
    AccountID TINYINT NOT NULL REFERENCES `Account`(AccountID),  
    PRODUCTID TINYINT NOT NULL REFERENCES PRODUCT(PRODUCTID),
   	orderDate date,
     FOREIGN KEY (PRODUCTID) REFERENCES PRODUCT(PRODUCTID),
      FOREIGN KEY (AccountID) REFERENCES `Account`(AccountID)
);
drop table if exists  ORDERDETAIL;
CREATE TABLE ORDERDETAIL (
    ORDERID TINYINT NOT NULL,
    PRODUCTID TINYINT NOT NULL,
    PICTURE VARCHAR(255),
    unitPrice DECIMAL(10, 2),
    Quantity INT,
    Discount INT,
    PRIMARY KEY (ORDERID, PRODUCTID),
    FOREIGN KEY (ORDERID) REFERENCES `ORDER`(ORDERID),
    FOREIGN KEY (PRODUCTID) REFERENCES PRODUCT(PRODUCTID)
);

DROP TABLE CUSTOMER;
CREATE TABLE `Account`
(
AccountID TINYINT AUTO_INCREMENT PRIMARY KEY,
USERNAME VARCHAR(100) NOT NULL,
`PASSWORD` VARCHAR(100) NOT NULL,
FULLNAME nvarchar(100) NOT NULL,
EMAIL VARCHAR(100) NOT NULL,
phone  VARCHAR(50) UNIQUE KEY NOT NULL,
Address nvarchar(255) not null,
CreateDate           DATETIME DEFAULT NOW(),
city nvarchar(255),
country nvarchar(255)
);

INSERT INTO CATEGORY (CATEGORYNAME) VALUES
    ('Điện Thoại'),
    ('LapTop');
    
INSERT INTO SUPPLIER (SUPPLIERNAME)       
VALUES ("Apple"),("SamSung"),("Xiaomi"),("Dell"),("Lenovo");
    
INSERT INTO PRODUCT (PRODUCTNAME,CATEGORYID,SUPPLIERID,UNITQUANTITY,UnitPrice,`DESCRIPTION`,RATING,PICTURE,`TYPE`)
VALUES 	("Iphone 11 128gb",1,1,1,11990000,"Máy, Cây lấy sim, Cáp, Sách hướng dẫn",5,"iphone11128gb.jpg","Top Selling"),
		("Iphone 12 64gb",1,1,1,15990000,"Máy, Cây lấy sim, Cáp, Sách hướng dẫn",4,"iphone1264gb.jpg","null"),
		("Iphone 14 plus 128gb",1,1,1,24990000,"Máy, Cây lấy sim, Cáp, Sách hướng dẫn",5,"iphone14plus.jpg","Top Selling"),
        ("Iphone 14 Pro max 128gb",1,1,1,26450000,"Máy, Cây lấy sim, Cáp, Sách hướng dẫn",5,"iphone14promax.jpg","Top Selling"),
        ("Samsung S23 128gb",1,2,1,21990000,"Samsung Galaxy S23 5G 256GB là chiếc điện thoại thuộc dòng cao cấp nhất của Samsung được giới thiệu vào tháng 02/2023",5,"samsungs23128.jpg","null"),
        ("Samsung S23 ultra 128gb",1,2,1,26990000,"Samsung Galaxy S23 ultra 5G 256GB là chiếc điện thoại thuộc dòng cao cấp nhất của Samsung được giới thiệu vào tháng 02/2023",5,"samsungs23ultra.jpg","Top Selling"),
        ("Samsung S21 FR 5G 128gb",1,2,1,9990000,"Samsung Galaxy S21 FE 5G (6GB/128GB) được Samsung ra mắt với dáng dấp thời thượng, cấu hình khỏe, chụp ảnh đẹp với bộ 3 camera chất lượng",5,"samsungs21fr5g129gb.jpg","null"),
        ("Samsung A73 128gb 5G",1,2,1,11340000,"Samsung Galaxy A73 5G 128GB được xem là sản phẩm nổi bật nhất dòng Galaxy A 2022 mới ra mắt",4,"samsungA73128gb.jpg","Top Selling"),
        ("Samsung Fold 256gb",1,2,1,23990000,"Samsung không chỉ là một flaship mà còn là một tác phẩm nghệ thuật",4,"samsunggap.jpg","null"),
        ("Samsung Z Flip 4 128gb",1,2,1,19990000,"Samsung Galaxy Z Flip4 128GB đã chính thức ra mắt thị trường công nghệ",4,"samsungzlip4.jpg","null"),
        ("Xiaomi 12T 128gb",1,3,1,10490000,"Xiaomi 12T 5G 256GB là smartphone đầu tiên trên thế giới trang bị con chip Dimensity 8100 Ultra",5,"xiaomi12T5G.jpg","Top Selling"),
        ("Xiaomi 13",1,3,1,17490000,"Xiaomi 13 5G - chiếc điện thoại đáng chú ý trên thị trường với nhiều điểm nâng cấp ấn tượng trong phân khúc giá",5,"xiaomi13.jpg","null"),
        ("Xiaomi Redmi Note 12 8GB 128gb",1,3,1,5290000,"Xiaomi Redmi Note 12 8GB/128GB - ĐỘC QUYỀN là mẫu điện thoại tầm trung vừa được ra mắt tại thị trường Việt Nam",4,"XiaomiRedminote12125gb.jpg","null");

INSERT INTO `Account` (USERNAME, `PASSWORD`, FULLNAME, EMAIL, PHONE, ADDRESS,CreateDate, CITY, COUNTRY)
VALUES ('Admin', '987654321', 'Admin', 'admin@gmail.com', '0932957799', '13 To Dang, Phuong ABC, Quan 1',"2022-09-15", 'TP.HCM', 'Vietnam'),
	   ('Username1', '@username1', 'Username1', 'username1@gmail.com', '098763452', '15 quan 1',"2023-04-15", 'TP.HCM', 'Vietnam');


        
SELECT*FROM PRODUCT;      
delete  from  Product where productid=12;
SELECT*FROM `Account`;  
SELECT*FROM SUPPLIER;  
DELETE FROM  PRODUCT where CATEGORYID=17;



