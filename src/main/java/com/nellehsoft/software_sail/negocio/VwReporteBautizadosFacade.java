/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.VwReporteBautizados;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Duverdiel
 */
@Stateless
public class VwReporteBautizadosFacade extends AbstractFacade<VwReporteBautizados> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VwReporteBautizadosFacade() {
        super(VwReporteBautizados.class);
    }
    
    
    public List<VwReporteBautizados> obtener_reporteBautismo (Date fechaInicio,Date fechaFin){
    Query g = em.createNamedQuery("VwReporteBautizados.findByFechaBautismo").setParameter("fechaInicio",fechaInicio,TemporalType.DATE).setParameter("fechaFin",fechaFin,TemporalType.DATE);
    return g.getResultList();
    }       
    
    public Number obtener_totalBautizados (Date fechaInicio,Date fechaFin){
    Query h = em.createNamedQuery("VwReporteBautizados.findBytotalBautizados").setParameter("fechaInicio",fechaInicio,TemporalType.DATE).setParameter("fechaFin",fechaFin,TemporalType.DATE);
    return   (Number)  h.getSingleResult();
    }       
    public Number obtener_totalBautizadosHombres (Date fechaInicio,Date fechaFin){
    Query u = em.createNamedQuery("VwReporteBautizados.findBytotalBautizadosHombres").setParameter("fechaInicio",fechaInicio,TemporalType.DATE).setParameter("fechaFin",fechaFin,TemporalType.DATE);
    return (Number) u.getSingleResult();
    }       
    public Number obtener_totalBautizadosMujeres (Date fechaInicio,Date fechaFin){
    Query i = em.createNamedQuery("VwReporteBautizados.findBytotalBautizadosMujeres").setParameter("fechaInicio",fechaInicio,TemporalType.DATE).setParameter("fechaFin",fechaFin,TemporalType.DATE);
    return (Number) i.getSingleResult();
    }       
    public Number obtener_totalBautizadosMembrecia (Date fechaInicio,Date fechaFin){
    Query y = em.createNamedQuery("VwReporteBautizados.findBytotalBautizadosMembresia").setParameter("fechaInicio",fechaInicio,TemporalType.DATE).setParameter("fechaFin",fechaFin,TemporalType.DATE);
    return (Number) y.getSingleResult();
    }       
    public Number obtener_totalBautizadosSellado (Date fechaInicio,Date fechaFin){
    Query t = em.createNamedQuery("VwReporteBautizados.findBytotalBautizadosSellado").setParameter("fechaInicio",fechaInicio,TemporalType.DATE).setParameter("fechaFin",fechaFin,TemporalType.DATE);
    return (Number) t.getSingleResult();
    }       
}