import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Terreng {

    Sted start;
    Lenkeliste<Sted> steder = new Lenkeliste<Sted>();
    ArrayList<Gjenstand> gjenstandene = new ArrayList<Gjenstand>();
    ArrayList<Skattkiste> skattkister = new ArrayList<Skattkiste>();

    public Terreng() throws FileNotFoundException {
        // metoder for aa legge steder inn i liste, lese gjenstander inn i kister og saa
        // sette kister paa steder
        leggInnSted();
        lesGjenstand();
        lagSkattkiste();
        plasserSkattkiste();
    }

    public Sted hentStart() {
        return steder.hent(0);
    }

    protected void leggInnSted() throws FileNotFoundException {
        Scanner lesSteder = new Scanner(new File("steder.txt"));
        while (lesSteder.hasNextLine()) {
            String beskrivelse = lesSteder.nextLine();
            Sted sted = new Sted(beskrivelse); // lager nytt sted med beskrivelsen
            steder.leggTil(sted);

        }
        steder.hent(steder.stoerrelse() - 1).utganger.add(steder.hent(0));
        for (int x = 0; x < steder.stoerrelse() - 2; x++) {
            steder.hent(x).utganger.add(steder.hent(x + 1));      

        }
    }

    private void lesGjenstand() throws FileNotFoundException {
        // legger inn gjenstander i liste
        Scanner gjenstander = new Scanner(new File("gjenstander.txt"));
        while (gjenstander.hasNextLine()) {
            String[] biter = gjenstander.nextLine().split(" ");
            String navn = biter[0];
            int verdi = Integer.parseInt(biter[1]);
            gjenstandene.add(new Gjenstand(navn, verdi));

        }
    }

    // fyller opp. Naar alle gjenstander er brukt en gang vil det velges random fra
    // listen
    private void lagSkattkiste() {
        int gx = 0;
        for (int x = 0; x < steder.stoerrelse(); x++) {
            Skattkiste skattkiste = new Skattkiste();
            Random random = new Random();
            int antallfyll = random.nextInt(skattkiste.MAX_GJENSTANDER + 1);
            for (int y = 0; y < antallfyll; y++) {
                if (gx < gjenstandene.size()) {
                    skattkiste.kiste.add(gjenstandene.get(gx));
                    gx++;
                } else {
                    skattkiste.kiste.add(gjenstandene.get(random.nextInt(gjenstandene.size())));
                }
            }
            skattkister.add(skattkiste);
        }
    }

    private void plasserSkattkiste() {
        for (int x = 0; x < steder.stoerrelse(); x++) {
            steder.hent(x).settSkattkiste(skattkister.get(x));
        }
    }
}
