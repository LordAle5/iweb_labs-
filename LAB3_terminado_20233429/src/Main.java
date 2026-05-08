import java.util.Scanner;

public  class Main {

        public static void main (String[] args) {
            Scanner sc = new Scanner(System.in);
            ControlBodega controlBodega = new ControlBodega();
            ControlItem controlItem = new ControlItem();
            boolean ejecutarMenu = true;

            while (ejecutarMenu){
                System.out.println("--------------------");
                System.out.println("Menú Principal");
                System.out.println("(1) Listar bodega");
                System.out.println("(2) Registrar bodega");
                System.out.println("(3) Registrar item en bodega");
                System.out.println("(4) Listar items de una bodega");
                System.out.println("(5) Calcular costo de almacenaje");
                System.out.println("(6) Salir");
                System.out.println("-------------------");
                System.out.println("Ingrese la opcion");

                String entrada = sc.nextLine();
                int opcion;
                try{
                    opcion = Integer.parseInt(entrada);
                } catch (NumberFormatException e){
                    System.out.println("La opcion NO es valida, ingrese nuevamente:");
                    continue;
                }

                switch (opcion){
                    case 1:
                        controlBodega.listarBodegas();
                        break;
                    case 2:
                        controlBodega.crearBodega();
                        break;
                    case 3:
                        controlItem.registrarItem(controlBodega);
                        break;
                    case 4:
                        controlItem.listarItem(controlBodega);
                        break;
                    case 5:
                        controlItem.calculoCostoAlmacen(controlBodega);
                        break;
                    case 6:
                        System.out.println("----Cerrando el programa----");
                        ejecutarMenu = false;
                        break;
                    default:
                        System.out.println("La opcion NO es valida, ingrese nuevamente:");
                }

            }
        }

}