import acm.program.ConsoleProgram;

public class SmallestAndLargest extends ConsoleProgram {
    public void run(){
        println("Smallest And Largest");
        println();

        final int SENTINEL = 0;
        int num, count = -1, smallestNum = 0, largestNum = 0;
        while(true){
            num = readInt("? ");
            count++;
            if(num == SENTINEL){
                if(count == SENTINEL){
                    println("No Largest or Smallest to be chosen since you have entered no values");
                }
                break;
            }
            if(count == 0){
                largestNum = num;
                smallestNum = num;
            }else{
                if(num > largestNum){
                    largestNum = num;
                }
                if(num < smallestNum){
                    smallestNum = num;
                }
            }

        }

        if(count!=0){
            println("Smallest: " + smallestNum);
            println("Largest: " + largestNum);
        }
    }
}
