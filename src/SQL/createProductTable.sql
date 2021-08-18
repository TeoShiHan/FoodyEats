
CREATE TABLE Food (
    foodID varchar(6)        not null,
	foodName   varchar(40)   not null,
    foodDesc   varchar(300),
    imgPath    varchar(300) not null,
    price         double        not null,    
    category      varchar(15)   not null,
    shopID        varchar(6)    not null, 
    primary key(foodID)
);