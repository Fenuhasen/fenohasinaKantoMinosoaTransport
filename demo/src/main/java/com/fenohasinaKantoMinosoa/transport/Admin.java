package com.fenohasinaKantoMinosoa.transport;

import java.time.LocalDate;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Admin {
    private int id;
    private String nom;
    private String prenom;
    private Agence agence;

    public void creerVoyage(Centre centreDepart, Centre centreArrivee, int distance, LocalDate dateDepart, Classe classe, int prixBillet, TaxiBrousse taxiBrousse, Chauffeur chauffeur){
        var voyage = new Voyage(agence.getVoyages().size() + 1 , centreDepart, centreArrivee, distance, dateDepart, classe, prixBillet, taxiBrousse, chauffeur, new ArrayList<>(), Status.EN_PREPARATION);
        agence.getVoyages().add(voyage);
    }
    
    public void retarderVoyage(int id, LocalDate dateDepart){
        for (Voyage voyage : agence.getVoyages()) {
            if(voyage.getId() == id){
                voyage.setDateDepart(dateDepart);
            }
        }
    }
    
    public void annulerVoyage(int id){
        for (Voyage voyage : agence.getVoyages()) {
            if(voyage.getId() == id){
                voyage.setStatus(Status.ANNULE);
            }
        }
    }

    public int obtenirDepense(){
        int total = 0;
        
        for (Chauffeur chauffeur : agence.getChauffeurs()) {
            total += chauffeur.obtenirTotalSalaire();
        }

        for (Voyage voyage : agence.getVoyages()){
            total += voyage.getDistance() * voyage.getTaxiBrousse().getDepenseParKilometre();
        }

        return total;
    }
    
    public int obtenirRevenue(){
        int total = 0;
        for (Reservation reservation : agence.getReservations()){
            if(reservation.getStatut() == StatutReservation.PAYE){
                total += reservation.obtenirTotal();
            }
        }
        return total;
    }
}
