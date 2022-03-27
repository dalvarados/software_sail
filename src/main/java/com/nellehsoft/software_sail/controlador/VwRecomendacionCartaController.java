package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.persistencia.VwRecomendacionCarta;
import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.VwRecomendacionCartaFacade;

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

@Named("vwRecomendacionCartaController")
@SessionScoped
public class VwRecomendacionCartaController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.VwRecomendacionCartaFacade ejbFacade;
    private List<VwRecomendacionCarta> items = null;
    private VwRecomendacionCarta selected;
    private List<VwRecomendacionCarta> list_cartaRecomendacion= null;  
    public VwRecomendacionCartaController() {
    }

    public VwRecomendacionCarta getSelected() {
        return selected;
    }

    public void setSelected(VwRecomendacionCarta selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VwRecomendacionCartaFacade getFacade() {
        return ejbFacade;
    }

    public List<VwRecomendacionCarta> getList_carta() {
     list_cartaRecomendacion=ejbFacade.findAll(); 
     return list_cartaRecomendacion;
    }    

    
    public VwRecomendacionCarta prepareCreate() {
        selected = new VwRecomendacionCarta();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VwRecomendacionCartaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VwRecomendacionCartaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VwRecomendacionCartaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<VwRecomendacionCarta> getItems() {
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

    public VwRecomendacionCarta getVwRecomendacionCarta(int id) {
        return getFacade().find(id);
    }

    public List<VwRecomendacionCarta> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<VwRecomendacionCarta> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = VwRecomendacionCarta.class)
    public static class VwRecomendacionCartaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VwRecomendacionCartaController controller = (VwRecomendacionCartaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vwRecomendacionCartaController");
            return controller.getVwRecomendacionCarta(getKey(value));
        }

        int getKey(String value) {
            int key;
            key = Integer.parseInt(value);
            return key;
        }

        String getStringKey(int value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof VwRecomendacionCarta) {
                VwRecomendacionCarta o = (VwRecomendacionCarta) object;
                return getStringKey(o.getIdPersona());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), VwRecomendacionCarta.class.getName()});
                return null;
            }
        }

    }

}
