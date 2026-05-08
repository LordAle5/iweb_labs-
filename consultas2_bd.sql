
#"1) Mostrar los departamentos que no tienen empleados. (1pt)

SELECT d.department_name 
FROM departments d
LEFT JOIN employees e ON d.department_id = e.department_id
WHERE e.employee_id IS NULL;

#2) Mostrar los países que no tienen localidades. (1pt)

SELECT c.country_name 
FROM countries c
LEFT JOIN locations l ON c.country_id = l.country_id
WHERE l.location_id IS NULL;


#3) Mostrar el salario promedio por cada puesto de trabajo. (1.5pt)
SELECT j.job_title, AVG(e.salary) AS salario_promedio
FROM employees e
JOIN jobs j ON e.job_id = j.job_id
GROUP BY j.job_title;

#4) Mostrar los departamentos con más de 2 empleados que ganan más de 10000. (1.5pt)
SELECT d.department_name, COUNT(e.employee_id) AS cantidad_empleados
FROM departments d
JOIN employees e ON d.department_id = e.department_id
WHERE e.salary > 10000
GROUP BY d.department_id, d.department_name
HAVING COUNT(e.employee_id) > 2;

#5) Mostrar el salario total por cada manager (jefe). (1.5pt)

SELECT m.first_name AS nombre_jefe, m.last_name AS apellido_jefe, SUM(e.salary) AS nomina_total_equipo
FROM employees e
JOIN employees m ON e.manager_id = m.employee_id
GROUP BY m.employee_id, m.first_name, m.last_name;


  36) Mostrar los empleados que ganan más que su jefe. (1.5pt)

SELECT e.first_name AS empleado, e.salary AS sueldo_empleado, 
       m.first_name AS jefe, m.salary AS sueldo_jefe
FROM employees e
JOIN employees m ON e.manager_id = m.employee_id
WHERE e.salary > m.salary;
