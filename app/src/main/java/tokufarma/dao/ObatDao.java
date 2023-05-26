package tokufarma.dao;

import tokufarma.Models.ObatModel;

public class ObatDao {
    private Connection conn;
    private Statement stmt;
    public ObatDaoDao() {
        conn = DatabaseConfig.getConnection();
        setupTable();
    }
    private void setupTable() {
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "obats", null);
            if (!rs.next()) {
                stmt = conn.createStatement();
                String sql = "CREATE TABLE students " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " nsme TEXT NOT NULL UNIQUE," +
                        " expiredDate TEXT NOT NULL, " +
                        " stock TEXT NOT NULL)";
                stmt.executeUpdate(sql);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Student> getAll() {
        try {
            List<Student> listObat = new ArrayList<>();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM obats");
            while (rs.next()) {
                String name = rs.getString("name");
                String expiredDate = rs.getString("expiredDate");
                int stock = rs.getint("stock");
                listStudent.add(new Student(name, nim, nickname));
            }
            return listStudent;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
    public void syncData(List<ObatModel> listObat) {
        try {
            stmt.executeUpdate("DELETE from obats");
            stmt = conn.createStatement();
            for (ObatModel obat : listObat) {
                String sql = String.format("""
INSERT INTO obats(name, expiredDate, stock)
VALUES('%s', '%s', '%s');
""",
                        obat.getName(),
                        obat.getExpiredDate(),
                        obat.getStock());
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
