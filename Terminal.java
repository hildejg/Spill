import java.util.Scanner;

public class Terminal implements Brukergrensesnitt {
    Scanner brukerinput;
    int svar;

    // konstruktor
    public Terminal(Scanner brukerinput) {
        this.brukerinput = brukerinput;
    }

    // printer det som sendes inn
    public void giStatus(String status) {
        System.out.println(status);
    }

    public int beOmKommando(String spoersmaal, String[] alternativer) {
        // Printer spoersmaal med alternativ
        System.out.println(spoersmaal);

        for (int x = 0; x <= alternativer.length - 1; x++) {
            System.out.println(alternativer[x]);
        }
        // registrerer svar
        svar = brukerinput.nextInt();
        return svar;
    }
}
