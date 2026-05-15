package org.example;

public class Alumno {

    private int idAlumno;
    private String nombre;
    private String dni;
    private String correo;
    private int edad;

    public Alumno(int idAlumno, String nombre, String dni,
                  String correo, int edad) {
        this.idAlumno = idAlumno;
        this.nombre   = nombre;
        this.dni      = dni;
        this.correo   = correo;
        this.edad     = edad;
    }

    // Getters
    public int getIdAlumno()  { return idAlumno; }
    public String getNombre() { return nombre; }
    public String getDni()    { return dni; }
    public String getCorreo() { return correo; }
    public int getEdad()      { return edad; }
}