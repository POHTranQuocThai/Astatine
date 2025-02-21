/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Tran Quoc Thai - CE181618
 */
public class User {

    private int userId;
    private String fullname;
    private String street;
    private String ward;
    private String district;
    private String city;
    private String country;
    private String password;
    private String email;
    private String phone;
    private boolean isAdmin;

    public User() {
    }

    public User(int userId, String password, String email) {
        this.userId = userId;
        this.password = password;
        this.email = email;
    }

    public User(int userId, String fullname,String email, String password,String phone,String street, String ward, String district, String city, String country, boolean isAdmin) {
        this.userId = userId;
        this.fullname = fullname != null ? fullname : "";  // Nếu fullname là null thì gán giá trị mặc định là chuỗi rỗng
        this.street = street != null ? street : "";          // Tương tự cho street
        this.ward = ward != null ? ward : "";
        this.district = district != null ? district : "";
        this.city = city != null ? city : "";
        this.country = country != null ? country : "";
        this.password = password != null ? password : "";
        this.email = email != null ? email : "";
        this.phone = phone != null ? phone : "";
        this.isAdmin = isAdmin;  // Không cần kiểm tra vì boolean có giá trị mặc định là false
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", fullname=" + fullname + ", street=" + street + ", ward=" + ward + ", district=" + district + ", city=" + city + ", country=" + country + ", password=" + password + ", email=" + email + ",  phone=" + phone + ", isAdmin=" + isAdmin + '}';
    }

}
