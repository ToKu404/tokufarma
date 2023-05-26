package tokufarma.Scenes;

public class ObatModel {
    private String name;
    private String ecpireDate;
    private int stock;

    public ObatModel(String name, String ecpireDate, int stock) {
        this.name = name;
        this.ecpireDate = ecpireDate;
        this.stock = stock;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEcpireDate() {
        return ecpireDate;
    }
    public void setEcpireDate(String ecpireDate) {
        this.ecpireDate = ecpireDate;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
