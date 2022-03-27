package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.persistencia.Iglesia;
import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.IglesiaFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("iglesiaController")
@SessionScoped
public class IglesiaController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.IglesiaFacade ejbFacade;
    private List<Iglesia> items = null;
    private Iglesia selected;
    private List<Iglesia> list_igleia_local;
    private String tipoIglesia="Local";
    private String NombreIglesia;    

    public IglesiaController() {
    }
    
    
    @PostConstruct
    public void init(){
    obtener_iglesiaLocal();
    obtener_lista_iglesia_Local();
    }

    public IglesiaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(IglesiaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public String getNombreIglesia() {
        return NombreIglesia;
    }

    public void setNombreIglesia(String NombreIglesia) {
        this.NombreIglesia = NombreIglesia;
    }

    
    
    public Iglesia getSelected() {
        return selected;
    }

    public List<Iglesia> getList_igleia_local() {
        return list_igleia_local;
    }

    public void setList_igleia_local(List<Iglesia> list_igleia_local) {
        this.list_igleia_local = list_igleia_local;
    }

    public String getTipoIglesia() {
        return tipoIglesia;
    }

    public void setTipoIglesia(String tipoIglesia) {
        this.tipoIglesia = tipoIglesia;
    }

    
    public void setSelected(Iglesia selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IglesiaFacade getFacade() {
        return ejbFacade;
    }

    public Iglesia prepareCreate() {
        selected = new Iglesia();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("IglesiaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("IglesiaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("IglesiaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Iglesia> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Iglesia getIglesia(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Iglesia> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Iglesia> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Iglesia.class)
    public static class IglesiaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IglesiaController controller = (IglesiaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "iglesiaController");
            return controller.getIglesia(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Iglesia) {
                Iglesia o = (Iglesia) object;
                return getStringKey(o.getIdIglesia());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Iglesia.class.getName()});
                return null;
            }
        }

    }
    
    public List<Iglesia> obtener_lista_iglesia_Local(){
    list_igleia_local=ejbFacade.obtener_lista_iglesia_local(tipoIglesia);
    return list_igleia_local;
   }
      
    public String obtener_iglesiaLocal(){        
    NombreIglesia=ejbFacade.obtener_iglesia_local(tipoIglesia); 
    return  "SAIL - Programa de uso exclusivo para la Iglesia "+NombreIglesia;
    }
    

 
}
