package com.exemplu;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Creăm colecția de publicații
        List<Publicatie> publicatii = new ArrayList<>();

        // Adăugăm câteva cărți și reviste pentru testare
        publicatii.add(new Carte("Povestea vieții", "Ion Popescu", 2010, 350));
        publicatii.add(new Carte("Misterele nopții", "Maria Ionescu", 2015, 280));
        publicatii.add(new Carte("Călătorie în timp", "George Vasilescu", 2020, 400));
        publicatii.add(new Revista("Știința pentru toți", "Ana Dumitrescu", 2018, 5000));
        publicatii.add(new Revista("Moda azi", "Elena Petrescu", 2019, 7500));
        publicatii.add(new Revista("Călătorii în lume", "Cristian Popa", 2021, 6000));

        // Scriem publicațiile într-un fișier CSV
        scriePublicatiiInCSV(publicatii, "publicatii.csv");

        // Afișăm meniul interactiv
        meniuInteractiv(publicatii);
    }

    // Meniu interactiv
    public static void meniuInteractiv(List<Publicatie> publicatii) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Meniu Interactiv ---");
            System.out.println("1. Afișează toate publicațiile");
            System.out.println("2. Filtrează publicațiile după anul de publicare");
            System.out.println("3. Afișează doar cărțile");
            System.out.println("4. Afișează doar revistele");
            System.out.println("5. Adaugă o nouă publicație");
            System.out.println("6. Calculează numărul total de cuvinte pentru o carte");
            System.out.println("7. Afișează revista cu cele mai multe exemplare");
            System.out.println("8. Ieșire");
            System.out.print("Alege o opțiune: ");

            int optiune = scanner.nextInt();
            scanner.nextLine(); // Consumăm newline

            switch (optiune) {
                case 1 -> afiseazaToatePublicatiile(publicatii);
                case 2 -> filtreazaDupaAn(publicatii, scanner);
                case 3 -> afiseazaCarti(publicatii);
                case 4 -> afiseazaReviste(publicatii);
                case 5 -> adaugaPublicatie(publicatii, scanner);
                case 6 -> calculeazaNumarCuvinteCarte(publicatii, scanner);
                case 7 -> afiseazaRevistaCuCeleMaiMulteExemplare(publicatii);
                case 8 -> {
                    System.out.println("La revedere!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opțiune invalidă. Încearcă din nou.");
            }
        }
    }

    // 1. Afișează toate publicațiile
    public static void afiseazaToatePublicatiile(List<Publicatie> publicatii) {
        System.out.println("\nToate publicațiile:");
        publicatii.forEach(System.out::println);
    }

    // 2. Filtrează publicațiile după anul de publicare
    public static void filtreazaDupaAn(List<Publicatie> publicatii, Scanner scanner) {
        System.out.print("Introdu anul minim de publicare: ");
        int an = scanner.nextInt();
        System.out.println("\nPublicațiile publicate după anul " + an + ":");
        publicatii.stream()
                .filter(p -> p.getAnPublicare() >= an)
                .forEach(System.out::println);
    }

    // 3. Afișează doar cărțile
    public static void afiseazaCarti(List<Publicatie> publicatii) {
        System.out.println("\nCărțile:");
        publicatii.stream()
                .filter(p -> p instanceof Carte)
                .map(Carte.class::cast)
                .forEach(System.out::println);
    }

    // 4. Afișează doar revistele
    public static void afiseazaReviste(List<Publicatie> publicatii) {
        System.out.println("\nRevistele:");
        publicatii.stream()
                .filter(p -> p instanceof Revista)
                .map(Revista.class::cast)
                .forEach(System.out::println);
    }

    // 5. Adaugă o nouă publicație
    public static void adaugaPublicatie(List<Publicatie> publicatii, Scanner scanner) {
        System.out.print("Tipul publicației (Carte/Revista): ");
        String tip = scanner.nextLine().trim();

        if (tip.equalsIgnoreCase("Carte")) {
            System.out.print("Titlu: ");
            String titlu = scanner.nextLine();
            System.out.print("Autor: ");
            String autor = scanner.nextLine();
            int anPublicare = citesteIntValid(scanner, "Anul de publicare: ");
            int numarPagini = citesteIntValid(scanner, "Număr de pagini: ");

            Carte carte = new Carte(titlu, autor, anPublicare, numarPagini);
            publicatii.add(carte);
            System.out.println("Cartea a fost adăugată cu succes!");
        } else if (tip.equalsIgnoreCase("Revista")) {
            System.out.print("Titlu: ");
            String titlu = scanner.nextLine();
            System.out.print("Autor: ");
            String autor = scanner.nextLine();
            int anPublicare = citesteIntValid(scanner, "Anul de publicare: ");
            int numarExemplare = citesteIntValid(scanner, "Număr de exemplare: ");

            Revista revista = new Revista(titlu, autor, anPublicare, numarExemplare);
            publicatii.add(revista);
            System.out.println("Revista a fost adăugată cu succes!");
        } else {
            System.out.println("Tip invalid. Te rog să introduci 'Carte' sau 'Revista'.");
        }
    }

    // 6. Calculează numărul total de cuvinte pentru o carte
    public static void calculeazaNumarCuvinteCarte(List<Publicatie> publicatii, Scanner scanner) {
        System.out.println("\n--- Calculează numărul total de cuvinte pentru o carte ---");

        List<Carte> carti = publicatii.stream()
                .filter(p -> p instanceof Carte)
                .map(Carte.class::cast)
                .toList();

        if (carti.isEmpty()) {
            System.out.println("Nu există cărți în colecție.");
            return;
        }

        System.out.println("Selectează indexul unei cărți:");
        for (int i = 0; i < carti.size(); i++) {
            System.out.println((i + 1) + ". " + carti.get(i));
        }

        int index = citesteIntValid(scanner, "Indexul cărții (1-" + carti.size() + "): ") - 1;

        if (index >= 0 && index < carti.size()) {
            Carte carte = carti.get(index);
            int cuvintePePagina = citesteIntValid(scanner, "Numărul de cuvinte pe pagină: ");
            int totalCuvinte = carte.calculeazaNumarCuvinte(cuvintePePagina);
            System.out.println("Cartea \"" + carte.getTitlu() + "\" conține " + totalCuvinte + " cuvinte.");
        } else {
            System.out.println("Index invalid.");
        }
    }

    // 7. Afișează revista cu cele mai multe exemplare
    public static void afiseazaRevistaCuCeleMaiMulteExemplare(List<Publicatie> publicatii) {
        Optional<Revista> revistaMaxExemplare = publicatii.stream()
                .filter(publicatie -> publicatie instanceof Revista)
                .map(publicatie -> (Revista) publicatie)
                .max(Comparator.comparingInt(Revista::getNumarExemplare));

        if (revistaMaxExemplare.isPresent()) {
            System.out.println("Revista cu cele mai multe exemplare este: " + revistaMaxExemplare.get().getTitlu());
        } else {
            System.out.println("Nu există reviste în colecție.");
        }
    }

    // Citire validă de numere întregi
    public static int citesteIntValid(Scanner scanner, String mesaj) {
        while (true) {
            System.out.print(mesaj);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Input invalid. Te rog introdu un număr valid.");
                scanner.next(); // Consumăm inputul invalid
            }
        }
    }

    // Scrierea publicațiilor într-un fișier CSV
    public static void scriePublicatiiInCSV(List<Publicatie> publicatii, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Tip,Titlu,Autor,AnPublicare,NumarPagini,NumarExemplare");
            writer.newLine();

            for (Publicatie publicatie : publicatii) {
                if (publicatie instanceof Carte) {
                    Carte carte = (Carte) publicatie;
                    writer.write("Carte," + carte.getTitlu() + "," + carte.getAutor() + "," + carte.getAnPublicare() + "," + carte.getNumarPagini() + ",");
                } else if (publicatie instanceof Revista) {
                    Revista revista = (Revista) publicatie;
                    writer.write("Revista," + revista.getTitlu() + "," + revista.getAutor() + "," + revista.getAnPublicare() + ",," + revista.getNumarExemplare());
                }
                writer.newLine();
            }

            System.out.println("Fișierul CSV a fost creat cu succes.");
        } catch (IOException e) {
            System.out.println("Eroare la scrierea în fișier: " + e.getMessage());
        }
    }
}
