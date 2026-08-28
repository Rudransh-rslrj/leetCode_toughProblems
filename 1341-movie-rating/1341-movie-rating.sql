# Write your MySQL query statement below
with t2 as 
(
    select user_id ,count(distinct movie_id) as temp
    from MovieRating
    group by user_id
),
t4 as 
(
    select movie_id ,avg(rating) as temp2
    from MovieRating
    WHERE created_at >= '2020-02-01'
      AND created_at < '2020-03-01'
    group by movie_id
)
(
    select t1.name as results from
    Users t1 join t2
    on t1.user_id=t2.user_id
    where t2.temp=(select max(t2.temp) from t2)
    ORDER BY t1.name asc
    LIMIT 1
)
union all
( 
    select t3.title as results from
    Movies t3 join t4
    on t3.movie_id=t4.movie_id
    where t4.temp2=(select max(t4.temp2) from t4)
    ORDER BY t3.title asc
    LIMIT 1
);