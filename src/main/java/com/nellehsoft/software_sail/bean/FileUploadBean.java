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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.ParseException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class FileUploadBean implements Serializable{	
	private static final long serialVersionUID = 4352236420460919694L;	
	private UploadedFile file;  
       private String destination="C:\\NellehSoft\\software_sail\\software_sail\\doc\\cartasRecomendacion\\";
       // private String destination="/home/bd/documentos/cartasRecomendacion/";
    
       public UploadedFile getFile() {  
        return file;  
    }  
  
    public void setFile(UploadedFile file) {  
        this.file = file;  
    }  
  
    public void copyFile(String fileName, InputStream in) throws FileNotFoundException, ParseException {
           try {            
              
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(destination + fileName));
              
                int read = 0;
                byte[] bytes = new byte[1024];
              
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
              
                in.close();
                out.flush();
                out.close();
              
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
    public void upload(FileUploadEvent event) {
        System.err.println("event.getFile().getFileName() = " + event.getFile().getFileName());     
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}