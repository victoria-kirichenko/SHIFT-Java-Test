import edu.cft.DataWorker;
import edu.cft.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DataWorkerTest {
    public static final String path = String.join(File.separator, "src", "test", "resources",
            "fileMergingResources") + File.separator;

    @BeforeAll
    public static void deleteAllOutputFiles() throws IOException {
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.filter(x -> x.getFileName().toString().equals("out.txt"))
                    .forEach(path1 -> {
                        try {
                            Files.delete(path1);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    });
        }
    }

    @Test
    public void ascCorrectSortCorrectIntegerData() throws IOException {
        String tPath = path + "case1" + File.separator;
        String outputFile = tPath + "out.txt";
        String checkFile = tPath + "check.txt";

        Validator parsed = Validator.getInstance(
                new String[]{"-i",
                        outputFile,
                        tPath + "1.txt",
                        tPath + "2.txt",
                        tPath + "3.txt"});
        DataWorker merger = new DataWorker(parsed);
        merger.mergeSortFiles();
        String strFromOutputFile = Files.readString(Paths.get(outputFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        String strFromCheckFile = Files.readString(Paths.get(checkFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        Assertions.assertEquals(strFromCheckFile, strFromOutputFile);
    }

    @Test
    public void ascCorrectSortIncorrectIntegerData() throws IOException {
        String tPath = path + "case2" + File.separator;
        String outputFile = tPath + "out.txt";
        String checkFile = tPath + "check.txt";

        Validator parsed = Validator.getInstance(
                new String[]{"-i",
                        outputFile,
                        tPath + "1.txt",
                        tPath + "2.txt",
                        tPath + "3.txt"});
        DataWorker merger = new DataWorker(parsed);
        merger.mergeSortFiles();
        String strFromOutputFile = Files.readString(Paths.get(outputFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        String strFromCheckFile = Files.readString(Paths.get(checkFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        Assertions.assertEquals(strFromCheckFile, strFromOutputFile);
    }

    @Test
    public void ascIncorrectSortCorrectIntegerData() throws IOException {
        String tPath = path + "case3" + File.separator;
        String outputFile = tPath + "out.txt";
        String checkFile = tPath + "check.txt";

        Validator parsed = Validator.getInstance(
                new String[]{"-i",
                        outputFile,
                        tPath + "1.txt",
                        tPath + "2.txt",
                        tPath + "3.txt"});
        DataWorker merger = new DataWorker(parsed);
        merger.mergeSortFiles();
        String strFromOutputFile = Files.readString(Paths.get(outputFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        String strFromCheckFile = Files.readString(Paths.get(checkFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        Assertions.assertEquals(strFromCheckFile, strFromOutputFile);
    }

    @Test
    public void ascIncorrectSortIncorrectIntegerData() throws IOException {
        String tPath = path + "case4" + File.separator;
        String outputFile = tPath + "out.txt";
        String checkFile = tPath + "check.txt";

        Validator parsed = Validator.getInstance(
                new String[]{"-i",
                        outputFile,
                        tPath + "1.txt",
                        tPath + "2.txt",
                        tPath + "3.txt"});
        DataWorker merger = new DataWorker(parsed);
        merger.mergeSortFiles();
        String strFromOutputFile = Files.readString(Paths.get(outputFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        String strFromCheckFile = Files.readString(Paths.get(checkFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        Assertions.assertEquals(strFromCheckFile, strFromOutputFile);
    }

    @Test
    public void descCorrectSortCorrectIntegerData() throws IOException {
        String tPath = path + "case5" + File.separator;
        String outputFile = tPath + "out.txt";
        String checkFile = tPath + "check.txt";

        Validator parsed = Validator.getInstance(
                new String[]{"-d",
                        "-i",
                        outputFile,
                        tPath + "1.txt",
                        tPath + "2.txt",
                        tPath + "3.txt"});
        DataWorker merger = new DataWorker(parsed);
        merger.mergeSortFiles();
        String strFromOutputFile = Files.readString(Paths.get(outputFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        String strFromCheckFile = Files.readString(Paths.get(checkFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        Assertions.assertEquals(strFromCheckFile, strFromOutputFile);
    }

    @Test
    public void ascCorrectSortCorrectStringData() throws IOException {
        String tPath = path + "case6" + File.separator;
        String outputFile = tPath + "out.txt";
        String checkFile = tPath + "check.txt";

        Validator parsed = Validator.getInstance(
                new String[]{"-a",
                        "-s",
                        outputFile,
                        tPath + "1.txt",
                        tPath + "2.txt",
                        tPath + "3.txt"});
        DataWorker merger = new DataWorker(parsed);
        merger.mergeSortFiles();
        String strFromOutputFile = Files.readString(Paths.get(outputFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        String strFromCheckFile = Files.readString(Paths.get(checkFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        Assertions.assertEquals(strFromCheckFile, strFromOutputFile);
    }

    @Test
    public void ascCorrectSortIncorrectStringData() throws IOException {
        String tPath = path + "case7" + File.separator;
        String outputFile = tPath + "out.txt";
        String checkFile = tPath + "check.txt";

        Validator parsed = Validator.getInstance(
                new String[]{"-a",
                        "-s",
                        outputFile,
                        tPath + "1.txt",
                        tPath + "2.txt",
                        tPath + "3.txt"});
        DataWorker merger = new DataWorker(parsed);
        merger.mergeSortFiles();
        String strFromOutputFile = Files.readString(Paths.get(outputFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        String strFromCheckFile = Files.readString(Paths.get(checkFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        Assertions.assertEquals(strFromCheckFile, strFromOutputFile);
    }

    @Test
    public void ascIncorrectSortCorrectStringData() throws IOException {
        String tPath = path + "case8" + File.separator;
        String outputFile = tPath + "out.txt";
        String checkFile = tPath + "check.txt";

        Validator parsed = Validator.getInstance(
                new String[]{"-a",
                        "-s",
                        outputFile,
                        tPath + "1.txt",
                        tPath + "2.txt",
                        tPath + "3.txt"});
        DataWorker merger = new DataWorker(parsed);
        merger.mergeSortFiles();
        String strFromOutputFile = Files.readString(Paths.get(outputFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        String strFromCheckFile = Files.readString(Paths.get(checkFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        Assertions.assertEquals(strFromCheckFile, strFromOutputFile);
    }

    @Test
    public void ascIncorrectSortIncorrectStringData() throws IOException {
        String tPath = path + "case9" + File.separator;
        String outputFile = tPath + "out.txt";
        String checkFile = tPath + "check.txt";

        Validator parsed = Validator.getInstance(
                new String[]{"-a",
                        "-s",
                        outputFile,
                        tPath + "1.txt",
                        tPath + "2.txt",
                        tPath + "3.txt"});
        DataWorker merger = new DataWorker(parsed);
        merger.mergeSortFiles();
        String strFromOutputFile = Files.readString(Paths.get(outputFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        String strFromCheckFile = Files.readString(Paths.get(checkFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        Assertions.assertEquals(strFromCheckFile, strFromOutputFile);
    }

    @Test
    public void descCorrectSortCorrectStringData() throws IOException {
        String tPath = path + "case10" + File.separator;
        String outputFile = tPath + "out.txt";
        String checkFile = tPath + "check.txt";

        Validator parsed = Validator.getInstance(
                new String[]{"-d",
                        "-s",
                        outputFile,
                        tPath + "1.txt",
                        tPath + "2.txt",
                        tPath + "3.txt"});
        DataWorker merger = new DataWorker(parsed);
        merger.mergeSortFiles();
        String strFromOutputFile = Files.readString(Paths.get(outputFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        String strFromCheckFile = Files.readString(Paths.get(checkFile), StandardCharsets.UTF_8)
                .replaceAll("\r", "");
        Assertions.assertEquals(strFromCheckFile, strFromOutputFile);
    }
}
