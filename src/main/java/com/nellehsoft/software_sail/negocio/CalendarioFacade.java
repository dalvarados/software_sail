/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.Calendario;
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
public class CalendarioFacade extends AbstractFacade<Calendario> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalendarioFacade() {
        super(Calendario.class);
    }

   public List<Calendario> obtener_lista_tipo (String tipo){
    Query g = em.createNamedQuery("Calendario.findByTipo").setParameter("tipo",tipo);
    return g.getResultList();
   }
   
    public List<Calendario> obtener_calendarioRol (int idRol){
    Query g = em.createNamedQuery("Calendario.findByCalendarioRol").setParameter("idRol", idRol);
    return g.getResultList();
   }      
}
