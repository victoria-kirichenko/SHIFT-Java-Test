import edu.cft.DataType;
import edu.cft.Order;
import edu.cft.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ValidatorTest {

    public static final String path = String.join(File.separator, "src", "test", "resources",
            "argumentsTestResources") + File.separator;

    @Test
    public void argumentsShouldBeParsedCorrectly1Case() throws IOException {
        Validator parsed = Validator.getInstance(
                new String[]{"-s",
                        path + "out.txt",
                        path + "in1.txt",
                        path + "in2.txt"});

        Assertions.assertEquals(Order.ASC, parsed.getOrder());
        Assertions.assertEquals(DataType.STRING, parsed.getType());
        File[] checkFiles = new File[]{new File(path + "in1.txt"), new File(path + "in2.txt")};
        Assertions.assertArrayEquals(checkFiles, parsed.getInputFiles().toArray(checkFiles));
        Assertions.assertEquals(new File(path + "out.txt"), parsed.getOutputFile());
    }

    @Test
    public void argumentsShouldBeParsedCorrectly2Case() throws IOException {
        Validator parsed = Validator.getInstance(
                new String[]{"-i",
                        path + "out.txt",
                        path + "in1.txt",
                        path + "in2.txt"});

        Assertions.assertEquals(Order.ASC, parsed.getOrder());
        Assertions.assertEquals(DataType.INTEGER, parsed.getType());
        File[] checkFiles = new File[]{new File(path + "in1.txt"), new File(path + "in2.txt")};
        Assertions.assertArrayEquals(checkFiles, parsed.getInputFiles().toArray(checkFiles));
        Assertions.assertEquals(new File(path + "out.txt"), parsed.getOutputFile());
    }

    @Test
    public void argumentsShouldBeParsedCorrectly3Case() throws IOException {
        Validator parsed = Validator.getInstance(
                new String[]{"-a",
                        "-i",
                        path + "out.txt",
                        path + "in1.txt",
                        path + "in2.txt"});

        Assertions.assertEquals(Order.ASC, parsed.getOrder());
        Assertions.assertEquals(DataType.INTEGER, parsed.getType());
        File[] checkFiles = new File[]{new File(path + "in1.txt"), new File(path + "in2.txt")};
        Assertions.assertArrayEquals(checkFiles, parsed.getInputFiles().toArray(checkFiles));
        Assertions.assertEquals(new File(path + "out.txt"), parsed.getOutputFile());
    }

    @Test
    public void argumentsShouldBeParsedCorrectly4Case() throws IOException {
        Validator parsed = Validator.getInstance(
                new String[]{"-d",
                        "-s",
                        path + "out.txt",
                        path + "in1.txt",
                        path + "in2.txt"});

        Assertions.assertEquals(Order.DESC, parsed.getOrder());
        Assertions.assertEquals(DataType.STRING, parsed.getType());
        File[] checkFiles = new File[]{new File(path + "in1.txt"), new File(path + "in2.txt")};
        Assertions.assertArrayEquals(checkFiles, parsed.getInputFiles().toArray(checkFiles));
        Assertions.assertEquals(new File(path + "out.txt"), parsed.getOutputFile());
    }

    @Test
    public void exceptionShouldBeThrown1() {
        IllegalArgumentException exc = Assertions.assertThrows(IllegalArgumentException.class, () ->
                Validator.getInstance(
                        new String[]{"-a",
                                path + "out.txt",
                                path + "in1.txt"})
        );
        Assertions.assertEquals(exc.getMessage(), "Invalid arguments. " +
                "Usage: [-a|-d] (need not use) [-i|-s] output_file input_file1 input_file2 ...");
    }

    @Test
    public void exceptionShouldBeThrown2() {
        IllegalArgumentException exc = Assertions.assertThrows(IllegalArgumentException.class,
                () -> Validator.getInstance(new String[]{"-s", path + "out.txt"}));
        Assertions.assertEquals(exc.getMessage(),
                "Invalid count arguments. " +
                        "Usage: [-a|-d] (need not use) [-i|-s] output_file input_file1 input_file2 ...");
    }

    @Test
    public void exceptionShouldBeThrown3() {
        IOException exc = Assertions.assertThrows(IOException.class, () ->
                Validator.getInstance(
                        new String[]{"-a",
                                "-i",
                                path + "out.txt"}));
        Assertions.assertEquals(exc.getMessage(), "There must be at least one input file to produce output file");
    }
}
