DELETE FROM Customer
WHERE customer_email IN (
    SELECT customer_email
    FROM Customer
    GROUP BY customer_email
    HAVING COUNT(customer_email) > 1
);

--SELECT * FROM CUSTOMER;