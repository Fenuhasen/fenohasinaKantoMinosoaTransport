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
    private Admin admin;

    public void reserver(Voyage voyage, int nombrePassager, String typePayement){
        var reservation = new Reservation(admin.getAgence().getReservations().size() + 1, LocalDate.now(), this, nombrePassager, voyage, typePayement, StatutReservation.NONPAYE);
       admin.getAgence().getReservations().add(reservation);
        voyage.getListeReservation().add(reservation);
    }

    public void payerReservation(int id){
        for (Reservation reservation : admin.getAgence().getReservations()) {
            if(reservation.getId() == id){
                reservation.setStatut(StatutReservation.PAYE);
            }
        }
    }
    
    public void annulerReservation(int id){
        for (Reservation reservation : admin.getAgence().getReservations()) {
            if(reservation.getId() == id){
                reservation.setStatut(StatutReservation.ANNULE);
            }
        }
    }
    
    public List<Reservation> listerReservation(){
        var liste = new ArrayList<Reservation>();
        for (Reservation reservation : admin.getAgence().getReservations()) {
            if (reservation.getClient().equals(this)){
                liste.add(reservation);
            }
        }
        return liste;
    }
    
}
