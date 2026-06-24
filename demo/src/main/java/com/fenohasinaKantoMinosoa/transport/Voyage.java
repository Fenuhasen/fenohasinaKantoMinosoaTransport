import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
    private List<Client> passagers;

    public String confirmerArrivee(){
        this.status = Status.ARRIVEE;
        return "Arrivée confirmée";
    }

    public int nombrePassagers(){
        return this.passagers.size();
    }

    public int nombrePlaceLibre(){
        return this.taxiBrousse.getPlacesMax()-this.listeReservation.size();
    }

    public int dureeEstimee{
        return this.taxiBrousse.getVitesseMoyenne / this.distance;
    }
}
