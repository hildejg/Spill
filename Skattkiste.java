import java.util.ArrayList;
import java.util.Random;

public class Skattkiste {
    int MAX_GJENSTANDER = 5;
    ArrayList<Gjenstand> kiste = new ArrayList<Gjenstand>(MAX_GJENSTANDER);

    public Skattkiste() {

    }

    // metode for aa legge inn i skattkiste, sjekker om den blir satt inn
    public void leggInnGjenstand(Gjenstand gjenstand) {

        if (kiste.size() < MAX_GJENSTANDER) {
            kiste.add(gjenstand);
        }
    }

    public int selgGjenstand(Gjenstand gjenstand) {
        int pris = 0;
        double utregn;
        if (this.erPlass()) {
            Random random = new Random();
            int variasjon = random.nextInt(3);
            // gir 25% mindre av orginalverdi
            if (variasjon == 0) {
                utregn = (gjenstand.hentGjenstandVerdi() * 0.75);
                Double nypris = new Double(utregn);
                pris = nypris.intValue();
            }
            // returnerer 25% mer enn orginalverdi
            if (variasjon == 1) {
                utregn = (gjenstand.hentGjenstandVerdi() * 1.25);
                Double nypris = new Double(utregn);
                pris = nypris.intValue(); 
            } else {
                // gir verdien
                pris = gjenstand.hentGjenstandVerdi();
            }
            // setter inn i kista paa ledig plass, returnerer pris
            kiste.add(kiste.size(), gjenstand);
        }
        return pris;
    }

    public boolean erPlass() {
        return kiste.size() < MAX_GJENSTANDER;
    }

    public boolean harGjenstand() {
        return kiste.size() >= 1;
    }

    public Gjenstand taUtGjenstand() {
        Random random = new Random();
        int tilfeldig = random.nextInt(kiste.size());
        Gjenstand gjenstand = kiste.get(tilfeldig);
        kiste.remove(tilfeldig);
        return gjenstand;
    }
}