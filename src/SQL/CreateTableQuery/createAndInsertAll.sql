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

INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00001','asapwell0','lnREpL6LgFKh','Ashien Sapwell','asapwell0@google.de','019-2463861','2021-06-29','Admin');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00002','smcilwain1','oaBIVspCcjpi','Sindee McIlwain','smcilwain1@webnode.com','011-90350091','2020-11-15','Admin');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00003','egreave2','cOX8fn7kW9S','Emlyn Greave','egreave2@nih.gov','011-67993279','2021-07-24','Admin');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00004','emuzzlewhite3','Qnos353','Erinna Muzzlewhite','emuzzlewhite3@stanford.edu','011-88009293','2020-12-14','Admin');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00005','bmcilvenna4','CfzStbusvKI','Burty McIlvenna','bmcilvenna4@google.ru','011-78414198','2021-05-12','Rider');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00006','ede5','Gw9L4jSr','Elyssa De Bruin','ede5@mayoclinic.com','011-81200835','2020-09-10','Rider');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00007','srowbottom6','FE9LA2Oc2NNQ','Shawn Rowbottom','srowbottom6@edublogs.org','018-6255410','2020-11-30','Rider');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00008','cwharby7','4Osmm1','Cthrine Wharby','cwharby7@si.edu','019-4827608','2021-06-01','Rider');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00009','cborless8','gbr2dy2WE','Cyndia Borless','cborless8@so-net.ne.jp','011-56407553','2020-09-12','Seller');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00010','abottleson9','xYdJVX','Adams Bottleson','abottleson9@umn.edu','019-8874285','2021-05-28','Seller');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00011','ctackella','STwkMzPVRgx','Claudette Tackell','ctackella@illinois.edu','011-89627670','2021-01-06','Seller');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00012','oelionb','RjLUBPFWUQ','Olga Elion','oelionb@answers.com','019-9650966','2021-02-26','Seller');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00013','bvanc','n5vmJRxLMPL2','Benita Van Baaren','bvanc@cdc.gov','010-4099028','2021-03-13','Buyer');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00014','ksteadmand','JQuOZUXKbV','Kelly Steadman','ksteadmand@upenn.edu','011-58350495','2020-09-27','Buyer');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00015','choltawaye','Mgbacj','Ciel Holtaway','choltawaye@hubpages.com','011-20715333','2021-06-05','Buyer');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00016','cmathesf','aKujyqyXd0W','Channa Mathes','cmathesf@meetup.com','019-8160881','2021-06-02','Buyer');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00017','hrushfordg','vfmsePecqP','Hubert Rushford','hrushfordg@godaddy.com','011-88895892','2021-03-28','Buyer');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00018','dcabrerh','Y60njqQN','Donetta Cabrer','dcabrerh@google.pl','011-41678451','2020-10-30','Buyer');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00019','rdickeyi','EyIhVcMRSi','Rhianon Dickey','rdickeyi@squarespace.com','018-4887934','2021-02-17','Buyer');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00020','knovacekj','Z30B2VZ','Kalle Novacek','knovacekj@sun.com','011-48349406','2021-07-20','Buyer');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00021','bellertonk','fWPrWQJ98','Babara Ellerton','bellertonk@artisteer.com','018-6806133','2021-07-07','Buyer');
INSERT INTO foodyeats.Account(accountID,username,password,name,email,mobileNo,regDate,type) VALUES ('A00022','rguthersonl','nKdWenYHi7','Retha Gutherson','rguthersonl@auda.org.au','011-96577712','2021-03-23','Buyer');


INSERT INTO foodyeats.Admin(adminID,NRIC,companyBranch,accountID) VALUES ('M00001','439829-42-2997','699-eff-673','A00001');
INSERT INTO foodyeats.Admin(adminID,NRIC,companyBranch,accountID) VALUES ('M00002','624860-08-7443','174-rmg-489','A00002');
INSERT INTO foodyeats.Admin(adminID,NRIC,companyBranch,accountID) VALUES ('M00003','569576-67-4816','779-guk-588','A00003');
INSERT INTO foodyeats.Admin(adminID,NRIC,companyBranch,accountID) VALUES ('M00004','137067-11-7105','435-aon-821','A00004');

INSERT INTO foodyeats.Rider(riderID,accountID,vehicleID,status) VALUES ('R00001','A00005','V00001',0);
INSERT INTO foodyeats.Rider(riderID,accountID,vehicleID,status) VALUES ('R00002','A00006','V00002',0);
INSERT INTO foodyeats.Rider(riderID,accountID,vehicleID,status) VALUES ('R00003','A00007','V00003',1);
INSERT INTO foodyeats.Rider(riderID,accountID,vehicleID,status) VALUES ('R00004','A00008','V00004',1);

INSERT INTO foodyeats.Buyer(buyerID,address,accountID,cartID) VALUES ('B00001','5 Arkansas Way,Terengganu','A00013','C00001');
INSERT INTO foodyeats.Buyer(buyerID,address,accountID,cartID) VALUES ('B00002','15130 Sutherland Court,Kuala Lumpur','A00014','C00002');
INSERT INTO foodyeats.Buyer(buyerID,address,accountID,cartID) VALUES ('B00003','8972 Hazelcrest Way,Kedah','A00015','C00003');
INSERT INTO foodyeats.Buyer(buyerID,address,accountID,cartID) VALUES ('B00004','35 Colorado Hill,Terengganu','A00016','C00004');
INSERT INTO foodyeats.Buyer(buyerID,address,accountID,cartID) VALUES ('B00005','7225 Schmedeman Pass,Putrajaya','A00017','C00005');
INSERT INTO foodyeats.Buyer(buyerID,address,accountID,cartID) VALUES ('B00006','845 Lien Center,Sabah','A00018','C00006');
INSERT INTO foodyeats.Buyer(buyerID,address,accountID,cartID) VALUES ('B00007','84284 Riverside Park,Negeri Sembilan','A00019','C00007');
INSERT INTO foodyeats.Buyer(buyerID,address,accountID,cartID) VALUES ('B00008','544 Kennedy Avenue,Sarawak','A00020','C00008');
INSERT INTO foodyeats.Buyer(buyerID,address,accountID,cartID) VALUES ('B00009','3517 Garrison Way,Terengganu','A00021','C00009');
INSERT INTO foodyeats.Buyer(buyerID,address,accountID,cartID) VALUES ('B00010','741 Chinook Circle,Johor','A00022','C00010');

INSERT INTO foodyeats.Seller(sellerID,address,NRIC,licenseNumber,bankAcc,accountID,shopID,status) VALUES ('V00001','657 Golf View Way,Sabah','588596-59-8672','497-jw-131',3373892514,'A00009','S00001',1);
INSERT INTO foodyeats.Seller(sellerID,address,NRIC,licenseNumber,bankAcc,accountID,shopID,status) VALUES ('V00002','6 Lien Park,Putrajaya','910501-15-1828','061-ca-195',8979989761,'A00010','S00002',1);
INSERT INTO foodyeats.Seller(sellerID,address,NRIC,licenseNumber,bankAcc,accountID,shopID,status) VALUES ('V00003','86777 Farwell Place,Melaka','470326-24-5291','556-tj-991',7943194770,'A00011','S00003',0);
INSERT INTO foodyeats.Seller(sellerID,address,NRIC,licenseNumber,bankAcc,accountID,shopID,status) VALUES ('V00004','803 Blue Bill Park Park,Sarawak','312827-30-7341','333-ba-924',5885948386,'A00012','S00004',0);

INSERT INTO foodyeats.Cart(cartID,buyerID,shopID) VALUES ('C00001','B00001','S00001');
INSERT INTO foodyeats.Cart(cartID,buyerID,shopID) VALUES ('C00002','B00002','S00002');
INSERT INTO foodyeats.Cart(cartID,buyerID,shopID) VALUES ('C00003','B00003','S00003');
INSERT INTO foodyeats.Cart(cartID,buyerID,shopID) VALUES ('C00004','B00004','S00004');
INSERT INTO foodyeats.Cart(cartID,buyerID,shopID) VALUES ('C00005','B00005','S00001');
INSERT INTO foodyeats.Cart(cartID,buyerID,shopID) VALUES ('C00006','B00006','S00002');
INSERT INTO foodyeats.Cart(cartID,buyerID,shopID) VALUES ('C00007','B00007','S00003');
INSERT INTO foodyeats.Cart(cartID,buyerID,shopID) VALUES ('C00008','B00008','S00004');
INSERT INTO foodyeats.Cart(cartID,buyerID,shopID) VALUES ('C00009','B00009','S00001');
INSERT INTO foodyeats.Cart(cartID,buyerID,shopID) VALUES ('C00010','B00010','S00002');

INSERT INTO foodyeats.CartItem(cartID,foodID,quantity) VALUES ('C00001','F00009',2);
INSERT INTO foodyeats.CartItem(cartID,foodID,quantity) VALUES ('C00002','F00019',3);
INSERT INTO foodyeats.CartItem(cartID,foodID,quantity) VALUES ('C00003','F00029',3);
INSERT INTO foodyeats.CartItem(cartID,foodID,quantity) VALUES ('C00004','F00039',1);
INSERT INTO foodyeats.CartItem(cartID,foodID,quantity) VALUES ('C00005','F00009',1);
INSERT INTO foodyeats.CartItem(cartID,foodID,quantity) VALUES ('C00006','F00019',2);
INSERT INTO foodyeats.CartItem(cartID,foodID,quantity) VALUES ('C00007','F00029',2);
INSERT INTO foodyeats.CartItem(cartID,foodID,quantity) VALUES ('C00008','F00039',3);
INSERT INTO foodyeats.CartItem(cartID,foodID,quantity) VALUES ('C00009','F00019',2);
INSERT INTO foodyeats.CartItem(cartID,foodID,quantity) VALUES ('C00010','F00005',1);
INSERT INTO foodyeats.CartItem(cartID,foodID,quantity) VALUES ('C00010','F00006',1);
INSERT INTO foodyeats.CartItem(cartID,foodID,quantity) VALUES ('C00010','F00007',1);

INSERT INTO foodyeats.Vehicle(vehicleID,type,plateNo,brand,model,color) VALUES ('V00001','Car','QAB0001','Proton','Wira','Black');
INSERT INTO foodyeats.Vehicle(vehicleID,type,plateNo,brand,model,color) VALUES ('V00002','Van','QAB0002','Toyota','Hiace','White');
INSERT INTO foodyeats.Vehicle(vehicleID,type,plateNo,brand,model,color) VALUES ('V00003','Car','QAB0003','Perodua','Myvi','Blue');
INSERT INTO foodyeats.Vehicle(vehicleID,type,plateNo,brand,model,color) VALUES ('V00004','Motorcycle','QAB0004','Yamaha','ZX-003','Red');

INSERT INTO foodyeats.Shop(shopID,shopName,dateCreated,startHour,endHour,tel,address,deliveryFee,status,imgPath) VALUES ('S00001','McDonalds','2021-08-06','08:00','22:00','06-0918533','363 Erie Street,Perlis',10,1,'/Images/S00001.png');
INSERT INTO foodyeats.Shop(shopID,shopName,dateCreated,startHour,endHour,tel,address,deliveryFee,status,imgPath) VALUES ('S00002','Top Spot Seafood','2021-01-29','08:00','22:00','09-0269230','84 Clarendon Point,Perak',15,1,'/Images/S00002.png');
INSERT INTO foodyeats.Shop(shopID,shopName,dateCreated,startHour,endHour,tel,address,deliveryFee,status,imgPath) VALUES ('S00003','Lepau','2021-08-07','08:00','22:00','09-9445866','826 Eliot Road,Perak',13,0,'/Images/S00003.png');
INSERT INTO foodyeats.Shop(shopID,shopName,dateCreated,startHour,endHour,tel,address,deliveryFee,status,imgPath) VALUES ('S00004','Knowher restaurant','2021-01-20','04:00','22:00','09-8846389','9617 Express Terrace,Johor',10,0,'/Images/S00004.png');
INSERT INTO foodyeats.Shop(shopID,shopName,dateCreated,startHour,endHour,tel,address,deliveryFee,status,imgPath) VALUES ('S00005','Jin Change seafood','2021-07-22','05:00','22:00','06-9470925','73 Maple Wood Hill,Terengganu',15,0,'/Images/S00005.png');

INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00001','Rockin? BBQ Beef','Rockin'' BBQ burger, featuring BBQ sauce, fresh red capsicums, grilled onions, fresh lettuce, ...','/Images/F00001.png',14.5,'Burger','S00001');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00002','Double Rockin? BBQ Beef','The burger does actually smell like a summer BBQ dish, all smokey hints of grilled vegetables.','/Images/F00002.png',7.3,'Burger','S00001');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00003','Triple Rockin? BBQ Beef','The new Rockin'' BBQ Beef and Chicken Burgers are going to rock your taste buds!','/Images/F00003.png',22.64,'Burger','S00001');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00004','Coca-Cola','the world''s most popular and biggest-selling soft drink in history.','/Images/F00004.png',4.53,'Drinks','S00001');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00005','100 Plus','100 Plus Isotonic Drink, sport drinks','/Images/F00005.png',4.53,'Drinks','S00001');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00006','Sundae Ice-cream','Cold, refreshing and creamy','/Images/F00006.png',3.9,'Dessert','S00001');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00007','Apple Pie','Sweet, baked, full with apple filling','/Images/F00007.png',3.8,'Dessert','S00001');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00008','French fries','Crispy, Salty and crunchy','/Images/F00008.png',4.5,'Snacks','S00001');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00009','Tomyam Seafood Soup','Spicy and sour with different varieties of seafood inside','/Images/F00009.png',9,'Soup','S00002');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00010','Mixed Vegetables','Carrots, green beans, cauliflower, brocolli and mushroom','/Images/F00010.png',8,'Vegetable','S00002');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00011','Butter Prawn','Buttery, Salty, Sweet, Spciy and garlicky','/Images/F00011.png',18,'Seafood','S00002');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00012','Steam Tilapia','Fresh, mild taste, suit for elderly and childen because Tilapia without small thorns','/Images/F00012.png',25,'Seafood','S00002');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00013','Nestum Chicken','Nestum-coated chicken that tender on the inside, crispy on the outside','/Images/F00013.png',15,'Meat','S00002');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00014','Salted Egg Yolk Crab','Family favourite, crab in creamy and spicy sauce','/Images/F00014.png',85,'Seafood','S00002');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00015','Chilies Crab','Stir-fried carb coated with sweet savory and spicy tomato-based sauce','/Images/F00015.png',85,'Seafood','S00002');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00016','Signature Beancurd','Tasty homemade, soft Tofu dish','/Images/F00016.png',8.5,'Tofu','S00002');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00017','Pansuh Chicken','Cooking chicken meat in a bamboo stalk and  full of tasty chicken soup','/Images/F00017.png',13,'Meat','S00003');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00018','Terung Asam Chicken Soup','Spicy, match with shrimp paste and sour flavour','/Images/F00018.png',7,'Soup','S00003');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00019','Calamari Fern Salad','Fern, squid and addicting seasonings','/Images/F00019.png',11,'Salad','S00003');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00020','Sarawak Chili Pepper Beef','Stir-fry wih beef, black pepper, chilies, onion and other Sarawak seasonings','/Images/F00020.png',14,'Meat','S00003');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00021','Sambal Tempe Ikan Bilis','Yummy, crunchy bits of ikan bilis, tempe slices and crisp potatoes','/Images/F00021.png',9.5,'Seafood','S00003');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00022','Smoked Fish','Kampung fish cured with smoking, unique taste and flavour','/Images/F00022.png',15,'Seafood','S00003');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00023','Fried Sweet Potato Leaves','Simple, delicious and healthy','/Images/F00023.png',7.9,'Vegetable','S00003');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00024','Steamed Brown Barlo Rice','Nutty flavour and delicious','/Images/F00024.png',2,'Rice','S00004');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00025','Mashed Potato','Salty, yummy and feel little fullness after having it','/Images/F00025.png',4,'Snacks','S00004');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00026','Briyani Sambal Goreng','Mixed rice dish that originating among the Muslims of the Indian, and matched with Indian spices','/Images/F00026.png',7.9,'Rice','S00004');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00027','Aglio Olio','Italian pasta include olive oil, garlic and cheese','/Images/F00027.png',11,'Noodles','S00004');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00028','Sarawak Kolok Mee','Dried light yellow egg noodle served with char siew(pork) or chicken','/Images/F00028.png',7,'Noodles','S00004');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00029','Asam laksa','Spicy, sour with full of fish flakes','/Images/F00029.png',8,'Noodles','S00004');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00030','Sushi Mempelam','Mango rolled up in pulut and doused in a delicious, rich santan sauce','/Images/F00030.png',9,'Snacks','S00004');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00031','Cone Ichi Kabin','Deep fried chicken wrapped in crispy popiah cone','/Images/F00031.png',9,'Snacks','S00004');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00032','Pizza Tempeh Teratai','Lotus root, hibiscus, tempeh, spicy tomato-sambal base','/Images/F00032.png',16,'Snacks','S00004');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00033','Sweet Sour Pork Rice','Pork coated with sweet sour paste','/Images/F00033.png',8,'Rice','S00005');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00034','Mongolian Ribs Rice','Ribs meat that cooked with soya sauce, chinese huangjiu and little chilies.','/Images/F00034.png',8,'Rice','S00005');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00035','Ginger Scallion Chicken Rice','Chicken meat cooked with ginger, onion and other flavouring','/Images/F00035.png',8,'Rice','S00005');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00036','Curry Chicken Rice','Delicious curry flavour with potato, and carrots','/Images/F00036.png',8,'Rice','S00005');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00037','Creamy Fish Fillet Rice','Crispy fish fillet coated with creamy paste','/Images/F00037.png',9,'Rice','S00005');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00038','Poached Egg','Fried egg','/Images/F00038.png',1,'Rice','S00005');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00039','Sin Chew Bee Hoon','Stir-fried bee hoon with vegetables and some meat','/Images/F00039.png',6.5,'Noodles','S00005');
INSERT INTO foodyeats.Food(foodID,foodName,foodDesc,imgPath,price,category,shopID) VALUES ('F00040','Fried Kway Teow','Stir-fried flat noodles with lard and prawn','/Images/F00040.png',6.5,'Noodles','S00005');

INSERT INTO foodyeats.`Order` (orderID, status, dateCreated, timeCreated, buyerID,riderID,shopID,paymentID,reviewID) VALUES ('O00001', 'Pending', '2021-09-07', '13:10:33','B00001', '', 'S00001', 'P00001', '' );
INSERT INTO foodyeats.`Order` (orderID, status, dateCreated, timeCreated, buyerID,riderID,shopID,paymentID,reviewID) VALUES ('O00002', 'Seller Accepted', '2021-09-07', '13:10:33','B00001', '', 'S00002', 'P00002', '');
INSERT INTO foodyeats.`Order` (orderID, status, dateCreated, timeCreated, buyerID,riderID,shopID,paymentID,reviewID) VALUES ('O00003', 'Seller Declined', '2021-09-07', '13:10:33','B00002', '', 'S00002', 'P00003', '');
INSERT INTO foodyeats.`Order` (orderID, status, dateCreated, timeCreated, buyerID,riderID,shopID,paymentID,reviewID) VALUES ('O00004', 'Rider Accepted', '2021-09-07', '13:10:33','B00003', 'R00001', 'S00003', 'P00004', '');
INSERT INTO foodyeats.`Order` (orderID, status, dateCreated, timeCreated, buyerID,riderID,shopID,paymentID,reviewID) VALUES ('O00005', 'Seller Ready', '2021-09-07', '13:10:33','B00004', 'R00002', 'S00002', 'P00005', '');
INSERT INTO foodyeats.`Order` (orderID, status, dateCreated, timeCreated, buyerID,riderID,shopID,paymentID,reviewID) VALUES ('O00006', 'Rider Collected', '2021-09-07', '13:10:33','B00005', 'R00003', 'S00004', 'P00006', '');
INSERT INTO foodyeats.`Order` (orderID, status, dateCreated, timeCreated, buyerID,riderID,shopID,paymentID,reviewID) VALUES ('O00007', 'Completed', '2021-09-07', '13:10:33','B00006', 'R00004', 'S00003', 'P00007','');
INSERT INTO foodyeats.`Order` (orderID, status, dateCreated, timeCreated, buyerID,riderID,shopID,paymentID,reviewID) VALUES ('O00008', 'Completed', '2021-09-07', '13:10:33','B00007', 'R00003', 'S00001', 'P00007', 'R00001');
INSERT INTO foodyeats.`Order` (orderID, status, dateCreated, timeCreated, buyerID,riderID,shopID,paymentID,reviewID) VALUES ('O00009', 'Completed', '2021-09-07', '13:10:33','B00008', 'R00002', 'S00005', 'P00009', '');
INSERT INTO foodyeats.`Order` (orderID, status, dateCreated, timeCreated, buyerID,riderID,shopID,paymentID,reviewID) VALUES ('O00010', 'Completed', '2021-09-07', '13:10:33','B00010', 'R00002', 'S00001', 'P00010', 'R00002');
INSERT INTO foodyeats.`Order` (orderID, status, dateCreated, timeCreated, buyerID,riderID,shopID,paymentID,reviewID) VALUES ('O00011', 'Completed', '2021-09-07', '13:10:33','B00003', 'R00002', 'S00003', 'P00011', 'R00003');
INSERT INTO foodyeats.`Order` (orderID, status, dateCreated, timeCreated, buyerID,riderID,shopID,paymentID,reviewID) VALUES ('O00012', 'Completed', '2021-09-07', '13:10:33','B00002', 'R00003', 'S00001', 'P00012', 'R00004');
INSERT INTO foodyeats.`Order` (orderID, status, dateCreated, timeCreated, buyerID,riderID,shopID,paymentID,reviewID) VALUES ('O00013', 'Completed', '2021-09-07', '13:10:33','B00006', 'R00001', 'S00002', 'P00013', 'R00005');

INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00001', 'F00001',1);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00001', 'F00002',2);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00002', 'F00009',8);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00002', 'F00016',4);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00003', 'F00012',4);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00003', 'F00015',2);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00004', 'F00017',2);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00004', 'F00023',2);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00005', 'F00010',2);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00005', 'F00011',2);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00006', 'F00024',3);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00006', 'F00032',1);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00007', 'F00019',2);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00007', 'F00022',2);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00008', 'F00004',2);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00008', 'F00008',1);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00009', 'F00040',5);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00010', 'F00003',2);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00010', 'F00007',3);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00010', 'F00008',2);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00011', 'F00020',1);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00012', 'F00001',2);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00012', 'F00008',1);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00013', 'F00013',8);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00013', 'F00014',6);
INSERT INTO foodyeats.`OrderItem` (orderID, foodID, quantity) VALUES ('O00013', 'F00015',4);

INSERT INTO foodyeats.`Payment` (paymentID, method, orderID) VALUES ('P00001', 'Credit/Debit Card', 'O00001');
INSERT INTO foodyeats.`Payment` (paymentID, method, orderID) VALUES ('P00002', 'Credit/Debit Card', 'O00002');
INSERT INTO foodyeats.`Payment` (paymentID, method, orderID) VALUES ('P00003', 'Credit/Debit Card', 'O00003');
INSERT INTO foodyeats.`Payment` (paymentID, method, orderID) VALUES ('P00004', 'Credit/Debit Card', 'O00004');
INSERT INTO foodyeats.`Payment` (paymentID, method, orderID) VALUES ('P00005', 'Credit/Debit Card', 'O00005');
INSERT INTO foodyeats.`Payment` (paymentID, method, orderID) VALUES ('P00006', 'Online Banking', 'O00006');
INSERT INTO foodyeats.`Payment` (paymentID, method, orderID) VALUES ('P00007', 'Online Banking', 'O00007');
INSERT INTO foodyeats.`Payment` (paymentID, method, orderID) VALUES ('P00008', 'Online Banking', 'O00008');
INSERT INTO foodyeats.`Payment` (paymentID, method, orderID) VALUES ('P00009', 'Online Banking', 'O00009');
INSERT INTO foodyeats.`Payment` (paymentID, method, orderID) VALUES ('P00010', 'Online Banking', 'O00010');
INSERT INTO foodyeats.`Payment` (paymentID, method, orderID) VALUES ('P00011', 'Credit/Debit Card', 'O00011');
INSERT INTO foodyeats.`Payment` (paymentID, method, orderID) VALUES ('P00012', 'Online Banking', 'O00012');
INSERT INTO foodyeats.`Payment` (paymentID, method, orderID) VALUES ('P00013', 'Online Banking', 'O00013');

INSERT INTO foodyeats.`Review` (reviewID, dateCreated, timeCreated, rating,comment,orderID,shopID) VALUES ('R00001', '2021-09-07','13:20:50',4,'A bit oily but very good','O00008','S00001');
INSERT INTO foodyeats.`Review` (reviewID, dateCreated, timeCreated, rating,comment,orderID,shopID) VALUES ('R00002', '2021-09-07','13:20:50',4,'A bit too salty for me. But overall it is delicious.','O00010','S00001');
INSERT INTO foodyeats.`Review` (reviewID, dateCreated, timeCreated, rating,comment,orderID,shopID) VALUES ('R00003', '2021-09-07','13:20:50',1,'I didn’t get what I ordered. Ordered Char Kuey Teow with extra clam (lid extra off course) and ended up getting a mediocre plain one WITHOUT clam! No kerang, totally disappointed. This order made me determined to make my own kuey teow grng, tq but no thanks!','O00011','S00002');
INSERT INTO foodyeats.`Review` (reviewID, dateCreated, timeCreated, rating,comment,orderID,shopID) VALUES ('R00004', '2021-09-07','13:20:50',3,'Yes the address given to the driver was different . I wonder why as I put in my house address and it was a hassle to get them to deliver to the address stated as I was unable to send a location since I was not at home','O00012','S00002');
INSERT INTO foodyeats.`Review` (reviewID, dateCreated, timeCreated, rating,comment,orderID,shopID) VALUES ('R00005', '2021-09-07','13:20:50',5,'Sedap nye... first time makan takoyaki yg ltak cheese... uoollsss should try...','O00013','S00003');