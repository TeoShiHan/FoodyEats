DROP TABLE FoodyEats.Account;
DROP TABLE FoodyEats.Buyer;
DROP TABLE FoodyEats.Rider;
DROP TABLE FoodyEats.Seller;
DROP TABLE FoodyEats.Admin;
DROP TABLE FoodyEats.Order;
DROP TABLE FoodyEats.OrderItem;
DROP TABLE FoodyEats.Vehicle;
DROP TABLE FoodyEats.Shop;
DROP TABLE FoodyEats.Food;
DROP TABLE FoodyEats.Payment;
DROP TABLE FoodyEats.Cart;
DROP TABLE FoodyEats.Review;
DROP TABLE FoodyEats.CartItem;

CREATE TABLE FoodyEats.Account (
    accountID     varchar(6)  	not null,
    username      varchar(15) 	not null,
    password      varchar(40)   not null,
    name          varchar(100)  not null,
	email         varchar(250),
    mobileNo      varchar(20)   not null,
    regDate      varchar(15)   not null,
    type       varchar(20) 	not null,
    primary key(accountID)
);

CREATE TABLE FoodyEats.Buyer (
    buyerID   varchar(6)    not null,
    address   varchar(200)  not null,
    accountID varchar(20)   not null,
    cartID    varchar(6)    not null,
    primary key(buyerID)
);

CREATE TABLE FoodyEats.Rider (
    riderID      varchar(6)    not null,
    accountID    varchar(20)   not null,
    vehicleID    varchar(6)    not null,
	status       tinyint 	   not null,
    primary key(riderID)
);

CREATE TABLE FoodyEats.Seller (
    sellerID      varchar(6)    not null,
    address       varchar(200)  not null,
    NRIC          varchar(30)    not null,
    licenseNumber varchar(15)   not null,
    bankAcc       varchar(15)    not null,
    accountID     varchar(20)   not null,
    shopID        varchar(6)    not null,
	status        tinyint    not null,
    primary key(sellerID)      
);

CREATE TABLE FoodyEats.Admin (
     adminID 		varchar(6) not null,
	 NRIC    		varchar(20)not null,
	 companyBranch  varchar(100)not null,
	 accountID 		varchar(6) not null,
	 primary key(adminID)
);

CREATE TABLE FoodyEats.Order (
    orderID     varchar(6)   not null,
    dateCreated varchar(20)  not null,
	timeCreated varchar(20),
    status 		varchar(15)  not null,
    buyerID     varchar(6)   not null,
    riderID     varchar(6), 
    shopID      varchar(6)   not null,
    paymentID   varchar(6)   not null,
	reviewID    varchar(6),
    primary key(orderID)
);

CREATE TABLE FoodyEats.OrderItem (
    orderID   varchar(6) not null,
    foodID varchar(6)    not null,
    quantity  int    	 not null,
    primary key(orderID, foodID)
);

CREATE TABLE FoodyEats.Vehicle (
    vehicleID  varchar(6)    not null,
    type       varchar(40)   not null,
    plateNo    varchar(15),
	brand      varchar(90),
	model      varchar(90),
    color      varchar(15),
    primary key(vehicleID)
);

CREATE TABLE FoodyEats.Shop (
    shopID      varchar(6)   not null,
    shopName    varchar(40)  not null,
    dateCreated varchar(10)  not null,
    startHour   varchar(10)  not null,
    endHour     varchar(10)  not null,
    tel         varchar(10)   not null,
    address     varchar(200) not null,
	deliveryFee double     not null,
    status      tinyint,
    imgPath     varchar(200) not null,
    primary key(shopID)
);

CREATE TABLE FoodyEats.Food (
    foodID 	   varchar(6)    not null,
	foodName   varchar(40)   not null,
    foodDesc   varchar(300),
    imgPath    varchar(300) not null,
    price      double        not null,    
    category   varchar(15)   not null,
    shopID     varchar(6)    not null, 
    primary key(foodID)
);

CREATE TABLE FoodyEats.Payment (
     paymentID varchar(6) not null,
     method    varchar(20) not null,
     orderID   varchar(6) not null,
     primary key(paymentID)
);

CREATE TABLE FoodyEats.Cart (
    cartID     varchar(6) not null,
    buyerID    varchar(6),
    shopID     varchar(6),
    primary key(cartID)
);

CREATE TABLE FoodyEats.Review (
    reviewID 	varchar(6) not null,
	dateCreated varchar(20),
	timeCreated varchar(20),
	rating      int,
	comment     varchar(300),
	orderID     varchar(6),
	shopID      varchar(6),
    primary key(reviewID)
);

CREATE TABLE FoodyEats.CartItem (
    cartID varchar(6) not null,
	foodID varchar(6) not null,
	quantity int      not null,
    primary key(cartID,foodID)
);