package fr.human_booster.dorian_ferreira.printemps.json_views;

public class JsonViewsAddress {
    public interface User {}
    public interface Billed {}
    public interface More {}
    public interface Latitude {}
    public interface Longitude {}
    public interface Country {}
    public interface City {}
    public interface ZipCode {}
    public interface Number {}
    public interface Street {}
    public interface Id {}

    public interface AddressUserShow extends
            Country,
            City,
            ZipCode,
            Street,
            Number,
            Billed
    {}
}
