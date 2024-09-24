package fr.human_booster.dorian_ferreira.printemps.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EntityNotFoundException extends RuntimeException {
    private String entity;
}
