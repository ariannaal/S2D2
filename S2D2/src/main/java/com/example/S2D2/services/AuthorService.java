package com.example.S2D2.services;

import com.example.S2D2.entities.Autore;
import com.example.S2D2.exceptions.BadRequestException;
import com.example.S2D2.exceptions.NotFoundException;
import com.example.S2D2.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthorService {

    private List<Autore> AuthorList = new ArrayList<>();

    @Autowired
    private AuthorRepository authorRepository;

    // ritorna tutti gli autori
    public List<Autore> getAllAuthors() {
        return this.AuthorList;
    }

    // salva gli autori
    public Autore saveAuthor(Autore autore) {
        if (this.authorRepository.existsByEmail(autore.getEmail())) {
            throw new BadRequestException("L'email " + autore.getEmail() + " è già in uso!");
        } else {
            autore.setAvatar("https://ui-avatars.com/api/?name="+autore.getNome()+"+"+autore.getCognome());
        }
        return this.authorRepository.save(autore);
    }

    // ritorna un autore con un certo id
//    public Autore findById(int id) {
//        return authorRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException(id));
//    }
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
