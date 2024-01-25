public class Order {
    private String orderName;
    private int orderQty;
    public Order(String orderName, int orderQty){
        this.orderName = orderName;
        this.orderQty = orderQty;
    }

    public String getOrderName(){
        return orderName;
    }
    public int getOrderQty() {
        return orderQty;
    }

}
