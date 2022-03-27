/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.CuentasPuc;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Duverdiel
 */
@Stateless
public class CuentasPucFacade extends AbstractFacade<CuentasPuc> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentasPucFacade() {
        super(CuentasPuc.class);
    }

    public List<CuentasPuc> obtener_cuentasPucFavoritas(){
        String sql = "SELECT id_puc,codigo_cuenta,concat(cast(codigo_cuenta as char(100)),' -> ',nombre) nombre,flag   FROM bd_sail.cuentas_puc where flag=1";    
        TypedQuery<CuentasPuc> lQuery = (TypedQuery<CuentasPuc>) em.createNativeQuery(sql, CuentasPuc.class);
        List<CuentasPuc> cuentasPucList = lQuery.getResultList();    
    return cuentasPucList;   
    }

    public List<CuentasPuc> obtener_cuentasPuc(String cuenta){ 
        String sql = "SELECT * FROM bd_sail.cuentas_puc where flag=1 and upper(nombre) like CONCAT(?1,'%')";    
        TypedQuery<CuentasPuc> lQuery = (TypedQuery<CuentasPuc>) em.createNativeQuery(sql, CuentasPuc.class).setParameter(1, cuenta );
        List<CuentasPuc> cuentasPucList = lQuery.getResultList();    
    return cuentasPucList;   
    }
    
    public CuentasPuc obtener_ObjetoCuentasPuc(String cuenta){ 
        String sql = "SELECT * FROM bd_sail.cuentas_puc where flag=1 and upper(nombre) like CONCAT(?1,'%')";    
        TypedQuery<CuentasPuc> lQuery = (TypedQuery<CuentasPuc>) em.createNativeQuery(sql, CuentasPuc.class).setParameter(1, cuenta );
        CuentasPuc cuentasPuc = lQuery.getSingleResult();    
    return cuentasPuc;   
    }   
    
}
