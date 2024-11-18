package com.exemplu;

public class Revista extends Publicatie {
    private int numarExemplare; // Asigură-te că numele este consistent

    public Revista(String titlu, String autor, int anPublicare, int numarExemplare) {
        super(titlu, autor, anPublicare);
        this.numarExemplare = numarExemplare;
    }

    // Metode get și set
    public int getNumarExemplare() {
        return numarExemplare;
    }

    public void setNumarExemplare(int numarExemplare) {
        this.numarExemplare = numarExemplare;
    }

    @Override
    public String toString() {
        return super.toString() + ", Număr Exemplare: " + numarExemplare;
    }
}
