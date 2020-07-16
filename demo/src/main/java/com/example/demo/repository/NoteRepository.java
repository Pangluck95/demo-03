/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Note;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 *
 * @author pangestu
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{
    
    @Query(value = "SELECT FROM Note a ", nativeQuery = true)
    List<Note> lstNotes();
    
    @Query(value = "Select user.email, user.name, user.user_name from note left join user on note.id_user=user.id where id = ?1",nativeQuery = true)
    Note getUserById(Integer Id);
    
//    @Query(value = "Select user.email, user.name, user.user_name from note left join user on note.id_user=user.id where id = %?1%",nativeQuery = true)
//        List<Note> getUserByNama();
    
//    @Query(value = "Select user.email, user.name, user.user_name from note left join user on note.id_user=user.id",nativeQuery = true)
//    List<Note> FindUser();

}