/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.controlador;

/**
 *
 * @author Duverdiel
 */
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import com.nellehsoft.software_sail.persistencia.Usuario;

@Named("plantillaController")
@SessionScoped
public class plantillaController implements Serializable {
     
    public void verificarSesion(){
        try{ 
          Usuario us= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
           if(us==null){
             FacesContext.getCurrentInstance().getExternalContext().redirect("../paginas/permisos.xhtml?faces-redirect=true");
           }            
        }catch (IOException e){
            
        }
    }
    
    public void cerrarSesion() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
              
    }
    
}
