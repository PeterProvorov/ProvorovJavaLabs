package encryptionStream;

import org.apache.commons.cli.*;

import java.io.*;

/**
 * Created by ThinkPad on 23.10.2016.
 */
public class DeltaEncryption implements BufferedReader {

    public static void code(String inFileName, String outFileName) throws IOException {
        try (InputStream inputStream = new DeltaEncryptionInputStream(new FileInputStream(inFileName));
             OutputStream outputStream = new FileOutputStream(outFileName)) {
            BufferedReader.rewrite(inputStream, outputStream);
        }
    }

    public static void code() throws IOException {
        try (InputStream inputStream = new DeltaEncryptionInputStream(System.in)) {
            BufferedReader.rewrite(inputStream, System.out);
        }
    }

    public static void decode(String inFileName, String outFileName) throws IOException {
        try (InputStream inputStream = new FileInputStream(inFileName);
             OutputStream outputStream = new DeltaEncryptionOutputStream(new FileOutputStream(outFileName))) {
            BufferedReader.rewrite(inputStream, outputStream);
        }
    }

    public static void decode() throws IOException {
        try (OutputStream outputStream = new DeltaEncryptionOutputStream(System.out)) {
            BufferedReader.rewrite(System.in, outputStream);
        }
    }

    public static void main(String[] args) {
        Option encryptOption = new Option("e", "encrypt", true, "Encryption");
        Option decryptOption = new Option("d", "decrypt", true, "Decryption");
        Option outOption = new Option("out", true, "Output file");
        Option helpOption = new Option("h", "help", true, "Help");

        encryptOption.setArgs(1);
        decryptOption.setArgs(1);
        outOption.setArgs(1);

        encryptOption.setArgName("filename or stdin");
        decryptOption.setArgName("filename or stdin");
        outOption.setArgName("output filename");

        Options options = new Options();
        options.addOption(encryptOption);
        options.addOption(decryptOption);
        options.addOption(helpOption);
        options.addOption(outOption);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine commandLine = parser.parse(options, args);
            if (commandLine.hasOption("e")) {
                if (commandLine.getOptionValue("e").equals("stdin")) {
                    DeltaEncryption.code();
                } else {
                    if (commandLine.hasOption("out")) {
                        DeltaEncryption.code(commandLine.getOptionValue("e"), commandLine.getOptionValue("out"));
                    } else {
                        String inFileName = commandLine.getOptionValue("e");
                        String outFileName = inFileName.substring(0, inFileName.lastIndexOf(".")) + ".diffcode";
                        DeltaEncryption.code(inFileName, outFileName);
                    }
                }
            }
            if (commandLine.hasOption("d")) {
                if (commandLine.getOptionValue("d").equals("stdin")) {
                    DeltaEncryption.decode();
                } else {
                    if (commandLine.hasOption("out")) {
                        DeltaEncryption.decode(commandLine.getOptionValue("d"), commandLine.getOptionValue("out"));
                    } else {
                        String inFileName = commandLine.getOptionValue("d");
                        String outFileName = inFileName.substring(0, inFileName.lastIndexOf(".")) + ".diffdecode";
                        DeltaEncryption.code(inFileName, outFileName);
                    }
                }
            }

            if (commandLine.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("Delta encryption", options, true);
            }
        } catch (ParseException exp) {
            System.err.println("Parsing error: " + exp.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
