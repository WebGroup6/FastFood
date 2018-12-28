package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.GioHang;

public class GioHangDAO {

	public static ArrayList<GioHang> dssanpham = new ArrayList<>();
	public static ArrayList<GioHang> giohang = new ArrayList<>();

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public GioHangDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;

	}

	public GioHangDAO() {
		dssanpham.removeAll(dssanpham);

		String sql = "SELECT * FROM SANPHAM";
		try {
			connect();

			Statement statement = jdbcConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				GioHang product = new GioHang();

				product.setMaSP(resultSet.getString("MaSP"));
				product.setTenSP(resultSet.getString("TenSP"));
				product.setGia(resultSet.getInt("GiaBan"));
				product.setSoLuong(1);

				dssanpham.add(product);
			}

		} catch (SQLException e) {
		}

	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public boolean ThemGioHang(String maSP) {
		for (int i = 0; i < dssanpham.size(); i++) {
			if (dssanpham.get(i).getMaSP().equals(maSP)) {
				giohang.add(dssanpham.get(i));
				return true;
			}
		}

		return false;
	}

	public boolean XoaGioHang(String maSP) {
		for (int i = 0; i < giohang.size(); i++) {
			if (giohang.get(i).getMaSP().equals(maSP)) {
				giohang.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean kiemTraSanPhamCoTrongGioHangChua(String maSP) {
		for (int i = 0; i < giohang.size(); i++) {
			if (giohang.get(i).getMaSP() == maSP) {
				return true;
			}
		}
		return false;
	}

	public List<GioHang> listGioHang() throws SQLException {
		List<GioHang> listSP = new ArrayList<>();

		String sql = "SELECT * FROM SANPHAM";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			GioHang SP = new GioHang();
			SP.setMaSP(resultSet.getString("MaSP"));
			SP.setTenSP(resultSet.getString("TenSP"));
			dssanpham.add(SP);
		}

		resultSet.close();
		statement.close();

		return listSP;
	}

	public static ArrayList<GioHang> getDssanpham() {
		return dssanpham;
	}

	public static void setDssanpham(ArrayList<GioHang> dssanpham) {
		GioHangDAO.dssanpham = dssanpham;
	}

	public static ArrayList<GioHang> getGiohang() {
		return giohang;
	}

	public static void setGiohang(ArrayList<GioHang> giohang) {
		GioHangDAO.giohang = giohang;
	}

}
