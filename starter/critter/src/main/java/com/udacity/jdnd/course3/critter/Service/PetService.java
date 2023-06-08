package com.udacity.jdnd.course3.critter.Service;

import com.udacity.jdnd.course3.critter.Entity.Customer;
import com.udacity.jdnd.course3.critter.Entity.Pet;
import com.udacity.jdnd.course3.critter.Repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.Repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Pet save(Pet newPet, Long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        newPet.setCustomer(customer);

        petRepository.save(newPet);
//        customer.getPets().add(newPet);
        customer.addPet(newPet);
        customerRepository.save(customer);
        return newPet;
    }

    public Pet findPetById(Long petId) {
        return petRepository.getOne(petId);
    }

    public List<Pet> findPetList() {
        return petRepository.findAll();
    }

    public List<Pet> findPetListByCustomer(Long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        List<Pet> listPets = customer.getPets();
        return listPets;
    }
}
