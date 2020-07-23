/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Karyawan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repository.KaryawanRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author pangestu
 */
@RestController
@RequestMapping("/karyawan")
public class KaryawanController {
    
    @Autowired
    KaryawanRepository karyawanRepository;
    
    @PostMapping("/add")
    public Karyawan addKaryawan(@RequestBody Karyawan detailKaryawan){
        return karyawanRepository.save(detailKaryawan);
    }
    
    @GetMapping("/listkaryawan")
    public ResponseEntity<?> getAllKaryawan(){
        
        List<Karyawan> karyawan = karyawanRepository.findAll();
        
        return ResponseEntity.ok(karyawan);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Karyawan> updateKaryawan(@PathVariable(value = "id") Integer idkaryawan, @RequestBody Karyawan karyawanDetail ){
    
        Karyawan karyawan = karyawanRepository.findById(idkaryawan).orElseThrow(() -> new ResourceNotFoundException("Karyawan", "id", karyawanDetail));
        
        karyawan.setJabatan(karyawanDetail.getJabatan());
        karyawan.setNama(karyawanDetail.getNama());
        
        karyawanRepository.save(karyawan);
        return ResponseEntity.ok(karyawan);
    }
    
    @DeleteMapping("/delete/{id}")
    public Karyawan deleteKaryawan(@PathVariable(name = "id") Integer idKaryawan){
    
        Karyawan karyawan = karyawanRepository.findById(idKaryawan).orElseThrow(() -> new ResourceNotFoundException("Karyawan", "id", idKaryawan));
        karyawanRepository.delete(karyawan);
        
        return karyawan;
    }
}
