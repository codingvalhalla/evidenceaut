package com.codingvalhalla.meredith.evidencevozidel.automobily;

import java.util.Objects;

public class OsobniAutomobil extends Vozidlo {

    private static final long serialVersionUID = -4549755242890811689L;

    private Barva barva;
    private double vykon;

    /**
     * Konstruktor osobniho automobilu
     *
     * @param znacka jmeno vyrobce
     * @param SPZ napr.: 4A2 3000
     * @param rokVyroby
     * @param vaha hodnota udavana v tunach (t)
     * @param palivo vyber z prevolenych
     * @param barva vyber z prevolenych
     * @param vykon vykon v kilowattech (kW)
     */
    public OsobniAutomobil(String znacka, String SPZ, int rokVyroby, double vaha, Palivo palivo, Barva barva, double vykon) {
        super(VozidloEnum.OSOBNI_AUTOMOBIL, znacka, SPZ, rokVyroby, vaha, palivo);
        this.barva = barva;
        this.vykon = vykon;
    }

    @Override
    public VozidloEnum getDruh() {
        return super.getDruh();
    }

    public void setVykon(double vykon) {
        this.vykon = vykon;
    }

    public void setBarva(Barva barva) {
        this.barva = barva;
    }

    public double getVykon() {
        return vykon;
    }

    public Barva getBarva() {
        return barva;
    }

    @Override
    public String toString() {
        return "OsobniAutomobil {" + super.toString() + ", barva= " + getBarva() + ", vykon= " + getVykon() + " kW }";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        OsobniAutomobil other = (OsobniAutomobil) obj;
        if (!Objects.equals(this.barva, other.barva)) {
            return false;
        }
        if (!Objects.equals(this.vykon, other.vykon)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(barva, vykon);
    }

}
