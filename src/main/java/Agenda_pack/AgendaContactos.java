    //--------------------------------------------------------------------------
//
//--------------------------------------------------------------------------
package Agenda_pack;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Admin
 */
public class AgendaContactos {

    ArrayList<Contacto> agenda = new ArrayList<Contacto>();

    //--------------------------------------------------------------------------
    //                       METODOS
    //--------------------------------------------------------------------------
    public boolean añadirContacto(Contacto contacto) {
        boolean añadido = true;
        if (existeContacto(contacto.getNombre())) {
            añadido = false;
        } else {
            agenda.add(contacto);
        }
        return añadido;
    }

    public boolean eliminarContacto(String nombre) {
        boolean eliminado = false;

        if (existeContacto(nombre)) {

            for (Contacto i : agenda) {
                if (i.getNombre().equalsIgnoreCase(nombre)) {
                    agenda.remove(i);
                    eliminado = true;
                }
            }
        }

        return eliminado;
    }

    public boolean existeContacto(String nombre) {
        boolean existe = false;
        for (Contacto i : agenda) {
            if (i.getNombre().equalsIgnoreCase(nombre)) existe = true; 
        }
        return existe;
    }

    public void listarContactos() {
        String lista = "";
        for (Contacto i : agenda) {
            i.infoContacto(); //ese metodo ya printea, no necesario volver a hacerlo
            System.out.println(); //separacion entre contactos
        }
    }

    //FUNCIONES PROPIAS---------------------------------------------------------
    
    
    //ORDENAR CONTACTOS POR ORDEN ALFABETICO
    public void ordenAlfabetico() {
         
        ArrayList<String> orden = new ArrayList<String>();

        for (Contacto i : agenda) {
            orden.add(i.getNombre());
        }
        System.out.println("Agenda en orden alfabetico: ");
        Collections.sort(orden); //esto almacena y ordena los nombres pero no sabe nada del resto de atributos
        for (String i : orden) {
            System.out.println(i + ", ");
        }
        System.out.println("");

    }

    /**
     * ORDENAR NOMBRES CONTACTOS AL REVES
     * Imprime por pantalla los strings propiedades de los contactos en orden.
     * Recorre con un foreach el arraylist de contactos, lo introduce en un
     * array de strings luego utiliza el metodo Collections.sort(), que para
     * hacerlo de forma reversa tambien coge como parametro el metodo
     * Collections.reverseOrder(). Requiere importar Collections. Después
     * recorremos el arraylist de strings ya ordenados e imprimimos.
     */
    public void ordenAlreves() {
        
        ArrayList<String> orden = new ArrayList<String>();

        for (Contacto i : agenda) {
            orden.add(i.getNombre());
        }
        System.out.println("Agenda en orden reverso:");
        Collections.sort(orden, Collections.reverseOrder());
        for (String i : orden) {
            System.out.println(i);
        }
        System.out.println("");
    }

    
    //CAMBIAR NOMBRE DE CONTACTO
    public boolean cambiarNombre(String nombreActual, String nombreNuevo) {
        
        boolean cambiado = false;

        if (existeContacto(nombreActual)) {

            for (Contacto i : agenda) {
                if (i.getNombre().equalsIgnoreCase(nombreActual)) {
                    i.setNombre(nombreNuevo);
                    cambiado = true;
                }
            }
        } else {
            System.out.println("Error, no existe el nombre en la agenda");
        }
        return cambiado;
    }

    
    //CAMBIAR TELF DE CONTACTO
    public boolean cambiarTelf(String nombre, String telfNuevo) {
        boolean cambiado = false;

        if (existeContacto(nombre)) {
            for (Contacto i : agenda) {
                if (i.getNombre().equalsIgnoreCase(nombre)) {
                    i.setTelefono(telfNuevo);
                    cambiado = true;
                }
            }
        }

        return cambiado;
    }
    
    //CAMBIAR WEBSITE DE CONTACTO
    public boolean cambiarWeb(String nombre, String websiteNueva) {
        boolean cambiado = false;
        
        if (existeContacto(nombre)) {
            for (Contacto i : agenda) {
                if (i.getNombre().equals(nombre)) {                       //AQUI ESTA EL CASTING DE CLASE A SUBCLASE
                    
                    ContactoEmpresa encontrado = (ContactoEmpresa)i;
                    encontrado.setWebsite(websiteNueva); 
                    
                    cambiado = true;
                }
            }
        }
        return cambiado;
    }

}
