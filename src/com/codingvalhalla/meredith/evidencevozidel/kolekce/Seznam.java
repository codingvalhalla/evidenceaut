package com.codingvalhalla.meredith.evidencevozidel.kolekce;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

/**
 *
 * @author Meredith
 * @param <E> E
 */
public class Seznam<E> implements ISeznam<E> {

    class Data {

        E data;
        Data dataZa;
        Data dataPred;

        public Data(E data) {
            this.data = data;
            this.dataZa = null;
            this.dataPred = null;

        }
    }
    private final int VELIKOST;
    private int pocet;

    private Data seznamPrvni = null;
    private Data sezmamPosledni = null;
    private Data seznamUkazatel = null;

    /**
     * Vytvori prazdny seznam bez maximalni velikosti
     */
    public Seznam() {
        VELIKOST = -1;
        pocet = 0;

    }

    /**
     * Vytvori prazdni seznam s maximalni
     *
     * @param maxPocet
     * @throws
     * com.codingvalhalla.meredith.evidencevozidel.kolekce.KolekceException
     * pokud je zadana hodnota zaporna nebo rovna nule
     */
    public Seznam(int maxPocet) throws KolekceException {
        if (maxPocet <= 0) {
            throw new KolekceException("Zadan spatny argument" + maxPocet);
        }
        this.VELIKOST = maxPocet;
        pocet = 0;

    }

    /**
     * Vraci maximalni velikost seznamu.
     *
     * @return maximalni velikost seznamu
     */
    @Override
    public int getVelikost() {
        return VELIKOST;
    }

    /**
     * Vraci pocet prvku v seznamu.
     *
     * @return pocet prvku v seznamu
     */
    @Override
    public int getPocet() {
        return pocet;
    }

    /**
     * Vraci {@code true} pokud seznam neobsahuje zadne prvky
     *
     * @return {@code true} pokud seznam neobsahuje zadne prvky
     */
    @Override
    public boolean jePrazdny() {
        return (pocet == 0);
    }

    /**
     * Vraci {@code true} pokud je seznam plny
     *
     * @return {@code true} pokud je seznam plny
     */
    @Override
    public boolean jePlny() {
        return (pocet == VELIKOST);
    }

    /**
     * Vlozi objekt s daty do seznamu
     *
     * @param data vkladany objekt do seznamu
     * @throws KolekceException pokud prekroci velikost seznamu
     */
    @Override
    public void pridej(E data) throws KolekceException {
        Objects.requireNonNull(data);
        Data nove = new Data(data);
        if (!jePlny()) {
            if (jePrazdny()) {
                seznamPrvni = nove;
            } else {
                seznamUkazatel.dataPred = sezmamPosledni;
                seznamUkazatel.dataZa = nove;
            }
            seznamUkazatel = nove;
            sezmamPosledni = nove;
            pocet++;

        } else {
            throw new KolekceException("Prekrocena velikost seznamu");
        }
    }

    /**
     * Metoda vrati datovy objekt ze seznamu podle shody s objektem (klicem) v
     * parametru metody.
     *
     * @param klic identifikace objektu, podle ktereho se vyhledá datovy objekt
     * v seznamu
     * @return instanci nalezeneho objektu nebo pokud je seznam prazdny, tak
     * vrati null.
     */
    @Override
    public E najdi(E klic) {
        Data ukazatel = seznamPrvni;
        while (ukazatel != null) {
            if (ukazatel.data.equals(klic)) {
                return ukazatel.data;
            }
            ukazatel = ukazatel.dataZa;
        }
        return null;
    }

    @Override
    public E odeber(E klic) {
        Data ukazatel = seznamPrvni;
        Data predUkazatelem = null;
        while (ukazatel != null) {
            if (ukazatel.data.equals(klic)) {
                E vystup = ukazatel.data;
                if (predUkazatelem != null) {
                    predUkazatelem.dataZa = ukazatel.dataZa;
                }
                pocet--;
                return vystup;
            }
            predUkazatelem = ukazatel;
            ukazatel = ukazatel.dataZa;

        }
        return null;
    }

    /**
     * Zrusi obsah seznamu.
     */
    @Override
    public void zrus() {
        seznamPrvni = null;
        sezmamPosledni = null;
        seznamUkazatel = null;
        pocet = 0;
    }

    /**
     * Metoda vrati pole s kopiemi datovych prvku seznamu o delce presne
     * odpovidajici poctu vlozenych objektu. Typ prvku pole bude Object, protoze
     * typ prvku pole nelze zmenit.
     *
     * @return pole objektu
     */
    @Override
    public E[] toArray() {
        Data ukazatel = seznamPrvni;
        E[] theArray;
        theArray = (E[]) new Object[pocet];
        for (int i = 0; i < pocet; i++) {
            theArray[i] = ukazatel.data;
            ukazatel = ukazatel.dataZa;
        }

        return theArray;

    }

    /**
     * Metoda vrati pole s kopiemi datovych prvku seznamu o delce presne
     * odpovidajici poctu vlozenych objektu. Pole bude mit prvky stejneho typu
     * jako pole v parametru.
     *
     * @param array vzorove pole s ocekavanym typem prvku pole
     *
     * @return pole objektu
     *
     * @throws IllegalArgumentException vystavi vyjimku, kdyz pole je mensi nez
     * pocet prvku v seznamu
     */
    @Override
    public E[] toArray(E[] array) throws IllegalArgumentException {
        if (array.length < pocet) {
            throw new IllegalArgumentException();
        }
        Data ukazatel = seznamPrvni;

        for (int i = 0; i < pocet; i++) {
            array[i] = ukazatel.data;
            ukazatel = ukazatel.dataZa;
        }
        return array;
    }

    /**
     * Metoda vrati pole s kopiemi datovych prvku seznamu o delce presne
     * odpovidajici poctu vlozenych objektu. Pole bude mit prvky typu E
     *
     * @param f funkce na vytvoreni pole skutecnym typem prvku
     *
     * @return pole s kopiemi datovych prvku seznamu
     */
    @Override
    public E[] toArray(Function<Integer, E[]> f) {
        E[] vystup = f.apply(pocet);
        return toArray(vystup);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Data ukazatel = seznamUkazatel;

            @Override
            public boolean hasNext() {
                return (ukazatel.data != null);
            }

            @Override
            public E next() {
                E vystup = null;
                if (hasNext()) {
                    vystup = ukazatel.data;
                }
                return vystup;
            }
        };
    }

}
