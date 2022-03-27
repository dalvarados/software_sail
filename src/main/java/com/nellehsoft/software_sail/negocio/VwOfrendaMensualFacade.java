/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.VwOfrendaMensual;
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
public class VwOfrendaMensualFacade extends AbstractFacade<VwOfrendaMensual> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VwOfrendaMensualFacade() {
        super(VwOfrendaMensual.class);
    }
    
   public List<VwOfrendaMensual> obtener_vista_ofrenda (String nombreComite){
    Query g = em.createNamedQuery("VwOfrendaMensual.findByNombreComite").setParameter("nombreComite",nombreComite);
    return g.getResultList();
   }    
    
}
