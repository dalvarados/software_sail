/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.Rol;
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
public class RolFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }
    
    public List<Rol> obtener_rol (String tipoRol){
    Query t = em.createNamedQuery("Rol.findByTipoRol").setParameter("tipoRol",tipoRol);
    return t.getResultList();
    }
    
//    public List<Rol> obtener_rol_sail (String tipoRol){
//    Query q = em.createNamedQuery("Rol.findByTipoRol").setParameter("tipoRol",tipoRol);
//    return q.getResultList();
//    }
//    
//    public List<Rol> obtener_rol_visita (String nombreRol){
//    Query w = em.createNamedQuery("Rol.findByNombreRol").setParameter("nombreRol",nombreRol);
//    return w.getResultList();
//    }    
    
}
