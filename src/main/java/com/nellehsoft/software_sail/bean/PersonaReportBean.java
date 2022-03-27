/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.bean;

import com.nellehsoft.software_sail.persistencia.Persona;
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
public class PersonaReportBean {
    @EJB 
    private com.nellehsoft.software_sail.negocio.PersonaFacade ejbFacade;
    private String documentoIdentidadReport;
    private Persona objeto_personaReport;
    private List<Persona> list_personaReport =new ArrayList();     

    public String getDocumentoIdentidadReport() {
        return documentoIdentidadReport;
    }

    public void setDocumentoIdentidadReport(String documentoIdentidadReport) {
        this.documentoIdentidadReport = documentoIdentidadReport;
    }

    public Persona getObjeto_personaReport() {
        return objeto_personaReport;
    }

    public void setObjeto_personaReport(Persona objeto_personaReport) {
        this.objeto_personaReport = objeto_personaReport;
    }

    public List<Persona> getList_personaReport() {
        return list_personaReport;
    }

    public void setList_personaReport(List<Persona> list_personaReport) {
        this.list_personaReport = list_personaReport;
    }

//    public void obtener_lista_personaReport(){
//    list_personaReport=ejbFacade.obtener_persona(documentoIdentidadReport);
//    if (list_personaReport.size()>0) {
//        objeto_personaReport = (Persona) list_personaReport.get(0);
//       }   
//    }

//   public void verPDF() throws JRException, IOException{
//    obtener_lista_personaReport();         
//    File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/rpt_bautismos.jasper"));
//    
//    byte[] bytes=JasperRunManager.runReportToPdf(jasper.getPath(),null, new JRBeanCollectionDataSource(this.getList_personaReport()));
//    HttpServletResponse  response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//    response.setContentType("application/pdf");
//    response.setContentLength(bytes.length);
//        try (ServletOutputStream outStream = response.getOutputStream()) {
//            outStream.write(bytes,0,bytes.length);
//            outStream.flush();
//        }
//    
//    FacesContext.getCurrentInstance().responseComplete();
//
//   }    
//   public void exportarPDF() throws JRException, IOException{
//    obtener_lista_personaReport();  
//    File jasper= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/rpt_bautismos.jasper"));
//    JasperPrint jasperPrint= JasperFillManager.fillReport(jasper.getPath(),null,new JRBeanCollectionDataSource(this.getList_personaReport()));
//    
//    HttpServletResponse  response=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//    response.addHeader("Content-disposition", "attachment; filename=ReporteBautismo.pdf");
//    ServletOutputStream stream=response.getOutputStream();
//    
//    JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
//    
//    stream.flush();
//    stream.close();
//    FacesContext.getCurrentInstance().responseComplete();
//   }
   
}
