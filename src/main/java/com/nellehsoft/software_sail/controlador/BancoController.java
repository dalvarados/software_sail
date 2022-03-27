package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.persistencia.Banco;
import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.BancoFacade;
import com.nellehsoft.software_sail.persistencia.CuentasPuc;

import java.io.Serializable;
import java.util.ArrayList;
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

@Named("bancoController")
@SessionScoped
public class BancoController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.BancoFacade ejbFacade;
    private List<Banco> items = null;
    private Banco selected;
    private List<CuentasPuc> list_cuenta_banco =new ArrayList(); 
    public BancoController() {
    }

    public List<CuentasPuc> getList_cuenta_banco() {
        return list_cuenta_banco;
    }

    public void setList_cuenta_banco(List<CuentasPuc> list_cuenta_banco) {
        this.list_cuenta_banco = list_cuenta_banco;
    }

    public Banco getSelected() {
        return selected;
    }

    public void setSelected(Banco selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private BancoFacade getFacade() {
        return ejbFacade;
    }

    public Banco prepareCreate() {
        getList_obtener_cuentas_puc_banco();
        selected = new Banco();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("BancoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("BancoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("BancoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Banco> getItems() {
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

    public Banco getBanco(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Banco> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Banco> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Banco.class)
    public static class BancoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BancoController controller = (BancoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "bancoController");
            return controller.getBanco(getKey(value));
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
            if (object instanceof Banco) {
                Banco o = (Banco) object;
                return getStringKey(o.getIdBanco());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Banco.class.getName()});
                return null;
            }
        }

    }
    
    public List<CuentasPuc> getList_obtener_cuentas_puc_banco() {           
        list_cuenta_banco=ejbFacade.obtener_cuentasPucBanco();    
    return list_cuenta_banco;      
    } 

}
