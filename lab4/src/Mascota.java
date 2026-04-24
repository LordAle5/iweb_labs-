public class Mascota {

    //hacempps el ID autoincremental
    private static int contadorID = 1;

    private int ID;
    private String nombre;
    private String especie;
    private int edad;

    public Mascota (String nombre, String especie, int edad){
        this.ID = contadorID ++;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
    }

    //getters
    public int getID(){return ID;}
    public String getNombre(){return nombre;}
    public String getEspecie(){return especie;}
    public int getEdad(){ return edad;}

    public void mostrarInfo(){
        System.out.println(" ID : " + ID);
        System.out.println(" Nombre : " + nombre);
        System.out.println(" Especie : " + especie);
        System.out.println(" Edad : " + edad);
    }



}
