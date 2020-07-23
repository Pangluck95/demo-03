/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author pangestu
 */
@Entity
@Table(name = "barang")
public class Barang implements Serializable{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="nama_barang")
    @JsonProperty(value = "nama_barang")
    private String nama_barang;
    
    
    @Column(name = "harga_barang")
    @JsonProperty(value = "harga_barang")
    private Integer harga_barang;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public Integer getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(Integer harga_barang) {
        this.harga_barang = harga_barang;
    }    
}
