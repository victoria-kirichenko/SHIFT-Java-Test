package edu.cft;

public class Main {
    public static void main(String[] args) {
        try {
            Validator validator = Validator.getInstance(args);
            DataWorker dataWorker = new DataWorker(validator);
            dataWorker.mergeSortFiles();
            Validator.printInfo(validator);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}