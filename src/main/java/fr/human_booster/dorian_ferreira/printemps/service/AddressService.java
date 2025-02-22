package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.AddressDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.AddressLodgingDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.AddressUserDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Address;
import fr.human_booster.dorian_ferreira.printemps.entity.User;
import fr.human_booster.dorian_ferreira.printemps.exception.EntityNotFoundException;
import fr.human_booster.dorian_ferreira.printemps.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository addressRepository;
    private UserService userService;

    public User create(AddressUserDTO addressUserDTO, String userId) {
        try{
            Address address = dtoToObject(addressUserDTO, new Address());

            address.setIsBilled(addressUserDTO.getIsBilled());

            address.setUser(userService.findById(userId));
            addressRepository.saveAndFlush(address);
            return address.getUser();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public User create(AddressUserDTO addressUserDTO, Principal principal) {
        try{
            Address address = dtoToObject(addressUserDTO, new Address());

            address.setIsBilled(addressUserDTO.getIsBilled());

            address.setUser(userService.findByEmail(principal.getName()));
            addressRepository.saveAndFlush(address);
            return address.getUser();
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public Address createInit(AddressUserDTO addressUserDTO, String userId) {
        try{
            Address address = dtoToObject(addressUserDTO, new Address());

            address.setIsBilled(addressUserDTO.getIsBilled());

            address.setUser(userService.findById(userId));

            return addressRepository.save(address);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public Address create(AddressLodgingDTO addressDTO) {
        Address address = dtoToObject(addressDTO, new Address());

        address.setLatitude(addressDTO.getLatitude());
        address.setLongitude(addressDTO.getLongitude());

        return addressRepository.saveAndFlush(address);
    }

    public Address createInit(AddressLodgingDTO addressDTO) {
        Address address = dtoToObject(addressDTO, new Address());

        address.setLatitude(addressDTO.getLatitude());
        address.setLongitude(addressDTO.getLongitude());

        return addressRepository.save(address);
    }

    public Address update(AddressUserDTO addressUserDTO, Long id) {
        try{
            Address address = dtoToObject(addressUserDTO, findById(id));

            address.setIsBilled(addressUserDTO.getIsBilled());

            return addressRepository.saveAndFlush(address);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public Address dtoToObject(AddressDTO addressUserDTO, Address address) {
        address.setStreet(addressUserDTO.getStreet());
        address.setNumber(addressUserDTO.getNumber());
        address.setZipCode(addressUserDTO.getZipCode());
        address.setCity(addressUserDTO.getCity());
        address.setCountry(addressUserDTO.getCountry());

        address.setMore(addressUserDTO.getMore());

        return address;
    }

    public Address findById(Long id) throws EntityNotFoundException {
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address"));
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public User delete(Long id) {
        Address address = findById(id);

        addressRepository.delete(address);

        return address.getUser();
    }
}
