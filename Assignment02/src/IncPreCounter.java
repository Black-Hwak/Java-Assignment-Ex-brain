import acm.program.ConsoleProgram;

public class IncPreCounter extends ConsoleProgram {
   public void run(){
       IncPreCounter c1 = new IncPreCounter(100);
       c1.incBy(10);
       c1.getVal();
       c1.getPreVal();
   }
    private int val;
    private int preVal;
    public IncPreCounter(int val){
        this.val = val;
    }

    public void incBy(int inc){
        this.preVal = this.val;
        this.val += inc;
    }

    public int getVal(){
        return this.val;
    }

    public int getPreVal(){
        return this.preVal;
    }
}
