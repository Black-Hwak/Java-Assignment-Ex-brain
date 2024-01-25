import acm.program.ConsoleProgram;

public class DiamondShape extends ConsoleProgram {
    public void run(){
        println("Diamond Shape");
        println();

        int diaWidth = readInt("Enter width of diamond: ");

        //Upper Shape
        int space = -1;
        for(int i=0; i< diaWidth/2+1; i++) {
            int star = diaWidth/2+1-i;
            for(int j=0; j<star; j++) {
                print("*");
            }
            if(space < 0){
                star = diaWidth/2;
            }
            for(int k=0; k<space; k++) {
                print(" ");
            }
            for(int j=0; j<star; j++) {
                print("*");
            }
            space+=2;
            println();
        }

        //Below Shape
        space-=4;
        for(int i=diaWidth-1; i>diaWidth/2; i--)
        {
            int star = diaWidth+1;
            for(int j=i; j<star; j++)
            {
                print("*");
            }
            if(space<0){
                star = diaWidth--;
            }
            for(int k=0; k<space; k++)
            {
                print(" ");
            }
            for(int j=i; j<star; j++)
            {
                print("*");
            }
            space-=2;
            println();
        }
    }
}
