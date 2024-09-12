package com.example.S2D2.services;

import com.example.S2D2.entities.Autore;
import com.example.S2D2.exceptions.BadRequestException;
import com.example.S2D2.exceptions.NotFoundException;
import com.example.S2D2.payloads.NewAuthorDTO;
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
    public Autore save(NewAuthorDTO body) {
        // Check if the email is already used
        authorRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + body.email() + " è già stata utilizzata");
        });

        Autore newAuthor = new Autore();
        newAuthor.setNome(body.nome());
        newAuthor.setCognome(body.cognome());
        newAuthor.setEmail(body.email());
        newAuthor.setDataDiNascita(String.valueOf(body.dataDiNascita()));
        newAuthor.setAvatar("https://ui-avatars.com/api/?name=" + body.nome().charAt(0) + "+" + body.cognome().charAt(0));

        return authorRepository.save(newAuthor);
    }
    // ritorna un autore con un certo id
    public Autore findById(String id){
        return this.authorRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new NotFoundException(id));
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
