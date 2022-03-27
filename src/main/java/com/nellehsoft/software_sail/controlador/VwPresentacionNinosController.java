package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.VwPresentacionNinosFacade;
import com.nellehsoft.software_sail.persistencia.VwPresentacionNinos;

import java.io.Serializable;
import java.util.ArrayList;
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

@Named("vwPresentacionNinosController")
@SessionScoped
public class VwPresentacionNinosController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.VwPresentacionNinosFacade ejbFacade;
    private List<VwPresentacionNinos> items = null;
    private VwPresentacionNinos selected;   
    private String documento;
    private List<VwPresentacionNinos> lista_presentacion_ninos =new ArrayList();

    public VwPresentacionNinosController() {
    }
    
    @PostConstruct  
    public void init(){ 
        obtener_lista_presentacion_ninos();
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public List<VwPresentacionNinos> getLista_presentacion_ninos() {
        return lista_presentacion_ninos;
        
    }

    public void setLista_presentacion_ninos(List<VwPresentacionNinos> lista_presentacion_ninos) {
        this.lista_presentacion_ninos = lista_presentacion_ninos;
    }


    
    public VwPresentacionNinos getSelected() {
        return selected;
    }

    public void setSelected(VwPresentacionNinos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VwPresentacionNinosFacade getFacade() {
        return ejbFacade;
    }

    public VwPresentacionNinos prepareCreate() {
        selected = new VwPresentacionNinos();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VwPresentacionNinosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            //items = null;    // Invalidate list of items to trigger re-query.
        }
       
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VwPresentacionNinosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VwPresentacionNinosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<VwPresentacionNinos> getItems() {
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

    public VwPresentacionNinos getVwPresentacionNinos(int id) {
        return getFacade().find(id);
    }

    public List<VwPresentacionNinos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<VwPresentacionNinos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = VwPresentacionNinos.class)
    public static class VwPresentacionNinosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VwPresentacionNinosController controller = (VwPresentacionNinosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vwPresentacionNinosController");
            return controller.getVwPresentacionNinos(getKey(value));
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
            if (object instanceof VwPresentacionNinos) {
                VwPresentacionNinos o = (VwPresentacionNinos) object;
                return getStringKey(o.getIdPersona());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), VwPresentacionNinos.class.getName()});
                return null;
            }
        }

    }
   
    public void obtener_lista_presentacion_ninos(){
   lista_presentacion_ninos=ejbFacade.obtener_presentacionNinos();
   }    
    
     public void obtener_lista_presentacion_ninos_buscar(){
   lista_presentacion_ninos=ejbFacade.obtener_presentacion_nino(documento);
   }    

}
