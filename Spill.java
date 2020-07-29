import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Spill {

    public static void main(String[] arg) throws FileNotFoundException {
        ArrayList<Spiller> spillere;
        Scanner scanner = new Scanner(System.in);
        Terminal terminal = new Terminal(scanner);

        // bestemmer antall spillere
        String antallSpillere = ("Hvor mange spillere onskes aa spille med?");
        String[] alternativerSpillere = new String[] { "1", "2", "3", "4", "5" }; // setter maks grense på 5
        int antallspiller = terminal.beOmKommando(antallSpillere, alternativerSpillere);
        // bestemmer terrenget
        if (terrengvalg(terminal) == 1) {
            Terreng terreng = new Terreng();
            spillere = opprettEnkelTerrengSpiller(terreng, antallspiller);
            System.out.println("Hvor mange trekk?");
            int antalltrekk = (new Scanner(System.in)).nextInt();
            spill(spillere, antalltrekk);

        } else {
            VeivalgTerreng terreng = new VeivalgTerreng();
            spillere = opprettVeivalgTerrengSpiller(terreng, antallspiller);
            System.out.println("Hvor mange trekk?");
            int antalltrekk = (new Scanner(System.in)).nextInt();
            spill(spillere, antalltrekk);
        }

    }
    //oppretter Spillere om det skal være enkelt terreng
    private static ArrayList<Spiller> opprettEnkelTerrengSpiller(Terreng terreng, int antallspiller) {
        ArrayList<Spiller> spillere = new ArrayList<Spiller>();
        Scanner scanner = new Scanner(System.in);
        Terminal terminal = new Terminal(scanner);
        while (antallspiller != 0) {
            int spillervalg = spillervalg(terminal);
            if (spillervalg == 1) {
                System.out.println("Hva skal Spilleren hete?");
                Scanner scan = new Scanner(System.in);
                String navn = scan.nextLine();
                terminal = new Terminal(scanner);
                spillere.add(new Spiller(terreng.hentStart(), terminal, navn));
                antallspiller--;
            } else {
                System.out.println("Hva skal Roboten hete?");
                Scanner scan = new Scanner(System.in);
                String navn = scan.nextLine();
                Robot robot = new Robot();
                spillere.add(new Spiller(terreng.hentStart(), robot, navn));
                antallspiller--;
            }
        }
        return spillere;

    }

    //Oppretter Veivalgspillere
    private static ArrayList<Spiller> opprettVeivalgTerrengSpiller(VeivalgTerreng terreng, int antallspiller) {
        ArrayList<Spiller> spillere = new ArrayList<Spiller>();
        Scanner scanner = new Scanner(System.in);
        Terminal terminal = new Terminal(scanner);
        while (antallspiller != 0) {
            int spillervalg = spillervalg(terminal);
            if (spillervalg == 1) {
                System.out.println("Hva skal Spilleren hete?");
                Scanner scan = new Scanner(System.in);
                String navn = scan.nextLine();
                terminal = new Terminal(scanner);
                spillere.add(new VeivalgSpiller(terreng.hentStart(), terminal, navn));
                antallspiller--;
            } else {
                System.out.println("Hva skal Roboten hete?");
                Scanner scan = new Scanner(System.in);
                String navn = scan.nextLine();
                Robot robot = new Robot();
                spillere.add(new VeivalgSpiller(terreng.hentStart(), robot, navn));
                antallspiller--;
            }
        }
        return spillere;
    }

    private static int terrengvalg(Terminal terminal) {
        String typespill = ("Vil du spille med enkelt terreng eller terreng med veivalg?");
        String[] alternativer = new String[] { "1. Enkelt terreng \n2. Terreng med veivalg" };
        return terminal.beOmKommando(typespill, alternativer);
    }

    private static int spillervalg(Terminal terminal) {
        String typespiller = ("Vil du spille med terminal eller robot?");
        String[] spilleralternativer = new String[] { "1. Terminal \n2. Robot" };
        return terminal.beOmKommando(typespiller, spilleralternativer);
    }

    //Spiller nytt trekk på alle spillere etter tur
    private static void spill(ArrayList<Spiller> spillere, int antallTrekk) {
        while (antallTrekk != 0) {
            for (int x = 0; x < spillere.size(); x++) {
                spillere.get(x).nyttTrekk();
            }
            antallTrekk--;
        }
        for (int y = 0; y < spillere.size(); y++) {
            System.out.println("Spiller: " + spillere.get(y).navn + " fikk en formue paa " + spillere.get(y).formue);
        }
    }
}