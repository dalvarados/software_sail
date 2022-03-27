package com.nellehsoft.software_sail.bean;

import com.nellehsoft.software_sail.persistencia.VwPresentacionNinos;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@ManagedBean
@RequestScoped
public class PresentacionNinosReportBean {
    @EJB 
    private com.nellehsoft.software_sail.negocio.VwPresentacionNinosFacade ejbFacade;
    private String documentoIdentidadReport;
    private VwPresentacionNinos objeto_PresentacionNinos;
    private List<VwPresentacionNinos> list_PresentacionNinos =new ArrayList();     

    public String getDocumentoIdentidadReport() {
        return documentoIdentidadReport;
    }

    public void setDocumentoIdentidadReport(String documentoIdentidadReport) {
        this.documentoIdentidadReport = documentoIdentidadReport;
    }

    public VwPresentacionNinos getObjeto_PresentacionNinos() {
        return objeto_PresentacionNinos;
    }

    public void setObjeto_PresentacionNinos(VwPresentacionNinos objeto_PresentacionNinos) {
        this.objeto_PresentacionNinos = objeto_PresentacionNinos;
    }

    public List<VwPresentacionNinos> getList_PresentacionNinos() {
        return list_PresentacionNinos;
    }

    public void setList_PresentacionNinos(List<VwPresentacionNinos> list_PresentacionNinos) {
        this.list_PresentacionNinos = list_PresentacionNinos;
    }
  

    public void obtener_lista_presentacionNinosReport(){
    list_PresentacionNinos=ejbFacade.obtener_presentacion_nino(documentoIdentidadReport);
    if (list_PresentacionNinos.size()>0) {
        objeto_PresentacionNinos = (VwPresentacionNinos) list_PresentacionNinos.get(0);
       }   
    }

   public void verPDF() throws JRException, IOException{
    obtener_lista_presentacionNinosReport();        
    File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/rpt_presentacion_ninos.jasper"));
    
    byte[] bytes=JasperRunManager.runReportToPdf(jasper.getPath(),null, new JRBeanCollectionDataSource(this.getList_PresentacionNinos()));
    HttpServletResponse  response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    response.setContentType("application/pdf");
    response.setContentLength(bytes.length);
        try (ServletOutputStream outStream = response.getOutputStream()) {
            outStream.write(bytes,0,bytes.length);
            outStream.flush();
        }
    
    FacesContext.getCurrentInstance().responseComplete();

   }    
   public void exportarPDF() throws JRException, IOException{
    obtener_lista_presentacionNinosReport();  
    File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/rpt_presentacion_ninos.jasper"));
    JasperPrint jasperPrint= JasperFillManager.fillReport(jasper.getPath(),null,new JRBeanCollectionDataSource(this.getList_PresentacionNinos()));
    
    HttpServletResponse  response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    response.addHeader("Content-disposition", "attachment; filename=rpt_presentacion_nino.pdf");
    ServletOutputStream stream=response.getOutputStream();
    
    JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
    
    stream.flush();
    stream.close();
    FacesContext.getCurrentInstance().responseComplete();
   }
   
}
