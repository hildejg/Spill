import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class VeivalgTerreng extends Terreng {

    public VeivalgTerreng() throws FileNotFoundException {
        super();
    }

    @Override
    protected void leggInnSted() throws FileNotFoundException {
        Scanner lesSteder = new Scanner(new File("steder.txt"));
        while (lesSteder.hasNextLine()) {
            String beskrivelse = lesSteder.nextLine();
            VeivalgSted sted = new VeivalgSted(beskrivelse); // lager nytt sted med beskrivelsen
            steder.leggTil(sted);
        }
        for (int x = 0; x < steder.stoerrelse(); x++) {
            Sted naaverende = steder.hent(x);
            if(x==steder.stoerrelse-1){//hvis siste sted
                naaverende.utganger.add(steder.hent(0));
            }else{
                naaverende.utganger.add(steder.hent(x + 1));
            }
            //legger inn to nye tilfeldige naboer
            for(int y = 0; y<2; y++){
                int utgangIndex = tilfeldigInteger(steder.stoerrelse(), x);
                naaverende.utganger.add(steder.hent(utgangIndex));
            }
        }
    }

    private int tilfeldigInteger(int max, int currentIndex) {
        boolean gjenta = true;
        int randomInt = 0;
        while (gjenta){
            randomInt = new Random().nextInt(max);
            if (randomInt != currentIndex){
                gjenta = false;
            }
        }
        return randomInt;
    }
}