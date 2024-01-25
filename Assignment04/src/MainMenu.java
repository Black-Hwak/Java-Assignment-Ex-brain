import acm.program.ConsoleProgram;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.SplittableRandom;
import java.util.random.RandomGenerator;

public class MainMenu extends ConsoleProgram {
    private Nursery nursery;
    public static final String SELL_TREES = "S";
    public static final String BUY_TREES = "B";
    public static final String DISPLAY_ON_HAND = "D";
    public static final String TREE_DETAIL = "T";
    public static final String CAPITAL = "C";
    public static final String NEW_DAY = "N";
    public static final String EXIT = "E";
    public static final String ORDER_AGAIN = "A";
    public static final String ORDER_FINISH = "F";
    public static final String ORDER_CANCEL = "C";
    public static final String DETAIL_SYSTEM = "?";
    public static final String YES_BUY = "Yes";
    public static final String NO_BUY = "No";
    public static final String CREDIT_PAY = "Credit";
    public static final String CASH_PAY = "Cash";
    public String canChooseTrees = "You Can Choose One of the Above Trees: ";
    public String canBuyTrees = "Which Type Of Tree You Wanna Buy Or Buy A New Tree: ";
    public String choseSymbol;
    public boolean running;

    @Override
    public void run() {
        setFont("JetBrains Mono NL-16");

        nursery = new Nursery("FeedMe Plant Nursery");
        setupInitialState();
        processMainMenu();
    }

    public void setupInitialState(){
        Tree apple = new Tree("Apple", "Fall", 500, 1000, 10);
        Tree orange = new Tree("Orange", "Spring", 700, 1000, 30);
        Tree plum = new Tree("Plum", "Summer", 1000, 1500, 20);
        Tree apricot = new Tree("Apricot", "Spring", 1200, 1500, 15);

        nursery.addTree(apple);
        nursery.addTree(orange);
        nursery.addTree(plum);
        nursery.addTree(apricot);
    }

    public void processMainMenu(){
        println("***** Welcome to the Magical " + nursery.getName() + " Nursery *****");
        println("Explore the Enchanted World of Trees!");
        println("Enter `"+ DETAIL_SYSTEM +"` to get explanation about our planet");
        println("Enter `"+ EXIT +"` to exit our planet");

        String choiceSys = readLine("Enter Your Path: ");
        if(!choiceSys.equals(EXIT) && choiceSys.equals(DETAIL_SYSTEM)){
            println("Unveil the Secrets of our enchanting Nursery.");
            running = true;
            while(running){
               showAllChoices();
            }
        }else{
            exitSystem(choiceSys);
        }

    }

    public void showAllChoices(){
        println("Choose an option:");
        println("  - `" + SELL_TREES + "`: Sell");
        println("  - `" + BUY_TREES + "`: Buy");
        println("  - `" + DISPLAY_ON_HAND + "`: Display On Stock");
        println("  - `" + TREE_DETAIL + "`: Tree Details");
        println("  - `" + CAPITAL + "`: Total Capital");
        println("  - `" + NEW_DAY + "`: Add a New Day");
        println("  - `" + EXIT + "`: Exit");

        String choice = readLine("Enter your choice: ");
        if(choice.equals(SELL_TREES)){
            println();
            sellProcess(choice);
        }else if(choice.equals(BUY_TREES)){
            println();
            buyProcess(choice);
        }else if(choice.equals(DISPLAY_ON_HAND)){
            println();
            displayAllStocksProcess();
        }else if(choice.equals(TREE_DETAIL)){
            println();
            treeDetailProcess();
        }else if(choice.equals(CAPITAL)){
            println();
            showCapitalProcess();
        }else if(choice.equals(NEW_DAY)){
            println();
            newDayProcess();
        }else if(choice.equals(EXIT)){
            exitSystem(choice);
        } else{
            println();
            println("Please Make Sure You Entered Correctly");
            println();
        }
    }

    int stockAmount;
    double totalPrice =0;
    boolean isNotFound;
    String treeChoice;

    // For Symbol S
    public void sellProcess(String symbol){
        choseSymbol = symbol;
        showAllTreesName();
        chooseTreeFor(canChooseTrees);
    }

    // For Symbol B
    public void buyProcess(String symbol){
        choseSymbol = symbol;
        showAllTreesName();
        chooseTreeFor(canBuyTrees);
    }

    // For Both Buy And Sell
    public void chooseTreeFor(String canChoose){
            treeChoice = readLine(canChoose);
            ArrayList<Tree> treeDetailsCheck = nursery.getSingleTreeFor(treeChoice);
            isNotFound = treeDetailsCheck.isEmpty();
            if(choseSymbol.equals(SELL_TREES)){
                // If order tree has
                if(!isNotFound) {
                    chooseQtyForSell(treeDetailsCheck.get(0));
                }else{
                    println("Sorry! We Don't Have This Kind Of Tree : " + treeChoice + "\n");
                    chooseTreeFor(canChooseTrees);
                }
            }else{
                if(!isNotFound){
                    chooseQtyForBuy(treeDetailsCheck.get(0));
                }else{
                   addNewTreeProcess(treeChoice);
                }
            }
    }

    // For Buy Qty And Buy New Tree
    public void addNewTreeProcess(String aNewTree){
        String buyChoice = readLine("Are You Sure To Buy This Kind Of Tree: `" + aNewTree +"` ?\n" +
                "Enter " + YES_BUY + " Or " + NO_BUY + ": ");
        if(buyChoice.equals(YES_BUY)){
            println("WOW! A New Tree Has Arrived.");
            String season = readLine("Enter the Season of New Tree : ");
            double newBuyPrice = readDouble("Enter the Buy Price of New Tree : ");
            double newSellPrice = readDouble("Enter the Sell Price of New Tree : ");
            int newStock = readInt("Enter the Stock Quantity of New Tree : ");
            Tree newTree = new Tree(aNewTree,season,newBuyPrice,newSellPrice,newStock);
            nursery.addTree(newTree);
            totalPrice += newBuyPrice * newStock;
            Order orderNewTree = new Order(aNewTree, newStock);
            nursery.addOrderTree(orderNewTree);
            println("The New Tree has been successfully recorded.");
            threeOptionsProcess();
        }else{
            println("New Tree purchase for " + aNewTree + " has been canceled.");
            println();
            chooseTreeFor(canBuyTrees);
        }
    }
    public void chooseQtyForBuy(Tree singleTree) {
        int buyTreeQty = readInt("The Quantity You Wanna Buy: ");
        Order orderTree = new Order(singleTree.getName(), buyTreeQty);
        nursery.addOrderTree(orderTree);

        stockAmount = singleTree.getStock() + buyTreeQty;
        singleTree.setStock(stockAmount);

        totalPrice += singleTree.getBuyPrice() * buyTreeQty;
        threeOptionsProcess();
    }

    // For Sale Qty
    public void chooseQtyForSell(Tree singleTree) {
        int sellTreeQty = readInt("The Quantity You Want: ");
        stockAmount = singleTree.getStock();

        if (stockAmount >= sellTreeQty) {
            Order orderTree = new Order(singleTree.getName(), sellTreeQty);
            nursery.addOrderTree(orderTree);

            stockAmount -= sellTreeQty;
            singleTree.setStock(stockAmount);

            totalPrice += singleTree.getSellPrice() * sellTreeQty;
            threeOptionsProcess();
        } else {
            println("Not Enough Stock To Sell `"+ singleTree.getName() +"`. Please Choose Another One");
            println();
            sellProcess(choseSymbol);

        }
    }

    // Order Again, Cancel, Finish
    public void threeOptionsProcess(){
        println("Choose an Option:");
        println("  - Enter `" + ORDER_AGAIN + "` to Order Again.");
        println("  - Enter `" + ORDER_CANCEL + "` to Cancel All Orders.");
        println("  - Enter `" + ORDER_FINISH + "` to Finish Your Order.");

        String choice = readLine("Enter your choice: ");
        if(choice.equals(ORDER_CANCEL)){
            orderCancelProcess();
        }else if(choice.equals(ORDER_AGAIN)){
            if(choseSymbol.equals(SELL_TREES)){
                sellProcess(choseSymbol);
            }else{
                buyProcess(choseSymbol);
            }
        }else if(choice.equals(ORDER_FINISH)){
            orderFinishProcess();
            transactionProcess(choseSymbol);
        }else{
            println("__________Not Valid Input__________");
            threeOptionsProcess();
        }
    }
    public void orderCancelProcess(){
        totalPrice = 0;
        println("All Orders Have Been Cancelled");

        ArrayList<Order> allOrderTrees = nursery.getAllOrderTreesFor();
        ArrayList<Tree> allTrees = nursery.getAllStockFor();

        for (int j = 0; j < allOrderTrees.size(); j++) {
            for (int k = 0; k <allTrees.size() ; k++) {
                Order orderCancelTree = allOrderTrees.get(j);
                Tree gettingATree = allTrees.get(k);
                if (orderCancelTree.getOrderName().equals(gettingATree.getName())) {
                    stockAmount = gettingATree.getStock() + orderCancelTree.getOrderQty();
                    gettingATree.setStock(stockAmount);
                }
            }
        }
        nursery.removeAllOrderTreesFor();
    }
    public void orderFinishProcess(){
        println("Order Complete");
        println();
        println("**********Thank You For Ordering**********");
        displayAllOrdersProcess();
        println();
        println("Total Price Will Be: " + totalPrice);
        nursery.removeAllOrderTreesFor();
    }
    public void displayAllOrdersProcess(){
        println("Order Details");
        println("===============");
        println("Type Of Tree\tNumber Of Trees You Ordered");

        ArrayList<Order> allOrderTrees = nursery.getAllOrderTreesFor();
        for (int i = 0; i < allOrderTrees.size(); i++) {
            Order orderTree = allOrderTrees.get(i);
            println(orderTree.getOrderName() + "\t\t" + orderTree.getOrderQty());
        }
    }
    public void transactionProcess(String choseSymbol){
        println("╔══════════════════════════╗");
        println("║ Payment Methods");
        println("╟─────────────────────     ╢");
        println("║ Enter `" + CASH_PAY + "` - Cash Payment    ║");
        println("║ Enter `" + CREDIT_PAY + "` - Credit Card   ║");
        println("╚══════════════════════════╝");

        String transType = readLine("Enter Your Preferred Payment Method: ");
        println("You Have Chosen " + transType + ".");
        println("---------------Thank you for your selection!---------------");

        if(transType.equals(CASH_PAY)){
            if(choseSymbol.equals(BUY_TREES)){
                nursery.setBalance(nursery.getBalance() - totalPrice);
            }else{
                nursery.setBalance(nursery.getBalance() + totalPrice);
            }
        } else{ // whatever enter except Cash, assume all wrong input as credit payment
            if(choseSymbol.equals(BUY_TREES)){
                transType += "ForBuy";
                Transaction trans = new Transaction(transType,totalPrice);
                nursery.addTransaction(trans);
            }else{
                transType += "ForSell";
                Transaction trans = new Transaction(transType,totalPrice);
                nursery.addTransaction(trans);
            }
        }
        // When All Payments Have Done
        totalPrice = 0;
    }

    // For Symbol D
    public void displayAllStocksProcess(){
        print("Type Of Tree");
        print("\tNumber Of Trees On Hand       ");
        print("\tSelling Price                 \n");
        ArrayList<Tree> allTrees = nursery.getAllStockFor();
        for(int i = 0; i < allTrees.size(); i++) {
            Tree tree = allTrees.get(i);
            print(tree.getName());
            print("\t\t" + tree.getStock());
            print("\t\t\t\t" + tree.getSellPrice() + "\n");
        }
        println();
    }

    // For Symbol T
    public void treeDetailProcess(){
        showAllTreesName();
        String singleTree = readLine("Enter Single Tree Name: ");
        ArrayList<Tree> treeDetailCheck = nursery.getSingleTreeFor(singleTree);
        if(!treeDetailCheck.isEmpty()){
            displayTreeDetail(treeDetailCheck);
        }else{
            println("No such tree : " + singleTree + "\n");
            treeDetailProcess();
        }
    }
    public void displayTreeDetail(ArrayList<Tree> treeDetails){
        println("Tree Details:");
        println("==============================================");
        for (int i = 0; i < treeDetails.size(); i++) {
            Tree treeDetail = treeDetails.get(i);
            println("Tree Name: " + treeDetail.getName());
            println("Season: " + treeDetail.getSeason());
            println("Buying Price: " + treeDetail.getBuyPrice());
            println("Selling Price: " + treeDetail.getSellPrice());
            println("Available Stock: " + treeDetail.getStock());
            println("==============================================");
        }
    }
    public void showAllTreesName() {
        println("We Have the Following Trees:");

        ArrayList<Tree> allTrees = nursery.getAllStockFor();
        for (int i = 0; i < allTrees.size(); i++) {
            Tree tree = allTrees.get(i);
            print(tree.getName());
            if (i < allTrees.size() - 1) {
                print(", ");
            }
        }
        println();
        println();
    }


    // For Symbol C
    double moneyToGet, moneyToPay, totalCapital;
    public void showCapitalProcess(){
        println("\t\t\t**********Here is Your Total Capital**********");
        println();
        ArrayList<Transaction> allTrans = nursery.getAllTransactionFor();
        for (int i = 0; i < allTrans.size(); i++) {
            Transaction currentTrans = allTrans.get(i);
            if(currentTrans.getTransType().contains(BUY_TREES)){
                moneyToPay += currentTrans.getAmount();
            }else{
                moneyToGet += currentTrans.getAmount();
            }
        }
        totalCapital = nursery.getBalance() + moneyToGet - moneyToPay;
        println("\tTotal Balance(On Hand):    " + nursery.getBalance());
        println("\tTotal Credit To Get(Sell): " + moneyToGet);
        println("\tTotal Credit To Pay(Buy):  " + moneyToPay);
        println();
        println("\t\t\tAll Your Total Capital: " + totalCapital);
        println();
        // Preventing Against Multiple Entering C
        moneyToGet = 0;
        moneyToPay = 0;
    }

    // For Symbol N New day
    public void newDayProcess(){
        println("\t\t\t********************************************");
        println("\t\t\t******* A New Day Has Dawned ******");
        println("\t\t\t********************************************");
        println();

        ArrayList<Transaction> allTrans = nursery.getAllTransactionFor();
        for (int i = 0; i < allTrans.size(); i++) {
            Transaction currentTrans = allTrans.get(i);
            currentTrans.setDelay(currentTrans.getDelay() - 1);
            if(currentTrans.getDelay() == 0){
                if(currentTrans.getTransType().contains(BUY_TREES)){
                    nursery.setBalance(nursery.getBalance() - currentTrans.getAmount());
                }else{
                    nursery.setBalance(nursery.getBalance() + currentTrans.getAmount());
                }
                nursery.removeTransDelayZero();
            }
        }
    }

    // For Symbol E Exit
    public void exitSystem(String exit) {
        if (exit.equals(EXIT)) {
            println("Thank You for Visiting Us! Have A Great Day.");
            running = false;
        }else{
            println("Invalid Input. Returning to the Main Menu.");
            processMainMenu();
        }
    }
}
