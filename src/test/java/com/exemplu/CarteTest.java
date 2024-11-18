package com.exemplu;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CarteTest {

    @Test
    public void testCalculeazaNumarCuvinteValid() {
        // Creăm un obiect Carte
        Carte carte = new Carte("Povestea vieții", "Ion Popescu", 2010, 350);

        // Verificăm numărul total de cuvinte pentru 300 de cuvinte pe pagină
        int cuvintePePagina = 300;
        int numarCuvinteAsteptat = 350 * 300;

        assertEquals(numarCuvinteAsteptat, carte.calculeazaNumarCuvinte(cuvintePePagina));
    }

    @Test
    public void testCalculeazaNumarCuvinteInvalid() {
        // Creăm un obiect Carte
        Carte carte = new Carte("Misterele nopții", "Maria Ionescu", 2015, 280);

        // Verificăm că metoda aruncă excepție pentru o valoare invalidă
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            carte.calculeazaNumarCuvinte(0); // Cuvinte pe pagină este 0, deci invalid
        });

        assertEquals("Numărul de cuvinte pe pagină trebuie să fie pozitiv.", exception.getMessage());
    }
}
