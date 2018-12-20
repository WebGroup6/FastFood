package DAO;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.HoaDon;

public class HoaDonDAO {

	private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public HoaDonDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
    
    //load danh sách hóa đơn
    public List<HoaDon> listAllHD() throws SQLException {
        List<HoaDon> listHD = new ArrayList<>();
         
        String sql = "SELECT * FROM HOADON";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            String maHD = resultSet.getString("maHD");
            String maKH = resultSet.getString("maKH");
            String maNV=resultSet.getString("maNV");
           
            
            int tongTien=resultSet.getInt("tongTien");
            Date ngayLap=resultSet.getDate("ngaylap");
            int trangThai=resultSet.getInt("trangthai");
           
            HoaDon hd = new HoaDon(maHD,maKH,maNV,tongTien,ngayLap,trangThai);
            listHD.add(hd);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listHD;
    }
    //thêm hóa đơn
    public boolean insertHD(HoaDon hd) throws SQLException {
        String sql = "INSERT INTO HOADON (MaHD,MaKH,MaNV,TongTien,NgayLap,TrangThai) VALUES (?, ?,?,?,?,?)";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, hd.getMaHD());
        statement.setString(2, hd.getMaKH());
        statement.setString(3, hd.getMaNV());
        statement.setInt(4, hd.getTongTien());
        statement.setDate(5,  (java.sql.Date) hd.getNgayLap());
        statement.setInt(6, hd.getTrangThai());
        
        
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    //Sửa hóa đơn
    public boolean updateHD(HoaDon hd) throws SQLException {
        String sql = "UPDATE HOADON SET  TongTien= ?,NgayLap = ?, TrangThai = ?";
        sql += " WHERE MaHD = ?";
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        
        statement.setString(1, hd.getMaHD());
        statement.setString(2, hd.getMaKH());
        statement.setString(3, hd.getMaNV());
        statement.setInt(4, hd.getTongTien());
        statement.setDate(5, (java.sql.Date) hd.getNgayLap());
        statement.setInt(6, hd.getTrangThai());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
    //Xóa hóa đơn
    public boolean deleteHD(HoaDon hd) throws SQLException {
        String sql = "DELETE FROM HOADON where MaHD = ?";
        
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1,hd.getMaHD());
       /* statement.setString(2, hd.getMaKH());
        statement.setString(3, hd.getMaNV());*/
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    
     
    public HoaDon getHoaDon(String MaHD) throws SQLException {
    	HoaDon hd = null;
        String sql = "SELECT * FROM HOADON WHERE MAHD = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, MaHD);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
        	 
             String MaKH=resultSet.getString("MaKH");
             String MaNV = resultSet.getString("MaNV");
             
             int TongTien=resultSet.getInt("TongTien");
             Date NgayLap=resultSet.getDate("NgayLap");
             int TrangThai=resultSet.getInt("TrangThai");
             
            hd = new HoaDon(MaHD,MaKH,MaNV,TongTien,NgayLap,TrangThai);
        }
         
        resultSet.close();
        statement.close();
         
        return hd;
    }
}
