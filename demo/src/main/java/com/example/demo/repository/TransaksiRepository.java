/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Transaksi;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author pangestu
 */
public interface TransaksiRepository extends JpaRepository<Transaksi, Integer>{
    
    @Query(value = "Select * from transaksi  INNER JOIN barang ON transaksi.id_barang = barang.id INNER JOIN customer on transaksi.id_customer = customer.id;",nativeQuery = true)
      List<Transaksi> lstTransaksi();
}
