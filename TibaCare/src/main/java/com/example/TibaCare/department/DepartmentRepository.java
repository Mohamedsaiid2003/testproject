package com.example.TibaCare.department;

import com.example.TibaCare.staff.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {


    Optional<Department> findByName(String name);
//    List<Staff> findByDepartmentId(Long Id);

}
