BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `withdraw` (
	`waid`	INTEGER NOT NULL,
	`wAmount`	INTEGER NOT NULL,
	`wDate`	TEXT NOT NULL,
	`op`	text NOT NULL DEFAULT 'Withdrawed',
	FOREIGN KEY(`waid`) REFERENCES `accounts`(`acc_no`) on delete cascade on update cascade
);


CREATE TABLE IF NOT EXISTS `users` (
	`uid`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`name`	TEXT NOT NULL,
	`username`	TEXT NOT NULL,
	`password`	TEXT NOT NULL,
	`address`	TEXT NOT NULL,
	`email`	TEXT NOT NULL,
	`mobile`	TEXT NOT NULL,
	`uDate`	TEXT NOT NULL,
	`uImage`	blob
);

CREATE TABLE IF NOT EXISTS `transfer` (
	`tAccno`	INTEGER NOT NULL,
	`tToAccno`	INTEGER NOT NULL,
	`tAmount`	INTEGER NOT NULL,
	`op`	text NOT NULL DEFAULT 'Transfered',
	`tDate`	TEXT NOT NULL,
	FOREIGN KEY(`tAccno`) REFERENCES `accounts`(`acc_no`) on delete cascade on update cascade
);
CREATE TABLE IF NOT EXISTS `received` (
	`rAccno`	INTEGER NOT NULL,
	`rFromAccno`	INTEGER NOT NULL,
	`rAmount`	INTEGER NOT NULL,
	`op`	text NOT NULL DEFAULT 'Received',
	`rDate`		TEXT NOT NULL,
	FOREIGN KEY (rAccno) REFERENCES accounts(acc_no) on delete cascade on update cascade
);
drop table received;

CREATE TABLE IF NOT EXISTS `deposit` (
	`daid`	INTEGER NOT NULL,
	`dAmount`	INTEGER NOT NULL,
	`dDate`	TEXT NOT NULL,
	`op`	text NOT NULL DEFAULT 'Deposited',
	FOREIGN KEY(`daid`) REFERENCES `accounts`(`acc_no`) on delete cascade on update cascade
);


CREATE TABLE IF NOT EXISTS `activity` (
	`aid`	INTEGER NOT NULL,
	`aDate`	TEXT NOT NULL,
	`aType`	TEXT NOT NULL,
	FOREIGN KEY(`aid`) REFERENCES `users`(`uid`) on delete cascade on update cascade
);

CREATE TABLE IF NOT EXISTS `accounts` (
	`acc_no`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`acc_id`	INTEGER NOT NULL,
	`acc_type`	TEXT NOT NULL,
	`acc_details`	TEXT,
	`acc_amount`	INTEGER NOT NULL,
	`acc_date`	TEXT NOT NULL,
	FOREIGN KEY(`acc_id`) REFERENCES `users`(`uid`) on delete cascade on update cascade
);
select aType,aDate from activity where aid=16;

COMMIT;
