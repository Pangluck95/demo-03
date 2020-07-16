/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedDate;

/**
 *
 * @author pangestu
 */
@Entity
@Table(name = "transaksi")
@JsonIgnoreProperties(value = {"tanggalTransaksi"}, 
        allowGetters = true)
public class Transaksi {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "tanggal",nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date tanggalTransaksi;
    
//    @OneToMany
//    @JoinColumn(name = "id_barang", referencedColumnName = "id", insertable = false, updatable = false)
//    private List<Barang> barang;
//
//    @ManyToOne
//    @JoinColumn(name = "id_customer", referencedColumnName = "id", insertable = false, updatable = false)
//    private Customer customers;

//    public List<Barang> getBarang() {
//        return barang;
//    }
//
//    public void setBarang(List<Barang> barang) {
//        this.barang = barang;
//    }
//    
//    public Customer getCustomers() {
//        return customers;
//    }
//
//    public void setCustomers(Customer customers) {
//        this.customers = customers;
//    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

     
}
