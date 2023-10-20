--SELECT * FROM CUSTOMER;
--SELECT * FROM UPI;
DELETE FROM Upi
WHERE upi_id IN (
    SELECT upi_id
    FROM Upi
    GROUP BY upi_id
    HAVING COUNT(upi_id) > 1
);

DELETE upi Upi WHERE upi.customer_id = 3;