/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Writer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pangestu
 */
@Repository
public interface WriterRepository extends JpaRepository<Writer, Integer>{
    
    @Query(value = "select * from tbl_wtriter where id = :id", nativeQuery = true)
    Writer getById(@Param("id") Integer id);
    
    @Query(value = "SELECT m FROM Writer m WHERE m.username LIKE ?1%")//m inisial from kelas
    List<Writer> getbyname(String username);
}
