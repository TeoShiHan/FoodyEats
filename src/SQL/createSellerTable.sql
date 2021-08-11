DROP TABLE fCe5HJjPF6.Seller;
CREATE TABLE Seller (
    sellerID      varchar(6)    not null,
    sellerName    varchar(40)   not null,
    address       varchar(200)  not null,
    state         varchar(30)    not null,
    NRIC          varchar(12)    not null,
    licenseNumber varchar(15)   not null,
    bankAcc       varchar(15)    not null,
    accountID     varchar(20)   not null,
    shopID        varchar(6)    not null,
    primary key(sellerID)      
);
insert into fCe5HJjPF6.Seller (sellerID, sellerName, address, state, NRIC, licenseNumber, bankAcc, accountID, shopID) values ('V28279', 'Leese De Bischof', '954 Autumn Leaf Parkway', 'Kedah', '146118696653', '991-xf-922', '6029389328', 'A1215', 'S21243');
insert into fCe5HJjPF6.Seller (sellerID, sellerName, address, state, NRIC, licenseNumber, bankAcc, accountID, shopID) values ('V05099', 'Jill Longmire', '638 Anhalt Park', 'Melaka', '449831545028', '687-rg-270', '3755480833', 'A8041', 'S22356');
insert into fCe5HJjPF6.Seller (sellerID, sellerName, address, state, NRIC, licenseNumber, bankAcc, accountID, shopID) values ('V67569', 'Nari Danilchenko', '59 Delaware Pass', 'Putrajaya', '054219315046', '161-dk-804', '1488716526', 'A7209', 'S23417');
insert into fCe5HJjPF6.Seller (sellerID, sellerName, address, state, NRIC, licenseNumber, bankAcc, accountID, shopID) values ('V88154', 'Virgilio Gyorgy', '32407 Carey Center', 'Selangor', '836215635972', '069-ke-556', '4249610033', 'A1071', 'S25805');
insert into fCe5HJjPF6.Seller (sellerID, sellerName, address, state, NRIC, licenseNumber, bankAcc, accountID, shopID) values ('V19935', 'Geri Pauly', '87 Lawn Pass', 'Terengganu', '404228572591', '586-be-445', '0076671684', 'A1248', 'S69999');

