import java.util.ArrayList;

public class Nursery {
    private final String name;
    private double balance = 10000;
    private ArrayList<Tree> trees;
    private ArrayList<Order> orderTrees;
    private ArrayList<Transaction> transactions;
    public Nursery(String name){
        this.name = name;
        trees = new ArrayList<Tree>();
        orderTrees = new ArrayList<Order>();
        transactions = new ArrayList<Transaction>();
    }
    public String getName() {return name;}
    public double getBalance() {return balance;}
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void addTree(Tree treeType){
        trees.add(treeType);
    };
    public void addOrderTree(Order orderTreeType){ orderTrees.add(orderTreeType);};
    public void addTransaction(Transaction transactionType){ transactions.add(transactionType);};

    // Tree Section
    public ArrayList<Tree> getSingleTreeFor(String singleTree) {
        ArrayList<Tree> inbox = new ArrayList<Tree>();
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            if(tree.getName().equals(singleTree)){
                inbox.add(tree);
            }
        }
      return inbox;
    }
    public ArrayList<Tree> getAllStockFor(){
        ArrayList<Tree> allStocks = new ArrayList<Tree>();
        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            allStocks.add(tree);
        }
        return allStocks;
    }

    // Order Section
    public ArrayList<Order> getAllOrderTreesFor(){
        ArrayList<Order> allOrders = new ArrayList<Order>();
        for (int i = 0; i < orderTrees.size(); i++) {
            Order orderTree = orderTrees.get(i);
            allOrders.add(orderTree);
        }
        return allOrders;
    }
    public void removeAllOrderTreesFor(){
        for (int i = 0; i < orderTrees.size(); i++) {
            orderTrees.remove(i);
            i--;
        }
    }

    // Transaction Section
    public ArrayList<Transaction> getAllTransactionFor(){
        ArrayList<Transaction> allTrans = new ArrayList<Transaction>();
        for (int i = 0; i < transactions.size(); i++) {
            Transaction currentTrans = transactions.get(i);
            allTrans.add(currentTrans);
        }
        return allTrans;
    }
    public void removeTransDelayZero(){
        for (int i = 0; i < transactions.size(); i++) {
            Transaction currentTrans = transactions.get(i);
            if(currentTrans.getDelay() == 0){
                transactions.remove(i);
                i--;
            }
        }
    }

}
