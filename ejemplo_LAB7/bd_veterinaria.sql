-- ======================================
-- CREAR Y USAR LA BASE DE DATOS
-- ======================================
CREATE DATABASE IF NOT EXISTS Veterinaria;
USE Veterinaria;

-- ======================================
-- TABLA ESPECIE
-- ======================================
CREATE TABLE especie (
    idespecie INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(45) NOT NULL
);

-- ======================================
-- TABLA VETERINARIO
-- ======================================
CREATE TABLE veterinario (
    idveterinario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100) NOT NULL
);

-- ======================================
-- TABLA DUENO
-- ======================================
CREATE TABLE dueno (
    iddueno INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20)
);

-- ======================================
-- TABLA MASCOTA
-- ======================================
CREATE TABLE mascota (
    idmascota      INT AUTO_INCREMENT PRIMARY KEY,
    nombre         VARCHAR(100) NOT NULL,
    edad           INT NOT NULL,
    peso           DECIMAL(5,2) NOT NULL,
    especie_id     INT NOT NULL,
    veterinario_id INT NOT NULL,
    dueno_id       INT NOT NULL,
    FOREIGN KEY (especie_id)     REFERENCES especie(idespecie),
    FOREIGN KEY (veterinario_id) REFERENCES veterinario(idveterinario),
    FOREIGN KEY (dueno_id)       REFERENCES dueno(iddueno)
);

-- ======================================
-- INSERTS ESPECIE
-- ======================================
INSERT INTO especie (nombre) VALUES
('Perro'),
('Gato'),
('Conejo'),
('Loro');

-- ======================================
-- INSERTS VETERINARIO
-- ======================================
INSERT INTO veterinario (nombre, especialidad) VALUES
('Carlos Perez',  'Cirugia'),
('Ana Torres',    'Dermatologia'),
('Luis Ramirez',  'Medicina General');

-- ======================================
-- INSERTS DUENO
-- ======================================
INSERT INTO dueno (nombre, telefono) VALUES
('Rodrigo Diaz',  '999111222'),
('Lucia Ramos',   '988777666'),
('Miguel Castro', '977555444');

-- ======================================
-- INSERTS MASCOTA
-- ======================================
INSERT INTO mascota (nombre, edad, peso, especie_id, veterinario_id, dueno_id) VALUES
('Max',    5, 12.50, 1, 1, 1),
('Michi',  2,  4.20, 2, 2, 2),
('Bunny',  1,  1.30, 3, 1, 1),
('Rocky',  7, 18.90, 1, 3, 3),
('Piolin', 3,  0.80, 4, 2, 2);

-- ======================================
-- CONSULTA DE VERIFICACIÓN
-- ======================================
SELECT
    m.idmascota,
    m.nombre,
    m.edad,
    m.peso,
    e.nombre AS especie,
    v.nombre AS veterinario,
    d.nombre AS dueno
FROM mascota m
INNER JOIN especie e     ON m.especie_id     = e.idespecie
INNER JOIN veterinario v ON m.veterinario_id = v.idveterinario
INNER JOIN dueno d       ON m.dueno_id       = d.iddueno;