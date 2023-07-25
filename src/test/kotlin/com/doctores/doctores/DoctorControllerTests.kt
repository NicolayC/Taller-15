package com.doctores.doctores

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;


public class DoctorTest {

    @Test
    public void testDoctorCreation() {
        Doctor doctor = new Doctor("Dakota", "Neville", "Medicina General", "Consultorio A", "dakota.neville@x.com");
        assertEquals("Dakota", doctor.getNombre());
        assertEquals("Neville", doctor.getApellido());
        assertEquals("Medicina General", doctor.getEspecialidad());
        assertEquals("Consultorio A", doctor.getConsultorio());
        assertEquals("jakota.neville@x.com", doctor.getCorreoContacto());
    }
}

// Test Total DoctorController
public class DoctorControllerTest {

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test getAllDoctors
    public void testGetAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        //Mock Process
        doctors.add(new Doctor("Dakota", "Neville", "Medicina General", "Consultorio A", "john.doe@example.com"));
        doctors.add(new Doctor("Jim", "Hougan", "Ortopedia", "Consultorio B", "jim.hougan@x.com"));

        // Method execution
        when(doctorService.getAllDoctors()).thenReturn(doctors);

        ResponseEntity<List<Doctor>> response = doctorController.getAllDoctors();
        List<Doctor> resultDoctors = response.getBody();

        // Asserts
        assertNotNull(resultDoctors);
        assertEquals(2, resultDoctors.size());
        assertEquals("Dakota", resultDoctors.get(0).getNombre());
        assertEquals("Neville", resultDoctors.get(0).getApellido());
        assertEquals("Consultorio A", resultDoctors.get(0).getConsultorio());
        assertEquals("dakota.neville@x.com", resultDoctors.get(1).getCorreoContacto());
    }

    // Test GetDoctorById
    public void testGetDoctorById() {
        //Mock Process
        Doctor doctor = new Doctor("Dakota", "Neville", "Medicina General", "Consultorio A", "dakota.neville@x.com");

        // Method execution
        when(doctorService.getDoctorById(1L)).thenReturn(doctor);

        ResponseEntity<Doctor> response = doctorController.getDoctorById(1L);
        Doctor resultDoctor = response.getBody();

        // Asserts
        assertNotNull(resultDoctor);
        assertEquals("Dakota", resultDoctor.getNombre());
        assertEquals("Neville", resultDoctor.getApellido());
        assertEquals("Medicina General", resultDoctor.getEspecialidad());
        assertEquals("dakota.neville@x.com", resultDoctor.getCorreoContacto());
    }
}