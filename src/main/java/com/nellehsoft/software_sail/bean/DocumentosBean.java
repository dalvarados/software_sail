/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.bean;

/**
 *
 * @author Duverdiel
 */
import com.nellehsoft.software_sail.persistencia.Documentos;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author User
 */
@Named("DocumentosBean")
@ManagedBean  
public class DocumentosBean implements Serializable{
    List<Documentos> doc;
    File curDir = new File("C:\\NellehSoft\\software_sail\\software_sail\\doc\\cartasRecomendacion");
//    File curDir = new File("/home/bd/documentos/cartasRecomendacion");
 private static final long     SerialVersionUID=1l;
 private StreamedContent file;

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
 


 
    public List<Documentos> getDoc() {
        doc=getAllFiles(curDir);
        return doc;
    }
    
    public List<Documentos> getAllFiles(File curDir) {
    doc = new ArrayList<>();
    File[] filesList = curDir.listFiles();
      
        for(File f : filesList){
        Documentos objDocumento=new Documentos();
            if(f.isDirectory())
                getAllFiles(f);
            if(f.isFile()){
     {
         try{
//         InputStream stream = new FileInputStream("/home/bd/documentos/cartasRecomendacion/"+f.getName());
        InputStream stream = new FileInputStream("C:\\NellehSoft\\software_sail\\software_sail\\doc\\cartasRecomendacion\\"+f.getName());
         file = new DefaultStreamedContent(stream,"application/csv", f.getName());
        }catch (Exception e){ }
     }
     objDocumento.setFile(file);
     objDocumento.setNombre(f.getName());
     doc.add(objDocumento); 
         }

      }
        return doc;
    }
    
}

