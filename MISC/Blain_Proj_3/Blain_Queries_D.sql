SELECT f_name,l_name,phone_number,address FROM Customer ORDER BY phone_number;

SELECT * FROM Invoices
WHERE rented_on BETWEEN '2023/03/24' AND '2023/04/24'
ORDER BY rented_on;

SELECT * FROM Distributor ORDER BY dist_name;

UPDATE Customer
SET l_name = 'Doe'
WHERE phone_number = 3345678901;
SELECT * FROM Customer ORDER BY l_name;

DELETE FROM Customer 
WHERE phone_number = 1678901234;
SELECT * FROM Customer;
