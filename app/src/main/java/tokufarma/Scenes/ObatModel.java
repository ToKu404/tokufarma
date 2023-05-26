package tokufarma.Scenes;

public class ObatModel {
    private String namaObat;
    private String expiredDate;
    private int stock;

    public ObatModel(String namaObat, String expiredDate, int stock) {
        this.namaObat = namaObat;
        this.expiredDate = expiredDate;
        this.stock = stock;
    }
    public String getNamaObat() {
        return namaObat;
    }
    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
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
