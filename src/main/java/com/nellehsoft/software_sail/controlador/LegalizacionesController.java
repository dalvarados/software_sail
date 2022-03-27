package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.persistencia.Legalizaciones;
import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.LegalizacionesFacade;
import com.nellehsoft.software_sail.persistencia.AsientoContable;
import com.nellehsoft.software_sail.persistencia.Persona;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("legalizacionesController")
@SessionScoped
public class LegalizacionesController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.LegalizacionesFacade ejbFacade;
    @EJB
    private com.nellehsoft.software_sail.negocio.AsientoContableFacade ejbFacadeAsientoContable;
    @EJB 
    private com.nellehsoft.software_sail.negocio.PersonaFacade ejbFacadePersona;
    
    private List<Legalizaciones> items = null;
    private Legalizaciones selected;
    private List<AsientoContable> list_legalizacion;    
    private List<Persona> rol_tercero;

    public LegalizacionesController() {
    }

    public Legalizaciones getSelected() {
        return selected;
    }

    public void setSelected(Legalizaciones selected) {
        this.selected = selected;
    }

    public List<AsientoContable> getList_legalizacion() {
        return list_legalizacion;
    }

    public void setList_legalizacion(List<AsientoContable> list_legalizacion) {
        this.list_legalizacion = list_legalizacion;
    }

    public List<Persona> getRol_tercero() {
        return rol_tercero;
    }

    public void setRol_tercero(List<Persona> rol_tercero) {
        this.rol_tercero = rol_tercero;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LegalizacionesFacade getFacade() {
        return ejbFacade;
    }

    public Legalizaciones prepareCreate() {
        selected = new Legalizaciones();
        obtener_rol_tercero();
        getList_obtener_AsientoContableTipoContableLegalizacion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LegalizacionesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LegalizacionesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LegalizacionesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Legalizaciones> getItems() {
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

    public Legalizaciones getLegalizaciones(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Legalizaciones> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Legalizaciones> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Legalizaciones.class)
    public static class LegalizacionesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LegalizacionesController controller = (LegalizacionesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "legalizacionesController");
            return controller.getLegalizaciones(getKey(value));
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
            if (object instanceof Legalizaciones) {
                Legalizaciones o = (Legalizaciones) object;
                return getStringKey(o.getIdLegalizacion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Legalizaciones.class.getName()});
                return null;
            }
        }

    }
    
    public List<AsientoContable> getList_obtener_AsientoContableTipoContableLegalizacion() {           
        list_legalizacion=ejbFacadeAsientoContable.obtener_ListAsientoContableTipoContableLegalizacion("Egreso");       
        return list_legalizacion;      
    }
    
    public void obtener_rol_tercero(){ 
    rol_tercero=ejbFacadePersona.obtener_rol_tercero();
    }    

}
