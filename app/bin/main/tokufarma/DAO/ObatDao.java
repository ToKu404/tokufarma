package tokufarma.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tokufarma.models.ObatModel;
import tokufarma.utils.DatabaseConfig;

public class ObatDao {
    private Connection conn;
    private Statement stmt;

    public ObatDao() {
        conn = DatabaseConfig.getConnection();
        setupTable();
    }

    private void setupTable() {
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "obats", null);
            if (!rs.next()) {
                stmt = conn.createStatement();
                String sql = "CREATE TABLE obats " +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " name TEXT NOT NULL, " +
                        " expiredDate TEXT NOT NULL, " +
                        " stock INTEGER NOT NULL)";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ObatModel> getAll() throws SQLException {
        try {
            List<ObatModel> listObat = new ArrayList<>();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM obats");
            while (rs.next()) {
                String name = rs.getString("name");
                String expireDate = rs.getString("expiredDate");
                int stock = rs.getInt("stock");
                listObat.add(new ObatModel(name, expireDate, stock));
            }
            return listObat;
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
                        VALUES('%s', '%s', '%d');
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