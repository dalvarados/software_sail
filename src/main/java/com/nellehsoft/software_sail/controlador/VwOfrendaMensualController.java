package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.persistencia.VwOfrendaMensual;
import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.VwOfrendaMensualFacade;
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
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@Named("vwOfrendaMensualController")
@SessionScoped
public class VwOfrendaMensualController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.VwOfrendaMensualFacade ejbFacade;
    private List<VwOfrendaMensual> items = null;
    private VwOfrendaMensual selected;
    private List<VwOfrendaMensual> list_ofrenda_comite= null;
    private String comite;    
    private ChartSeries ofrendaMensual;
    private BarChartModel barModel;
    
    
    public VwOfrendaMensualController() {
    }
    
    public VwOfrendaMensualFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(VwOfrendaMensualFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }
    
    public List<VwOfrendaMensual> getList_ofrenda_comite() {
     list_ofrenda_comite=ejbFacade.obtener_vista_ofrenda(comite); 
     createBarModel();
     return list_ofrenda_comite;
    }

    public void setList_ofrenda_comite(List<VwOfrendaMensual> list_ofrenda_comite) {
        this.list_ofrenda_comite = list_ofrenda_comite;
    }

    public String getComite() {
        return comite;
    }

    public void setComite(String comite) {
        this.comite = comite;
    }

    public ChartSeries getOfrendaMensual() {
        return ofrendaMensual;
    }

    public void setOfrendaMensual(ChartSeries ofrendaMensual) {
        this.ofrendaMensual = ofrendaMensual;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    
   public VwOfrendaMensual getSelected() {
        return selected;
    }

    public void setSelected(VwOfrendaMensual selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VwOfrendaMensualFacade getFacade() {
        return ejbFacade;
    }

    public VwOfrendaMensual prepareCreate() {
        selected = new VwOfrendaMensual();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VwOfrendaMensualCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VwOfrendaMensualUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VwOfrendaMensualDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<VwOfrendaMensual> getItems() {
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

    public VwOfrendaMensual getVwOfrendaMensual(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<VwOfrendaMensual> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<VwOfrendaMensual> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = VwOfrendaMensual.class)
    public static class VwOfrendaMensualControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VwOfrendaMensualController controller = (VwOfrendaMensualController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vwOfrendaMensualController");
            return controller.getVwOfrendaMensual(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof VwOfrendaMensual) {
                VwOfrendaMensual o = (VwOfrendaMensual) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), VwOfrendaMensual.class.getName()});
                return null;
            }
        }

    }
  
    public void obtener_ajax_ofrenda_comite(AjaxBehaviorEvent event){
    list_ofrenda_comite=ejbFacade.obtener_vista_ofrenda(comite);
    getList_ofrenda_comite();
    }
    
    public List<VwOfrendaMensual> getList_rep_ofrenda_comite() {
     list_ofrenda_comite=ejbFacade.obtener_vista_ofrenda(comite);  
    return list_ofrenda_comite;
    }   
    
private BarChartModel initBarModel(){
    BarChartModel model=new BarChartModel();
    ofrendaMensual=new ChartSeries();
    ofrendaMensual.setLabel("Valor Ofrenda");
    for (VwOfrendaMensual cData: list_ofrenda_comite){
       ofrendaMensual.set(cData.getFecha(),cData.getValor()) ;
    }
    model.addSeries(ofrendaMensual);
    return model;
}

private void createBarModel(){
    barModel=initBarModel();
    barModel.setTitle("Ofrenda Mensual");
    barModel.setLegendPosition("ne");
    barModel.setStacked(true);
    
    Axis xAxis=barModel.getAxis(AxisType.X);
    xAxis.setLabel("Categoria");
    
    Axis yAxis=barModel.getAxis(AxisType.Y);
    xAxis.setLabel("Mes");
    yAxis.setMin(0);
    yAxis.setMax(1000000);
   }    

}
