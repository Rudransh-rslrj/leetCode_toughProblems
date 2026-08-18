-- # Write your MySQL query statement below
-- SELECT student_id, student_name, subject_name ,COUNT(subject_name) as attended_exams
-- FROM
-- Examinations JOIN
-- (SELECT * FROM Students, SUBJECTS) AS temp
-- ON Examinations.student_id=temp.student_id
-- AND Examinations.subject_name=temp.subject_name
-- GROUP BY student_id, subject_name;


SELECT 
    temp.student_id,
    temp.student_name,
    temp.subject_name,
    COUNT(Examinations.subject_name) AS attended_exams
FROM Examinations
RIGHT JOIN
    (SELECT * FROM Students, Subjects) AS temp
ON Examinations.student_id = temp.student_id
AND Examinations.subject_name = temp.subject_name
GROUP BY 
    temp.student_id,
    temp.student_name,
    temp.subject_name
ORDER BY 
    temp.student_id,
    temp.subject_name;