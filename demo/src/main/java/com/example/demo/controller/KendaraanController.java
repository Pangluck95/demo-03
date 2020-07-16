/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Karyawan;
import com.example.demo.model.Kendaraan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repository.*;
import java.util.List;
import javax.validation.Valid;
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
@RequestMapping("/api")
public class KendaraanController {

    @Autowired
    KendaraanRepository kendaraanRepository;

    @Autowired
    KaryawanRepository karyawanRepository;

    @GetMapping("/listkendaraan")
    public List<Kendaraan> getListKendaraan() {
        return kendaraanRepository.findAll();
    }

    @PostMapping("/kendaraan")
    public Kendaraan addkendaraan(@Valid @RequestBody Kendaraan kendaraan) {
        return kendaraanRepository.save(kendaraan);
    }

    @GetMapping("/kendaraan/{id}")
    public Kendaraan getKendaraanById(@PathVariable(value = "id") Integer kendaranID) {
        return kendaraanRepository.findById(kendaranID).orElseThrow(() -> new ResourceNotFoundException("kendaraan", "id", kendaranID));
    }

    @GetMapping("/carikendaraan/{merek}")
    public List<Kendaraan> getKendaraanBymerek(@PathVariable(value = "merek") String merek) {
        return kendaraanRepository.findByMerek(merek);
    }

    @GetMapping("/warnakendaraan/{warna}")
    public List<Kendaraan> getKendaraanByWarna(@PathVariable(value = "warna") String warna) {
        return kendaraanRepository.findBywarna(warna);
    }

    @PutMapping("/kendaraan/{id}")
    public Kendaraan updateKendaran(@PathVariable(value = "id") Integer kendaraanId,
            @Valid @RequestBody Kendaraan kendaraanDetails) {

        Kendaraan kendaran = kendaraanRepository.findById(kendaraanId)
                .orElseThrow(() -> new ResourceNotFoundException("kendaraan", "id", kendaraanId));

        kendaran.setMerek(kendaraanDetails.getMerek());
        kendaran.setPlat(kendaraanDetails.getPlat());
        kendaran.setWarna(kendaraanDetails.getWarna());
//        kendaran.setId_karyawan(kendaraanDetails.getId_karyawan());

        Kendaraan updatedKendaraan = kendaraanRepository.save(kendaran);
        return updatedKendaraan;
    }

    @DeleteMapping("/kendaraan/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Integer id) {
        Kendaraan kendaraan = kendaraanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

        kendaraanRepository.delete(kendaraan);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/datapemilik")
    public ResponseEntity<?> getPemilikKendaraan() {
        List<Kendaraan> lstkendaran = kendaraanRepository.lstkendaran();
        return ResponseEntity.ok(lstkendaran);
    }

    @GetMapping("/getKaryawan")
    public List<Kendaraan> getKaryawans() {
        List<Kendaraan> lstKendaraan = karyawanRepository.getKaryawan();
        return lstKendaraan;
    }

    
//    @PutMapping("/getKaryawana/{id}")
//    public Kendaraan GetKaryawan(@PathVariable(value = "id") Integer karyawanID,
//            @Valid @RequestBody Kendaraan kendaraanDetails) {
//
//        Kendaraan kendaran = kendaraanRepository.findById(karyawanID)
//                .orElseThrow(() -> new ResourceNotFoundException("Karyawan", "id", karyawanID));
//
//    }
    
//    @GetMapping("/getKaryawan/{id}")
//    public List<Kendaraan> getKaryawan(@PathVariable(value = "id") Integer id) {
//        List<Kendaraan> lstkaryawan = kendaraanRepository.getKaryawan(id);
//        return lstkaryawan;
//    }
    
    @GetMapping("/getKaryawan/{id}")
    public String getKaryawanById(@PathVariable(value = "id") Integer id) {
        Kendaraan kendaraan = kendaraanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kendaraan", "id", id));
        Karyawan karyawan = karyawanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Karyawan", "id", kendaraan.getId()));
//        Map<String, String> map = new HashMap<>();
        String name = karyawan.getNama();
//        String jbatan = karyawan.getJabatan();
//        map.put(name, jbatan);
        return name;
    }
}
