/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nellehsoft.software_sail.negocio;

import com.nellehsoft.software_sail.bean.AsientoContableTotales;
import com.nellehsoft.software_sail.persistencia.AsientoContable;
import com.nellehsoft.software_sail.persistencia.CuentasPuc;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext; 
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Duverdiel
 */
@Stateless
public class AsientoContableFacade extends AbstractFacade<AsientoContable> {

    @PersistenceContext(unitName = "com.nellehsoft_software_sail_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsientoContableFacade() {
        super(AsientoContable.class);
    }
    
   public List<AsientoContable> obtener_AsientoContable (String numeroTipoContable,int idTipoContable){
    Query g = em.createNamedQuery("AsientoContable.findByListaEgresosDetalle").setParameter("numeroTipoContable",numeroTipoContable).setParameter("idTipoContable",idTipoContable);
    return g.getResultList();
   }

   public List<AsientoContable> obtener_A1sientoContableTipoContableIngreso(String tipo){
    Query y = em.createNamedQuery("AsientoContable.findTipoContableIngresos").setParameter("tipo",tipo);
    return y.getResultList();
   }
   
    public List<AsientoContable> obtener_ListAsientoContableTipoContable(String tipo){
        String sql = "select a.id_asiento,a.id_tercero,a.id_puc,a.fecha_registro,a.id_tipo_contable,a.numero_tipo_contable,a.descripcion,\n" +
                        "p.nombre num_cheque,doc_referencia,b.valor_debito,a.valor_credito,a.id_tipo_contable diferencia,a.id_tesorero,a.id_ciudad,a.id_comite,\n" +
                        "a.id_proposito,a.id_usuario,a.fecha_tipo_contable,a.anulado \n" +
                        "from `bd_sail`.`asiento_contable` a\n" +
                        "inner join ( select max(id_asiento) id_asiento,numero_tipo_contable,a1.id_tipo_contable,sum(valor_debito) valor_debito \n" +
                        "             from `bd_sail`.`asiento_contable` a1 \n" +
                        "             inner join tipo_contable a2 on a1.id_tipo_contable=a2.id_tipo_contable \n" +
                        "			 where nombre=?1\n" +
                        "             group by  id_tipo_contable,numero_tipo_contable ) b\n" +
                        "on a.id_asiento=b.id_asiento \n" +
                        "inner join `bd_sail`.`persona` p on a.id_tercero=p.id_persona\n" +
                        "where  a.descripcion NOT LIKE 'SI-%' and a.descripcion NOT LIKE 'Liquidacion Diezmo -%' and legalizacion is null;";    
        TypedQuery<AsientoContable> lQuery = (TypedQuery<AsientoContable>) 
        em.createNativeQuery(sql, AsientoContable.class).setParameter(1, tipo);
        List<AsientoContable>  listadoAasientoContableIngresos = lQuery.getResultList();    
    return listadoAasientoContableIngresos;   
    }   
       
    public List<AsientoContable> obtener_ListAsientoContableTipoContableLegalizacion(String tipo){
        String sql = "select a.id_asiento,a.id_tercero,a.id_puc,a.fecha_registro,a.id_tipo_contable,a.numero_tipo_contable,a.descripcion,\n" +
                        "p.nombre num_cheque,doc_referencia,b.valor_debito,a.valor_credito,a.id_tipo_contable diferencia,a.id_tesorero,a.id_ciudad,a.id_comite,\n" +
                        "a.id_proposito,a.id_usuario,a.fecha_tipo_contable,a.anulado \n" +
                        "from `bd_sail`.`asiento_contable` a\n" +
                        "inner join ( select max(id_asiento) id_asiento,numero_tipo_contable,a1.id_tipo_contable,sum(valor_debito) valor_debito \n" +
                        "             from `bd_sail`.`asiento_contable` a1 \n" +
                        "             inner join tipo_contable a2 on a1.id_tipo_contable=a2.id_tipo_contable \n" +
                        "			 where nombre=?1\n" +
                        "             group by  id_tipo_contable,numero_tipo_contable ) b\n" +
                        "on a.id_asiento=b.id_asiento \n" +
                        "inner join `bd_sail`.`persona` p on a.id_tercero=p.id_persona\n" +
                        "where  a.descripcion NOT LIKE 'SI-%' and a.descripcion NOT LIKE 'Liquidacion Diezmo -%' and legalizacion=1;";    
        TypedQuery<AsientoContable> lQuery = (TypedQuery<AsientoContable>) 
        em.createNativeQuery(sql, AsientoContable.class).setParameter(1, tipo);
        List<AsientoContable>  listadoAasientoContableLegalizacionEgresos = lQuery.getResultList();    
    return listadoAasientoContableLegalizacionEgresos;   
    }   

    
    public List<AsientoContable> obtener_AsientoContableTipoContable(String tipo){
    Query y = em.createNamedQuery("AsientoContable.findTipoContableUnico").setParameter("tipo",tipo);
    return y.getResultList();
   }
   
   public List<AsientoContable> obtener_AsientoContableTRF(){
    Query m = em.createNamedQuery("AsientoContable.findByDescripcionTransf");
    return m.getResultList();
   }   

   public List<AsientoContable> obtener_AsientoContableSaldoInicial(){
    Query si = em.createNamedQuery("AsientoContable.findByDescripcionSaldoIni");
    return si.getResultList();
   } 
   
    public List<CuentasPuc> obtener_cuentasPucActivos(){
        String sql = "SELECT * FROM bd_sail.cuentas_puc where flag=1 and (upper(nombre) like'FONDO%')"; 
        TypedQuery<CuentasPuc> lQuery = (TypedQuery<CuentasPuc>) em.createNativeQuery(sql, CuentasPuc.class);
        List<CuentasPuc> cuentasPucList = lQuery.getResultList();    
    return cuentasPucList;   
    }

    public List<CuentasPuc> obtener_cuentasPucCuentaAuxiliar(){
        String sql = "SELECT * FROM bd_sail.cuentas_puc where flag=1 and (upper(nombre) like'CUENTA AUXILIAR%')"; 
        TypedQuery<CuentasPuc> lQuery = (TypedQuery<CuentasPuc>) em.createNativeQuery(sql, CuentasPuc.class);
        List<CuentasPuc> cuentaAuxiliarList = lQuery.getResultList();    
    return cuentaAuxiliarList;   
    }
    

    public AsientoContableTotales obtener_AsientoContableTotales(String numero_tipo_contable,int id_tipo_contable){
        String sql = "SELECT coalesce(sum(valor_debito),0) valor_debito, coalesce(sum(valor_credito),0) valor_credito,\n" +
                     "(coalesce(sum(valor_debito),0) - coalesce(sum(valor_credito),0)) diferencia \n" +
                     "FROM bd_sail.asiento_contable where numero_tipo_contable=?1 and id_tipo_contable=?2;";    
        TypedQuery<AsientoContableTotales> lQuery = (TypedQuery<AsientoContableTotales>) 
        em.createNativeQuery(sql, AsientoContableTotales.class).setParameter(1, numero_tipo_contable).setParameter(2, id_tipo_contable );
        AsientoContableTotales asientoContableTotales = lQuery.getSingleResult();    
    return asientoContableTotales;   
    }    
    
    public void insertar_partida_debito(Integer id_tercero,Integer id_puc,Date fecha_registro,Integer id_tipo_contable,String numero_tipo_contable,String descripcion,String doc_referencia,Integer valor_debito,Integer  id_ciudad,Integer  id_usuario){
      String sql = "INSERT INTO bd_sail.asiento_contable("
                 + "id_tercero,id_puc,fecha_registro,id_tipo_contable,numero_tipo_contable,descripcion,doc_referencia,valor_debito,diferencia,id_ciudad,id_usuario,fecha_tipo_contable) "
                 + "VALUES ( ?1,?2,?3,?4,?5,?6,?7,?8,0,?9,?10,CURDATE() )";       
    em.createNativeQuery(sql).setParameter(1, id_tercero ).setParameter(2, id_puc ) 
    .setParameter(3, fecha_registro ).setParameter(4,id_tipo_contable).setParameter(5, numero_tipo_contable )
    .setParameter(6, descripcion ).setParameter(7,doc_referencia).setParameter(8, valor_debito )
    .setParameter(9,id_ciudad).setParameter(10,id_usuario).executeUpdate();
    }
    
    public String obtener_nombre_cliente (Integer codBrief){
        Query h = em.createNamedQuery("Brief.findByNombreCliente").setParameter("codBrief",codBrief);
        return  (String) h.getSingleResult();
    }

    
    public int obtener_saldoFondoLocal(){
        String sql = "select coalesce(sum(coalesce(a.valor_debito,0)) - sum(coalesce(a.valor_credito,0)),0) saldo_fondo_local " +
                      "from bd_sail.asiento_contable a " +
                      "inner join cuentas_puc c on a.id_puc=c.id_puc " +
                      "where upper(c.nombre) LIKE 'FONDO LOCAL%'";    
        Query saldo_fondo_local = em.createNativeQuery(sql, int.class);             
    return (int) saldo_fondo_local.getSingleResult() ;   
    }    
        
    public int obtener_saldoCajaMenor(){
        String sql2 = "select coalesce(sum(coalesce(a.valor_debito,0)) - sum(coalesce(a.valor_credito,0)),0) saldo_caja_menor " +
                      "from bd_sail.asiento_contable a " +
                      "inner join cuentas_puc c on a.id_puc=c.id_puc " +
                      "where upper(c.nombre) LIKE 'CAJA MENOR%'";    
        Query saldo_caja_menor = em.createNativeQuery(sql2, int.class);             
    return (int) saldo_caja_menor.getSingleResult();   
    }    

    public int obtener_DiferenciaPorNumeroDocumento(String numero_tipo_contable,int idTipoContable){
        String sql4 = "select CONVERT(coalesce(sum(coalesce(valor_debito,0))-sum(coalesce(valor_credito,0)),-1), SIGNED INTEGER) diferencia FROM bd_sail.asiento_contable "
                + "where numero_tipo_contable= ?1 and id_tipo_contable= ?2";    
        Query diferenciaNumeroDocumento =em.createNativeQuery(sql4).setParameter(1, numero_tipo_contable ).setParameter(2, idTipoContable );          
        long diferenciaNumeroDocumento1 = (Long) diferenciaNumeroDocumento.getSingleResult();
    return (int) diferenciaNumeroDocumento1;   
    }

    public int validaNumeroEgresoIngreso(String numero_tipo_contable,int idTipoContable){
        String sql3 = "SELECT count(1) FROM bd_sail.asiento_contable where numero_tipo_contable= ?1 and id_tipo_contable= ?2";    
        Query validaNumeroEgresoIngreso =em.createNativeQuery(sql3).setParameter(1, numero_tipo_contable ).setParameter(2, idTipoContable );          
        long validaNumeroEgresoIngreso1 = (Long) validaNumeroEgresoIngreso.getSingleResult();
    return (int) validaNumeroEgresoIngreso1;

    }
    
//*****Delete ofrenda asiento contable
    public void delete_asientocontable_ingresos(Integer id_tipo_contable,String numero_tipo_contable){
      String sql = "DELETE FROM  bd_sail.asiento_contable WHERE "
                 + "id_tipo_contable=?1 and numero_tipo_contable=?2";       
    em.createNativeQuery(sql).setParameter(1, id_tipo_contable ).setParameter(2, numero_tipo_contable ) 
    .executeUpdate();
    }

    
//*****Anular ofrenda asiento contable
    public void anular_asientocontable(Integer id_tipo_contable,String numero_tipo_contable){
      String sql = "UPDATE bd_sail.asiento_contable "
              + "SET anulado=1 "
              + "WHERE id_tipo_contable=?1 and numero_tipo_contable=?2";       
    em.createNativeQuery(sql).setParameter(1, id_tipo_contable ).setParameter(2, numero_tipo_contable ) 
    .executeUpdate();
    }
    
    public void update_asientocontable_ingresos(Date fecha_registro,Integer valor_debito, Integer id_ciudad,
                Integer id_comite,Integer id_proposito ,Integer id_tipo_contable,String numero_tipo_contable,
                String descripcion,Integer id_tesorero){
      String sql = "UPDATE bd_sail.asiento_contable "
              +" SET fecha_registro=?1,valor_debito=?2,id_ciudad=?3,id_comite=?4,id_proposito=?5,"
              + "fecha_tipo_contable=CURDATE(),descripcion=?8,id_tesorero=?9 "
              +" WHERE id_tipo_contable=?6 and numero_tipo_contable=?7 and valor_debito is not null";       
    em.createNativeQuery(sql).setParameter(1, fecha_registro ).setParameter(2, valor_debito ) 
    .setParameter(3, id_ciudad ).setParameter(4, id_comite ).setParameter(5, id_proposito ) 
    .setParameter(6, id_tipo_contable ).setParameter(7, numero_tipo_contable ).setParameter(8, descripcion )
    .setParameter(9, id_tesorero ).executeUpdate();
    }    
    
    public void update_asientocontable_IngresoCredito(Date fecha_registro,Integer valor_debito, Integer id_ciudad,
                Integer id_comite,Integer id_proposito ,Integer id_tipo_contable,String numero_tipo_contable,
                String descripcion,Integer id_tesorero){
      String sql = "UPDATE bd_sail.asiento_contable "
              +" SET fecha_registro=?1,valor_credito=?2,id_ciudad=?3,id_comite=?4,id_proposito=?5,"
              + "fecha_tipo_contable=CURDATE(),descripcion=?8 ,id_tesorero=?9"
              +" WHERE id_tipo_contable=?6 and numero_tipo_contable=?7 and valor_credito is not null";       
    em.createNativeQuery(sql).setParameter(1, fecha_registro ).setParameter(2, valor_debito ) 
    .setParameter(3, id_ciudad ).setParameter(4, id_comite ).setParameter(5, id_proposito ) 
    .setParameter(6, id_tipo_contable ).setParameter(7, numero_tipo_contable ).setParameter(8, descripcion )
    .setParameter(9, id_tesorero ).executeUpdate();
    }   

   public void update_asientoContableAportePastor(Date fecha_registro,Integer valorPastor, Integer numIngreso){
      String sqldebito = "UPDATE bd_sail.asiento_contable "
              +" SET fecha_registro=?1,valor_debito=?2"
              +" WHERE diferencia=?3 and trim(descripcion)='Liquidacion Diezmo -Aporte Pastor' and valor_credito is null";       
    em.createNativeQuery(sqldebito).setParameter(1, fecha_registro ).setParameter(2, valorPastor ) 
    .setParameter(3, numIngreso ).executeUpdate();      
    String sqlCredito = "UPDATE bd_sail.asiento_contable "
              +" SET fecha_registro=?1,valor_credito=?2"
              +" WHERE diferencia=?3 and trim(descripcion)='Liquidacion Diezmo -Aporte Pastor' and valor_debito is  null";       
    em.createNativeQuery(sqlCredito).setParameter(1, fecha_registro ).setParameter(2, valorPastor ) 
    .setParameter(3, numIngreso ).executeUpdate();	
    }
   
   public void update_asientoContableCorpenteUnida(Date fecha_registro,Integer valorCorpenteUnida, Integer numIngreso){
      String sqldebito = "UPDATE bd_sail.asiento_contable "
              +" SET fecha_registro=?1,valor_debito=?2"
              +" WHERE diferencia=?3 and trim(descripcion)='Liquidacion Diezmo -CorpenteUnida Diezmos' and valor_credito is null";       
    em.createNativeQuery(sqldebito).setParameter(1, fecha_registro ).setParameter(2, valorCorpenteUnida ) 
    .setParameter(3, numIngreso ).executeUpdate();
      String sqlcredito = "UPDATE bd_sail.asiento_contable "
              +" SET fecha_registro=?1,valor_credito=?2"
              +" WHERE diferencia=?3 and trim(descripcion)='Liquidacion Diezmo -CorpenteUnida Diezmos' and valor_debito is null";       
    em.createNativeQuery(sqlcredito).setParameter(1, fecha_registro ).setParameter(2, valorCorpenteUnida ) 
    .setParameter(3, numIngreso ).executeUpdate();     
    }

    public void update_asientoContableDiezmos(Date fecha_registro,Integer valorDiezmo,Integer numIngreso){
      String sqldebito = "UPDATE bd_sail.asiento_contable "
              +" SET fecha_registro=?1,valor_debito=?2"
              +" WHERE diferencia=?3 and trim(descripcion)='Liquidacion Diezmo -Diezmos' and valor_credito is null";       
    em.createNativeQuery(sqldebito).setParameter(1, fecha_registro ).setParameter(2, valorDiezmo ) 
    .setParameter(3, numIngreso ).executeUpdate();
    }
    
    public void update_asientoContableIngresosDiezmos(Date fecha_registro,Integer ingresoDiezmo,Integer numIngreso){    
    String sqlCredito = "UPDATE bd_sail.asiento_contable "
              +" SET fecha_registro=?1,valor_credito=?2"
              +" WHERE diferencia=?3 and trim(descripcion)='Liquidacion Diezmo -Ingreso Diezmos' and valor_debito is  null";       
    em.createNativeQuery(sqlCredito).setParameter(1, fecha_registro ).setParameter(2, ingresoDiezmo ) 
    .setParameter(3, numIngreso ).executeUpdate();	
    }       

    public void update_asientoContableFondoLocal(Date fecha_registro,Integer valorFondoLocal,Integer numIngreso){    
    String sqlCredito = "UPDATE bd_sail.asiento_contable "
              +" SET fecha_registro=?1,valor_credito=?2"
              +" WHERE diferencia=?3 and trim(descripcion)='Liquidacion Diezmo -Diezmos fondo local' and valor_debito is  null";       
    em.createNativeQuery(sqlCredito).setParameter(1, fecha_registro ).setParameter(2, valorFondoLocal ) 
    .setParameter(3, numIngreso ).executeUpdate();	
    }    

//*****Delete diezmo asiento contable
    public void delete_asientocontable_diezmo(Integer id_tipo_contable,Integer diferencia){
      String sql = "DELETE FROM  bd_sail.asiento_contable WHERE "
                 + "substring(descripcion,1,20)='Liquidacion Diezmo -' and diferencia=?2";       
    em.createNativeQuery(sql).setParameter(1, id_tipo_contable ).setParameter(2, diferencia ) 
    .executeUpdate();
    }
    
    
}
