package phonebook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBook {
    // Atributos
    private Map<String, String> contactos;
    private final String archivo = "contactos.txt";

    // Constructor para inicializar el HashMap
    public AddressBook() {
        contactos = new HashMap<>();
    }

    // Método para cargar los contactos desde un archivo
    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] partes = line.split(",");
                if (partes.length == 2) {
                    String numero = partes[0];
                    String nombre = partes[1];
                    contactos.put(numero, nombre);
                }
            }
            System.out.println("Contactos cargados correctamente.");
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo de contactos: " + e.getMessage());
        }
    }

    // Método para guardar los contactos en un archivo
    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Map.Entry<String, String> contacto : contactos.entrySet()) {
                writer.write(contacto.getKey() + "," + contacto.getValue());
                writer.newLine();
            }
            System.out.println("Contactos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo de contactos: " + e.getMessage());
        }
    }

    // Método para listar los contactos
    public void list() {
        System.out.println("Contactos:");
        for (Map.Entry<String, String> contacto : contactos.entrySet()) {
            System.out.println(contacto.getKey() + " : " + contacto.getValue());
        }
    }

    // Método para crear un nuevo contacto
    public void create() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número del contacto: ");
        String numero = scanner.nextLine();
        System.out.print("Ingrese el nombre del contacto: ");
        String nombre = scanner.nextLine();

        if (!contactos.containsKey(numero)) {
            contactos.put(numero, nombre);
            System.out.println("Contacto agregado correctamente.");
        } else {
            System.out.println("El número ya está en la agenda.");
        }
    }

    // Método para eliminar un contacto
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número del contacto a eliminar: ");
        String numero = scanner.nextLine();

        if (contactos.containsKey(numero)) {
            contactos.remove(numero);
            System.out.println("Contacto eliminado correctamente.");
        } else {
            System.out.println("El contacto no existe.");
        }
    }

    // Menú interactivo para gestionar los contactos
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Agenda Telefónica ---");
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Guardar cambios");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> list();
                case 2 -> create();
                case 3 -> delete();
                case 4 -> save();
                case 5 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }
}

