# Write your MySQL query statement below
select t2.id from
weather t1 join weather t2
on DATE_ADD(t1.recordDate, INTERVAL 1 DAY) = t2.recordDate
where t1.temperature <t2.temperature