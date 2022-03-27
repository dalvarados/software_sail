/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.VwRecomendacionCarta;
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
public class VwRecomendacionCartaFacade extends AbstractFacade<VwRecomendacionCarta> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VwRecomendacionCartaFacade() {
        super(VwRecomendacionCarta.class);
    }
    
   public List<VwRecomendacionCarta> obtener_persona_carta (String documentoIdentidad){
    Query h = em.createNamedQuery("VwRecomendacionCarta.findByDocumentoIdentidad").setParameter("documentoIdentidad",documentoIdentidad);
    return h.getResultList();
    }    
    
}
