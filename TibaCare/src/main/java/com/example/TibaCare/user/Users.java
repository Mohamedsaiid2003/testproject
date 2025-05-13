package com.example.TibaCare.user;
import com.example.TibaCare.enums.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;
import static jakarta.persistence.GenerationType.SEQUENCE;
@Entity(name = "usres")
@Table(
        name ="users",
        uniqueConstraints = {
                @UniqueConstraint(name = "users_email_unique",columnNames = "email"),
                @UniqueConstraint(name = "users_national_identity_card_unique",columnNames = "national_identity_card")

        }
)
public class Users  implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "users_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private int id;
    @Column(
            name = "firstname",
            nullable = false,
            columnDefinition ="TEXT"
    )
    private String firstname;
    @Column(
            name = "lastname",
            nullable = false,
            columnDefinition ="TEXT"
    )
    private String lastname;
    @Column(
            name = "email",
            nullable = false,
            columnDefinition ="TEXT",
            unique = true
    )
    private String email;
    @Column(
            name = "mobilnumber",
            nullable = false,
            columnDefinition ="TEXT"
    )
    private String mobilnumber;
    @Column(
            name = "adress",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String adress;
    @Column(
            name = "national_identity_card",
            nullable = false,
            columnDefinition ="TEXT",
            unique = true
    )
    private String national_identity_card;
    @Column(
            name = "gender",
            nullable = false,
            columnDefinition ="TEXT"
    )
    private String gender;
    @Column(
            name = "date_of_birth",
            nullable = false
    )
    private LocalDate date_of_birth;
    @Column(
            name = "age",
            nullable = false
    )
    @Transient
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;
    public Users() {
    }
    public Users(int id, String firstname, String lastname,
                 String email, String mobilnumber,
                 String adress, String national_identity_card,
                 String gender, LocalDate date_of_birth,Role role, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobilnumber = mobilnumber;
        this.adress = adress;
        this.national_identity_card = national_identity_card;
        this.gender = gender;
        this.role = role;
        this.date_of_birth = date_of_birth;
        this.password = password;
    }
    public Users(String firstname, String lastname,
                 String email, String mobilnumber,
                 String adress, String national_identity_card,
                 String gender, LocalDate date_of_birth,Role role, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobilnumber = mobilnumber;
        this.adress = adress;
        this.national_identity_card = national_identity_card;
        this.gender = gender;
        this.role = role;
        this.date_of_birth = date_of_birth;
        this.password = password;
    }
    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobilnumber() {
        return mobilnumber;
    }
    public void setMobilnumber(String mobilnumber) {
        this.mobilnumber = mobilnumber;
    }
    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }
    public String getNational_identity_card() {
        return national_identity_card;
    }
    public void setNational_identity_card(String national_identity_card) {
        this.national_identity_card = national_identity_card;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getAge(){
        return Period.between(this.date_of_birth,LocalDate.now()).getYears();
    }
    public String getName() {
        return this.firstname + this.lastname;
    }
    public void setName(String name) {
        name = this.firstname + this.lastname;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));

    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return getEmail();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", mobilnumber='" + mobilnumber + '\'' +
                ", adress='" + adress + '\'' +
                ", national_identity_card='" + national_identity_card + '\'' +
                ", gender='" + gender + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", age=" + age +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }
}
