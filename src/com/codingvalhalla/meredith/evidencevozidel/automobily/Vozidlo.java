package com.codingvalhalla.meredith.evidencevozidel.automobily;

import java.util.Objects;

public abstract class Vozidlo implements java.io.Serializable {

    private static final long serialVersionUID = -8469035288741271724L;
    private final VozidloEnum druh;
    private final String ZNACKA;
    private String SPZ;
    private final int ROK_VYROBY;
    private double vaha;
    private Palivo palivo;

    /**
     * Constructor 1
     *
     * @param druh vozidla
     * @param znacka jmeno vyrobce
     * @param SPZ napr.: 4A2 3000
     * @param rokVyroby rok vyroby vozu
     * @param vaha hodnota udavana v tunach (t)
     * @param palivo vyber z prevolenych
     */
    public Vozidlo(VozidloEnum druh, String znacka, String SPZ, int rokVyroby, double vaha, Palivo palivo) {
        this.druh = druh;
        this.ZNACKA = znacka;
        this.SPZ = SPZ;
        this.ROK_VYROBY = rokVyroby;
        this.vaha = vaha;
        this.palivo = palivo;
    }

    public VozidloEnum getDruh() {
        return druh;
    }

    public void setSPZ(String SPZ) {
        this.SPZ = SPZ;
    }

    public void setPalivo(Palivo palivo) {
        this.palivo = palivo;
    }

    public void setVAHA(double VAHA) {
        this.vaha = VAHA;
    }

    public String getSPZ() {
        return SPZ;
    }

    public int getROK_VYROBY() {
        return ROK_VYROBY;
    }

    public double getVAHA() {
        return vaha;
    }

    public String getZNACKA() {
        return ZNACKA;
    }

    public Palivo getPalivo() {
        return palivo;
    }

    @Override
    public String toString() {
        return "Znacka= " + getZNACKA() + ", SPZ= " + getSPZ() + ", Rok vyboby= " + getROK_VYROBY() + ", Váha= " + getVAHA() + " t , Palivo= " + getPalivo();
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
        Vozidlo other = (Vozidlo) obj;
        if (!Objects.equals(this.druh, other.druh)) {
            return false;
        }
        if (!Objects.equals(this.ZNACKA, other.ZNACKA)) {
            return false;
        }
        if (!Objects.equals(this.SPZ, other.SPZ)) {
            return false;
        }
        if (!Objects.equals(this.ROK_VYROBY, other.ROK_VYROBY)) {
            return false;
        }
        if (!Objects.equals(this.vaha, other.vaha)) {
            return false;
        }
        if (!Objects.equals(this.palivo, other.palivo)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ROK_VYROBY, SPZ, ZNACKA, palivo, vaha, druh);
    }

    }
