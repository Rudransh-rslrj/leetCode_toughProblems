CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
    SELECT
        CASE
            WHEN COUNT(*) =N THEN MIN(salary)
            ELSE NULL
        END
    AS salary FROM
    (
        SELECT DISTINCT salary FROM Employee
        ORDER by salary Desc
        LIMIT N
    )
    AS temp

  );
END