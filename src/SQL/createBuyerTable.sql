DROP TABLE fCe5HJjPF6.Buyer;
CREATE TABLE Buyer (
    buyerID   varchar(6)    not null,
    address   varchar(200)  not null,
    accountID varchar(20)   not null,
    cartID    varchar(6)    not null,
    primary key(buyerID)
);

insert into fCe5HJjPF6.Buyer (buyerID, address, accountID, cartID) values ('B34615', '56 Sloan Avenue', 'A5450', 'C02979');
insert into fCe5HJjPF6.Buyer (buyerID, address, accountID, cartID) values ('B58481', '8278 Petterle Road', 'A6483', 'C72831');
insert into fCe5HJjPF6.Buyer (buyerID, address, accountID, cartID) values ('B18585', '13 Mitchell Trail', 'A5331', 'C60430');
insert into fCe5HJjPF6.Buyer (buyerID, address, accountID, cartID) values ('B66575', '48623 Ryan Way', 'A4464', 'C35628');
insert into fCe5HJjPF6.Buyer (buyerID, address, accountID, cartID) values ('B91986', '4 Bunker Hill Street', 'A3524', 'C66089');
