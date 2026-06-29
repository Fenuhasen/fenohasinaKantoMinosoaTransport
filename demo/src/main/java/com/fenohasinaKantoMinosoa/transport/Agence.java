package com.fenohasinaKantoMinosoa.transport;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class Agence {
    private int id;
    private List<Client> clients;
    private List<Reservation> reservations;
    private List<Voyage> voyages;
    private List<TaxiBrousse> taxiBrousses;
    private List<Chauffeur> chauffeurs;
    private List<Centre> centres;

    
}
