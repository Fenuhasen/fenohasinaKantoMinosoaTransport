package com.fenohasinaKantoMinosoa.transport;

import java.time.LocalDate;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Reservation {
    private int id;
    private LocalDate date;
    private Client client;
    private int nombrePassager;
    private Voyage voyage;
    private String typePaiement;
    private StatutReservation statut;

    public int obtenirTotal(){
        var total = 0;
        return total;
    }

}
