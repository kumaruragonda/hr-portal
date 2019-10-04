package com.hrportal.employee;

import org.junit.Test;

import java.time.LocalDate;

public class EmployeeUnitTest {

    @Test
    public void givenNullFirstName_whenCallingRequiredArgsConstructore_thenThrowNullPointerException(){
        Employee.of(null,"uragonda",27, LocalDate.now().minusDays(1),LocalDate.now());
    }


}
