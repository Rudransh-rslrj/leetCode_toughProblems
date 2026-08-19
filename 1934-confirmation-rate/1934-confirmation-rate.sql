SELECT t4.user_id, COALESCE(rate,0) AS confirmation_rate
FROM Signups t4
LEFT JOIN (
    SELECT t1.c1 AS user_id, ROUND(COALESCE(t2.c2, 0) / t1.c2, 2) AS rate
    FROM (
        SELECT a.user_id AS c1, COUNT(a.action) AS c2
        FROM Confirmations a
        GROUP BY c1
    ) AS t1
    LEFT JOIN (
        SELECT a.user_id AS c1, COUNT(a.action) AS c2
        FROM Confirmations a
        WHERE a.action = 'confirmed'
        GROUP BY c1
    ) AS t2
    ON t1.c1 = t2.c1
) AS t3
ON t4.user_id = t3.user_id
GROUP BY t4.user_id;