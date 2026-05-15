-- consulta1:
select c.nombre_clase, e.nombre, e.especialidad
from clases c
JOIN entrenadores e ON c.id_entrenador = e.id_entrenador;

-- consulta 2:
select a.id_alumno, a.nombre, a.dni, a.correo
from alumnos a
left join inscripciones i on a.id_alumno = i.id_alumno
where i.id_inscripcion is null;

-- consulta 3:
select c.nombre_clase, COUNT(i.id_inscripcion) AS cantidad_alumnos
from clases c
left join inscripciones i ON c.id_clase = i.id_clase
group by c.id_clase, c.nombre_clase;

-- consulta 4:
select tipo_membresia, sum(monto) as ingreso_total
from pagos 
group by tipo_membresia;

-- consulta 5:
select c.nombre_clase, count(i.id_inscripcion) as cantidad_alumnos
from clases c 
JOIN inscripciones i on c.id_clase = i.id_clase
group by c.id_clase, c.nombre_clase
order by cantidad_alumnos desc
LIMIT 3;

-- consulta 6:
select e.nombre, e.especialidad, count(c.id_clase) as cantidad_clases
from entrenadores e 
join clases c on e.id_entrenador = c.id_entrenador
group by e.id_entrenador, e.nombre, e.especialidad
Having count(c.id_clase) > 2;

-- consulta 7:

select a.nombre, p.tipo_membresia, p.monto
from alumnos a
join pagos p on a.id_alumno = p.id_alumno
where p.monto > (select avg (monto) from pagos);

-- consulta 8:

select a.nombre, a.correo, sum(p.monto) as monto_total
from alumnos a
join pagos p on a.id_alumno = p.id_alumno
group by a.id_alumno, a.nombre, a.correo;

select * from alumnos;
