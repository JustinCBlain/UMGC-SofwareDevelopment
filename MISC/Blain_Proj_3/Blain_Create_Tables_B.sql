--set date format
ALTER SESSION SET NLS_DATE_FORMAT='YYYY-MM-DD';

--create tables
CREATE TABLE Customer ( 
    phone_number NUMERIC NOT NULL PRIMARY KEY,
    purchase_history CLOB,
    f_name VARCHAR(15),
    l_name VARCHAR(15),
    address VARCHAR(50));

CREATE TABLE Fee (
    fee_ID VARCHAR(5) NOT NULL PRIMARY KEY,
    price FLOAT NOT NULL);
    
CREATE TABLE Distributor (
    distributor_ID VARCHAR(5) NOT NULL PRIMARY KEY,
    dist_name VARCHAR(50),
    discount VARCHAR(50));
      
CREATE TABLE Catalog (
    issue VARCHAR(5) NOT NULL PRIMARY KEY,
    distributor_ID VARCHAR(5) NOT NULL REFERENCES Distributor(distributor_ID),
    movies CLOB NOT NULL);

CREATE TABLE Award (
    award_ID VARCHAR(15) PRIMARY KEY,
    nominee VARCHAR(15));

CREATE TABLE Sale (
    sale_ID VARCHAR(10) NOT NULL PRIMARY KEY,
    genre VARCHAR(15),
    discount FLOAT NOT NULL);

CREATE TABLE Movie (
    shop_ID VARCHAR(10) NOT NULL PRIMARY KEY,
    title VARCHAR(50),
    catalog_ID VARCHAR(5) NOT NULL REFERENCES Catalog(issue),
    sale_ID VARCHAR(10) REFERENCES Sale(sale_ID),
    genre VARCHAR(15),
    talent CLOB,
    run_time INT NOT NULL ,
    rating INT,
    release_date DATE NOT NULL,
    director VARCHAR(25),
    award_ID VARCHAR(15) REFERENCES Award(award_ID));
    
CREATE TABLE Item (
    item_ID VARCHAR(10) NOT NULL PRIMARY KEY,
    distributor_ID VARCHAR(5) NOT NULL REFERENCES Distributor(distributor_ID),
    movie_ID VARCHAR(15) NOT NULL REFERENCES Movie(shop_ID),
    price FLOAT,
    media VARCHAR(3) NOT NULL);
    
CREATE TABLE Invoices (
    invoice_ID INT NOT NULL PRIMARY KEY,
    customer_ID INT NOT NULL REFERENCES Customer(phone_number),
    total_price FLOAT NOT NULL,
    rental_price FLOAT NOT NULL,
    rented_on DATE NOT NULL,
    fee_ID VARCHAR(5) REFERENCES Fee(fee_ID),
    due_date  DATE NOT NULL,
    rewound INT NOT NULL,
    damaged INT NOT NULL,
    late INT NOT NULL);
    
CREATE TABLE Purchase_Order (
    po_ID INT PRIMARY KEY NOT NULL,
    distributor_ID VARCHAR(5) NOT NULL REFERENCES Distributor(distributor_ID),
    total_price FLOAT NOT NULL,
    movie_ID VARCHAR(15) REFERENCES Movie(shop_ID),
    quantity INT);