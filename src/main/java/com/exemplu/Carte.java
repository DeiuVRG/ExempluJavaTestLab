package com.exemplu;

public class Carte extends Publicatie {
    private int numarPagini;

    public Carte(String titlu, String autor, int anPublicare, int numarPagini) {
        super(titlu, autor, anPublicare);
        this.numarPagini = numarPagini;
    }

    // Metode get și set
    public int getNumarPagini() {
        return numarPagini;
    }

    public void setNumarPagini(int numarPagini) {
        this.numarPagini = numarPagini;
    }

    @Override
    public String toString() {
        return super.toString() + ", Număr Pagini: " + numarPagini;
    }

    // Noua metodă pentru calcularea numărului total de cuvinte
    public int calculeazaNumarCuvinte(int cuvintePePagina) {
        if (cuvintePePagina <= 0) {
            throw new IllegalArgumentException("Numărul de cuvinte pe pagină trebuie să fie pozitiv.");
        }
        return numarPagini * cuvintePePagina;
    }
}
