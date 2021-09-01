CREATE TABLE Payment (
     paymentID varchar(6) not null,
     method    varchar(10) not null,
     orderID   varchar(6) not null,
     primary key(paymentID)
); 