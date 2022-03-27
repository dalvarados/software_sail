/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.Iglesia;
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
public class IglesiaFacade extends AbstractFacade<Iglesia> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IglesiaFacade() {
        super(Iglesia.class);
    }
    
    public String obtener_iglesia_local (String tipoIglesia){
    Query g = em.createNamedQuery("Iglesia.findByTipoIglesia").setParameter("tipoIglesia",tipoIglesia);
    return g.getSingleResult().toString();
    }     
    
    public Iglesia obtener_objeto_iglesia_local (String tipoIglesia){
    Query g = em.createNamedQuery("Iglesia.findByTipoIglesiaLocal").setParameter("tipoIglesia",tipoIglesia);
    return (Iglesia) g.getSingleResult();
    }
    
    public List<Iglesia> obtener_lista_iglesia_local (String tipoIglesia){
    Query g = em.createNamedQuery("Iglesia.findByTipoListaIglesia").setParameter("tipoIglesia",tipoIglesia);
    return g.getResultList();
    }     
}
