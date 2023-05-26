package tokufarma.Scenes;

public class ObatModel {
    private String nama;
    private String expiredDate;
    private int stock;

    public ObatModel(String nama, String expiredDate, int stock) {
        this.nama = nama;
        this.expiredDate = expiredDate;
        this.stock = stock;
    }
    
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
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
