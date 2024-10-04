package phonebook;

public class Main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        addressBook.load();  // Cargar contactos desde el archivo al iniciar
        addressBook.menu();  // Iniciar el menú interactivo
    }
}
