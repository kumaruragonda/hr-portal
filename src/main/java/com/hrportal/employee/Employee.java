package com.hrportal.employee;

import com.hrportal.enums.EmployeeType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_EMPLOYEE", uniqueConstraints = @UniqueConstraint(columnNames ={"firstName","lastName"} ))
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class Employee implements Serializable {

    @Id
    @GeneratedValue
    private Long  id;

    @NotEmpty
    private String firstName;

    @Transient
    private String middleName;

    @NotEmpty
    private String lastName;

    private BigDecimal Salary;

    @NotNull
    @Min(0)
    @Max(99)
    private int age;

    @Past
    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private LocalDate dateOfJoining;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    public Employee(String  firstName, String lastName, int age, LocalDate dateOfBirth, LocalDate dateOfJoining) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.dateOfBirth=dateOfBirth;
        this.dateOfJoining=dateOfJoining;
    }

    public static Employee of(String  firstName, String lastName, int age, LocalDate dateOfBirth, LocalDate dateOfJoining) {
        return new Employee(  firstName,  lastName,  age,  dateOfBirth,  dateOfJoining);
    }
}
