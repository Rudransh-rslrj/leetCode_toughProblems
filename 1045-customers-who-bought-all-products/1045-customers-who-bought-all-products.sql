# Write your MySQL query statement below
-- select customer_id
-- from Customer t1
-- right join Product t2
-- on t1.product_key=t2.product_key
-- group by customer_id
-- having count(distinct t2.product_key)=(select count(distinct product_key) from 
-- Customer)  ;


SELECT customer_id
FROM Customer
GROUP BY customer_id
HAVING COUNT(DISTINCT product_key) = (
    SELECT COUNT(*)
    FROM Product
);