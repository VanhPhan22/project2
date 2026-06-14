package project2.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Jockey")
public class Jockey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "full_name", columnDefinition = "NVARCHAR(255)", nullable = false)
    private String fullName;

    @Column(name = "user_name", columnDefinition = "NVARCHAR(255)", nullable = false, unique = true)
    private String userName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "experience_years", nullable = false)
    private Integer experience_years;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    /*___________________________________________________________________________________________________________ */

    //MAPPING MỐI QUAN HỆ 1-N (Jockey - Notification)
    //1 Jockey --< có nhiều Notification - List<Notification>
    @OneToMany(mappedBy = "jockey", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> notificationList = new ArrayList<>();;

    public void addNotification(Notification o){
        notificationList.add(o);
        o.setJockey(this);
    }

    public void removeNotification(Notification o){
        notificationList.remove(o);
        o.setJockey(null);
    }

    /*___________________________________________________________________________________________________________ */

    //MAPPING MỐI QUAN HỆ 1-N (Jockey - RaceParticipation)
    //1 Jockey--< có nhiều RaceParticipation - List<RaceParticipation>
    @OneToMany(mappedBy = "jockey" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RaceParticipation> raceParticipationList = new ArrayList<>();

    public void addRaceParticipation(RaceParticipation o){
        raceParticipationList.add(o);
        o.setJockey(this);
    }

    public void removeRaceParticipation(RaceParticipation o){
        raceParticipationList.remove(o);
        o.setJockey(null);
    }

    /*___________________________________________________________________________________________________________ */

    public Jockey() {
    }

    public Jockey(String fullName, String userName, Integer age, Integer experience_years, String phone, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.age = age;
        this.experience_years = experience_years;
        this.phone = phone;
        this.password = password;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getExperience_years() {
        return experience_years;
    }

    public void setExperience_years(Integer experience_years) {
        this.experience_years = experience_years;
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

    @Override
    public String toString() {
        return "Jockey{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", experience_years=" + experience_years +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

