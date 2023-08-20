package edu.cft;

import java.io.*;
import java.util.List;

public class DataWorker {
    final private Validator validator;

    public DataWorker(Validator validator) {
        this.validator = validator;
    }

    public void mergeSortFiles() {
        BufferedReader[] readers = getFileReaders(validator.getInputFiles());
        if (readers == null) return;

        boolean[] stopReading = new boolean[readers.length];
        String[] inputLines = new String[readers.length];
        String[] previousLines = new String[readers.length];

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(validator.getOutputFile(), false))) {
            while (true) {
                String lineToWrite = getLineToWrite(readers, stopReading, inputLines, previousLines);
                if (lineToWrite == null) break;
                writer.write(lineToWrite);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Couldn't write to file: " + e.getMessage());
        }

        closeReaders(readers);
    }

    private BufferedReader[] getFileReaders(List<File> inputFiles) {
        int numOfFiles = inputFiles.size();
        BufferedReader[] readers = new BufferedReader[numOfFiles];
        try {
            for (int i = 0; i < numOfFiles; ++i) {
                readers[i] = new BufferedReader(new FileReader(inputFiles.get(i)));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            closeReaders(readers);
            readers = null;
        }
        return readers;
    }

    private void closeReaders(BufferedReader[] readers) {
        for (BufferedReader reader : readers) {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private String getLineToWrite(BufferedReader[] readers, boolean[] stopReading, String[] inputLines,
                                  String[] previousLines) throws IOException {
        for (int i = 0; i < readers.length; ++i) {
            if (inputLines[i] != null) continue;
            String line = null;
            boolean isInvalidLine = true;
            while (!stopReading[i] && isInvalidLine) {
                if ((line = readers[i].readLine()) == null) {
                    stopReading[i] = true;
                    continue;
                }
                if (isValid(line)) {
                    inputLines[i] = line;
                    isInvalidLine = false;
                }
            }

            if (line != null && !processFileDataOrder(inputLines, i, stopReading, previousLines)) {
                validator.getBadOrderFilenames().add(validator.getInputFiles().get(i).getName());
            }
        }
        return findBestLine(inputLines);
    }

    private boolean isValid(String line) {
        if (line.contains(" ")) return false;
        return validator.getType() != DataType.INTEGER || line.matches("^[+-]?[0-9]+$");
    }

    private boolean processFileDataOrder(String[] inputLines, int index, boolean[] stopReading,
                                         String[] previousLines) {
        String prevLine = previousLines[index];
        String currLine = inputLines[index];

        if (prevLine == null || satisfiesOrder(currLine, prevLine, validator.getOrder())) {
            previousLines[index] = currLine;
            return true;
        }

        inputLines[index] = null;
        stopReading[index] = true;
        return false;
    }

    private boolean satisfiesOrder(String current, String previous, Order order) {
        int currNum, prevNum;
        if (validator.getType() == DataType.INTEGER) {
            currNum = Integer.parseInt(current);
            prevNum = Integer.parseInt(previous);
            return (order == Order.ASC) ? currNum >= prevNum : currNum <= prevNum;
        }
        return (order == Order.ASC) ? current.compareTo(previous) >= 0 : current.compareTo(previous) <= 0;
    }

    private String findBestLine(String[] inputLines) {
        String bestValue = null;
        int bestElementIndex = -1;

        for (int i = 0; i < inputLines.length; ++i) {
            if (inputLines[i] == null) continue;

            if (bestValue == null) {
                bestValue = inputLines[i];
                bestElementIndex = i;
            }
            if (satisfiesOrder(inputLines[i], bestValue, validator.getOrder().opposite())) {
                bestValue = inputLines[i];
                bestElementIndex = i;
            }
        }

        if (bestValue != null) inputLines[bestElementIndex] = null;
        return bestValue;
    }
}
