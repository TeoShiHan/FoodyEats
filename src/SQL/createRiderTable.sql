DROP TABLE fCe5HJjPF6.Rider;
CREATE TABLE Rider (
    riderID    varchar(6)    not null,
    riderName       varchar(40)   not null,
    accountID  varchar(20)   not null,
    vehicleID varchar(6)    not null,
    primary key(riderID)
);

insert into fCe5HJjPF6.Rider (riderID, riderName, accountID, vehicleID) values ('R80097', 'Alexine Jearum', 'A5450', 'V44668');
insert into fCe5HJjPF6.Rider (riderID, riderName, accountID, vehicleID) values ('R81368', 'Eileen Spilsted', 'A6483', 'V76296');
insert into fCe5HJjPF6.Rider (riderID, riderName, accountID, vehicleID) values ('R01959', 'Rodrique Lindstrom', 'A5331', 'V87974');
insert into fCe5HJjPF6.Rider (riderID, riderName, accountID, vehicleID) values ('R67991', 'Findley Echlin', 'A4464', 'V94554');
insert into fCe5HJjPF6.Rider (riderID, riderName, accountID, vehicleID) values ('R00974', 'Ashely O''Lagen', 'A3524', 'V95929');

