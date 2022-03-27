package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.persistencia.PersonaCargoComite;
import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.PersonaCargoComiteFacade;
import com.nellehsoft.software_sail.persistencia.Comite;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
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

@Named("personaCargoComiteController")
@SessionScoped
public class PersonaCargoComiteController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.PersonaCargoComiteFacade ejbFacade;
    private List<PersonaCargoComite> items = null;
    private PersonaCargoComite selected;
    private List<PersonaCargoComite> list_persona_comite= null;
    private Comite comite;    

    public PersonaCargoComiteController() {
    }

    public List<PersonaCargoComite> getList_persona_comite() {
        return list_persona_comite;
    }

    public void setList_persona_comite(List<PersonaCargoComite> list_persona_comite) {
        this.list_persona_comite = list_persona_comite;
    }

    public Comite getComite() {
        return comite;
    }

    public void setComite(Comite comite) {
        this.comite = comite;
    }


    
    public PersonaCargoComite getSelected() {
        return selected;
    }

    public void setSelected(PersonaCargoComite selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PersonaCargoComiteFacade getFacade() {
        return ejbFacade;
    }

    public PersonaCargoComite prepareCreate() {
        selected = new PersonaCargoComite();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PersonaCargoComiteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            getList_rep_asistencia_comite();
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PersonaCargoComiteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PersonaCargoComiteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PersonaCargoComite> getItems() {
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

    public PersonaCargoComite getPersonaCargoComite(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PersonaCargoComite> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PersonaCargoComite> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PersonaCargoComite.class)
    public static class PersonaCargoComiteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonaCargoComiteController controller = (PersonaCargoComiteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personaCargoComiteController");
            return controller.getPersonaCargoComite(getKey(value));
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
            if (object instanceof PersonaCargoComite) {
                PersonaCargoComite o = (PersonaCargoComite) object;
                return getStringKey(o.getIdPersonaCargoComite());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PersonaCargoComite.class.getName()});
                return null;
            }
        }

    }

    public void getList_rep_asistencia_comite() {
    list_persona_comite=ejbFacade.obtener_mimbros_comite(selected.getIdComite().getNombreComite(),selected.getFecha());  
   }     
    
}
