/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Writer;
import com.example.demo.repository.WriterRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/writer")
public class WriterController {
   
    @Autowired
    WriterRepository writerRepository;
    
    @PostMapping("/")
    public Writer addWriter(@RequestBody Writer writerDetail){
        return writerRepository.save(writerDetail);
    } 
    
    @GetMapping("/")
    public ResponseEntity<?> getAllWriter(){   
        List<Writer> listWriter = writerRepository.findAll();
      return ResponseEntity.ok(listWriter);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getWriterById(@PathVariable(value = "id") Integer idWriter){
        Writer writer = writerRepository.findById(idWriter).orElseThrow(() -> new ResourceNotFoundException("Writer", "id", idWriter));
        
        return ResponseEntity.ok(writer);
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getWriterByName(@PathVariable(value = "name")String username){
        List<Writer> writer = writerRepository.getbyname(username);
        return ResponseEntity.ok(writer);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateWriter(@PathVariable(value = "id") Integer id, @RequestBody Writer writerDetail){
    
        Writer writer = writerRepository.findById(id).orElseThrow(() ->  new ResourceNotFoundException("Writer", "id", id));
        writer.setUsername(writerDetail.getUsername());
        writer.setEmail(writerDetail.getEmail());
        writerRepository.save(writer);
        
        return ResponseEntity.ok(writer);
    }
    
    @DeleteMapping("/{id}")
    public Writer deleteWriter(@PathVariable(value = "id") Integer id){
    
        Writer writer = writerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Writer", "id", id));
        writerRepository.delete(writer);
        
        return writer;
    }
}
