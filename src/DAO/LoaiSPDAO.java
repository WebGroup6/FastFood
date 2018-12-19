package DAO;

import Model.LoaiSP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoaiSPDAO {

	private String jdbcURL;
	private Connection jdbcConnection;

	public LoaiSPDAO(String jdbcURL) {
		this.jdbcURL = jdbcURL;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL);

		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public boolean insertLoaiSP(LoaiSP loaisp) throws SQLException {
		String sql = "INSERT INTO loaisp (MaLoai,TenLoaiSP) VALUES (?, ?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, loaisp.getMaLoai());
		statement.setString(2, loaisp.getTenLoaiSP());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<LoaiSP> listAllLoaiSP() throws SQLException {
		List<LoaiSP> listLoaiSP = new ArrayList<>();

		String sql = "SELECT * FROM LOAISP";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String maLoai = resultSet.getString("MaLoai");
			String tenLoaiSP = resultSet.getString("TenLoaiSP");

			LoaiSP loaisp = new LoaiSP(maLoai, tenLoaiSP);
			listLoaiSP.add(loaisp);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listLoaiSP;
	}

	public boolean deleteLoaiSP(LoaiSP loaisp) throws SQLException {
		String sql = "DELETE FROM loaisp where MaLoai = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, loaisp.getMaLoai());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean updateLoaiSP(LoaiSP loaisp) throws SQLException {
		String sql = "UPDATE loaisp SET TenLoaiSP = ?";
		sql += " WHERE MaLoai = ?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, loaisp.getMaLoai());
		statement.setString(2, loaisp.getTenLoaiSP());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public LoaiSP getLoaiSP(String MaLoai) throws SQLException {
		LoaiSP loaisp = null;
		String sql = "SELECT * FROM loaisp WHERE MaLoai = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, MaLoai);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String TenLoaiSP = resultSet.getString("TenLoaiSP");

			loaisp = new LoaiSP(MaLoai, TenLoaiSP);
		}

		resultSet.close();
		statement.close();

		return loaisp;
	}
}
