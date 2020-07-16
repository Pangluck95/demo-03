/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.tutorial1.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pangestu
 */
@RestController
@RequestMapping("/transaksis")
public class TransaksisController {

    @Autowired
    TransaksisRepository transaksisRepository;
    @Autowired
    BarangsRepository barangsRepository;

    @GetMapping("/listtransaksis")
    public ResponseEntity<?> getAllTransaksi() {
        List<Transaksis> transaksi = transaksisRepository.findAll();
        return ResponseEntity.ok(transaksi);
    }

    @GetMapping("/searchbystatus/{status}")
    public ResponseEntity<?> getByStatus(@PathVariable(value = "status") String status) {
        List<Transaksis> list = transaksisRepository.getstatus(status);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/caribystatus/{status}")
    public ResponseEntity<?> getstatus(@PathVariable(value = "status") String status) {
        List<Transaksis> transaksi = new ArrayList<Transaksis>();
        if ("lunas".equals(status)) {
            status = "cash";
               transaksi = transaksisRepository.getstatus(status);
        } else {
            status = "piutang";
            transaksi = transaksisRepository.getstatus(status);
        }
        return ResponseEntity.ok(transaksi);
    }
    
    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<?> updatestatus(@PathVariable(value = "id") Integer id){
        
        Transaksis transaksi = transaksisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaksis", "id", id));
        transaksi.setStatus("ga ada uang");
        
        Transaksis updatetransaksi = transaksisRepository.save(transaksi);
        HttpHeaders headers = new HttpHeaders(); 
        headers.add("Respon","sukses");
        
        return ResponseEntity.accepted().headers(headers).body(headers);
    }
        
    @PutMapping("/update/{id}")
    public ResponseEntity<Transaksis> updateTransaksis(@PathVariable(value = "id")Integer idTransaksis ,@RequestBody Transaksis transaksisDetail){
    
        Transaksis transaksis = transaksisRepository.findById(idTransaksis).orElseThrow(() -> new ResourceNotFoundException("Transaksis", "id", transaksisDetail));
        
        transaksis.setKodeTransaksi(transaksisDetail.getKodeTransaksi());
        transaksis.setStatus(transaksisDetail.getStatus());
        transaksis.setTanggal(transaksisDetail.getTanggal());
        
        transaksisRepository.save(transaksis);
        
        return ResponseEntity.ok(transaksis);
    }
    
    @PostMapping("/add")
    public Transaksis addTransaksis(@RequestBody Transaksis transaksisDetail){
        return transaksisRepository.save(transaksisDetail);
    }
    
    @GetMapping("/detailTransaksi/{id}")
    public ResponseEntity<?> getDetailTransaksi(@PathVariable(name = "id") Integer idTransaksi){
    
        Transaksis listtransaksis = transaksisRepository.findById(idTransaksi).orElseThrow(() -> new ResourceNotFoundException("Transaksis", "id", idTransaksi));
        List<Barangs> listbarang = barangsRepository.getByIdTransaksi(idTransaksi);
        List<DTOBarang> detailbarang = new ArrayList<>();
        DtoTransaksis dtoTransaksi = new DtoTransaksis();
        
        Integer harga;
        for(Barangs barangs : listbarang){
        
            if(barangs.getIdTransaksi()==idTransaksi){
                DTOBarang dto = new DTOBarang();
                harga = barangs.getHarga() * barangs.getQuantity();
                dto.setId(barangs.getId());
                dto.setName(barangs.getNamaBarang());
                dto.setIdTrans(barangs.getIdTransaksi());
                dto.setAmount(barangs.getHarga());
                dto.setQuantity(barangs.getQuantity());
                dto.setTotal(harga);
                detailbarang.add(dto);            
            }            
        }
        dtoTransaksi.setTransaksi(listtransaksis);
        dtoTransaksi.setListProduk(detailbarang);
        
        return ResponseEntity.ok(dtoTransaksi);
    }
}