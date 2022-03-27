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

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named("calculadoraBean")

public class CalculadoraBean   implements Serializable{

    private int numBillete100;
    private int numBillete50;
    private int numBillete20;
    private int numBillete10;
    private int numBillete5;
    private int numBillete2;
    private int numBillete1;
    private int monedas;
    private int resultado;

   
    public int getNumBillete100() {
        return numBillete100;
    }

    public void setNumBillete100(int numBillete100) {
        this.numBillete100 = numBillete100;
    }

    public int getNumBillete50() {
        return numBillete50;
    }

    public void setNumBillete50(int numBillete50) {
        this.numBillete50 = numBillete50;
    }

    public int getNumBillete20() {
        return numBillete20;
    }

    public void setNumBillete20(int numBillete20) {
        this.numBillete20 = numBillete20;
    }

    public int getNumBillete10() {
        return numBillete10;
    }

    public void setNumBillete10(int numBillete10) {
        this.numBillete10 = numBillete10;
    }

    public int getNumBillete5() {
        return numBillete5;
    }

    public void setNumBillete5(int numBillete5) {
        this.numBillete5 = numBillete5;
    }

    public int getNumBillete2() {
        return numBillete2;
    }

    public void setNumBillete2(int numBillete2) {
        this.numBillete2 = numBillete2;
    }

    public int getNumBillete1() {
        return numBillete1;
    }

    public void setNumBillete1(int numBillete1) {
        this.numBillete1 = numBillete1;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    
    
    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }
    public int calcular(){
       resultado= (numBillete100*100000) + 
                   (numBillete50*50000) +
                   (numBillete20*20000) +
                   (numBillete10*10000) +
                   (numBillete5*5000) +
                   (numBillete2*2000) +
                   (numBillete1*1000) +
                    monedas; 
               this.setNumBillete100(0);
       return resultado ;

    } 
    
}
