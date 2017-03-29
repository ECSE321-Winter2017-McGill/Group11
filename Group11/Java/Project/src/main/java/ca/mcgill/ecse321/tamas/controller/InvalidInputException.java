package ca.mcgill.ecse321.tamas.controller;

public class InvalidInputException extends Exception {

    private static final long serialVersionUID = 6468988204L;

    public InvalidInputException(String errorMessage){

        super(errorMessage);

    }

}
