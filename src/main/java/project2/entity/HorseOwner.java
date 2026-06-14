package project2.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HorseOwner")
public class HorseOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "full_name", columnDefinition = "NVARCHAR(255)", nullable = false)
    private String fullName;

    @Column(name = "user_name", columnDefinition = "NVARCHAR(255)", nullable = false, unique = true)
    private String userName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    /*___________________________________________________________________________________________________________ */

    //MAPPING MỐI QUAN HỆ 1-N (HorseOwner - Notification)
    //1 HorseOwner --< có nhiều Notification - List<Notification>
    @OneToMany(mappedBy = "horseOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> notificationList = new ArrayList<>();;

    public void addNotification(Notification o){
        notificationList.add(o);
        o.setHorseOwner(this);
    }

    public void removeNotification(Notification o){
        notificationList.remove(o);
        o.setHorseOwner(null);
    }

    /*___________________________________________________________________________________________________________ */

    //MAPPING MỐI QUAN HỆ 1-N (HorseOwner - Horse)
    //1 HorseOwner --< có nhiều Horse - List<Horse>
    @OneToMany(mappedBy = "horseOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Horse> horseList = new ArrayList<>();

    public void addHorse(Horse o){
        horseList.add(o);
        o.setHorseOwner(this);
    }

    public void removeHorse(Horse o){
        horseList.remove(o);
        o.setHorseOwner(null);
    }

    /*___________________________________________________________________________________________________________ */

    public HorseOwner() {
    }

    public HorseOwner(String fullName, String userName, String phone, String password, String email) {
        this.fullName = fullName;
        this.userName = userName;
        this.phone = phone;
        this.password = password;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    @Override
    public String toString() {
        return "HorseOwner{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

