package model;

public class Brand {
    private int brandId;
    private String brandName;
    private String country;

    // Constructor có tham số
    public Brand(int brandId, String brandName, String country) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.country = country;
    }

    // Constructor không tham số
    public Brand() {
    }

    // Getter cho brandId
    public int getBrandId() {
        return brandId;
    }

    // Setter cho brandId
    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    // Sửa lỗi tên getter cho brandName
    public String getBrandName() {
        return brandName;
    }

    // Sửa lỗi tên setter cho brandName
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    // Getter cho country
    public String getCountry() {
        return country;
    }

    // Setter cho country
    public void setCountry(String country) {
        this.country = country;
    }
}
