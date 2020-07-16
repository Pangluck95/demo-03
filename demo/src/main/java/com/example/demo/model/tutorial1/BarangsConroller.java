 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model.tutorial1;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/barang")
public class BarangsConroller {

    @Autowired
    BarangsRepository barangsRepository;

    @GetMapping("/")
    public ResponseEntity<?> getAllTransaksi() {
        List<Barangs> lstBarang = barangsRepository.findAll();
        return ResponseEntity.ok(lstBarang);
    }

    @GetMapping("cari/{id}")
    public ResponseEntity<?> getTotalTransaksiById(@PathVariable(value = "id") Integer idbarang) {
        List<Barangs> lstBarangs = barangsRepository.getByIdTransaksi(idbarang);

        Integer harga = 0;
        Integer total = 0;
        String nama;
//        for(Barangs barang : lstBarangs){
//            harga = barang.getHarga();
//            total=total+harga;
//            nama = barang.getNamaBarang();
//        }
        int a = 0;
        Barangs barang = new Barangs();
        Iterator iter = lstBarangs.iterator();
        while (iter.hasNext()) {
            harga = barang.getHarga();
            total = total + harga;
            nama = barang.getNamaBarang();
            a++;
        }

        TotalTransaksi transaksi = new TotalTransaksi();
        transaksi.setTotal(total);

        HashMap<String, Integer> tampil = new HashMap<String, Integer>();
        tampil.put("Totalkan", harga);
        return ResponseEntity.ok(tampil);
    }

    @GetMapping("/nilaiAset")
    public ResponseEntity<?> getAllaset() {
        List<Barangs> lstBarang = barangsRepository.findAll();
        HashMap<String, Integer> tampil = new HashMap<String, Integer>();
        Integer harga, quantity, nilai;
        String namabarang;
        for (Barangs barangs : lstBarang) {
            harga = barangs.getHarga();
            quantity = barangs.getQuantity();
            nilai = harga * quantity;
            tampil.put(barangs.getNamaBarang(), nilai);
        }
        return ResponseEntity.ok(tampil);
    }

    
    @GetMapping("/hitungtransaksi1/{id}")
    public ResponseEntity<?> hitungtransaksi1(@PathVariable(value = "id") Integer id){
    
        List<Barangs> listBarangs = barangsRepository.getByIdTransaksi(id);
        Integer nilai;
        HashMap<Barangs, Integer> tampil = new HashMap<Barangs, Integer>();
        
        for(Barangs barangs : listBarangs){
            
            nilai = barangs.getHarga()*barangs.getQuantity();
            tampil.put(barangs, nilai);
        }
        return ResponseEntity.ok(tampil);
    }
    @GetMapping("/hitungtransaksi/{id}")
    public ResponseEntity<?> hitungtransaksi(@PathVariable(value = "id") Integer id) {

        List<DTOBarang> lstDto = new ArrayList<>();

        List<Barangs> listBarangs = barangsRepository.getByIdTransaksi(id);
        int harga;
        for (Barangs barangs : listBarangs) {
            
            DTOBarang dto = new DTOBarang();

            harga = barangs.getHarga() * barangs.getQuantity();

            dto.setName(barangs.getNamaBarang());
            dto.setIdTrans(barangs.getIdTransaksi());
            dto.setAmount(barangs.getHarga());
            dto.setQuantity(barangs.getQuantity());
            dto.setTotal(harga);

            lstDto.add(dto);
        }
                return ResponseEntity.ok(lstDto);
    }   
    @PutMapping("/updateBarangs")
    public ResponseEntity<Barangs> updateBarang(@RequestBody Barangs detailBarangs) {
        
        Barangs barangs = barangsRepository.findById(detailBarangs.getId()).orElseThrow(() -> new ResourceNotFoundException("Barangs","id", detailBarangs.getId()));
        
        barangs.setHarga(detailBarangs.getHarga());
        barangs.setNamaBarang(detailBarangs.getNamaBarang());
        barangs.setQuantity(detailBarangs.getQuantity());
        barangs.setIdTransaksi(detailBarangs.getIdTransaksi());
        barangsRepository.save(barangs);
        
        return ResponseEntity.ok(barangs);
    }
    
//    @PutMapping("/updatebyid/{id}")
//    public Barangs updateBarangById(@PathVariable(value = "id") Integer idBarang,
//            @Valid @RequestBody Barangs detailBarangs){
//        
//        Barangs barangs = barangsRepository.findById(idBarang)
//                .orElseThrow(() -> new ResourceNotFoundException("Barangs", "id", idBarang));
//        barangs.setHarga(detailBarangs.getHarga());
//        barangs.setNamaBarang(detailBarangs.getNamaBarang());
//        barangs.setQuantity(detailBarangs.getQuantity());
//        barangs.setIdTransaksi(detailBarangs.getIdTransaksi());
//
//        Barangs updateBarang = barangsRepository.save(barangs);
//        
//        return barangs;
//    }
    
    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<Barangs> updateBarangById(@PathVariable(value = "id") 
            Integer idBarang, @RequestBody Barangs detailBarangs){
        
        Barangs barangs = barangsRepository.findById(idBarang).orElseThrow(
                () -> new ResourceNotFoundException("Barangs", "id", idBarang));
        
        barangs.setId(detailBarangs.getId());
        barangs.setNamaBarang(detailBarangs.getNamaBarang());
        barangs.setHarga(detailBarangs.getHarga());
        barangs.setQuantity(detailBarangs.getQuantity());
        barangs.setIdTransaksi(detailBarangs.getIdTransaksi());
        Barangs update = barangsRepository.save(barangs);
        
        return ResponseEntity.ok(update);
    }
    
    @PostMapping("/addBarang")
    public Barangs tambahBarang(@Valid @RequestBody Barangs barangs){
        return barangsRepository.save(barangs);
    }
    
    @DeleteMapping("/delete/{id}")
    public Barangs hapusBarang(@PathVariable(name = "id")Integer idbarang){
        
        Barangs barangs = barangsRepository.findById(idbarang).orElseThrow(() -> new ResourceNotFoundException("Barangs", "id", idbarang));
        barangsRepository.delete(barangs);
        
        return barangs;
    }
    
    @PutMapping("/updateoradd/{id}")
    public ResponseEntity<Barangs> updateOrAdd(@PathVariable("id") Integer idBarang,@RequestBody Barangs detailBarang){
        
        if(idBarang>0){
            detailBarang.setId(idBarang);
            updateBarang(detailBarang);
        }
        else if(idBarang==0){
            tambahBarang(detailBarang);
        }
        return ResponseEntity.ok(detailBarang);
    }
}
