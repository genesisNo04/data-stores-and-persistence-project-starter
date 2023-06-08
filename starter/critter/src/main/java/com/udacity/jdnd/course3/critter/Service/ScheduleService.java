package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.DTO.ScheduleDTO;
import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Employee;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Entity.Schedule;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import com.udacity.jdnd.course3.critter.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findScheduleForEmployee(Long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        return scheduleRepository.findByEmployees(employee);
    }

    public List<Schedule> findScheduleForPet(Long petId) {
        Pet pet = petRepository.getOne(petId);
        return scheduleRepository.findByPets(pet);
    }

    public List<Schedule> findScheduleForCustomer(Long customerId) {
        List<Schedule> schedules = new ArrayList<>();
        Customer customer = customerRepository.getOne(customerId);
        List<Pet> pets = customer.getPets();
        for (Pet pet : pets) {
            schedules.addAll(findScheduleForPet(pet.getId()));
        }
        return schedules;
    }
}
