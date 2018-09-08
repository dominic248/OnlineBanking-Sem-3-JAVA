
create table if not exists AccountInfo(
	ID integer NOT NULL UNIQUE,
	Name text NOT NULL,
	Usernames text NOT NULL UNIQUE,
	Passwords text NOT NULL,
	DOB text NOT NULL,
	Age integer NOT NULL,
	Address text NOT NULL,
	Citys text NOT NULL,
	States text NOT NULL,
	Countrys text NOT NULL,
	Pincode integer NOT NULL,
	Phone text NOT NULL,
	Email text NOT NULL,
	primary key(`ID`)
);
insert or ignore into AccountInfo(ID,Name,Username,Passwords,DOB,Age,Address,Citys,States,Countrys,Pincode,Phone,Email) VALUES (1,'Dominic','dms24','123','24/08/1999',19,'Nav','Thane','Maharashtra','India',401107,'+919594183245','dms24081999@gmail.com');

