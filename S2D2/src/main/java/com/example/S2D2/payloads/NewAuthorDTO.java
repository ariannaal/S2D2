package com.example.S2D2.payloads;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewAuthorDTO(

                           @NotEmpty(message = "Il nome è obbligatorio")
                           @Size(min = 3, max = 20, message = "Il nome deve essere compresa tra 3 e 20 caratteri")
                           String nome,

                           @NotEmpty(message = "Il cognome è obbligatorio")
                           @Size(min = 3, max = 20, message = "Il cognome deve essere compreso tra 3 e 20 caratteri")
                           String cognome,

                           @NotEmpty(message = "La mail è obbligatoria")
                           @Size(min = 3, max = 20, message = "La mail deve essere compresa tra 3 e 20 caratteri")
                           String email,

                           @Min(value = 10, message = "La data di nascita deve essere compresa da 10 caratteri")
                           @Max(value = 10, message = "La data di nascita deve essere compresa da 10 caratteri")
                           String dataDiNascita,

                           @Min(value = 1, message = "L'ID autore deve essere un numero positivo")
                           int author_id

) {}