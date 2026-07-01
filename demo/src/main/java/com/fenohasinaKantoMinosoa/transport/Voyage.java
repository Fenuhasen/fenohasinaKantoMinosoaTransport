package com.fenohasinaKantoMinosoa.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter

public class Voyage {
    private int id;
    private Centre centreDepart;
    private Centre centreArrivee;
    private int distance;
    private LocalDate dateDepart;
    private Classe classe;
    private int prixBillet;
    private TaxiBrousse taxiBrousse;
    private Chauffeur chauffeur;
    private List<Reservation> listeReservation;
    private Status status;

    public String confirmerArrivee(){
        this.status = Status.ARRIVEE;
        return "Arrivée confirmée";
    }

    public int nombrePassagers(){
        int total = 0;
        for (Reservation reservation : listeReservation) {
            total += reservation.getNombrePassager();
        }
        return total;
    }

    public int nombrePlaceLibre(){
        return this.getTaxiBrousse().getPlacesMax() - nombrePassagers();
    }

    public int dureeEstimee(){
        return this.taxiBrousse.getVitesseMoyenne() / this.distance;
    }

    public Voyage(int id, Centre centreDepart, Centre centreArrivee, int distance, LocalDate dateDepart, Classe classe, int prixBillet,
            TaxiBrousse taxiBrousse, Chauffeur chauffeur) {
        this.id = id;
        this.centreDepart = centreDepart;
        this.centreArrivee = centreArrivee;
        this.distance = distance;
        this.dateDepart = dateDepart;
        this.classe = classe;
        this.prixBillet = prixBillet;
        this.taxiBrousse = taxiBrousse;
        this.chauffeur = chauffeur;
        this.status = Status.EN_PREPARATION;
        this.listeReservation = new ArrayList<>();
    }
}