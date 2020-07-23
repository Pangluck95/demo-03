/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Karyawan;
import com.example.demo.model.Kendaraan;
import com.example.demo.model.PemilikDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author pangestu
 *
 */
@Repository
public interface KaryawanRepository extends JpaRepository<Karyawan, Integer>{
    
   
//    List<Karyawan> findByName(String name);
//    List<Karyawan> findbyid()
    
    @Query("select k from Kendaraan k left join k.karyawan j")
    List<Kendaraan> getKaryawan();
}
