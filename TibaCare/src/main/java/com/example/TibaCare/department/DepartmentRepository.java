package com.example.TibaCare.department;

import com.example.TibaCare.staff.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
//    List<Staff> findByDepartmentId(Long Id);

}
