package com.fenohasinaKantoMinosoa.transport;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.fenohasinaKantoMinosoa.transport.Reservation;

@AllArgsConstructor
@Getter
@Setter
public class Client {
    private int id;
    private String nom;
    private String prenom;
    private String contact;
    private String adresse;

    public void reserver(Voyage voyage, int nombrePassager, String typePayement){
        var reservation = new Reservation(0, LocalDate.now(), this, nombrePassager, voyage, typePayement, StatutReservation.NONPAYE);
    }

    public void payerReservation(int id){

    }
    
    public void annulerReservation(int id){

    }

    public List<Reservation> listerReservation(){
        var liste = new ArrayList<Reservation>();
        return liste;
    }

    
}
