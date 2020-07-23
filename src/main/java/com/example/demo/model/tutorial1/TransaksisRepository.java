/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model.tutorial1;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author pangestu
 */
public interface TransaksisRepository extends JpaRepository<Transaksis, Integer>{
    @Query(value = "select * from tbl_transaksi where status = ?1",nativeQuery = true)
    List<Transaksis> getstatus(String status);
    
    
}
