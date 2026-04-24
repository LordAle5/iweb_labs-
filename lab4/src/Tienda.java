import java.util.ArrayList;
import java.util.Scanner;

public class Tienda {

    private ArrayList<Mascota> mascotas = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // ─────────────────────────────────────────
    // OPCIÓN 1: Agregar mascota
    // ─────────────────────────────────────────
    public void agregarMascota() {
        int tipo = 0;
        // Validar tipo con excepción
        while (true) {
            try {
                System.out.print("Tipo (1=Normal, 2=Exótica): ");
                tipo = Integer.parseInt(sc.nextLine());
                if (tipo == 1 || tipo == 2) break;
                System.out.println("⚠ Ingrese 1 o 2.");
            } catch (NumberFormatException e) {
                System.out.println("⚠ Error: debe ser un número.");
            }
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Especie: ");
        String especie = sc.nextLine();

        int edad = 0;
        // Validar edad con excepción
        while (true) {
            try {
                System.out.print("Edad: ");
                edad = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("⚠ Error: la edad debe ser un número.");
            }
        }

        if (tipo == 1) {
            mascotas.add(new MascotaNormal(nombre, especie, edad));
        } else {
            System.out.print("País de origen: ");
            String pais = sc.nextLine();
            mascotas.add(new MascotaExotica(nombre, especie, edad, pais));
        }

        System.out.println("✅ Mascota agregada correctamente.");
    }

    // ─────────────────────────────────────────
    // OPCIÓN 2: Registrar cliente
    // ─────────────────────────────────────────
    public void registrarCliente() {
        System.out.print("Nombre  : ");
        String nombre = sc.nextLine();
        System.out.print("DNI     : ");
        String dni = sc.nextLine();
        System.out.print("Correo  : ");
        String correo = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        clientes.add(new Cliente(nombre, dni, correo, telefono));
        System.out.println("✅ Cliente registrado correctamente.");
    }

    // ─────────────────────────────────────────
    // OPCIÓN 3: Adoptar mascota
    // ─────────────────────────────────────────
    public void adoptarMascota() {
        int idMascota = 0, idCliente = 0;

        // Validar ID mascota
        while (true) {
            try {
                System.out.print("ID de la mascota: ");
                idMascota = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("⚠ Error: el ID debe ser un número.");
            }
        }

        // Validar ID cliente
        while (true) {
            try {
                System.out.print("ID del cliente: ");
                idCliente = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("⚠ Error: el ID debe ser un número.");
            }
        }

        // Buscar mascota
        Mascota mascotaEncontrada = null;
        for (Mascota m : mascotas) {
            if (m.getId() == idMascota) {
                mascotaEncontrada = m;
                break;
            }
        }

        // Buscar cliente
        Cliente clienteEncontrado = null;
        for (Cliente c : clientes) {
            if (c.getId() == idCliente) {
                clienteEncontrado = c;
                break;
            }
        }

        // Validaciones
        if (mascotaEncontrada == null) {
            System.out.println("⚠ No existe mascota con ID " + idMascota);
            return;
        }
        if (clienteEncontrado == null) {
            System.out.println("⚠ No existe cliente con ID " + idCliente);
            return;
        }
        if (mascotaEncontrada.isAdoptada()) {
            System.out.println("⚠ Esta mascota ya fue adoptada.");
            return;
        }

        // Realizar adopción
        mascotaEncontrada.adoptar(clienteEncontrado);
        clienteEncontrado.agregarMascota(mascotaEncontrada);
        System.out.println("✅ ¡Adopción exitosa!");
    }

    // ─────────────────────────────────────────
    // OPCIÓN 4: Buscar mascota
    // ─────────────────────────────────────────
    public void buscarMascota() {
        System.out.print("Buscar por ID o nombre (Enter para ver todas): ");
        String filtro = sc.nextLine().trim();

        boolean encontrado = false;
        for (Mascota m : mascotas) {
            boolean coincide = filtro.isEmpty()
                    || String.valueOf(m.getId()).equals(filtro)
                    || m.getNombre().equalsIgnoreCase(filtro);

            if (coincide) {
                System.out.println("-----------------------------");
                m.mostrarInfo();
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("⚠ No se encontraron mascotas.");
        }
    }

    // ─────────────────────────────────────────
    // OPCIÓN 5: Buscar cliente
    // ─────────────────────────────────────────
    public void buscarCliente() {
        System.out.print("Buscar por ID o nombre (Enter para ver todos): ");
        String filtro = sc.nextLine().trim();

        boolean encontrado = false;
        for (Cliente c : clientes) {
            boolean coincide = filtro.isEmpty()
                    || String.valueOf(c.getId()).equals(filtro)
                    || c.getNombre().equalsIgnoreCase(filtro);

            if (coincide) {
                System.out.println("-----------------------------");
                c.mostrarInfo();
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("⚠ No se encontraron clientes.");
        }
    }
}
