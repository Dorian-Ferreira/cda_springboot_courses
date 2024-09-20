package fr.human_booster.dorian_ferreira.printemps.service;

import fr.human_booster.dorian_ferreira.printemps.dto.AddressDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.AddressLodgingDTO;
import fr.human_booster.dorian_ferreira.printemps.dto.AddressUserDTO;
import fr.human_booster.dorian_ferreira.printemps.entity.Address;
import fr.human_booster.dorian_ferreira.printemps.repository.AddressRepository;
import fr.human_booster.dorian_ferreira.printemps.service.interfaces.ServiceDtoInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService implements ServiceDtoInterface<Address, AddressDTO> {

    private AddressRepository addressRepository;
    private UserService userService;

    public Address create(AddressUserDTO addressUserDTO, String userId) {
        Address address = dtoToObject(addressUserDTO, new Address());

        address.setBilled(addressUserDTO.isBilled());

        address.setUser(userService.findById(userId));

        return addressRepository.saveAndFlush(address);
    }

    public Address create(AddressLodgingDTO addressDTO) {
        Address address = dtoToObject(addressDTO, new Address());

        address.setLatitude(addressDTO.getLatitude());
        address.setLongitude(addressDTO.getLongitude());

        return addressRepository.saveAndFlush(address);
    }

    public Address update(AddressUserDTO addressUserDTO, Long id) {
        Address address = dtoToObject(addressUserDTO, findById(id));

        address.setBilled(addressUserDTO.isBilled());

        return addressRepository.saveAndFlush(address);
    }

    @Override
    public Address dtoToObject(AddressDTO addressUserDTO, Address address) {
        address.setStreet(addressUserDTO.getStreet());
        address.setNumber(addressUserDTO.getNumber());
        address.setZipCode(addressUserDTO.getZipCode());
        address.setCity(addressUserDTO.getCity());
        address.setCountry(addressUserDTO.getCountry());

        address.setMore(addressUserDTO.getMore());

        return address;
    }

    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow();
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}
