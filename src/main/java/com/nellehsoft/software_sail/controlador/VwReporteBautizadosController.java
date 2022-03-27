/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.VwReporteBautizadosFacade;
import com.nellehsoft.software_sail.persistencia.VwReporteBautizados;
import java.io.Serializable;
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

@Named("vwReporteBautizadosController")
@SessionScoped
public class VwReporteBautizadosController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.VwReporteBautizadosFacade ejbFacade;
    private List<VwReporteBautizados> items = null;
    private VwReporteBautizados selected;
    private List<VwReporteBautizados> list_reporteBautizadosLocal;
    private List<VwReporteBautizados> list_reporteBautizadosTotal;    
    private List<VwReporteBautizados> list_reporteBautizadosHombres;    
    private List<VwReporteBautizados> list_reporteBautizadosMujeres;        
    private List<VwReporteBautizados> list_reporteBautizadosMembrecia;
    private List<VwReporteBautizados> list_reporteBautizadosSellado;
    private Number BautizadosTotal; 
    private Number BautizadosHombres;    
    private Number BautizadosMujeres;
    private Number BautizadosMembrecia;
    private Number BautizadosSellado;   
    private Date fechaInicio;    
    private Date fechaFin;
    
    public VwReporteBautizadosController() {
    }

    public VwReporteBautizados getSelected() {
        return selected;
    }

    public List<VwReporteBautizados> getList_reporteBautizadosTotal() {
        return list_reporteBautizadosTotal;
    }

    public void setList_reporteBautizadosTotal(List<VwReporteBautizados> list_reporteBautizadosTotal) {
        this.list_reporteBautizadosTotal = list_reporteBautizadosTotal;
    }

    public List<VwReporteBautizados> getList_reporteBautizadosHombres() {
        return list_reporteBautizadosHombres;
    }

    public void setList_reporteBautizadosHombres(List<VwReporteBautizados> list_reporteBautizadosHombres) {
        this.list_reporteBautizadosHombres = list_reporteBautizadosHombres;
    }

    public List<VwReporteBautizados> getList_reporteBautizadosMujeres() {
        return list_reporteBautizadosMujeres;
    }

    public void setList_reporteBautizadosMujeres(List<VwReporteBautizados> list_reporteBautizadosMujeres) {
        this.list_reporteBautizadosMujeres = list_reporteBautizadosMujeres;
    }

    public List<VwReporteBautizados> getList_reporteBautizadosMembrecia() {
        return list_reporteBautizadosMembrecia;
    }

    public void setList_reporteBautizadosMembrecia(List<VwReporteBautizados> list_reporteBautizadosMembrecia) {
        this.list_reporteBautizadosMembrecia = list_reporteBautizadosMembrecia;
    }

    public List<VwReporteBautizados> getList_reporteBautizadosSellado() {
        return list_reporteBautizadosSellado;
    }

    public void setList_reporteBautizadosSellado(List<VwReporteBautizados> list_reporteBautizadosSellado) {
        this.list_reporteBautizadosSellado = list_reporteBautizadosSellado;
    }
  
    public List<VwReporteBautizados> getList_reporteBautizadosLocal() {
        return list_reporteBautizadosLocal;
    }

    public void setList_reporteBautizadosLocal(List<VwReporteBautizados> list_reporteBautizadosLocal) {
        this.list_reporteBautizadosLocal = list_reporteBautizadosLocal;
    }

    public Date getFechaInicio() {
     return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin(){
     return fechaFin;        
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Number getBautizadosTotal() {
        return BautizadosTotal;
    }

    public void setBautizadosTotal(Number BautizadosTotal) {
        this.BautizadosTotal = BautizadosTotal;
    }

    public Number getBautizadosMujeres() {
        return BautizadosMujeres;
    }

    public void setBautizadosMujeres(Number BautizadosMujeres) {
        this.BautizadosMujeres = BautizadosMujeres;
    }

    public Number getBautizadosMembrecia() {
        return BautizadosMembrecia;
    }

    public void setBautizadosMembrecia(Number BautizadosMembrecia) {
        this.BautizadosMembrecia = BautizadosMembrecia;
    }

    public Number getBautizadosSellado() {
        return BautizadosSellado;
    }

    public void setBautizadosSellado(Number BautizadosSellado) {
        this.BautizadosSellado = BautizadosSellado;
    }

    public Number getBautizadosHombres() {
        return BautizadosHombres;
    }

    public void setBautizadosHombres(Number BautizadosHombres) {
        this.BautizadosHombres = BautizadosHombres;
    }

    
    
    public void setSelected(VwReporteBautizados selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VwReporteBautizadosFacade getFacade() {
        return ejbFacade;
    }

    public VwReporteBautizados prepareCreate() {
        selected = new VwReporteBautizados();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VwReporteBautizadosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VwReporteBautizadosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VwReporteBautizadosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<VwReporteBautizados> getItems() {
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

    public VwReporteBautizados getVwReporteBautizados(int id) {
        return getFacade().find(id);
    }

    public List<VwReporteBautizados> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<VwReporteBautizados> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = VwReporteBautizados.class)
    public static class VwReporteBautizadosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VwReporteBautizadosController controller = (VwReporteBautizadosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vwReporteBautizadosController");
            return controller.getVwReporteBautizados(getKey(value));
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
            if (object instanceof VwReporteBautizados) {
                VwReporteBautizados o = (VwReporteBautizados) object;
                return getStringKey(o.getIdPersona());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), VwReporteBautizados.class.getName()});
                return null;
            }
        }

    }
    
    public void obtener_ReporteBautizadoLocal(){        
    list_reporteBautizadosLocal=ejbFacade.obtener_reporteBautismo(fechaInicio, fechaFin);
    BautizadosTotal=ejbFacade.obtener_totalBautizados(fechaInicio, fechaFin);
    BautizadosHombres=ejbFacade.obtener_totalBautizadosHombres(fechaInicio, fechaFin);
    BautizadosMujeres=ejbFacade.obtener_totalBautizadosMujeres(fechaInicio, fechaFin);
    BautizadosMembrecia=ejbFacade.obtener_totalBautizadosMembrecia(fechaInicio, fechaFin);
    BautizadosSellado=ejbFacade.obtener_totalBautizadosSellado(fechaInicio, fechaFin);
    }    
}       

