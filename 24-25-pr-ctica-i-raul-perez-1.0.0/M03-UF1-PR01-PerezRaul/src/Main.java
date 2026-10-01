import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Main {
    public class GestorContrasenas { }

    private static ArrayList<String[]> contrasenas = new ArrayList<>();//lista para almacenar nombre y contraseña//
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            System.out.println("Selecciona una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1:
                    generarNuevaContrasena();
                    break;
                case 2:
                    guardarContrasena();
                    break;
                case 3:
                    consultarContrasena();
                    break;
                case 4:
                    eliminarContrasena();
                    break;
                case 5:
                    System.out.println("saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intenta de nuevamente");
            }
        } while (opcion !=5);
    }

    //muestra el menu principal con las opciones dispinobles

    public static void mostrarMenu() {
        System.out.println("\n=== Gestor de Contraseñas ===");
        System.out.println("1. Generar nueva contraseña");
        System.out.println("2. Guardar contraseña");
        System.out.println("3. Consultar contraseñas");
        System.out.println("4. Eliminar contraseña");
        System.out.println("5. Salir");
    }
    public static void generarNuevaContrasena() {
        System.out.println("Introduce la longitud de la contraseña: ");
        int longitud = scanner.nextInt();
        System.out.println("¿Incluir numeros? (true/false): ");
        boolean incluirNumeros = scanner.nextBoolean();
        System.out.println("¿Incluir simbolos? (true/false): ");
        boolean incluirSimbolos = scanner.nextBoolean();

        String nuevaContrasena = generarContrasena(longitud, incluirNumeros, incluirSimbolos);
        System.out.println("Contraseña genereda: " + nuevaContrasena);
    }
    //guarda una contraseña en el gestor asociando un nombre o descripcion
    public static void guardarContrasena() {
        System.out.println("Introduce el nombre o descripcion del servicio: ");
        String nombre = scanner.nextLine();
        System.out.println("Introduce la contraseña: ");
        String contrasena = scanner.nextLine();

        contrasenas.add(new String[]{nombre, contrasena});
        System.out.println("Contraseña guardada con exito. ");
    }
    public static void consultarContrasena() {
        if (contrasenas.isEmpty()){
            System.out.println("No hay contraseñas almacenadas.");
        }else {
            System.out.println("\n=== Contraseñas Guardadas ===");
            for (String[] entrada : contrasenas) {
                System.out.println("Servicio: " + entrada[0] + ",Contraseña: " + entrada[1]);
            }
        }
    }
    public static void eliminarContrasena() {
        System.out.println("Introduce el nombre o descripcion del servicio a eliminar");
        String nombre = scanner.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < contrasenas.size();i++){
            if (contrasenas.get(i)[0].equalsIgnoreCase(nombre)){
                contrasenas.remove(i);
                encontrado = true;
                System.out.println("Contraseña eliminada con exito.");
                break;
            }
        }
        if (!encontrado){
            System.out.println("No se encontro una contraseña con ese nombre.");
        }
    }
    public static String generarContrasena(int longitud, boolean incluirNumeros, boolean incluirSimbolos){
        String letras = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeros = "0123456789";
        String simbolos = "!@#$%^&*()_+-=[]{}|;:,.<>?";

        String base=letras;
        if (incluirNumeros){
            base +=numeros;
        }
        if (incluirSimbolos){
            base += simbolos;
        }

        char[] contrasena = new char[longitud];
        Random random = new Random();

        for (int i =0; i <longitud;i++){
            int index = random.nextInt(base.length());
            contrasena[i]=base.charAt(index);
        }
        return new String(contrasena);
    }

}
