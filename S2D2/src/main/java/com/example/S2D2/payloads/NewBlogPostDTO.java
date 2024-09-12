package com.example.S2D2.payloads;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewBlogPostDTO(

        @NotEmpty(message = "La categoria è obbligatoria")
        @Size(min = 3, max = 20, message = "La categoria deve essere compresa tra 3 e 20 caratteri")
        String categoria,

        @NotEmpty(message = "Il titolo è obbligatorio")
        @Size(min = 3, max = 30, message = "Il titolo deve essere compreso tra 3 e 30 caratteri")
        String titolo,

        @NotEmpty(message = "Il contenuto è obbligatorio")
        @Size(min = 3, max = 100, message = "Il contenuto deve essere compreso tra 3 e 100 caratteri")
        String contenuto,

        @Min(value = 1, message = "Il tempo di lettura deve essere almeno 1")
        @Max(value = 200, message = "Il tempo di lettura deve essere al massimo 200")
        int tempoDiLettura,

        @Min(value = 1, message = "L'ID autore deve essere un numero positivo")
        int author_id
) {}