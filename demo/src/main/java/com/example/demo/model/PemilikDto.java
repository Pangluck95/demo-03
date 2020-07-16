/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

/**
 *
 * @author pangestu
 */
public class PemilikDto {
    private String namaPemilik;
    private String jabatanPemilik;
    private String merekKendaran;
    private String warnaKendraan;

    public PemilikDto(String namaPemilik, String jabatanPemilik, String merekKendaran, String warnaKendraan) {
        this.namaPemilik = namaPemilik;
        this.jabatanPemilik = jabatanPemilik;
        this.merekKendaran = merekKendaran;
        this.warnaKendraan = warnaKendraan;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getJabatanPemilik() {
        return jabatanPemilik;
    }

    public void setJabatanPemilik(String jabatanPemilik) {
        this.jabatanPemilik = jabatanPemilik;
    }

    public String getMerekKendaran() {
        return merekKendaran;
    }

    public void setMerekKendaran(String merekKendaran) {
        this.merekKendaran = merekKendaran;
    }

    public String getWarnaKendraan() {
        return warnaKendraan;
    }

    public void setWarnaKendraan(String warnaKendraan) {
        this.warnaKendraan = warnaKendraan;
    }
    
//    @Override
//    public String toString(){
//    return "PemilikDto [ nama="+ namaPemilik +", jabatan="+ jabatanPemilik +",merek"+ merekKendaran +",warna"+warnaKendraan+"]";
//    }
}
