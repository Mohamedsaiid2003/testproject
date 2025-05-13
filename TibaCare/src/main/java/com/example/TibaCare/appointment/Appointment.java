package com.example.TibaCare.appointment;

import com.example.TibaCare.department.Department;
import com.example.TibaCare.staff.Staff;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "appointment")
@Table(
        name = "appointment",
        uniqueConstraints = {
                @UniqueConstraint(name = "users_email_unique",columnNames = "email")
        }
)
public class Appointment  {
    @Id
    @SequenceGenerator(
            name = "appointment_sequence",
            sequenceName = "appointment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "appointment_sequence"
    )
    @Column(
            name = "id",
            nullable = false
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
            name = "email",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    private String email;

    @Column(
            name = "mobilnumber",
            nullable = false,
            columnDefinition = "TEXT"
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
            columnDefinition = "TEXT"
    )
    private String national_identity_card;
    @Column(
            name = "gender",
            nullable = false,
            columnDefinition ="TEXT"
    )
    private String gender;
    @Column(
            name = "appointment_date",
            nullable = false
    )
    private LocalDate appointment_date;

    private LocalDate date_of_birth;
    @ManyToOne
    @JoinColumn(name = "department_name_id")
    private Department department_name;


    @ManyToOne
    @JoinColumn(name = "doctor_name_id")
    private Staff doctor_name;

    @Transient
    private Integer age;


    public Appointment() {
    }

    public Appointment(Long id, String firstname, String lastname, String email,
                       String mobilnumber, String adress, String national_identity_card,
                       String gender, LocalDate appointment_date, LocalDate date_of_birth,
                       Department department_name, Staff doctor_name) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobilnumber = mobilnumber;
        this.adress = adress;
        this.national_identity_card = national_identity_card;
        this.gender = gender;
        this.appointment_date = appointment_date;
        this.date_of_birth = date_of_birth;
        this.department_name = department_name;
        this.doctor_name = doctor_name;
    }

    public Appointment(String firstname, String lastname, String email,
                       String mobilnumber, String adress, String national_identity_card,
                       String gender, LocalDate appointment_date, LocalDate date_of_birth,
                       Department department_name, Staff doctor_name) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobilnumber = mobilnumber;
        this.adress = adress;
        this.national_identity_card = national_identity_card;
        this.gender = gender;
        this.appointment_date = appointment_date;
        this.date_of_birth = date_of_birth;
        this.department_name = department_name;
        this.doctor_name = doctor_name;
    }
    public String getName() {
        return this.firstname + this.lastname;
    }

    public void setName(String name) {
        name = this.firstname + this.lastname;
    }
    public Staff getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(Staff doctor_name) {
        this.doctor_name = doctor_name;
    }

    public Department getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(Department department_name) {
        this.department_name = department_name;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public LocalDate getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(LocalDate appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNational_identity_card() {
        return national_identity_card;
    }

    public void setNational_identity_card(String national_identity_card) {
        this.national_identity_card = national_identity_card;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getAge(){
        return Period.between(this.date_of_birth,LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", mobilnumber='" + mobilnumber + '\'' +
                ", adress='" + adress + '\'' +
                ", national_identity_card='" + national_identity_card + '\'' +
                ", gender='" + gender + '\'' +
                ", appointment_date=" + appointment_date +
                ", date_of_birth=" + date_of_birth +
                ", department_name=" + department_name +
                ", doctor_name=" + doctor_name +
                ", age=" + age +
                '}';
    }
}