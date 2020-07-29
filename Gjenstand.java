public class Gjenstand {
    String navn;
    int verdi;

    public Gjenstand(String navn, int verdi) {
        this.navn = navn;
        this.verdi = verdi;
    }

    public String hentGjenstandNavn() {
        return navn;
    }

    public int hentGjenstandVerdi() {
        return verdi;
    }
}