public package tokufarma.Scenes;

public class ObatModel {
    private String name;
    private String expiredDateString;
    private int stock;

    public ObatModel(String name, String expiredDateString, int stock) {
        this.name = name;
        this.expiredDateString = expiredDateString;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiredDateString() {
        return expiredDateString;
    }

    public void setExpiredDateString(String expiredDateString) {
        this.expiredDateString = expiredDateString;
    }

    public int getStock() {
        return stock;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }

} {
    
}
