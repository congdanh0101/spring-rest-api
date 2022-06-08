package springboot.restapi.app.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import springboot.restapi.app.Exception.ResourceNotFoundException;
import springboot.restapi.app.Model.Employee;
import springboot.restapi.app.Repository.EmployeeRepository;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        // TODO Auto-generated method stub
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
        // TODO Auto-generated method stub
        // Optional<Employee> employee = employeeRepository.findById(id);
        // if(employee.isPresent()){
        // return employee.get();
        // }else{
        // throw new ResourceNotFoundException("Employee","Id",id);
        // }

        return employeeRepository.findById(id).orElseThrow(() 
        -> new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        //check employee exist
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Employee", "id", id));   

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        employeeRepository.save(existingEmployee);
        
        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {
        //check employee exist
        Employee employee = employeeRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Employee", "Id", id));

        employeeRepository.deleteById(id);
    }

}
