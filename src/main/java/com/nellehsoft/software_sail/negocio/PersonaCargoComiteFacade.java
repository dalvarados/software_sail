/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.PersonaCargoComite;
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
public class PersonaCargoComiteFacade extends AbstractFacade<PersonaCargoComite> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaCargoComiteFacade() {
        super(PersonaCargoComite.class);
    }
    
   public List<PersonaCargoComite> obtener_mimbros_comite (String nombreComite,String fecha){
    Query g = em.createNamedQuery("PersonaCargoComite.findByPersonaCargo").setParameter("nombreComite",nombreComite).setParameter("fecha",fecha);
    return g.getResultList();
   }     
    
}
