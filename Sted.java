import java.util.ArrayList;
public class Sted {

    public String beskrivelse;
    public Skattkiste kiste;
    ArrayList<Sted> utganger = new ArrayList<Sted>();

    public Sted(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public void settSkattkiste(Skattkiste innsendtKiste) {
        kiste = innsendtKiste;
    }

    public Skattkiste hentKiste() {
        return kiste;
    }

    public Sted gaaVidere() {
        return this.utganger.get(0);
    }
}