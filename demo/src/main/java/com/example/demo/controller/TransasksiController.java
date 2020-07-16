/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Transaksi;
import com.example.demo.repository.BarangRepository;
import com.example.demo.repository.TransaksiRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pangestu
 */
@RestController
@RequestMapping("/transaksi")
public class TransasksiController {
    
    @Autowired
    TransaksiRepository transaksiRepository;
    
            
    @GetMapping("/lsitdata")
    public List<Transaksi> getTransaksi() {
        List<Transaksi> alltransaksi = transaksiRepository.lstTransaksi();
        return alltransaksi;
    }
    
    @GetMapping("/listTransaksi")
    public ResponseEntity<?> getAllTransaksi(){
        List<Transaksi> transaksi = transaksiRepository.findAll();
        return ResponseEntity.ok(transaksi);
    }   
}
