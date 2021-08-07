CREATE TABLE OrderDetails (
    orderID   varchar(6)   not null,
    productID varchar(6)   not null,
    quantity  int    not null,
    primary key(orderID, productID)
);