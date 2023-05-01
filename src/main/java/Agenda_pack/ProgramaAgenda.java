package Agenda_pack;

/*Enunciado:
 *Debemos implementar un programa que funcione como una agenda telefónica de contactos.
Un contacto tiene un nombre y un teléfono (no es necesario validarlos). Se considera que dos
contactos son iguales si sus nombres son iguales.
Una agenda de contactos está formada por un conjunto de contactos y debe permitir realizar las
siguientes operaciones:
• boolean añadirContacto(Contacto c): Añade un contacto a la agenda e indica si se ha
añadido. No se pueden meter contactos que existan, es decir, no podemos duplicar nombres
(aunque tengan distinto teléfono).
• boolean eliminarContacto(String nombre): elimina el contacto de la agenda. Indica si se ha
podido eliminar.
• boolean existeContacto(String nombre): Indica si el contacto indicado existe.
• void listarContactos(): Muestra por pantalla toda la agenda.
• int buscaContacto(String nombre): busca un contacto por su nombre y devuelve su posición
en la agenda
Implementa las clases Contacto y Agenda.
Implementa también la clase ProgramaAgenda con una función main para realizar pruebas

Queremos modificar la agenda de contactos para poder almacenar personas y empresas. Las
personas tienen nombre, teléfono y cumpleaños; las empresas tienen nombre, teléfono y página
web. Tendrás que modificar la clase Contacto para que sea abstracta (y tal vez otros cambios).
Implementa las clases ContactoPersona y ContactoEmpresa, que heredarán de Contacto. Agenda
deberá seguir conteniendo una sola lista de contactos. Modifica ProgramaAgenda para realizar
pruebas con la nueva agenda.

Extra Cristina:
El ejercicio de la agenda es como el original, 
pero tiene que permitir cargar la agenda desde un archivo, 
guardar una copia de seguridad cada vez que el usuario añada un contacto 
y permitir al usuario guardar una copia. 
Así como gestionar las excepciones de entrada y salida de datos.
 */
import Agenda_pack.ContactoPersona;
import Agenda_pack.AgendaContactos;
import Agenda_pack.Contacto;
import java.io.File;
import java.io.FileWriter;
import java.text.ParseException;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ProgramaAgenda {

    public static void main(String[] args) {
        try {
            AgendaContactos agenda = new AgendaContactos();

            String nombre = "";
            String telefono = "";
            String fechaNac = "";
            String website = "";

            //new---------------------------------------------------------------
            File archivoAgenda = new File("agenda_existente.txt");

            File copiaSeguridad = new File("copia_seguridad_agenda.txt");
            FileWriter escritor = null;
            if (copiaSeguridad.exists() && copiaSeguridad.isFile()) {
                copiaSeguridad.delete();
            } else {
                if (copiaSeguridad.mkdir()) {
                    escritor = new FileWriter(copiaSeguridad);
                } else {
                    throw new Exception("No ha podido crearse la copia de seguridad.");
                }

            }

            // IMPORTAR CONTACTOS
            if (archivoAgenda.exists() && archivoAgenda.isFile()) {
                Scanner lector = new Scanner(archivoAgenda, "UTF-8");

                while (lector.hasNextLine()) {
                    String linea = lector.nextLine();
                    String conceptos[] = linea.split(", ");

                    if (conceptos.length == 3) {
                        if (ContactoPersona.fechaNacValida(conceptos[2])) {         // BUG: No admite la fecha como valida, aunque el programa funciona igualmente
                            ContactoPersona nuevaPersona = new ContactoPersona(conceptos[0], conceptos[1], conceptos[2]);
                            agenda.añadirContacto(nuevaPersona);
                        } else {
                            ContactoEmpresa nuevaEmpresa = new ContactoEmpresa(conceptos[0], conceptos[1], conceptos[2]);
                            agenda.añadirContacto(nuevaEmpresa);
                        }
                    } else {
                        throw new Exception("Un contacto no ha podido importarse, una linea del archivo debe estar corrupta.");
                    }
                }
                lector.close();

                //probablemente todo el codigo antiguo deba estar ahora aqui...                
            } else {
                throw new Exception("La agenda existente no existe o no es un archivo de texto.");
            }

            //fin new------------------------------------------------------------------
            
            // MENU E INTERACCION CON USUARIO
            Scanner teclado = new Scanner(System.in);
            //SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); //formato de fecha español
            System.out.println();
            System.out.println("Bienvenido.");
            System.out.println("Seleccione una opcion:");

            char opcionElegida;

            do {
                System.out.println("Pulse 1 para crear un contacto y añadirlo a la agenda.");
                System.out.println("Pulse 2 para eliminar un contacto existente");
                System.out.println("Pulse 3 para modificar datos de un contacto");
                System.out.println("Pulse 4 para ordenar y visualizar alfabeticamente los contactos");
                System.out.println("Pulse 5 para ordenar y visualizar alfabeticamente los contactos en orden inverso");
                System.out.println("Pulse 0 para salir.");

                opcionElegida = teclado.nextLine().charAt(0);

                switch (opcionElegida) {
                    case '1':
                        System.out.println("¿Desea crear un contacto de persona o de empresa?");
                        System.out.println("Pulse 1 si desea crear un contacto de persona, o pulse 2 si desea que sea de empresa.");
                        System.out.println("Pulse 0 si ya no desea crear ningún contacto");
                        char tipoContacto = teclado.nextLine().charAt(0);
                        //teclado.nextLine(); //limpiar buffer

                        switch (tipoContacto) {
                            case '1': //persona
                                //VALIDAMOS DATOS
                                boolean nombreValido = false;
                                boolean telefonoValido = false;
                                boolean cumpleValido = false;

                                while (nombreValido == false) {
                                    System.out.println("Introduzca un nombre válido para el contacto:");
                                    nombre = teclado.nextLine();
                                    if (Contacto.nombreValido(nombre)) {
                                        nombreValido = true;
                                    }
                                }

                                while (telefonoValido == false) {
                                    System.out.println("Introduzca en telefono válido del contacto:");
                                    telefono = teclado.nextLine();
                                    if (Contacto.telfValido(telefono)) {
                                        telefonoValido = true;
                                    }
                                }

                                while (cumpleValido == false) {
                                    System.out.println("Introduzca la fecha de nacimiento del contacto con formato: dd/mm/aaaa");
                                    fechaNac = teclado.nextLine();
                                    if (ContactoPersona.fechaNacValida(fechaNac)) {
                                        cumpleValido = true;
                                    }
                                }

                                //CREAMOS CONTACTO Y AÑADIMOS A LA LISTA:
                                ContactoPersona persona = new ContactoPersona(nombre, telefono, fechaNac);
                                agenda.añadirContacto(persona);
                                break;

                            case '2': //empresa
                                nombreValido = false;
                                telefonoValido = false;
                                boolean webValida = false;

                                while (nombreValido == false) {
                                    System.out.println("Introduce el nombre de la empresa:");
                                    nombre = teclado.nextLine();
                                    if (Contacto.nombreValido(nombre)) {
                                        nombreValido = true;
                                    }
                                }

                                while (telefonoValido == false) {
                                    System.out.println("Introduzca en telefono válido del contacto:");
                                    telefono = teclado.nextLine();
                                    if (Contacto.telfValido(telefono)) {
                                        telefonoValido = true;
                                    }
                                }

                                while (webValida == false) {
                                    System.out.println("Introduce la dirección del sitio web:");
                                    website = teclado.nextLine();

                                }
                                break;
                            case '0': //salir
                                break;
                            default:
                                System.out.println("Caracter introducido no válido.");
                        }
                        break;

                    case '2':
                        boolean eliminacionValida = false;

                        while (eliminacionValida == false) {
                            System.out.println("Diga el nombre del contacto que desea eliminar");
                            String contactoEliminar = teclado.nextLine();

                            if (agenda.eliminarContacto(contactoEliminar) == true) {
                                eliminacionValida = true;
                            }
                        }
                        break;

                    case '3': //modificar datos de contacto
                        System.out.println("Pulse 1 si desea modificar el nombre del contacto");
                        System.out.println("Pulse 2 si desea modificar el telefono del contacto");
                        System.out.println("Pulse 3 si desea modificar la dirección del sitio web del contacto empresarial");
                        System.out.println("Pulse 0 si no desea realizar ninguna opción");
                        char tipoModificacion = teclado.nextLine().charAt(0);
                        switch (tipoModificacion) {
                            case '1':
                                System.out.println("Ha elegido modificar el nombre de contacto.");
                                System.out.println("Escriba el nombre del contacto que desea modificar:");
                                nombre = teclado.nextLine();
                                System.out.println("Escriba el nombre nuevo:");
                                String nombreNuevo = teclado.nextLine();

                                if (agenda.cambiarNombre(nombre, nombreNuevo) == true) {
                                    System.out.println("Nombre actualizado correctamente.");
                                } else {
                                    System.out.println("No ha podido actualizarse el nombre.");
                                    System.out.println("Es posible que haya utilizado números o introducido un caracter extraño.");
                                }
                                break;

                            case '2':
                                System.out.println("Ha elegido modificar el telefono del contacto");
                                System.out.println("Escriba el nombre del contacto que desea modificar:");
                                nombre = teclado.nextLine();
                                System.out.println("Escriba el telefono que desea actualizar:");
                                String telfNuevo = teclado.nextLine();
                                if (agenda.cambiarTelf(nombre, telfNuevo) == true) {
                                    System.out.println("El teléfono ha sido actualizado correctamente.");
                                } else {
                                    System.out.println("Error al actualizar el telefono de contacto.");
                                }

                                break;

                            case '3':
                                System.out.println("Ha elegido modificar la dirección web de un contacto empresarial");
                                System.out.println("Escriba el nombre del contacto que desea modificar:");
                                nombre = teclado.nextLine();
                                System.out.println("Escriba la dirección del sitio web que desea actualizar");
                                String webNueva = teclado.nextLine();
                                agenda.cambiarWeb(nombre, webNueva); //ERROR CON TIPOS CONTACTOEMPRESA
                                //FALTA VALIDAR TAMBIEN
                                break;

                            case '0':
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }
                        break;

                    case '4': //ordenar alfabeticamente contactos
                        agenda.ordenAlfabetico();
                        break;

                    case '5': //ordenar al reves contactos
                        agenda.ordenAlreves();
                        break;
                    case '0': //salir
                        break;
                    default:
                        System.out.println("Caracter introducido no válido");
                }

            } while (opcionElegida != '0');

            escritor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//------------------------------------------------------------------------------
//                               FUNCIONES
//------------------------------------------------------------------------------
    /**
     * Le pasamos el fileWriter que ya sabe donde escribir (en que archivo)
     * y le pasamos las variables que vayamos a escribir.
     * Añadimos comas y espacios.
     * No importa mezclar contactos de Personas o Empresas porque al leer la copia
     * el programa ya diferencia si se trata de fecha o una web y ya crea el contacto
     * de manera diferente.
     *      Queda ver cómo copiamos la copia en el original y borramos el que haya
     * que borrar cada vez.
     * @param escritor
     * @param nombre
     * @param telefono
     * @param fechaOweb
     * @throws Exception
     * @return 
     */
    public void escribirContacto(FileWriter escritor, String nombre, String telefono, String fechaOweb) throws Exception{
        boolean escrito = false;
        escritor.write(nombre + ", " + telefono + ", " + fechaOweb + "\n");
    }
}
