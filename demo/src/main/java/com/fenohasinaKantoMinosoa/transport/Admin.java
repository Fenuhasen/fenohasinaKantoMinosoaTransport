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
    
    public void creerVoyage(int id, Centre centreDepart, Centre centreArrivee, int distance, LocalDate dateDepart, Classe classe, int prixBillet, TaxiBrousse taxiBrousse, Chauffeur chauffeur){
        var voyage = new Voyage(id, centreDepart, centreArrivee, distance, dateDepart, classe, prixBillet, taxiBrousse, chauffeur, new ArrayList<>(), StatutVoyage.AVENIR);
    }

    public void retarderVoyage(Voayge voyage){

    }

    public void annulerVoyage(Voyage voyage){

    }

    public int obtenirDepense(){
        return 0;
    }
    
    public int obtenirRevenue(){
        return 0;
    }
}
