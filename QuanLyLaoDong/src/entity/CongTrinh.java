package entity;

import java.time.LocalDate;

public class CongTrinh {
	private String maCT;
	private String tenCT;
	private LocalDate ngayCP;
	private LocalDate ngayKC;
	private LocalDate ngayHTDK;
	private String diaDiem;

	@Override
	public String toString() {
		return "CongTrinh [maCT=" + maCT + ", tenCT=" + tenCT + ", ngayCP=" + ngayCP + ", ngayKC=" + ngayKC
				+ ", ngayHTDK=" + ngayHTDK + ", diaDiem=" + diaDiem + "]";
	}

	public String getMaCT() {
		return maCT;
	}

	public void setMaCT(String maCT) {
		this.maCT = maCT;
	}

	public String getTenCT() {
		return tenCT;
	}

	public void setTenCT(String tenCT) {
		this.tenCT = tenCT;
	}

	public LocalDate getNgayCP() {
		return ngayCP;
	}

	public void setNgayCP(LocalDate ngayCP) {
		this.ngayCP = ngayCP;
	}

	public LocalDate getNgayKC() {
		return ngayKC;
	}

	public void setNgayKC(LocalDate ngayKC) {
		this.ngayKC = ngayKC;
	}

	public LocalDate getNgayHTDK() {
		return ngayHTDK;
	}

	public void setNgayHTDK(LocalDate ngayHTDK) {
		this.ngayHTDK = ngayHTDK;
	}

	public String getDiaDiem() {
		return diaDiem;
	}

	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}

	public CongTrinh(String maCT, String tenCT, LocalDate ngayCP, LocalDate ngayKC, LocalDate ngayHTDK,
			String diaDiem) {
		super();
		this.maCT = maCT;
		this.tenCT = tenCT;
		this.ngayCP = ngayCP;
		this.ngayKC = ngayKC;
		this.ngayHTDK = ngayHTDK;
		this.diaDiem = diaDiem;
	}

	public CongTrinh() {

	}

	public CongTrinh(String maCT) {
		this.maCT = maCT;
	}

	public CongTrinh(String maCT, String tenCT) {
		this.maCT = maCT;
		this.tenCT = tenCT;
	}
}
