# Write your MySQL query statement below
with t1 as (
    select visited_on, sum(amount) as amount
    from Customer
    group by visited_on
),
t2 as( 
select 
visited_on,
sum(amount) OVER (
    ORDER BY visited_on
    ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
) as amount,
round(AVG(amount) OVER (
    ORDER BY visited_on
    ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
),2)as average_amount
from t1
)
select * from t2
where visited_on>=DATE_ADD((select min(visited_on) as min_date from Customer), INTERVAL 6 DAY);
