/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.bean;

import java.io.Serializable;

/**
 *
 * @author Duverdiel
 */
public class AsientoContableTotales implements Serializable {
    
private Integer valor_debito;
private Integer valor_credito;
private Integer diferencia;

    public AsientoContableTotales() {
    }

    public Integer getValor_debito() {
        return valor_debito;
    }

    public void setValor_debito(Integer valor_debito) {
        this.valor_debito = valor_debito;
    }

    public Integer getValor_credito() {
        return valor_credito;
    }

    public void setValor_credito(Integer valor_credito) {
        this.valor_credito = valor_credito;
    }

    public Integer getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(Integer diferencia) {
        this.diferencia = diferencia;
    }


    
}
