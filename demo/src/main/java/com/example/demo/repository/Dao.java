/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import java.util.List;

/**
 *
 * @author pangestu
 */
public interface Dao<T> {
    List<T> getAll();
    void save(T t);
    void delete(T t);
//    void update(T t, String[] params);
    
}
