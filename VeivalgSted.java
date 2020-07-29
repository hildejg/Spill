public class VeivalgSted extends Sted{

    public VeivalgSted(String beskrivelse) {
        super(beskrivelse);
    }
    
    public Sted gaaVidere(int valg) {
        return this.utganger.get(valg);
    }
       
}