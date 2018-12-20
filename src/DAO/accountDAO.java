package DAO;
import Model.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class accountDAO {

	private String jdbcURL;
    private Connection jdbcConnection;
     
    public accountDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
            	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL);
           
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    
    public boolean insertACC(ACCOUNT acc) throws SQLException {
        String sql = "exec spr_ACCOUNT_ThemACCOUNT ?,?,?";
        connect();
      
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, acc.getTenDN());
        statement.setString(2, acc.getmK());
        statement.setString(3, acc.getQuyenHan());
         
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
     
//    public List<ACCOUNT> listAllaccount() throws SQLException {
//        List<LoaiSP> listLoaiSP = new ArrayList<>();
//         
//        String sql = "SELECT * FROM LOAISP";
//         
//        connect();
//         
//        Statement statement = jdbcConnection.createStatement();
//        ResultSet resultSet = statement.executeQuery(sql);
//        
//        while (resultSet.next()) {
//        	LoaiSP loaisp=new LoaiSP();
//        	loaisp.setMaLoai(resultSet.getString("MaLoai"));
//        	loaisp.setTenLoaiSP((resultSet.getString("TenLoaiSP")));
//
//            listLoaiSP.add(loaisp);
//        }
//         
//        resultSet.close();
//        statement.close();
//         
//        disconnect();
//         
//        return listLoaiSP;
//    }
    
     
    public boolean deleteACC(ACCOUNT acc) throws SQLException {
        String sql = "exec spro_ACCOUNT_XoaACCOUNT ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1,acc.getTenDN());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;     
    }
     
    public boolean updateACC(ACCOUNT acc) throws SQLException {
        String sql = "exec spro_ACCOUNT_SuaACCOUNT ?,?,?";
        
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, acc.getTenDN());
        statement.setString(2, acc.getmK());
        statement.setString(3, acc.getQuyenHan());
        
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;     
    }
     
    
}
