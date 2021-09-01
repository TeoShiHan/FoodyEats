CREATE TABLE cus_order (
    orderID     varchar(6)   not null,
    dateCreated varchar(10)  not null,
    orderStatus varchar(15)  not null,
    buyerID     varchar(6)   not null,
    riderID     varchar(6), 
    shopID      varchar(6)   not null,
    paymentID   varchar(6)   not null,
    primary key(orderID)
);