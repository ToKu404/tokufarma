package tokufarma.Models;

public class Obat {
    private String name;
    private String expiredDate;
    private int stock;

    public Obat(String name, String expiredDate, int stock) {
        this.name = name;
        this.expiredDate = expiredDate;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    
}
