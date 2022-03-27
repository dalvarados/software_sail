package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.persistencia.VwAsistenciaMensual;
import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.VwAsistenciaMensualFacade;
import com.nellehsoft.software_sail.persistencia.Comite;

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
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@Named("vwAsistenciaMensualController")
@SessionScoped
public class VwAsistenciaMensualController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.VwAsistenciaMensualFacade ejbFacade;
    private List<VwAsistenciaMensual> items = null;
    private VwAsistenciaMensual selected;
    private List<VwAsistenciaMensual> list_asistencia_comite= null;
    private String comite;    
    private ChartSeries asistenciaMensual;
    private BarChartModel barModel;
    
    
    public VwAsistenciaMensualController() {
    }
    
  @PostConstruct
   public void init(){
   }
  
   public VwAsistenciaMensualFacade getEjbFacade() {
        return ejbFacade;
    }

    public ChartSeries getAsistenciaMensual() {
        return asistenciaMensual;
    }

    public void setAsistenciaMensual(ChartSeries asistenciaMensual) {
        this.asistenciaMensual = asistenciaMensual;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public void setEjbFacade(VwAsistenciaMensualFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public List<VwAsistenciaMensual> getList_asistencia_comite() {
     list_asistencia_comite=ejbFacade.obtener_vista_asistencia(comite); 
     createBarModel();
     return list_asistencia_comite;
    }

    public void setList_asistencia_comite(List<VwAsistenciaMensual> list_asistencia_comite) {
        this.list_asistencia_comite = list_asistencia_comite;
    }

    public String getComite() {
        return comite;
    }

    public void setComite(String comite) {
        this.comite = comite;
    }

    
    
    public VwAsistenciaMensual getSelected() {
        return selected;
    }

    public void setSelected(VwAsistenciaMensual selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VwAsistenciaMensualFacade getFacade() {
        return ejbFacade;
    }

    public VwAsistenciaMensual prepareCreate() {
        selected = new VwAsistenciaMensual();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VwAsistenciaMensualCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VwAsistenciaMensualUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VwAsistenciaMensualDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<VwAsistenciaMensual> getItems() {
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

    public VwAsistenciaMensual getVwAsistenciaMensual(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<VwAsistenciaMensual> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<VwAsistenciaMensual> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = VwAsistenciaMensual.class)
    public static class VwAsistenciaMensualControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VwAsistenciaMensualController controller = (VwAsistenciaMensualController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vwAsistenciaMensualController");
            return controller.getVwAsistenciaMensual(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof VwAsistenciaMensual) {
                VwAsistenciaMensual o = (VwAsistenciaMensual) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), VwAsistenciaMensual.class.getName()});
                return null;
            }
        }

    }
    
         public void obtener_ajax_asistencia_comite(AjaxBehaviorEvent event){
             list_asistencia_comite=ejbFacade.obtener_vista_asistencia(comite);
        }
    
    public List<VwAsistenciaMensual> getList_rep_asistencia_comite() {
     list_asistencia_comite=ejbFacade.obtener_vista_asistencia(comite);  
    return list_asistencia_comite;
    }   
    
private BarChartModel initBarModel(){
    BarChartModel model=new BarChartModel();
    asistenciaMensual=new ChartSeries();
    asistenciaMensual.setLabel("Cantidad Asistentes");
    for (VwAsistenciaMensual cData: list_asistencia_comite){
       asistenciaMensual.set(cData.getFecha(),cData.getValor()) ;
    }
    model.addSeries(asistenciaMensual);
    return model;
}

private void createBarModel(){
    barModel=initBarModel();
    barModel.setTitle("Asistencia Mensual");
    barModel.setLegendPosition("ne");
    barModel.setStacked(true);
    
    Axis xAxis=barModel.getAxis(AxisType.X);
    xAxis.setLabel("Categoria");
    
    Axis yAxis=barModel.getAxis(AxisType.Y);
    xAxis.setLabel("Mes");
    yAxis.setMin(0);
    yAxis.setMax(500);
   }

}
