package com.example.TibaCare.Controller;

import com.example.TibaCare.department.Department;
import com.example.TibaCare.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping
    public void addDepartment(@RequestBody Department department) {
        departmentService.saveDepartments(department);
    }
    @PutMapping(path = "{departmentId}")
    public void updateDepartment(
            @PathVariable("departmentId") Long departmentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        Optional<Department> existingDepartment = departmentService.getDepartmentsById(departmentId);
        if (existingDepartment.isPresent()) {
            Department department = existingDepartment.get();
            department.setId(departmentId);
        }
            }
    @DeleteMapping(path = "{departmentId}")
    public void deleteUser(@PathVariable("departmentId") Long departmentId) {
        departmentService.deleteDepartments(departmentId);
    }


}