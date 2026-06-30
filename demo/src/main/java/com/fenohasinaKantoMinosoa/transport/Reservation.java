package com.fenohasinaKantoMinosoa.transport;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Reservation {
    private int id;
    private LocalDate date;
    private Client client;
    private int nombrePassager;
    private Voyage voyage;
    private String typePaiement;
    private StatutReservation statut;

    public int obtenirTotal(){
        var total = voyage.getPrixBillet() * nombrePassager;
        return total;
    }

}
