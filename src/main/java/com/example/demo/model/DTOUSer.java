/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.util.List;

/**
 *
 * @author pangestu
 */
public class DTOUSer {
    public String descripsi;
    public List<User> listuser;

    public String getDescripsi() {
        return descripsi;
    }

    public void setDescripsi(String descripsi) {
        this.descripsi = descripsi;
    }

    public List<User> getListuser() {
        return listuser;
    }

    public void setListuser(List<User> listuser) {
        this.listuser = listuser;
    }
}
