package com.doctores.doctores

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoctorTest {

    @Test
    public void testDoctorCreation() {
        Doctor doctor = new Doctor("John", "Doe", Especialidad.MEDICINA_GENERAL, "Consultorio A", "john.doe@example.com");
        assertEquals("John", doctor.getNombre());
        assertEquals("Doe", doctor.getApellido());
        assertEquals(Especialidad.MEDICINA_GENERAL, doctor.getEspecialidad());
        assertEquals("Consultorio A", doctor.getConsultorio());
        assertEquals("john.doe@example.com", doctor.getCorreoContacto());
    }
}
Pruebas para el controlador DoctorController:
java
Copy code
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

public class DoctorControllerTest {

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("John", "Doe", Especialidad.MEDICINA_GENERAL, "Consultorio A", "john.doe@example.com"));
        doctors.add(new Doctor("Jane", "Smith", Especialidad.CARDIOLOGIA, "Consultorio B", "jane.smith@example.com"));

        when(doctorService.getAllDoctors()).thenReturn(doctors);

        ResponseEntity<List<Doctor>> response = doctorController.getAllDoctors();
        List<Doctor> resultDoctors = response.getBody();

        assertNotNull(resultDoctors);
        assertEquals(2, resultDoctors.size());
        assertEquals("John", resultDoctors.get(0).getNombre());
        assertEquals("Doe", resultDoctors.get(0).getApellido());
        assertEquals("Consultorio A", resultDoctors.get(0).getConsultorio());
        assertEquals("jane.smith@example.com", resultDoctors.get(1).getCorreoContacto());
    }

    @Test
    public void testGetDoctorById() {
        Doctor doctor = new Doctor("John", "Doe", Especialidad.MEDICINA_GENERAL, "Consultorio A", "john.doe@example.com");

        when(doctorService.getDoctorById(1L)).thenReturn(doctor);

        ResponseEntity<Doctor> response = doctorController.getDoctorById(1L);
        Doctor resultDoctor = response.getBody();

        assertNotNull(resultDoctor);
        assertEquals("John", resultDoctor.getNombre());
        assertEquals("Doe", resultDoctor.getApellido());
        assertEquals(Especialidad.MEDICINA_GENERAL, resultDoctor.getEspecialidad());
        assertEquals("john.doe@example.com", resultDoctor.getCorreoContacto());
    }
}