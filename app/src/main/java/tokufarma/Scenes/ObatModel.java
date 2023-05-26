package tokufarma.Scenes;

public class ObatModel {
    private String name;
    private String expireDate;
    private int stock;
    public ObatModel(String name, String expireDate, int stock) {
        this.name = name;
        this.expireDate = expireDate;
        this.stock = stock;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}