public class ObatDao {
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
}
