package com.example.S2D2.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "blogposts")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoDiLettura;

    // relazione unidirezionale Many-to-One verso Autore
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Autore autoreId;

    public BlogPost(String categoria, String titolo, String cover, String contenuto, int tempoDiLettura, Autore autoreId) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = cover;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.autoreId=autoreId;
    }

    public int getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public int getTempoDiLettura() {
        return tempoDiLettura;
    }

    public void setTempoDiLettura(int tempoDiLettura) {
        this.tempoDiLettura = tempoDiLettura;
    }

    public Autore getAutoreId() {
        return autoreId;
    }

    public void setAutoreId(Autore autoreId) {
        this.autoreId = autoreId;
    }
}
