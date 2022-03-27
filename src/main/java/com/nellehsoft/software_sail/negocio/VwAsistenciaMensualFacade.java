/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.VwAsistenciaMensual;
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
public class VwAsistenciaMensualFacade extends AbstractFacade<VwAsistenciaMensual> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VwAsistenciaMensualFacade() {
        super(VwAsistenciaMensual.class);
    }
    
   public List<VwAsistenciaMensual> obtener_vista_asistencia (String nombreComite){
    Query g = em.createNamedQuery("VwAsistenciaMensual.findByNombreComite").setParameter("nombreComite",nombreComite);
    return g.getResultList();
   }
}
