public class Tree {
    private String name;
    private String season;
    private double buyPrice;
    private double sellPrice;
    private int stock;

    public String getName() {
        return name;
    }
    public String getSeason() {
        return season;
    }
    public double getBuyPrice() { return buyPrice;}
    public double getSellPrice() {
        return sellPrice;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stockAmount){
        this.stock = stockAmount;
    }
    public Tree(String name, String season, double buyPrice, double sellPrice,int stock){
        this.name = name;
        this.season = season;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.stock = stock;
    }


}
