create table User_table
(
user_table_id serial not null primary key,
name varchar(255) not null,
surname varchar(255) not null
);

create table Receipt
(
receipt_id serial not null primary key,
date timestamp not null,
receipt_price decimal(8,2) not null,
user_table_id integer not null,
FOREIGN KEY(user_table_id) REFERENCES User_table(user_table_id) ON DELETE CASCADE
);

create table Category
(
category_id serial not null primary key,
category_name varchar(255) not null,
user_table_id integer not null,
FOREIGN KEY(user_table_id) REFERENCES User_table(user_table_id) ON DELETE CASCADE
);

create table Product
(
product_id serial not null primary key,
category_id integer not null,
product_name varchar(255) not null,
FOREIGN KEY (category_id) REFERENCES Category(category_id) ON DELETE CASCADE
);

create table Product_receipt
(
receipt_id integer not null,
product_id integer not null,
PRIMARY KEY (receipt_id,product_id),
FOREIGN KEY (receipt_id) REFERENCES Receipt(receipt_id) ON DELETE CASCADE,
FOREIGN KEY (product_id) REFERENCES Product(product_id) ON DELETE CASCADE
);


create table Price
(
price_id serial not null primary key,
product_price decimal(8,2) not null,
product_id integer not null,
receipt_id integer not null,
FOREIGN KEY (product_id) REFERENCES Product(product_id) ON DELETE CASCADE,
FOREIGN KEY (receipt_id) REFERENCES Receipt(receipt_id) ON DELETE CASCADE
);
