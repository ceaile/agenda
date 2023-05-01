
package Agenda_pack;

/**
 *
 * @author Admin
 */
public class ContactoEmpresa extends Contacto {
    
    //Hereda: String nombre
    //Hereda: String telefono
    
    private String website;
    //--------------------------------------------------------------------------
    //                      CONSTRUCTOR
    //--------------------------------------------------------------------------
    public ContactoEmpresa(String nombre, String telefono, String website){
        super(nombre, telefono);
        this.website = website;
        
    }
    //--------------------------------------------------------------------------
    //                      METODOS
    //--------------------------------------------------------------------------
    
    public void infoContacto(){
        String info = "ContactoEmpresa: nombre=" + nombre + ", telefono=" + telefono + ", website=" + website;
        System.out.println(info);
    }
    
    //--------------------------------------------------------------------------
    //                      GETTERS & SETTERS
    //--------------------------------------------------------------------------

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    
    
    
}
