/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model.tutorial1;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pangestu
 */
@Repository
public interface BarangsRepository extends JpaRepository<Barangs, Integer>{
        
  @Query(value = "Select * from tbl_barang where id_transaksi = ?1", nativeQuery = true)
  public List<Barangs> getByIdTransaksi(Integer idTransaksi);

  @Query(value = "Select * from tbl_barang where id = ?1", nativeQuery = true)
  public Barangs getById(Integer idBarang);

  
    
//        List<Barangs> getByNama(Integer idTransaksi);
//      List<Kendaraan> findByMerek(String merek);

}
