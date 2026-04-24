import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tienda tienda = new Tienda();
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\n===== 🐾 BatiPet =====");
            System.out.println("1. Agregar mascota");
            System.out.println("2. Registrar cliente");
            System.out.println("3. Adoptar mascota");
            System.out.println("4. Buscar mascota");
            System.out.println("5. Buscar clientes");
            System.out.println("6. Salir");
            System.out.print("Opción: ");

            try {
                int opcion = Integer.parseInt(sc.nextLine());
                switch (opcion) {
                    case 1: tienda.agregarMascota();   break;
                    case 2: tienda.registrarCliente(); break;
                    case 3: tienda.adoptarMascota();   break;
                    case 4: tienda.buscarMascota();    break;
                    case 5: tienda.buscarCliente();    break;
                    case 6:
                        System.out.println("👋 ¡Hasta luego!");
                        ejecutando = false;
                        break;
                    default:
                        System.out.println("⚠ Opción inválida. Elige entre 1 y 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠ Error: debes ingresar un número.");
            }
        }
    }
}
