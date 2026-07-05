package com.fenohasinaKantoMinosoa.transport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class Chauffeur {
    private int id;
    private String nom;
    private String prenom;
    private int salaire;
    private Admin admin;

    public int obtenirTotalSalaire() {
        return 0;
    }

    public Chauffeur(int id, String nom, String prenom, int salaire, Admin admin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.salaire = salaire;
        this.admin = admin;
        
        admin.getAgence().getChauffeurs().add(this);


        String sql = "INSERT INTO chauffeur(id, nom, prenom, salaire) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = ConnexionBD.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            stmt.setString(2, nom);
            stmt.setString(3, prenom);
            stmt.setInt(4, salaire);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}