use testdb;
CREATE TABLE currency_exchange 
(
ID BIGINT NOT NULL AUTO_INCREMENT,
currency_from VARCHAR(20),
currency_to VARCHAR(20),
conversion_multiple DECIMAL,
PRIMARY KEY (ID)
);
insert into currency_exchange(currency_from, currency_to, conversion_multiple)
values
('USD', 'INR', 65),
('EUR', 'INR', 75),
('AUD', 'INR', 25),
('UAE', 'INR', 20);