public class Spiller {
    Skattkiste ryggsekk;
    int formue;
    String navn;
    String[] alternativer;
    Brukergrensesnitt grensesnitt;
    Sted plassering;

    // to konstruktorer - den vil overrride
    public Spiller(Sted start, Terminal grensesnitt, String navn) {
        this.ryggsekk = new Skattkiste();
        this.formue = 0;
        this.navn = navn;
        this.plassering = start;
        this.alternativer = new String[] { "1. ja", "2. nei" };
        this.grensesnitt = grensesnitt;

    }

    public Spiller(Sted start, Robot grensesnitt, String navn) {
        this.ryggsekk = new Skattkiste();
        this.formue = 0;
        this.navn = navn;
        this.plassering = start;
        this.alternativer = new String[] { "1. ja", "2. nei" };
        this.grensesnitt = grensesnitt;

    }

    public void nyttTrekk() {
        grensesnitt.giStatus("\n" + this.navn + " har " + this.formue + " i formue. \n" + plassering.beskrivelse);
        String giSpoersmaal = ("Vil du selge en gjenstand? Svar med tall");
        int valg = grensesnitt.beOmKommando(giSpoersmaal, alternativer);

        // Salg av gjenstand - fra ryggsekk
        if (valg == 1) {
            // om sekken er tom
            if (this.ryggsekk.kiste.size() == 0) {
                System.out.println("Beklager, ingenting aa selge her");

                // om sekken ikke er tom
            } else {
                Gjenstand salgSkatt = ryggsekk.taUtGjenstand();

                // om kista er full
                if (!plassering.kiste.erPlass()) {
                    System.out.println("Ikke noe plass i kista, du beholder " + salgSkatt.navn);
                    this.ryggsekk.leggInnGjenstand(salgSkatt);

                    // om plass i kista
                } else {
                    int pris = plassering.kiste.selgGjenstand(salgSkatt);
                    // this.ryggsekk.ledige ++;
                    formue = formue + pris;
                    System.out.println("Du har gitt bort: " + salgSkatt.navn + " verdi: " + pris
                            + "\nDin formue er naa: " + formue);
                }
            }
        } else if (valg == 2) {
            System.out.println("Du valgte aa ikke selge noe");
        } else {
            System.out.println("Du oppga ugyldig svaralternativ - du mister trekket");
        }
        // ta inn gjenstand
        String taSpoersmaal = ("Vil du ta til deg en gjenstand? Svar med tall");
        valg = (grensesnitt.beOmKommando(taSpoersmaal, alternativer));

        if (valg == 1) {

            // om kista er tom
            if (!plassering.kiste.harGjenstand()) {
                System.out.println("Beklager, ingenting aa hente her");
                System.out.println("Gaa videre");
                this.plassering = plassering.gaaVidere();

            } else {
                Gjenstand skatt = plassering.kiste.taUtGjenstand();

                // om ledig plass i ryggsekk
                if (ryggsekk.erPlass()) {
                    ryggsekk.leggInnGjenstand(skatt);
                    System.out.println("Du la inn en " + skatt.navn + " med verdi " + skatt.verdi + " i sekken.");
                    this.plassering = plassering.gaaVidere();

                } else {
                    System.out
                            .println("Du har ikke plass i ryggsekken " + skatt.navn + " ble ikke lagt inn, gaa videre");
                    plassering.kiste.leggInnGjenstand(skatt);
                    this.plassering = plassering.gaaVidere();
                    grensesnitt.giStatus(
                            this.navn + " har " + this.formue + " i formue. \n du staar naa " + plassering.beskrivelse);
                }

            }
        } else if (valg == 2) {

            // ikke trekk valgt..
            this.plassering = plassering.gaaVidere();

        } else {
            System.out.println("Du oppga ugyldig svaralternativ - du mister trekket");
            this.plassering = plassering.gaaVidere();
        }

    }

}