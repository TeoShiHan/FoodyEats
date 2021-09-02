DROP TABLE fCe5HJjPF6.Shop;
CREATE TABLE Shop (
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

insert into fCe5HJjPF6.Shop values('S00001', 'Mc Donalds', '10/4/2012', '7:00am', '12:00pm', '088-995529', '03096 Merchant Point', 9, 0, '../Images/S00001.png');
insert into fCe5HJjPF6.Shop values('S00002', 'Top Spot Seafood', '10/27/2016', '8:00am', '12:00pm', '09-2967998', '776 Ridgeway Trail', 10, 0, '../Images/S00002.jpg');
insert into fCe5HJjPF6.Shop values('S00003', 'Lepau', '5/30/2012', '8:00am', '10:00pm', '09-3723681', '4596 Esch Place', 22, 0, '../Images/S00003.jpg');
insert into fCe5HJjPF6.Shop values('S00004', 'Knowhere Bangsar', '12/21/2019', '8:00am', '12:00pm', '08-9620638', '6090 Rutledge Place', 24, 0, '../Images/S00004.JPG');
insert into fCe5HJjPF6.Shop values('S00005', 'Kim Cheong Restaurant', '2/27/2015', '7:00am', '10:00pm', '07-2709909', '3071 John Wall Crossing', 7, 0, '../Images/S00005.jpg');
