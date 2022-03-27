/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.persistencia;

/**
 *
 * @author Duverdiel
 */
import org.primefaces.model.StreamedContent;

/**
 *
 * @author User
 */
public class Documentos {
    
    private String nombre ;
    private StreamedContent file ;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
    
    
}

