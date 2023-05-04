
package Agenda_pack;


import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Admin
 */
public class ContactoPersona extends Contacto {

    //Hereda: String nombre
    //Hereda: String telefono
    private final String cumpleaños;

    //--------------------------------------------------------------------------
    //                      CONSTRUCTOR
    //--------------------------------------------------------------------------
    public ContactoPersona(String nombre, String telefono, String cumpleaños) {
        super(nombre, telefono);
        this.cumpleaños = cumpleaños;

    }
    //--------------------------------------------------------------------------
    //                      METODOS
    //--------------------------------------------------------------------------

    public void infoContacto() {
        String info = "ContactoPersona: nombre=" + nombre + ", telefono=" + telefono + ", cumpleaños=" + cumpleaños;
        System.out.println(info);
    }

    //VALIDAR FECHA NACIMIENTO
    static public boolean fechaNacValida(String fechaNac) {
        boolean valido = false;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); //formato de fecha español
        
        try {
            formatoFecha.parse(fechaNac);
            valido = true;
        } catch (ParseException ex) {
            
        }
        
        return valido;
    }

    //--------------------------------------------------------------------------
    //                      GETTERS & SETTERS
    //--------------------------------------------------------------------------
    public String getCumpleaños() {
        return cumpleaños;
    }

}
