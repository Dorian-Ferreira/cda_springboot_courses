package fr.human_booster.dorian_ferreira.printemps.service.interfaces;

public interface ServiceDtoInterface<E, D> {
    E dtoToObject(D dto, E object);
}
