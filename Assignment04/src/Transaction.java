public class Transaction {
    private String transType;
    private double amount;
    private int delay;
    public Transaction(String transType, double amount){
        this.transType = transType;
        this.amount= amount;
        this.delay = 4;
    }

    public String getTransType() { return transType; }
    public double getAmount() {
        return amount;
    }
    public int getDelay() { return delay; }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
