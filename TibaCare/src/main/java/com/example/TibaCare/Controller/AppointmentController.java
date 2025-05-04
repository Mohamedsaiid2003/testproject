package com.example.TibaCare.Controller;

import com.example.TibaCare.appointment.Appointment;
import com.example.TibaCare.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/v1/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;
    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    @GetMapping(path = "getdata")
    public List<Appointment> getappointment(){
        return appointmentService.getappointment();
    }
    @PostMapping(path = "addappointment")
    public void registerNewAppointment(@RequestBody Appointment appointment){
        appointmentService.addNewAppointment(appointment);
    }
    @DeleteMapping(path = "{appointmentId}")
    public void deletAppointmentId(@PathVariable("appointmentId") Long appointmentId){
        appointmentService.deleteappointment(appointmentId);
    }
    @PutMapping(path = "{appointmentId}")
    public void updateAppointment(
            @PathVariable("appointmentId") Long appointmentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        appointmentService.updateappointment(appointmentId,name,email);
    }
}
