
package Agenda_pack;

/**
 *
 * @author Admin
 */
public abstract class Contacto {

    //--------------------------------------------------------------------------
    //                      ATRIBUTOS
    //--------------------------------------------------------------------------
    protected String nombre;
    protected String telefono;

    //--------------------------------------------------------------------------
    //                  CONSTRUCTOR
    //--------------------------------------------------------------------------
    /*
    public Contacto(){  
    }
     */
    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    //--------------------------------------------------------------------------
    //                      METODOS
    //--------------------------------------------------------------------------
    public abstract void infoContacto();

    //VALIDAR NOMBRE
    static public boolean nombreValido(String nombre) {
        boolean valido = false;
        if (nombre.matches("^[a-zA-Z\\s]+")) {
            valido = true;
        }
        return valido;
    }

    //VALIDAR TELF
    static public boolean telfValido(String telf) {
        boolean valido = false;
        if (telf.matches("[0-9]{9}")) {
            valido = true;
        }
        return valido;
    }

    //--------------------------------------------------------------------------
    //                         GETTERS & SETTERS
    //--------------------------------------------------------------------------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
