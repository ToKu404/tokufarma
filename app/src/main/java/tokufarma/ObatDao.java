private void setupTable() {
    try {
        DatabaseMetaData meta = conn.getMetaData();
        ResultSet rs = meta.getTables(null, null, "obats", null);
        if (!rs.next()) {
            stmt = conn.createStatement();
            String sql = "CREATE TABLE obats " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " name TEXT NOT NULL, " +
                    " expiredData TEXT NOT NULL, " +
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