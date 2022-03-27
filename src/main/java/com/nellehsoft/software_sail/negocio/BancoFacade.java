/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.Banco;
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
public class BancoFacade extends AbstractFacade<Banco> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BancoFacade() {
        super(Banco.class);
    }
    
    public List<CuentasPuc> obtener_cuentasPucBanco(){
        String sql = "SELECT * FROM bd_sail.cuentas_puc where flag=1 and upper(nombre) like'BANCO%'";    
        TypedQuery<CuentasPuc> lQuery = (TypedQuery<CuentasPuc>) em.createNativeQuery(sql, CuentasPuc.class);
        List<CuentasPuc> cuentasPucList = lQuery.getResultList();    
    return cuentasPucList;   
    } 
    
}
