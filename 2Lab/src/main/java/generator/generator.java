package generator;

import org.apache.commons.cli.*;

import java.io.*;
import java.util.Random;

/**
 * Created by ThinkPad on 28.10.2016.
 */
public class Generator {
    private static void generateGaussSequence(double mean, double variance, int size, String outFileName) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(outFileName)) {
            Random rand = new Random();

            for (int i = 0; i < size; i++) {
                double tmpRes = rand.nextGaussian() * Math.sqrt(variance) + mean;
                int res = Math.abs((int) (10 * tmpRes));
                outputStream.write(res);
            }
        }
    }

    private static void generateGaussSequence(double mean, double variance, int size) throws IOException {
        try (OutputStream outputStream = System.out) {
            Random rand = new Random();

            for (int i = 0; i < size; i++) {
                double tmpRes = rand.nextGaussian() * Math.sqrt(variance) + mean;
                int res = Math.abs((int) (10 * tmpRes));
                //outputStream.write(res);
                System.out.println(res + " ");
            }
        }
    }

    public static void main(String[] args) {
        Option meanOption = new Option("m", "mean", true, "Mean");
        Option varOption = new Option("v", "variance", true, "variance");
        Option sizeOption = new Option("s", "size", true, "Size");
        Option outOption = new Option("out", true, "Output file");
        Option helpOption = new Option("h", "help", true, "Help");

        meanOption.setArgs(1);
        varOption.setArgs(1);
        sizeOption.setArgs(1);

        meanOption.setArgName("value");
        varOption.setArgName("value");
        sizeOption.setArgName("value");

        Options options = new Options();
        options.addOption(meanOption);
        options.addOption(varOption);
        options.addOption(sizeOption);
        options.addOption(helpOption);
        options.addOption(outOption);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine commandLine = parser.parse(options, args);
            if (commandLine.hasOption("m") && commandLine.hasOption("v") && commandLine.hasOption("s")) {
                double mean = Double.parseDouble(commandLine.getOptionValue("m"));
                double var = Double.parseDouble(commandLine.getOptionValue("v"));
                int size = Integer.parseInt(commandLine.getOptionValue("s"));

                if (commandLine.hasOption("out")) {
                    Generator.generateGaussSequence(mean, var, size, commandLine.getOptionValue("out"));
                } else {
                    Generator.generateGaussSequence(mean, var, size);
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
