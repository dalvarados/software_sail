/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.VwPresentacionNinos;
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
public class VwPresentacionNinosFacade extends AbstractFacade<VwPresentacionNinos> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VwPresentacionNinosFacade() {
        super(VwPresentacionNinos.class);
    }
    
    public List<VwPresentacionNinos> obtener_presentacionNinos (){
    Query g = em.createNamedQuery("VwPresentacionNinos.findAll");
    return g.getResultList();
   }
    
   public List<VwPresentacionNinos> obtener_presentacion_nino (String documentoIdentidad){
    Query h = em.createNamedQuery("VwPresentacionNinos.findByDocumentoIdentidad").setParameter("documentoIdentidad",documentoIdentidad);
    return h.getResultList();
    } 
    
}
