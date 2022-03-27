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

@Named("DocumentosBeanCMemb")
@ManagedBean  
public class DocumentosBeanCMemb implements Serializable{
    List<Documentos> docMemb;
    File curDirMemb = new File("C:\\NellehSoft\\software_sail\\software_sail\\doc\\cartasMembresia\\");
    //File curDirMemb = new File("/home/bd/documentos/cartasMembresia");
 private static final long     SerialVersionUID=1l;
 private StreamedContent fileMemb;

    public StreamedContent getFileMemb() {
        return fileMemb;
    }

    public void setFileMemb(StreamedContent fileMemb) {
        this.fileMemb = fileMemb;
    }


    
    public List<Documentos> getDocMemb() {
        docMemb=getAllFilesMemb(curDirMemb);
        return docMemb;
    }

    
    public List<Documentos> getAllFilesMemb(File curDirMemb) {
    docMemb = new ArrayList<>();
    File[] filesListMemb = curDirMemb.listFiles();
      
        for(File f : filesListMemb){
        Documentos objDocumentoMemb=new Documentos();
            if(f.isDirectory())
                getAllFilesMemb(f);
            if(f.isFile()){
     {
         try{
        // InputStream streamMemb = new FileInputStream("/home/bd/documentos/cartasMembresia/"+f.getName());
         InputStream streamMemb = new FileInputStream("C:\\NellehSoft\\software_sail\\software_sail\\doc\\cartasMembresia\\"+f.getName());
         fileMemb = new DefaultStreamedContent(streamMemb,"application/csv", f.getName());
        }catch (Exception e){ }
     }
     objDocumentoMemb.setFile(fileMemb);
     objDocumentoMemb.setNombre(f.getName());
     docMemb.add(objDocumentoMemb); 
                
                
             }

         }
         
        return docMemb;
    }    
    
}
