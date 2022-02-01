package AULA;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Crud crud = new Crud();
        Scanner sc = new Scanner(System.in);
        System.out.println("Vítejte v anglicko - českém slovníčku");
        int volba = 0;

        while (volba != 6) {
            System.out.println();
            System.out.println("Máte na výběr ze 6 možností:");
            System.out.println("1 - překlad");
            System.out.println("2 - vložit nové slovíčko");
            System.out.println("3 - vymazat slovíčko");
            System.out.println("4 - změna překladu slovíčka");
            System.out.println("5 - počet slovíček ve slovníčku");
            System.out.println("6 - konec");
            System.out.println("Zvolte možnost:");
            volba = Integer.parseInt(sc.nextLine());
            switch (volba) {
                case 1 -> crud.preloz();
                case 2 -> crud.vloz();
                case 3 -> crud.vymaz();
                case 4 -> crud.zmen();
                case 5 -> crud.vypis();
                case 6 -> System.out.println("Děkuji za použití slovníčku.");
                default -> System.out.println("Zadal si špatnou možnost");
            }
        }
    }
}