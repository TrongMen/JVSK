package entity;

public class CongViec {
	private int maCV;
	private CongTrinh ct;
	private NhanVien nv;
	private int ngayCong;
	public int getMaCV() {
		return maCV;
	}
	public void setMaCV(int maCV) {
		this.maCV = maCV;
	}
	public CongTrinh getCt() {
		return ct;
	}
	public void setCt(CongTrinh ct) {
		this.ct = ct;
	}
	public NhanVien getNv() {
		return nv;
	}
	public void setNv(NhanVien nv) {
		this.nv = nv;
	}
	
	
	public int getNgayCong() {
		return ngayCong;
	}
	public void setNgayCong(int ngayCong) {
		this.ngayCong = ngayCong;
	}
	
	@Override
	public String toString() {
		return "CongViec [maCV=" + maCV + ", ct=" + ct + ", nv=" + nv + ", ngayCong=" + ngayCong + "]";
	}
	public CongViec(int maCV, CongTrinh ct, NhanVien nv, int ngayCong) {
		super();
		this.maCV = maCV;
		this.ct = ct;
		this.nv = nv;
		this.ngayCong = ngayCong;
	}
	public CongViec() {
	}
	
	
}
