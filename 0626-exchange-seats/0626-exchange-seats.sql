
SELECT id,
       CASE
           WHEN id % 2 = 0 THEN prev_student
           WHEN next_student IS NOT NULL THEN next_student
           ELSE student
       END AS student
FROM (
    SELECT id,
           student,
           LAG(student) OVER (ORDER BY id) AS prev_student,
           LEAD(student) OVER (ORDER BY id) AS next_student
    FROM Seat
)as s;