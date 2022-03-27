/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.AsientoContable;
import com.nellehsoft.software_sail.persistencia.TipoContable;
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
public class TipoContableFacade extends AbstractFacade<TipoContable> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoContableFacade() {
     super(TipoContable.class);
    }
    
    public TipoContable obtener_tipoContable(String nombre){
     Query a = em.createNamedQuery("TipoContable.findByNombre").setParameter("nombre",nombre);
    return (TipoContable) a.getSingleResult();
    }
    
    public  List<TipoContable> obtener_List_tipoContable(String nombre){
     Query a = em.createNamedQuery("TipoContable.findByNombre").setParameter("nombre",nombre);
    return  a.getResultList();
    }

    public TipoContable obtener_tipoContableId(int idTipoContable){
     Query a = em.createNamedQuery("TipoContable.findByIdTipoContable").setParameter("idTipoContable",idTipoContable);
    return (TipoContable) a.getSingleResult();
    }    
    
}
