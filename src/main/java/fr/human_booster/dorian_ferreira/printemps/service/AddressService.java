package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.AddressLodgingDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.AddressUserDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Address;
import fr.human_booster.dorian_ferreira.printemps.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository addressRepository;
    private UserService userService;

    public Address create(AddressUserDTO addressUserDTO, String userId) {
        Address address = new Address();

        address.setStreet(addressUserDTO.getStreet());
        address.setNumber(addressUserDTO.getNumber());
        address.setZipCode(addressUserDTO.getZipCode());
        address.setCity(addressUserDTO.getCity());
        address.setCountry(addressUserDTO.getCountry());

        address.setMore(addressUserDTO.getMore());

        address.setBilled(addressUserDTO.isBilled());

        address.setUser(userService.findById(userId));

        return addressRepository.saveAndFlush(address);
    }

    public Address create(AddressLodgingDTO addressDTO) {
        Address address = new Address();

        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setZipCode(addressDTO.getZipCode());
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());

        address.setLatitude(addressDTO.getLatitude());
        address.setLongitude(addressDTO.getLongitude());

        address.setMore(addressDTO.getMore());

        return addressRepository.saveAndFlush(address);
    }

    public Address update(AddressUserDTO addressUserDTO, Long id) {
        Address address = findById(id);

        if(addressUserDTO.getStreet().equals(address.getStreet()))
            address.setStreet(addressUserDTO.getStreet());

        if(addressUserDTO.getNumber().equals(address.getNumber()))
            address.setNumber(addressUserDTO.getNumber());

        if(addressUserDTO.getZipCode().equals(address.getZipCode()))
            address.setZipCode(addressUserDTO.getZipCode());

        if(addressUserDTO.getCity().equals(address.getCity()))
            address.setCity(addressUserDTO.getCity());

        if(addressUserDTO.getCountry().equals(address.getCountry()))
            address.setCountry(addressUserDTO.getCountry());

        if(addressUserDTO.getMore().equals(address.getMore()))
            address.setMore(addressUserDTO.getMore());

        if(addressUserDTO.isBilled() != address.isBilled())
            address.setBilled(addressUserDTO.isBilled());

        return addressRepository.saveAndFlush(address);
    }

    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow();
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}
