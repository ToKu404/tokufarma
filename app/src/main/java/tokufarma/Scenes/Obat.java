package tokufarma.Scenes;

public class Obat {
    private String name;
    private String expiredDate;
    private int stok;

    public Obat(String name, String expiredDate, int stok) {
        this.name = name;
        this.expiredDate = expiredDate;
        this.stok = stok;
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
    public int getStok() {
        return stok;
    }
    public void setStok(int stok) {
        this.stok = stok;
    }
}
