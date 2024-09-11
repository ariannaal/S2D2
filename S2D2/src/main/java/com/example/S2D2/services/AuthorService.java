package com.example.S2D2.services;

import com.example.S2D2.entities.Autore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AuthorService {

    private List<Autore> AuthorList = new ArrayList<>();

    // ritorna tutti gli autori
    public List<Autore> getAllAuthors() {
        return this.AuthorList;
    }

    // salva gli autori
    public Autore saveAuthor(Autore autore) {
        this.AuthorList.add(autore);
        return autore;
    }

    // ritorna un autore con un certo id
    public Autore findAuthorById(int id) {
        for (Autore autore : AuthorList) {
            if (autore.getId() == id) {
                return autore;
            }
        }
        return null;
    }

    // modifica lo specifico autore
    public Autore findAuthorAndUpdate(int id, Autore nuovoAutore) {
        for (Autore autore : AuthorList) {
            if (autore.getId() == id) {

                autore.setNome(nuovoAutore.getNome());
                autore.setCognome(nuovoAutore.getCognome());

                return autore;
            }
        }
        return null;
    }

    // elimina l'autore soecifico
    public void deleteAuthor(int id) {
        for (Autore autore : AuthorList) {
            if (autore.getId() == id) {
                this.AuthorList.remove(autore);
            }
        }
    }
}
