package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.KhachHang;
import Model.NhanVien;

public class KhachHangDAO {

	private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public KhachHangDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
            	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
           
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    
     
    public List<KhachHang> listAllKH() throws SQLException {
        List<KhachHang> listKH = new ArrayList<>();
         
        String sql = "SELECT * FROM KHACHHANG";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            String maKH = resultSet.getString("maKH");
            String tenDN=resultSet.getString("TenDN");
            String hoTen = resultSet.getString("HoTen");
            
            String diaChi=resultSet.getString("DiaChi");
            String email=resultSet.getString("Email");
            String sdt=resultSet.getString("SDT");
            int tichLuy=resultSet.getInt("TichLuy");
            KhachHang kh = new KhachHang(maKH,tenDN,hoTen,diaChi,email,sdt,tichLuy);
            listKH.add(kh);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listKH;
    }
    
    
    public boolean insertKH(KhachHang kh) throws SQLException {
        String sql = "exec spro_KhachHang_ThemKH ?,?,?,?,?,?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, kh.getMaKH());
        statement.setString(2, kh.getHoTen());
        statement.setString(3, kh.getDiaChi());
        statement.setString(4, kh.getEmail());
        statement.setString(5, kh.getSdt());
        statement.setString(6, kh.getTenDN());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
    public boolean deleteKH(KhachHang kh) throws SQLException {
        String sql = "exec spro_KhachHang_XoaKhachHang ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1,kh.getMaKH());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateKH(KhachHang kh) throws SQLException {
        String sql = "UPDATE KHACHHANG SET  TenDN= ?,HoTen = ?, DiaChi = ?, Email=?, SDT=?,TichLuy = ?";
        sql += " WHERE MaKH = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        
        statement.setString(1,kh.getMaKH() );
        statement.setString(2, kh.getTenDN());
        statement.setString(3, kh.getHoTen());
        statement.setString(4, kh.getDiaChi());
        statement.setString(5, kh.getEmail());
        statement.setString(6, kh.getSdt());
        statement.setInt(7, kh.getTichLuy());
        
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    public KhachHang getKhachHang(String MaKH) throws SQLException {
    	KhachHang kh = null;
        String sql = "SELECT * FROM KHACHHANG WHERE MaKH = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, MaKH);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	 
             String TenDN=resultSet.getString("TenDN");
             String HoTen = resultSet.getString("HoTen");
             
             String DiaChi=resultSet.getString("DiaChi");
             String Email=resultSet.getString("Email");
             String SDT=resultSet.getString("SDT");
             int TichLuy=resultSet.getInt("TichLuy");
            
            kh = new KhachHang(MaKH,TenDN,HoTen,DiaChi,Email,SDT,TichLuy);
        }
         
        resultSet.close();
        statement.close();
         
        return kh;
    }
}
