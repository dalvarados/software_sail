/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.Cuenta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Duverdiel
 */
@Stateless
public class CuentaFacade extends AbstractFacade<Cuenta> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em; 

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentaFacade() {
        super(Cuenta.class);
    }
   
   public List<Cuenta> obtener_cuenta (String nombreCuenta){
    Query p = em.createNamedQuery("Cuenta.findByNombreCuenta").setParameter("nombreCuenta",nombreCuenta);
    return p.getResultList();
    }
   
    public List<Cuenta> obtener_cuentaSinDiezmo (){
    Query d = em.createNamedQuery("Cuenta.findByNombreCuentaSinDiezmo");
    return d.getResultList();
    }    
}
