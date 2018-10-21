package com.codingvalhalla.meredith.evidencevozidel.kolekce;

import com.codingvalhalla.meredith.evidencevozidel.automobily.Vozidlo;

/**
 *
 * @author Meredith
 */
public class DataHandler {

    private Seznam<Vozidlo> seznam;

    public DataHandler() {
        seznam = new Seznam<>();

    }

    public DataHandler(int pocet) throws KolekceException {
        seznam = new Seznam<>(pocet);
    }

    public Seznam<Vozidlo> getSeznam() {
        return seznam;
    }
    /*
    private ReadOnlyIntegerWrapper pocetPolozek = new ReadOnlyIntegerWrapper(0);
    
    public ReadOnlyIntegerProperty pocetPolozekProperty = pocetPolozek.getReadOnlyProperty();
     */
}
