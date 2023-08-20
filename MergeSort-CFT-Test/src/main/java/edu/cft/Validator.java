package edu.cft;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    private Order order;
    private DataType type;
    private File outputFile;
    private boolean outputFileFound;
    private List<File> inputFiles;
    private List<String> missingInputFiles;
    private List<String> badOrderFilenames;

    public Validator() {
        order = Order.ASC;
        type = DataType.INTEGER;
        outputFile = null;
        outputFileFound = false;
        inputFiles = new ArrayList<>();
        missingInputFiles = new ArrayList<>();
        badOrderFilenames = new ArrayList<>();
    }

    public static Validator getInstance(String[] args) throws IOException {
        int minArguments = 3;
        if (args.length < minArguments) throw new IllegalArgumentException("Invalid count arguments. Usage: [-a|-d] (need not use) [-i|-s] output_file input_file1 input_file2 ...");
        int currI = 0;
        Validator settings = new Validator();

        if (args[currI].equals("-a") || args[currI].equals("-d")) {
            if (args[currI].equals("-d")) settings.setOrder(Order.DESC);
            currI++;
        }

        if (!args[currI].equals("-s") && !args[currI].equals("-i")) {
            throw new IllegalArgumentException("Invalid arguments. Usage: [-a|-d] (need not use) [-i|-s] output_file input_file1 input_file2 ...");
        } else if (args[currI].equals("-s")) {
            settings.setType(DataType.STRING);
        }

        File outputFile = new File(args[++currI]);
        settings.setOutputFileFound(!outputFile.createNewFile());
        settings.setOutputFile(outputFile);

        while (++currI < args.length) {
            File inputFile = new File(args[currI]);
            if (inputFile.isFile()) settings.addInputFile(inputFile);
            else settings.getMissingInputFiles().add(inputFile.getName());
        }

        if (settings.getInputFiles().size() == 0)
            throw new IOException("There must be at least one input file to produce output file");

        return settings;
    }

    public static void printInfo(Validator validator) {
        System.out.println("\nOrder type: " + validator.getOrder());
        System.out.println("Data type: " + validator.getType());
        System.out.println("Output file: " + validator.getOutputFile().getName());
        for (File f : validator.getInputFiles()) {
            System.out.println("Input file: " + f.getName());
        }

        System.out.println();

        if (!validator.isOutputFileFound()) {
            System.out.println(
                    "Выходной файл \"" + validator.getOutputFile().getName() + "\" был найден и был создан программой");
        }

        for (String name : validator.getMissingInputFiles()) {
            System.out.println("Входной файл \"" + name + "\" не был найден");
        }

        for (String name : validator.getBadOrderFilenames()) {
            System.out.println("Нарушен порядок сортировки данных в файле: " + name);
        }
    }
    public void addInputFile(File file) {
        inputFiles.add(file);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public boolean isOutputFileFound() {
        return outputFileFound;
    }

    public void setOutputFileFound(boolean outputFileFound) {
        this.outputFileFound = outputFileFound;
    }

    public List<File> getInputFiles() {
        return inputFiles;
    }

    public void setInputFiles(List<File> inputFiles) {
        this.inputFiles = inputFiles;
    }

    public List<String> getMissingInputFiles() {
        return missingInputFiles;
    }

    public void setMissingInputFiles(List<String> missingInputFiles) {
        this.missingInputFiles = missingInputFiles;
    }

    public List<String> getBadOrderFilenames() {
        return badOrderFilenames;
    }

    public void setBadOrderFilenames(List<String> badOrderFilenames) {
        this.badOrderFilenames = badOrderFilenames;
    }
}
