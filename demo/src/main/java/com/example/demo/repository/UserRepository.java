/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pangestu
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
      
    @Query(value = "Select user.email, user.name, user.user_name from note left join user on note.id_user=user.id",nativeQuery = true)
    List<NoteRepository> getUser();
      
}
