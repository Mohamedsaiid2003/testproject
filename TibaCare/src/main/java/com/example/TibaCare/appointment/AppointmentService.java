package com.example.TibaCare.appointment;


import com.example.TibaCare.user.Users;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private  AppointmentRepository appointmentRepository;

    public List<Appointment> getappointment(){
        return appointmentRepository.findAll();
    }

    public void addNewAppointment(Appointment appointment) {
        if (appointmentRepository== null) {
            Users users = new Users();
            appointment.setName(users.getName());
            appointment.setEmail(users.getEmail());
            appointment.setNational_identity_card(users.getNational_identity_card());
            appointment.setGender(users.getGender());
            appointment.setAdress(users.getAdress());
            appointment.setDate_of_birth(users.getDate_of_birth());
        } else {
            Optional<Appointment> optionalAppointment = appointmentRepository.findAppointmentByEmail(appointment.getEmail());
            if (optionalAppointment.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            Optional<Appointment> optionalAppointment1 = appointmentRepository.findAppointmentBynational_identity_card(appointment.getNational_identity_card());
            if (optionalAppointment1.isPresent()) {
                throw new IllegalStateException("national_identity_card taken");
            }
            appointmentRepository.save(appointment);
        }
    }

        public void deleteappointment (Long appointmentId){
            boolean exists = appointmentRepository.existsById(appointmentId);
            if (!exists) {
                throw new IllegalStateException("appointment with id " + appointmentId + "does not exists");
            }
            appointmentRepository.deleteById(appointmentId);
        }
        @Transactional
        public void updateappointment (Long appointmentId, String name, String email){
            Appointment appointment = appointmentRepository.findById(appointmentId)
                    .orElseThrow(() -> new IllegalStateException("appointment with id " + appointmentId +
                            "does not exists"));
            if (name != null && name.length() > 0 && !Objects.equals(appointment.getName(), name)) {
                appointment.setName(name);
            }
            if (email != null && email.length() > 0 && !Objects.equals(appointment.getEmail(), email)) {
                Optional<Appointment> appointmentOptional = appointmentRepository.findAppointmentByEmail(email);
                if (appointmentOptional.isPresent()) {
                    throw new IllegalStateException("email taken");
                }
                appointment.setEmail(email);
            }
        }

}