package com.nellehsoft.software_sail.controlador;

import com.nellehsoft.software_sail.persistencia.VwDiezmoMensual;
import com.nellehsoft.software_sail.controlador.util.JsfUtil;
import com.nellehsoft.software_sail.controlador.util.JsfUtil.PersistAction;
import com.nellehsoft.software_sail.negocio.VwDiezmoMensualFacade;

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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@Named("vwDiezmoMensualController")
@SessionScoped
public class VwDiezmoMensualController implements Serializable {

    @EJB
    private com.nellehsoft.software_sail.negocio.VwDiezmoMensualFacade ejbFacade;
    private List<VwDiezmoMensual> items = null;
    private VwDiezmoMensual selected;
    private ChartSeries diezmoMensual;
    private BarChartModel barModel;    
    private List<VwDiezmoMensual> list_diezmo= null;    

    public VwDiezmoMensualController() {
    }

  @PostConstruct
   public void init(){
   }    
    public VwDiezmoMensual getSelected() {
        return selected;
    }

    public VwDiezmoMensualFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(VwDiezmoMensualFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public ChartSeries getDiezmoMensual() {
        return diezmoMensual;
    }

    public void setDiezmoMensual(ChartSeries diezmoMensual) {
        this.diezmoMensual = diezmoMensual;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public List<VwDiezmoMensual> getList_diezmo() {
     list_diezmo=ejbFacade.findAll(); 
     createBarModel();        
     return list_diezmo;
    }

    public void setList_diezmo(List<VwDiezmoMensual> list_diezmo) {
        this.list_diezmo = list_diezmo;
    }


    
    public void setSelected(VwDiezmoMensual selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VwDiezmoMensualFacade getFacade() {
        return ejbFacade;
    }

    public VwDiezmoMensual prepareCreate() {
        selected = new VwDiezmoMensual();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VwDiezmoMensualCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VwDiezmoMensualUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VwDiezmoMensualDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<VwDiezmoMensual> getItems() {
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

    public VwDiezmoMensual getVwDiezmoMensual(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<VwDiezmoMensual> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<VwDiezmoMensual> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = VwDiezmoMensual.class)
    public static class VwDiezmoMensualControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VwDiezmoMensualController controller = (VwDiezmoMensualController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vwDiezmoMensualController");
            return controller.getVwDiezmoMensual(getKey(value));
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
            if (object instanceof VwDiezmoMensual) {
                VwDiezmoMensual o = (VwDiezmoMensual) object;
                return getStringKey(o.getFecha());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), VwDiezmoMensual.class.getName()});
                return null;
            }
        }

    }

private BarChartModel initBarModel(){
    BarChartModel model=new BarChartModel();
    diezmoMensual=new ChartSeries();
    diezmoMensual.setLabel("Valor Diezmo");
    for (VwDiezmoMensual cData: list_diezmo){
       diezmoMensual.set(cData.getFecha(),cData.getValor()) ;
    }
    model.addSeries(diezmoMensual);
    return model;
}

private void createBarModel(){
    barModel=initBarModel();
    barModel.setTitle("Diezmo Mensual");
    barModel.setLegendPosition("ne");
    barModel.setStacked(true);
    
    Axis xAxis=barModel.getAxis(AxisType.X);
    xAxis.setLabel("Categoria");
    
    Axis yAxis=barModel.getAxis(AxisType.Y);
    xAxis.setLabel("Mes");
    yAxis.setMin(0);
    yAxis.setMax(15000000);
   }    

}
