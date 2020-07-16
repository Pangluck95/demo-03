/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Article;
import com.example.demo.model.Writer;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.WriterRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    WriterRepository writerRepository;

    @PostMapping("/")
    public ResponseEntity<?> addArticle(@RequestBody Article articleDetail) {

        Writer writer = writerRepository.findById(articleDetail.getIdWriter()).orElseThrow(() ->  new ResourceNotFoundException("Writer", "id", articleDetail.getIdWriter()));
//        Writer writer = writerRepository.getById(articleDetail.getIdWriter());
        if (writer == null) {
            return new ResponseEntity<>("Writer ID not fount : " + articleDetail.getIdWriter(), HttpStatus.OK);
        }
        articleDetail.setCreated(new Date());
        articleRepository.save(articleDetail);

        return ResponseEntity.ok(articleDetail);
    }

    @GetMapping("/")
    public ResponseEntity<?> getListArticle() {
        List<Article> listArticle = articleRepository.findAll();

        return ResponseEntity.ok(listArticle);
    }
    
    @GetMapping("/getWriterById/{id}")
    public ResponseEntity<?> getListArticle(@PathVariable(value = "id") Integer id) {
        Writer writer = writerRepository.getById(id);

        return ResponseEntity.ok(writer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable(value = "id") Integer idArticle) {

        Article article = articleRepository.findById(idArticle).orElseThrow(() -> new ResourceNotFoundException("Article", "id", idArticle));
        return ResponseEntity.ok(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> editArticle(@PathVariable(value = "id") Integer idArticle, @RequestBody Article articleDetail) {

        Article article = articleRepository.findById(idArticle).orElseThrow(() -> new ResourceNotFoundException("Article", "id", idArticle));

        article.setTitle(articleDetail.getTitle());
        article.setContend(articleDetail.getContent());
        article.setUpdated(new Date());
        articleRepository.save(article);

        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{id}")
    public Article deleteArticle(@PathVariable(value = "id") Integer idArticle) {
        Article article = articleRepository.findById(idArticle).orElseThrow(() -> new ResourceNotFoundException("Article", "id", idArticle));
        articleRepository.delete(article);
        return article;
    }

}
