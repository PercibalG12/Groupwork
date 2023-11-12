import java.io.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {

    private static final Logger logger = Logger.getLogger(FileManager.class.getName());

    public static void fileStatus(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                logger.info("File created: " + file.getName());
            } else {
                logger.info("The file exists");
            }

            if (file.canRead()) {
                logger.info("File is readable");
            } else {
                logger.info("File is not readable");
            }

            if (file.canWrite()) {
                logger.info("File is writable");
            } else {
                logger.info("File is not writable");
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
        }
    }

    public static void writeToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to the file: " + e.getMessage(), e);
        }
    }

    public static boolean searchFile(String fileName, String searchString) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(searchString)) {
                    return true;
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + fileName, e);
        }
        return false;
    }

    public static void updateDepartmentRecordInFile(String fileName, Department updatedDepartment) {
        try {
            File inputFile = new File(fileName);
            File tempFile = new File("temp.txt");

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    // Check if the line contains the department code to be updated
                    if (line.contains(updatedDepartment.getDepartmentCode())) {
                        // Replace the line with the updated information
                        writer.write(updatedDepartment.serializeToString());
                        writer.newLine();
                    } else {
                        // Copy unchanged lines to the temporary file
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }

            // Rename the temporary file to the original file
            if (tempFile.renameTo(inputFile)) {
                System.out.println("File updated successfully.");
            } else {
                System.out.println("Error updating file.");
            }

        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

}