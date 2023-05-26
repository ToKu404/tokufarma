package tokufarma.Scenes;

public class Obat {
    private String nama;
    private String expiredDate;
    private int stok;

    
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

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public Obat(String nama, String expiredDate, int stok) {
        this.nama = nama;
        this.expiredDate = expiredDate;
        this.stok = stok;
    }
    
}
