/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.PemilikDto;
import com.example.demo.model.Kendaraan;
import com.example.demo.model.PemilikKendaraan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pangestu
 */
@Repository
public interface KendaraanRepository extends JpaRepository<Kendaraan, Integer>{
    
      List<Kendaraan> findByMerek(String merek);
      List<Kendaraan> findBywarna(String merek);
      
    @Query(value = "Select karyawan.id karyawan.nama, karyawan.jabatan  from kendaraan INNER JOIN karyawan ON kendaraan.id_karyawan=karyawan.id_karyawan;",nativeQuery = true)
    List<Kendaraan> lstkendaran();

    @Query(value = "select karyawan.id, karyawan.nama, karyawan.jabatan from kendaraan left join karyawan ON kendaraan.id_karyawan=karyawan.id where kendaraan.id = ?1",nativeQuery = true)
    public List<Kendaraan> getKaryawan(Integer id);
      
    @Override
    public List<Kendaraan> findAll();
    
//    @Query("SELECT new com.example.demo.PemilikDto(karyawan.name, karyawan.jabtan, kendaraan.merek, karyawan.warna) "+ "FROM kendaraan INNER JOIN karyawan ON kendaraan.id_karyawan=karyawan.id_karyawan")
//	List<PemilikDto> Join();
}
