package com.example.learn.boot.unittest.Employee;

import com.example.learn.boot.unittest.UnitTestApplicationTests;
import com.example.learn.boot.unittest.model.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.Id;

import java.util.Objects;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeTest extends UnitTestApplicationTests {

    private EmployeeTest Id;

    @Test
    public void createEmployeeTest() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("Employee");
        dto.setAddress("Kathmandu");
        dto.setEmail("user@user.com");
        dto.setPhone("9876554433");

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Employee"))
                .andExpect(jsonPath("$.address").value("Kathmandu"))
                .andExpect(jsonPath("$.email").value("user@user.com"))
                .andExpect(jsonPath("$.phone").value("9876554433"));
    }
@Test
    public void getEmployeeById() throws Exception {
        createEmployee("aaku","pkr","aaku@gmail.com","67687888");



        mockMvc.perform(MockMvcRequestBuilders.get("/employee/1")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("aaku"));

   }

    @Test
    public void deleteEmployeeById() throws Exception {
        createEmployee("aaku","pkr","aaku@gmail.com","67687888");

        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

   @Test
    public void listAllTest() throws Exception{
        createEmployee("aaru","ktm","aradhana@gmail.com","8594854");

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].name").value("aaru"));



   }

    @Test
    public void updateEmployeeTest() throws Exception {
        // Create an employee
        createEmployee("dina", "bhutan", "dina@gmail.com", "4564454");

        // Update the employee
        String updatedEmployeeJson = "{ \"name\": \"deena\", \"address\": \"kathmandu\", \"email\": \"deena@gmail.com\", \"phone\": \"151544\" }";
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJson))
                .andExpect(status().isOk());

        // Verify that the employee was updated
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("deena"))
                .andExpect(jsonPath("$.address").value("kathmandu"))
                .andExpect(jsonPath("$.email").value("deena@gmail.com"))
                .andExpect(jsonPath("$.phone").value("151544"));
    }

    @Test
    public void createEmployeeTestNegative() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName(null); // Set a required field to null
        dto.setAddress("Kathmandu");
        dto.setEmail("user@user.com");
        dto.setPhone("9876554433");
        mockMvc.perform(MockMvcRequestBuilders.post("/employee/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest()); // Verify that the response status is 400
    }

    @Test
    public void negativegetEmployeeById() throws Exception {
        createEmployee("aaku","pkr","aaku@gmail.com","67687888");



        mockMvc.perform(MockMvcRequestBuilders.get("/employee/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


    }

    @Test
    public void deleteEmployeeByIdTest() throws Exception {
        // Create an employee to delete
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("aaku");
        dto.setAddress("pkr");
        dto.setEmail("aaku@gmail.com");
        dto.setPhone("67687888");
        EmployeeDTO savedDto = employeeService.save(dto);

        // Send DELETE request to delete the employee
        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/" + savedDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that the employee has been deleted
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/" + savedDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createEmployeeNegTest() throws Exception {
        EmployeeDTO dto = new EmployeeDTO();
//      dto.setName();
        dto.setAddress("brt");
        dto.setEmail("aaku@gmail.com");
        dto.setPhone("9834123433");

        mockMvc.perform(MockMvcRequestBuilders.post("/employee/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void listAllTestNotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/employee/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound()); // Verify that the response status is 404
    }

    @Test
    public void updateEmployeeNotFoundTest() throws Exception {
        // Create an employee
        createEmployee("dina", "bhutan", "dina@gmail.com", "4564454");

        // Update the employee
        String updatedEmployeeJson = "{ \"name\": \"deena\", \"address\": \"kathmandu\", \"email\": \"deena@gmail.com\", \"phone\": \"151544\" }";
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedEmployeeJson))
                .andExpect(status().isOk());

        // Verify that the employee was not updated
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }








}
