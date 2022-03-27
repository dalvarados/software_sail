/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.persistencia.CuentasPuc;
import com.nellehsoft.software_sail.persistencia.Diezmo;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Duverdiel
 */
@Stateless
public class DiezmoFacade extends AbstractFacade<Diezmo> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiezmoFacade() {
        super(Diezmo.class);
    }
    
    public List<CuentasPuc> obtener_cuentasPuc(String cuenta){ 
        String sql = "SELECT * FROM bd_sail.cuentas_puc where flag=1 and upper(nombre) like CONCAT(?1,'%')";    
        TypedQuery<CuentasPuc> lQuery = (TypedQuery<CuentasPuc>) em.createNativeQuery(sql, CuentasPuc.class).setParameter(1, cuenta );
        List<CuentasPuc> cuentasPucList = lQuery.getResultList();    
    return cuentasPucList;   
    }
    

    public List<CuentasPuc> obtener_cuentasPucDiezmos(){
        String sql = "SELECT * FROM bd_sail.cuentas_puc where flag=1 and upper(nombre) like 'DIEZMOS%'";    
        TypedQuery<CuentasPuc> lQuery = (TypedQuery<CuentasPuc>) em.createNativeQuery(sql, CuentasPuc.class);
        List<CuentasPuc> cuentasPucList = lQuery.getResultList();    
    return cuentasPucList;   
    }

    public CuentasPuc obtener_cuentasPucActivos(){
        String sql = "SELECT * FROM bd_sail.cuentas_puc where flag=1 and upper(nombre) like 'FONDO LOCAL'";    
        TypedQuery<CuentasPuc> lQuery = (TypedQuery<CuentasPuc>) em.createNativeQuery(sql, CuentasPuc.class);
        CuentasPuc cuentasPucList = lQuery.getSingleResult();    
    return cuentasPucList;   
    }

    public void insertar_partida_credito_corpenteunida(Integer id_tercero,Integer id_puc,Date fecha_registro,Integer id_tipo_contable,String numero_tipo_contable,String descripcion,String doc_referencia,Integer valor_credito,Integer  id_ciudad,Integer  id_usuario){
      String sql = "INSERT INTO bd_sail.asiento_contable("
                 + "id_tercero,id_puc,fecha_registro,id_tipo_contable,numero_tipo_contable,descripcion,doc_referencia,valor_credito,diferencia,id_ciudad,id_usuario,fecha_tipo_contable) "
                 + "VALUES ( ?1,?2,?3,?4,?5,?6,?7,?8,null,?9,?10,CURDATE() )";       
    em.createNativeQuery(sql).setParameter(1, id_tercero ).setParameter(2, id_puc ) 
    .setParameter(3, fecha_registro ).setParameter(4,id_tipo_contable).setParameter(5, numero_tipo_contable )
    .setParameter(6, descripcion ).setParameter(7,doc_referencia).setParameter(8, valor_credito )
    .setParameter(9,id_ciudad).setParameter(10,id_usuario).executeUpdate();
    } 
    
    public void insertar_partida_credito_pastor(Integer id_tercero,Integer id_puc,Date fecha_registro,Integer id_tipo_contable,String numero_tipo_contable,String descripcion,String doc_referencia,Integer valor_credito,Integer  id_ciudad,Integer  id_usuario){
      String sql = "INSERT INTO bd_sail.asiento_contable("
                 + "id_tercero,id_puc,fecha_registro,id_tipo_contable,numero_tipo_contable,descripcion,doc_referencia,valor_credito,diferencia,id_ciudad,id_usuario,fecha_tipo_contable) "
                 + "VALUES ( ?1,?2,?3,?4,?5,?6,?7,?8,null,?9,?10,CURDATE() )";       
    em.createNativeQuery(sql).setParameter(1, id_tercero ).setParameter(2, id_puc ) 
    .setParameter(3, fecha_registro ).setParameter(4,id_tipo_contable).setParameter(5, numero_tipo_contable )
    .setParameter(6, descripcion ).setParameter(7,doc_referencia).setParameter(8, valor_credito )
    .setParameter(9,id_ciudad).setParameter(10,id_usuario).executeUpdate();
    }

    public void insertar_partida_credito_fondo(Integer id_tercero,Integer id_puc,Date fecha_registro,Integer id_tipo_contable,String numero_tipo_contable,String descripcion,String doc_referencia,Integer valor_credito,Integer  id_ciudad,Integer  id_usuario){
      String sql = "INSERT INTO bd_sail.asiento_contable("
                 + "id_tercero,id_puc,fecha_registro,id_tipo_contable,numero_tipo_contable,descripcion,doc_referencia,valor_credito,diferencia,id_ciudad,id_usuario,fecha_tipo_contable) "
                 + "VALUES ( ?1,?2,?3,?4,?5,?6,?7,?8,null,?9,?10,CURDATE() )";       
    em.createNativeQuery(sql).setParameter(1, id_tercero ).setParameter(2, id_puc ) 
    .setParameter(3, fecha_registro ).setParameter(4,id_tipo_contable).setParameter(5, numero_tipo_contable )
    .setParameter(6, descripcion ).setParameter(7,doc_referencia).setParameter(8, valor_credito )
    .setParameter(9,id_ciudad).setParameter(10,id_usuario).executeUpdate();
    }    
}