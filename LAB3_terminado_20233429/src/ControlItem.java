import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlItem {

    private ArrayList<Item> listaItems = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocalDate leerFecha(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje + " (DD/MM/AAAA): ");
                String entrada = sc.nextLine();
                return LocalDate.parse(entrada, formato);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Ejemplo: 15/03/2025");
            }
        }
    }


    public void registrarItem(ControlBodega controlBodega) {
        System.out.println("Registrar Item");

        System.out.print("Ingrese ID del ítem: ");
        String Id = sc.nextLine();

        System.out.print("Ingrese tipo: ");
        String Tipo = sc.nextLine();

        LocalDate fechaIngreso = leerFecha("Ingrese Fecha de ingreso");
        LocalDate fechaSalida = leerFecha("Ingrese Fecha de salida");

        double Volumen = 0;
        while (true) {
            try {
                System.out.print("Ingrese Volumen (m3): ");
                Volumen = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Debe ser un número.");
            }
        }

        double Peso = 0;
        while (true) {
            try {
                System.out.print("Ingrese Peso (kg): ");
                Peso = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Debe ser un número.");
            }
        }

        System.out.print("Ingrese ID de la bodega: ");
        String idBodega = sc.nextLine();
        Bodega bodega = controlBodega.buscarBodegaPorId(idBodega);

        if (bodega == null) {
            System.out.println("No existe una bodega con ese ID.");
            return;
        }


        Item nuevoItem = new Item(Id, Tipo, fechaIngreso,fechaSalida ,Volumen , Peso, bodega);
        listaItems.add(nuevoItem);
        bodega.agregarItem(nuevoItem);
        System.out.println(" Ítem registrado.");
    }

    public void listarItem(ControlBodega controlBodega) {
        System.out.print("Ingrese ID de la bodega: ");
        String idBodega = sc.nextLine();
        Bodega bodega = controlBodega.buscarBodegaPorId(idBodega);

        if (bodega == null) {
            System.out.println("No existe esa bodega.");
            return;
        }

        ArrayList<Item> items = bodega.getListaItem();

        if (items.isEmpty()) {
            System.out.println("La bodega no tiene ítems registrados.");
            return;
        }

        System.out.println("ID, TIPO, F.INGRESO, F.SALIDA, VOLUMEN, PESO");
        for (Item i : items) {
            System.out.println(i.getId() + ", " + i.getTipo() + ", " +
                    i.getFechaIngreso().format(formato) + ", " + i.getFechaSalida().format(formato) + ", " +
                    i.getVolumen() + ", " + i.getPeso());
        }
    }

    public void calculoCostoAlmacen(ControlBodega controlBodega) {
        System.out.print("Ingrese ID de la bodega: ");
        String idBodega = sc.nextLine();
        Bodega bodega = controlBodega.buscarBodegaPorId(idBodega);

        if (bodega == null) {
            System.out.println("No existe esa bodega.");
            return;
        }

        System.out.print("Ingrese ID del ítem: ");
        String idItem = sc.nextLine();

        Item itemEncontrado = null;
        for (Item i : bodega.getListaItem()) {
            if (i.getId().equalsIgnoreCase(idItem)) {
                itemEncontrado = i;
                break;
            }
        }

        if (itemEncontrado == null) {
            System.out.println("No existe un ítem con ese ID en la bodega.");
            return;
        }

        long dias = ChronoUnit.DAYS.between( itemEncontrado.getFechaIngreso(), itemEncontrado.getFechaSalida() );

        double costo = bodega.getCostoXDiaMt() * ((double) dias);

        System.out.println("--- Costo de Almacenaje ---");
        System.out.println("Ítem          : " + itemEncontrado.getId());
        System.out.println("Fecha ingreso : " + itemEncontrado.getFechaIngreso().format(formato));
        System.out.println("Fecha salida  : " + itemEncontrado.getFechaSalida().format(formato));
        System.out.println("Días almacen. : " + dias);
        System.out.println("Costo total   : S/. " + String.format("%.2f", costo));
    }


}
