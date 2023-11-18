package entity;
import java.time.LocalDate;
import java.util.Date;

public class NhanVien {

    private String maNV;
    private String hoTen;
    private LocalDate ngaySinh;
    private boolean gioiTinh;
    private String diaChi;
    private PhongBan maPB; 
    private String chucVu;
    private String matKhau;

    // Constructor không tham số
    public NhanVien() {
    	
    }

    public NhanVien(String maNV) {
    	this.maNV = maNV;
    }
    
    public NhanVien(String maNV, String hoTen) {
    	this.maNV = maNV;
    	this.hoTen = hoTen;
    }
    
    // Constructor với đầy đủ tham số
    public NhanVien(String maNV, String hoTen, LocalDate ngaySinh, boolean gioiTinh,
                    String diaChi, PhongBan phongBan, String chucVu, String matKhau) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.maPB = phongBan;
        this.chucVu = chucVu;
        this.matKhau = matKhau;
    }

    // Getter và Setter cho các thuộc tính
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public PhongBan getMaPB() {
        return maPB;
    }

    public void setMaPB(PhongBan maPB) {
        this.maPB = maPB;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh
				+ ", diaChi=" + diaChi + ", maPB=" + maPB + ", chucVu=" + chucVu + ", matKhau=" + matKhau + "]";
	}
    
    
}
