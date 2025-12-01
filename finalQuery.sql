WITH emp_totals AS (
  SELECT
    e.emp_id,
    d.department_name,
    e.first_name,
    e.last_name,
    e.dob,
    SUM(p.amount) AS total_salary
  FROM employee e
  JOIN department d ON e.department = d.department_id
  JOIN payments p ON e.emp_id = p.emp_id
  WHERE DAY(p.payment_time) <> 1
  GROUP BY e.emp_id, d.department_name, e.first_name, e.last_name, e.dob
),
ranked AS (
  SELECT
    et.*,
    ROW_NUMBER() OVER (PARTITION BY et.department_name ORDER BY et.total_salary DESC) AS rn
  FROM emp_totals et
)
SELECT
  department_name AS DEPARTMENT_NAME,
  total_salary AS SALARY,
  CONCAT(first_name, ' ', last_name) AS EMPLOYEE_NAME,
  TIMESTAMPDIFF(YEAR, dob, CURDATE()) AS AGE
FROM ranked
WHERE rn = 1;
