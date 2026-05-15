package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ControlAlumno controlAlumno         = new ControlAlumno();
        ControlEntrenador controlEntrenador = new ControlEntrenador();
        ControlClase controlClase           = new ControlClase();
        ControlInscripcion controlInscripcion = new ControlInscripcion();
        ControlPago controlPago             = new ControlPago();

        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\n================================");
            System.out.println("   SISTEMA FITCONTROL");
            System.out.println("================================");
            System.out.println("(1) Registrar alumno");
            System.out.println("(2) Registrar entrenador");
            System.out.println("(3) Registrar clase");
            System.out.println("(4) Inscribir alumno a una clase");
            System.out.println("(5) Buscar alumno");
            System.out.println("(6) Buscar clases");
            System.out.println("(7) Registrar pago de membresia");
            System.out.println("(8) Salir");
            System.out.println("================================");
            System.out.print("Ingrese la opcion: ");

            String entrada = sc.nextLine();
            int opcion;

            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("La opcion NO es valida, ingrese nuevamente.");
                continue;
            }

            switch (opcion) {
                case 1: controlAlumno.registrarAlumno();                                      break;
                case 2: controlEntrenador.registrarEntrenador();                              break;
                case 3: controlClase.registrarClase(controlEntrenador);                       break;
                case 4: controlInscripcion.inscribirAlumno(controlAlumno, controlClase);      break;
                case 5: controlAlumno.buscarAlumno();                                         break;
                case 6: controlClase.buscarClase();                                           break;
                case 7: controlPago.registrarPago(controlAlumno);                             break;
                case 8: System.out.println("Cerrando el programa..."); ejecutando = false;    break;
                default: System.out.println("La opcion NO es valida, ingrese nuevamente.");
            }
        }
    }
}