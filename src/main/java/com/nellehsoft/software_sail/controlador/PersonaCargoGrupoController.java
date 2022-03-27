package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.persistencia.PersonaCargoGrupo;
import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.PersonaCargoGrupoFacade;
import com.nellehsoft.software_sail.persistencia.Grupo;
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
import javax.faces.event.AjaxBehaviorEvent;

@Named("personaCargoGrupoController")
@SessionScoped
public class PersonaCargoGrupoController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.PersonaCargoGrupoFacade ejbFacade;
    private List<PersonaCargoGrupo> items = null;
    private PersonaCargoGrupo selected;
    private List<PersonaCargoGrupo> list_persona_grupo= null;
    private Grupo grupo;      

    public PersonaCargoGrupoController() {
    }

    public PersonaCargoGrupo getSelected() {
        return selected;
    }

    public List<PersonaCargoGrupo> getList_persona_grupo() {
        return list_persona_grupo;
    }

    public void setList_persona_grupo(List<PersonaCargoGrupo> list_persona_grupo) {
        this.list_persona_grupo = list_persona_grupo;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    
    
    public void setSelected(PersonaCargoGrupo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PersonaCargoGrupoFacade getFacade() {
        return ejbFacade;
    }

    public PersonaCargoGrupo prepareCreate() {
        selected = new PersonaCargoGrupo();
        initializeEmbeddableKey();        
       return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PersonaCargoGrupoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            getList_rep_asistencia_grupo();
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PersonaCargoGrupoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PersonaCargoGrupoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PersonaCargoGrupo> getItems() {
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

    public PersonaCargoGrupo getPersonaCargoGrupo(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PersonaCargoGrupo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PersonaCargoGrupo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = PersonaCargoGrupo.class)
    public static class PersonaCargoGrupoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonaCargoGrupoController controller = (PersonaCargoGrupoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personaCargoGrupoController");
            return controller.getPersonaCargoGrupo(getKey(value));
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
            if (object instanceof PersonaCargoGrupo) {
                PersonaCargoGrupo o = (PersonaCargoGrupo) object;
                return getStringKey(o.getIdPersonaCargoGrupo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PersonaCargoGrupo.class.getName()});
                return null;
            }
        }

    }
 
    public void getList_rep_asistencia_grupo() {
    list_persona_grupo=ejbFacade.obtener_mimbros_grupo(selected.getIdGrupo().getNombreGrupo(),selected.getFecha());  
    }
}
