/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.bean;

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
 * @author Duverdiel
 */
@Named("DocumentosBeanRBautis")
@ManagedBean 
public class DocumentosBeanRBautis implements Serializable{
    List<Documentos> docBautis;
    File curDirBautis = new File("C:\\NellehSoft\\software_sail\\software_sail\\doc\\registroBautismo\\");
//    File curDirBautis = new File("/home/bd/documentos/registroBautismo");
 private static final long     SerialVersionUID=1l;
 private StreamedContent fileBautis;

    public StreamedContent getFileBautis() {
        return fileBautis;
    }

    public void setFileBautis(StreamedContent fileBautis) {
        this.fileBautis = fileBautis;
    }


    
    public List<Documentos> getDocBautis() {
        docBautis=getAllFilesBautis(curDirBautis);
        return docBautis;
    }

    
    public List<Documentos> getAllFilesBautis(File curDirBautis) {
    docBautis = new ArrayList<>();
    File[] filesListBautis = curDirBautis.listFiles();
      
        for(File f : filesListBautis){
        Documentos objDocumentoBautis=new Documentos();
            if(f.isDirectory())
                getAllFilesBautis(f);
            if(f.isFile()){
     {
         try{
         //InputStream streamBautis = new FileInputStream("/home/bd/documentos/registroBautismo/"+f.getName());
         InputStream streamBautis = new FileInputStream("C:\\NellehSoft\\software_sail\\software_sail\\doc\\registroBautismo\\"+f.getName());
         fileBautis = new DefaultStreamedContent(streamBautis,"application/csv", f.getName());
        }catch (Exception e){ }
     }
     objDocumentoBautis.setFile(fileBautis);
     objDocumentoBautis.setNombre(f.getName());
     docBautis.add(objDocumentoBautis); 
                
                
             }

         }
         
        return docBautis;
    } 
    
}
