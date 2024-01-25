import acm.program.ConsoleProgram;

public class MilesPerGallon extends ConsoleProgram {
    public void run(){
        println("Miles Per Gallon");
        println();

        final int SENTINEL = -1;
        while(true){
            int startMile = readInt("Initial Miles: ");

            if(startMile == SENTINEL){
                println("Bye");
                break;
            }

            int endMile = readInt("Final Miles: ");
            int gallon = readInt("Gallons: ");
            double milesPerGallon = (double) (endMile - startMile) /gallon;
            println("Miles Per Gallon: " + milesPerGallon + "\n");
        }
    }
}
