/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Duverdiel
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

   public Persona obtener_persona (String documentoIdentidad){
      try {
        Query h = em.createNamedQuery("Persona.findByDocumentoIdentidad").setParameter("documentoIdentidad",documentoIdentidad);
        return (Persona) h.getSingleResult();        
        }
     catch(NoResultException e){
       return null;
     } 
   } 
   
   public Persona obtener_pastor (){
      try {
        Query p = em.createNamedQuery("Persona.findByPastor");
        return (Persona) p.getSingleResult();      
        }
     catch(NoResultException e){
       return null;
     }        
   }

   public List<Persona> obtener_conyuge (String sexo){
    Query sm = em.createNamedQuery("Persona.findBySexo").setParameter("sexo",sexo);
    return sm.getResultList();
    }   
   
    public List<Persona> obtener_persona_sort(){
    Query u = em.createNamedQuery("Persona.findAllCreyentes");
    return u.getResultList();
    }
    
    public List<Persona> obtener_lista_visitas(){
    Query v = em.createNamedQuery("Persona.findVisitas");
    return v.getResultList();
    }
    
    public List<Persona> obtener_lista_contable(){
    Query u = em.createNamedQuery("Persona.findRolContable");
    return u.getResultList();
    }
    
    public List<Persona> obtener_rol_tercero_servPublico(){
    Query r = em.createNamedQuery("Persona.findRolProveedorServPubl");
    return r.getResultList();
    }    

    public List<Persona> obtener_rol_tercero(){
    Query k = em.createNamedQuery("Persona.findRolTercero");
    return k.getResultList();
    } 
    
    public Persona obtener_propietario(){
    Query a = em.createNamedQuery("Persona.findPropietarioContable");
    return (Persona) a.getSingleResult();
    } 
    
    public Persona obtener_corpenteunida(){
    Query z = em.createNamedQuery("Persona.findCorpenteUnida");
    return (Persona) z.getSingleResult();
    }    
    
}
