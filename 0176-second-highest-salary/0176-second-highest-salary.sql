# Write your MySQL query statement below
SELECT Max(salary) AS SecondHighestSalary
FROM Employee WHERE salary <
( 
    SELECT MAX(salary) from Employee
)