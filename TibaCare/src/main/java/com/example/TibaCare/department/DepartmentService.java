package com.example.TibaCare.department;

import com.example.TibaCare.staff.Staff;
import com.example.TibaCare.staff.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private  DepartmentRepository departmentRepository;
    @Autowired
    private StaffRepository staffRepository;


    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }


    public Optional<Department> getDepartmentsById(Long id) {
        return departmentRepository.findById(id);
    }
    public Department saveDepartments(Department department) {
        return departmentRepository.save(department);
    }
    public void assignDoctorsToDepartment(Long departmentId, List<Long> staffIds) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalStateException("Department not found"));

        List<Staff> staffList = staffRepository.findAllById(staffIds);

        for (Staff staff : staffList) {
            staff.setDepartment(department);
        }

        staffRepository.saveAll(staffList);
    }

    public Optional<Department> updatedService(Long id) {
        Optional<Department> existingservice = departmentRepository.findById(id);
        if (existingservice.isPresent()) {
            Department service = existingservice.get();
            service.setId(service.getId());
            return Optional.of(departmentRepository.save(service));
        }
        return Optional.empty();
    }
    public boolean deleteDepartments(Long id) {
        Optional<Department> existingService = departmentRepository.findById(id);
        if (existingService.isPresent()) {
            departmentRepository.delete(existingService.get());
            return true;
        }
        return false;
    }
}
