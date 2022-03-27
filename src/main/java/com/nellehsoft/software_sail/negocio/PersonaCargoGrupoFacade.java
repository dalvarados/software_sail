/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.PersonaCargoGrupo;
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
public class PersonaCargoGrupoFacade extends AbstractFacade<PersonaCargoGrupo> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaCargoGrupoFacade() {
        super(PersonaCargoGrupo.class);
    }
    
    public List<PersonaCargoGrupo> obtener_mimbros_grupo (String nombreGrupo,String fecha){
    Query g = em.createNamedQuery("PersonaCargoGrupo.findByPersonaCargoGrupo").setParameter("nombreGrupo",nombreGrupo).setParameter("fecha",fecha);
    return g.getResultList();
   }
    
}
