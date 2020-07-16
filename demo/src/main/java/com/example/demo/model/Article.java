/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.LastModifiedDate;

/**
 *
 * @author pangestu
 */
@Entity
@Table(name = "tbl_article")
public class Article {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "title")
    @JsonProperty(value = "title")
    private String title;
    
    @Column(name = "content")
    @JsonProperty(value = "content")
    private String content;
    
    @Column(name = "created")
    @JsonProperty(value = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    
    @Column(name = "updated")
    @JsonProperty(value = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    
    @Column(name = "id_writer")
    @JsonProperty(value = "id_writer")
    private Integer idWriter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }

    public void setContend(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getIdWriter() {
        return idWriter;
    }

    public void setIdWriter(Integer idWriter) {
        this.idWriter = idWriter;
    }
    
    
}
    


