package com.example.TibaCare.staff;

import com.example.TibaCare.department.Department;
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

@Entity(name = "Staff")
@Table(
        name = "staff",
        uniqueConstraints = {
                @UniqueConstraint(name = "staff_email_unique",columnNames = "email"),
                @UniqueConstraint(name = "staff_national_identity_card_unique",columnNames = "national_identity_card")
        }
)
public class Staff implements UserDetails {
    @Id
    @SequenceGenerator(
            name = "staff_sequence",
            sequenceName = "staff_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "staff_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "firstname",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstname;

    @Column(
            name = "lastname",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastname;

    @Column(
            name = "gender",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "role",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private Role  role;

    @Column(
            name = "mobilnumber",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String mobilnumber;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String email;

    @Column(
            name = "adress",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String adress;

    @Column(
            name = "national_identity_card",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String national_identity_card;


    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;



    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;
    private LocalDate date_of_birth;
    @Transient
    private Integer age;

    public Staff() {
    }
    public Staff(Long id, String firstname, String lastname,
                 String gender, Role role, String mobilnumber,
                 String email, String adress, String national_identity_card,
                 String password, LocalDate date_of_birth , Department  department) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.role = role;
        this.mobilnumber = mobilnumber;
        this.email = email;
        this.adress = adress;
        this.department = department;
        this.national_identity_card = national_identity_card;
        this.password = password;
        this.date_of_birth = date_of_birth;
    }

    public Staff(String firstname, String lastname, String gender,
                 Role role, String mobilnumber, String email,
                 String adress, String national_identity_card,
                 String password, LocalDate date_of_birth,Department department) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.role = role;
        this.mobilnumber = mobilnumber;
        this.email = email;
        this.adress = adress;
        this.department = department;
        this.national_identity_card = national_identity_card;
        this.password = password;
        this.date_of_birth = date_of_birth;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setName(String name) {
        name = this.firstname + this.lastname;
    }
    public String getName() {
        return this.firstname + this.lastname;
    }
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getMobilnumber() {
        return mobilnumber;
    }

    public void setMobilnumber(String mobilnumber) {
        this.mobilnumber = mobilnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Integer getAge(){
        return Period.between(this.date_of_birth,LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", role='" + role + '\'' +
                ", mobilnumber='" + mobilnumber + '\'' +
                ", email='" + email + '\'' +
                ", adress='" + adress + '\'' +
                ", national_identity_card='" + national_identity_card + '\'' +
                ", department=" + department +
                ", password='" + password + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", age=" + age +
                '}';
    }
}
