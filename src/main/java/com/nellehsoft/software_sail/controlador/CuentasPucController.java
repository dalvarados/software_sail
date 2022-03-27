package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.persistencia.CuentasPuc;
import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.CuentasPucFacade;
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

@Named("cuentasPucController")
@SessionScoped
public class CuentasPucController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.CuentasPucFacade ejbFacade;
    private List<CuentasPuc> items = null;
    private CuentasPuc selected;
    private List<CuentasPuc> list_cuenta_favoritas;

    public CuentasPucController() {
    }
    
    public List<CuentasPuc> getList_cuenta_favoritas() {
        return list_cuenta_favoritas;
    }

    public void setList_cuenta_favoritas(List<CuentasPuc> list_cuenta_favoritas) {
        this.list_cuenta_favoritas = list_cuenta_favoritas;
    }


    public CuentasPuc getSelected() {
        return selected;
    }

    public void setSelected(CuentasPuc selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CuentasPucFacade getFacade() {
        return ejbFacade;
    }

    public CuentasPuc prepareCreate() {
        selected = new CuentasPuc();
        selected.setFlag(1);
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CuentasPucCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CuentasPucUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CuentasPucDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<CuentasPuc> getItems() {
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

    public CuentasPuc getCuentasPuc(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CuentasPuc> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CuentasPuc> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CuentasPuc.class)
    public static class CuentasPucControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CuentasPucController controller = (CuentasPucController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cuentasPucController");
            return controller.getCuentasPuc(getKey(value));
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
            if (object instanceof CuentasPuc) {
                CuentasPuc o = (CuentasPuc) object;
                return getStringKey(o.getIdPuc());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CuentasPuc.class.getName()});
                return null;
            }
        }

    }
    
    public List<CuentasPuc> getList_obtener_cuentas_favoritas() {           
        list_cuenta_favoritas=ejbFacade.obtener_cuentasPucFavoritas();
    return list_cuenta_favoritas;      
    }    

}
