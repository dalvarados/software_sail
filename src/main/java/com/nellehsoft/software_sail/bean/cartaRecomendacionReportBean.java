/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.bean;

import com.nellehsoft.software_sail.persistencia.VwRecomendacionCarta;
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

/**
 *
 * @author Duverdiel
 */
@ManagedBean
@RequestScoped
public class cartaRecomendacionReportBean {
    @EJB 
    private com.nellehsoft.software_sail.negocio.VwRecomendacionCartaFacade ejbFacade;
    private String documentoIdentidadReport;
    private String descripcion;    
    private VwRecomendacionCarta objeto_cartaRecReport;
    private List<VwRecomendacionCarta> list_cartaRecReport =new ArrayList();     

    public String getDocumentoIdentidadReport() {
        return documentoIdentidadReport;
    }

    public void setDocumentoIdentidadReport(String documentoIdentidadReport) {
        this.documentoIdentidadReport = documentoIdentidadReport;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public VwRecomendacionCarta getObjeto_cartaRecReport() {
        return objeto_cartaRecReport;
    }

    public void setObjeto_cartaRecReport(VwRecomendacionCarta objeto_cartaRecReport) {
        this.objeto_cartaRecReport = objeto_cartaRecReport;
    }

    public List<VwRecomendacionCarta> getList_cartaRecReport() {
        return list_cartaRecReport;
    }

    public void setList_cartaRecReport(List<VwRecomendacionCarta> list_cartaRecReport) {
        this.list_cartaRecReport = list_cartaRecReport;
    }

    public void obtener_lista_cartaRecReport(){
    list_cartaRecReport=ejbFacade.obtener_persona_carta(documentoIdentidadReport);
    if (list_cartaRecReport.size()>0) {
        objeto_cartaRecReport = (VwRecomendacionCarta) list_cartaRecReport.get(0);
        objeto_cartaRecReport.setDescripcion(descripcion);
       }   
    }

   public void verPDF() throws JRException, IOException{
    obtener_lista_cartaRecReport();         
    File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/rpt_carta_recomendacion.jasper"));
    
    byte[] bytes=JasperRunManager.runReportToPdf(jasper.getPath(),null, new JRBeanCollectionDataSource(this.getList_cartaRecReport()));
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
    obtener_lista_cartaRecReport();  
    File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/rpt_carta_recomendacion.jasper"));
    JasperPrint jasperPrint= JasperFillManager.fillReport(jasper.getPath(),null,new JRBeanCollectionDataSource(this.getList_cartaRecReport()));
    
    HttpServletResponse  response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    response.addHeader("Content-disposition", "attachment; filename=rpt_carta_recomendacion.pdf");
    ServletOutputStream stream=response.getOutputStream();
    
    JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
    
    stream.flush();
    stream.close();
    FacesContext.getCurrentInstance().responseComplete();
   }
   
}
