import java.util.Random;

public class Robot implements Brukergrensesnitt {

    public Robot() {

    }

    public void giStatus(String status) {
        System.out.println(status);
    }

    // Ber om random tall 0-1, endrer til 1 eller 2 for lettleslighet for
    // terminalbruker
    public int beOmKommando(String spoersmaal, String[] alternativer) {
        Random random = new Random();
        int robotValg = random.nextInt(alternativer.length - 1);
        robotValg += 1;
        return robotValg;
    }
}