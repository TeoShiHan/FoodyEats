DROP TABLE fCe5HJjPF6.Vehicle;
CREATE TABLE Vehicle (
    vehicleID  varchar(6)    not null,
    v_type       varchar(40)   not null,
	v_name       varchar(40)   not null,
    plateNo    varchar(15)   not null,
    color      varchar(15)   not null,
    primary key(vehicleID)
);
insert into fCe5HJjPF6.Vehicle (vehicleID, v_type, v_name, plateNo, color) values ('V24734', 'van', 'Nissan vannate', 'QLH 9472', 'blue');
insert into fCe5HJjPF6.Vehicle (vehicleID, v_type, v_name, plateNo, color) values ('V90154', 'motorcycle', 'Yamaha XMax 250', 'CPS 2550', 'blue');
insert into fCe5HJjPF6.Vehicle (vehicleID, v_type, v_name, plateNo, color) values ('V56211', 'vehicle', 'Perodua Myvi', 'KPJ 9018', 'white');
insert into fCe5HJjPF6.Vehicle (vehicleID, v_type, v_name, plateNo, color) values ('V80516', 'van', 'Toyota HIACE', 'CPR 1494', 'green');
insert into fCe5HJjPF6.Vehicle (vehicleID, v_type, v_name, plateNo, color) values ('V90147', 'vehicle', 'Perodua Myvi', 'EWU 1156', 'green');

