import java.util.ArrayList;
import java.util.Scanner;

public class ControlBodega {

    private ArrayList<Bodega> listaBodegas = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void listarBodegas(){
        System.out.println("Listar bodegas");
        if (listaBodegas.isEmpty()){
            System.out.println("No hay bodegas listadas");
            return;
        }
        System.out.println("ID, NOMBRE, UBICACION, CAPACIDAD, COSTOXDIAMT");
        for (Bodega b: listaBodegas){
            System.out.println(b.getId() + ", " + b.getNombre() + ", " +
                    b.getUbicacion() + ", " + b.getCapacidad() + ", " +
                    b.getCostoXDiaMt());
        }
    }

    public ArrayList<Bodega> getListaBodegas(){
        return listaBodegas;
    }

    public void crearBodega(){
        System.out.println("Registrar Bodega");

        System.out.println("Ingrese ID:");
        String Id = sc.nextLine();

        System.out.println("Ingrese Nombre: ");
        String Nombre = sc.nextLine();

        System.out.println("Ingrese ubicacion");
        String Ubicacion = sc.nextLine();

        int Capacidad = 0;
        while (true) {
            try {
                System.out.print("Ingrese Capacidad: ");
                Capacidad = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("La capacidad debe ser un número entero.");
            }
        }

        double Costo = 0;
        while (true) {
            try {
                System.out.print("Ingrese Costo diario : ");
                Costo = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("El costo debe ser un número.");
            }
        }

        Bodega bodegaNueva = new Bodega (Id, Nombre, Ubicacion, Capacidad, Costo);
        listaBodegas.add(bodegaNueva);
        System.out.print("Bedega registrada");



    }

    public Bodega buscarBodegaPorId(String Id) {
        for (Bodega b : listaBodegas) {
            if (b.getId().equalsIgnoreCase(Id)) {
                return b;
            }
        }
        return null;
    }

}
