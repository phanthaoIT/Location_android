package Models;

/**
 * Created by Thanh on 4/30/2018.
 */

public class DiaDiemUaThich {
    private double latitude;
    private double longitude;
    private int image; //Thay đổi image từ url sau
    private int imgLoaiDiaDiem; //Cafe / nhà hàng
    private String TenCuaHang;
    private String ViTri;

    public DiaDiemUaThich(double latitude, double longitude, int image, int imgLoaiDiaDiem, String tenCuaHang, String viTri) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
        this.imgLoaiDiaDiem = imgLoaiDiaDiem;
        TenCuaHang = tenCuaHang;
        ViTri = viTri;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTenCuaHang() {
        return TenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        TenCuaHang = tenCuaHang;
    }

    public String getViTri() {
        return ViTri;
    }

    public void setViTri(String viTri) {
        ViTri = viTri;
    }

    public int getImage() {

        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImgLoaiDiaDiem() {
        return imgLoaiDiaDiem;
    }

    public void setImgLoaiDiaDiem(int imgLoaiDiaDiem) {
        this.imgLoaiDiaDiem = imgLoaiDiaDiem;
    }
}
