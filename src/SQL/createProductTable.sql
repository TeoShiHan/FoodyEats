DROP TABLE fCe5HJjPF6.Product;
CREATE TABLE Product (
    productID varchar(6)        not null,
    productName   varchar(40)   not null,
    productDesc   varchar(300),
    price         double        not null,    
    imgPath       varchar(250)  not null,
    category      varchar(15)   not null,
    shopID        varchar(6)    not null, 
    primary key(productID)
);