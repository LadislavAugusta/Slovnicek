package AULA;

import java.sql.*;
import java.util.Scanner;

public class Crud {

    Scanner sc = new Scanner(System.in);

    public void preloz() {
        System.out.println("Zadej anglické slovíčko k překladu:");
        String anglickeSlovicko = sc.nextLine();
        try (Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/slovnicek?user=root&password=");
            PreparedStatement dotaz = spojeni.prepareStatement("SELECT cesky FROM slovo WHERE anglicky=?");) {
            dotaz.setString(1, anglickeSlovicko);
            try (ResultSet vysledky = dotaz.executeQuery()) {
                vysledky.next();
                String cesky = vysledky.getString("cesky");
                System.out.println("Překlad " + anglickeSlovicko + ": " + cesky);
            }
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází");
        }
    }

    public void vloz() {
        System.out.println("Zadej nové slovíčko anglicky:");
        String noveAnglickeSlovicko = sc.nextLine();
        System.out.println("Zadej nové slovíčko česky:");
        String noveCeskeSlovicko = sc.nextLine();
        try (Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/slovnicek?user=root&password=");
            PreparedStatement dotaz = spojeni.prepareStatement("INSERT INTO slovo (anglicky, cesky) VALUES (?, ?)");) {
            dotaz.setString(1, noveAnglickeSlovicko);
            dotaz.setString(2, noveCeskeSlovicko);
            int radku = dotaz.executeUpdate();
            System.out.println(radku);
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází");
        }
    }

    public void vymaz() {
        System.out.println("Zadej anglické slovíčko, které chceš vymazat:");
        String vymazAnglickeslovicko = sc.nextLine();
        try (Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/slovnicek?user=root&password=");
             PreparedStatement dotaz = spojeni.prepareStatement("DELETE FROM slovo WHERE anglicky=?");) {
            dotaz.setString(1, vymazAnglickeslovicko);
            int radku = dotaz.executeUpdate();
            System.out.println(radku);
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází");
        }
    }

    public void zmen() {
        System.out.println("Zadej anglické slovíčko, u kterého chceš upravit překlad:");
        String zmenAnglickeSlovicko = sc.nextLine();
        System.out.println("Zadej nový překlad:");
        String zmenCeskeSlovicko = sc.nextLine();
        try (Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/slovnicek?user=root&password=");
             PreparedStatement dotaz = spojeni.prepareStatement("UPDATE slovo SET cesky=? WHERE anglicky=?");) {
            dotaz.setString(1, zmenCeskeSlovicko);
            dotaz.setString(2, zmenAnglickeSlovicko);
            int radku = dotaz.executeUpdate();
            System.out.println(radku);
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází");
        }
    }

    public void vypis() {
        try (Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/slovnicek?user=root&password=");
             PreparedStatement dotaz = spojeni.prepareStatement("SELECT * FROM slovo");
             ResultSet vysledky = dotaz.executeQuery();) {

            while (vysledky.next()) {
                int id = vysledky.getInt(1);
                String cesky = vysledky.getString("cesky");
                String anglicky = vysledky.getString("anglicky");
                System.out.println("Id: " + id + ", česky: " + cesky + ", anglicky: " + anglicky);
            }

        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází");
        }
        try (Connection spojeni = DriverManager.getConnection("jdbc:mysql://localhost/slovnicek?user=root&password=");
             PreparedStatement dotaz = spojeni.prepareStatement("SELECT COUNT(*) FROM slovo");) {
            ResultSet vysledek = dotaz.executeQuery();
            vysledek.next();
            System.out.println(vysledek.getInt(1));
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází");
        }
    }
}
