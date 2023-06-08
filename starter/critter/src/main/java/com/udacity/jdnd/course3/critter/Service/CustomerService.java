package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    public Customer saveCustomer(Customer customer, List<Long> petIds) {
        List<Pet> petList = new ArrayList<>();

        if (petIds != null) {
            petList = petIds.stream().map(petId -> petRepository.getOne(petId)).collect(Collectors.toList());
        }

        customer.setPets(petList);
        customerRepository.save(customer);

        return customer;
    }

    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer findCustomerByPet(Long petId) {
        Pet pet = petRepository.getOne(petId);
        return pet.getCustomer();
    }
}
