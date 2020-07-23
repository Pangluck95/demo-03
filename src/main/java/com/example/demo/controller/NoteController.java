/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DTOUSer;
import com.example.demo.model.Note;
import com.example.demo.model.User;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.UserRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;

/**
 *
 * @author pangestuif(note.getId_user()!=null)
*/
@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    UserRepository userRepository;

    // Get All Notes
    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
    
    
    // Create a new Note
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        note.setCreatedAt(new Date());
        note.setUpdatedAt(new Date());
        return noteRepository.save(note);
    }

    @GetMapping("/allNotes")
    public ResponseEntity<?> getAllNootes() {
        List<Note> lstNote = noteRepository.lstNotes();
        return ResponseEntity.ok(lstNote);
    }

    // cari note
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Integer noteId) {
        return noteRepository.findById(noteId).orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    // Update noteObject
    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Integer noteId,
            @Valid @RequestBody Note noteDetails) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    //hapus Note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Integer noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }

//    @GetMapping("/carikanuser/{id}")
//    public String getKaryawanById(@PathVariable(value = "id") Integer id) {
//        Note note = noteRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Kendaraan", "id", id));
//        User karyawan = karyawanRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Karyawan", "id", kendaraan.getId()));
////        Map<String, String> map = new HashMap<>();
//        String name = karyawan.getNama();
//        String jbatan = karyawan.getJabatan();
//        map.put(name, jbatan);
//        return name;
//    public List<Note> getKendaraanBynama(@PathVariable(value = "id_user") Integer Id) {
//        return noteRepository.getUserById(Id);
//    }
    @GetMapping("/mencarikanuser/{id}")
    public Note getKaryawans(@PathVariable(value = "id") Integer Id) {
        Note lstKendaraan = noteRepository.getUserById(Id);
        
        return lstKendaraan;
    }

    
    @GetMapping("/carikanuser/{id}")
    @ResponseBody
    public ResponseEntity<?> findId(@PathVariable(value = "id") Integer noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        User user = userRepository.findById(note.getId_user())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", note.getId_user()));
        return ResponseEntity.ok(note);        
    }
}
