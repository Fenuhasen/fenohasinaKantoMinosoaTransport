package com.fenohasinaKantoMinosoa.transport;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class TaxiBrousse {
    private int id;
    private String matricule;
    private int placesMax;
    private int depenseParKilometre;
    private int vitesseMoyenne;
    //methodes coming soon
}