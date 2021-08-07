DROP TABLE fCe5HJjPF6.Shop;
CREATE TABLE Shop (
    shopID      varchar(6)   not null,
    dateCreated varchar(10)  not null,
    startHour   varchar(10)  not null,
    endHour     varchar(10)  not null,
    tel         varchar(10)   not null,
    address     varchar(200) not null,
    status      char(1),
    deliveryFee int     not null,
    primary key(shopID)
);

insert into fCe5HJjPF6.Shop (shopID, dateCreated, startHour, endHour, tel, address, deliveryFee, status) values ('S23417', '10/4/2012', '7:00am', '12:00pm', '088-995529', '03096 Merchant Point', 9, null);
insert into fCe5HJjPF6.Shop (shopID, dateCreated, startHour, endHour, tel, address, deliveryFee, status) values ('S21243', '10/27/2016', '8:00am', '12:00pm', '09-2967998', '776 Ridgeway Trail', 10, null);
insert into fCe5HJjPF6.Shop (shopID, dateCreated, startHour, endHour, tel, address, deliveryFee, status) values ('S25805', '5/30/2012', '8:00am', '10:00pm', '09-3723681', '4596 Esch Place', 22, null);
insert into fCe5HJjPF6.Shop (shopID, dateCreated, startHour, endHour, tel, address, deliveryFee, status) values ('S69999', '12/21/2019', '8:00am', '12:00pm', '08-9620638', '6090 Rutledge Place', 24, null);
insert into fCe5HJjPF6.Shop (shopID, dateCreated, startHour, endHour, tel, address, deliveryFee, status) values ('S22356', '2/27/2015', '7:00am', '10:00pm', '07-2709909', '3071 John Wall Crossing', 7, null);
