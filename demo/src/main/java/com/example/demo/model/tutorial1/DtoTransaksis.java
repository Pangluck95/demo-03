/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model.tutorial1;

import java.util.List;

/**
 *
 * @author pangestu
 */
public class DtoTransaksis {
    
    private Transaksis transaksi;
    private List<DTOBarang> listProduk;
    Integer id; String tanggal; String kodeTransaksi; String status;
    
    public void Transaksis(Integer id, String tanggal, String kodeTransaksi, String status) {
        this.id = id;
        this.tanggal = tanggal;
        this.kodeTransaksi = kodeTransaksi;
        this.status = status;
    } 
    public Transaksis getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksis transaksi) {
        this.transaksi = transaksi;
    }

    public List<DTOBarang> getListProduk() {
        return listProduk;
    }

    public void setListProduk(List<DTOBarang> listProduk) {
        this.listProduk = listProduk;
    }

    

}
