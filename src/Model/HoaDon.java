package Model;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HoaDon {
	private String maHD;
	private String maKH;
	private String maNV;
	private int tongTien;
	private Date ngayLap;
	private int trangThai;
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public int getTongTien() {
		return tongTien;
	}
	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
	public Date getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	public HoaDon() {
		super();
	}
	public HoaDon(String maHD, String maKH, String maNV, int tongTien, Date ngayLap, int trangThai) {
		super();
		this.maHD = maHD;
		this.maKH = maKH;
		this.maNV = maNV;
		this.tongTien = tongTien;
		this.ngayLap = ngayLap;
		this.trangThai = trangThai;
	}
	public HoaDon(String maHD) {
		super();
		this.maHD = maHD;
	}
	
	
}

