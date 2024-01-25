import acm.program.ConsoleProgram;

public class PossibilitiesOfDice extends ConsoleProgram {
    public void run(){
        println("Possibilities Of Two(6-sided) Dice");
        println();

        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                print("("+ i + ", " + j +")\t");
            }
            println();
        }
    }
}

