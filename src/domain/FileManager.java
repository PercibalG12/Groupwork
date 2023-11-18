/*
//
Date: 10/16/2023
Author: Jordayne Price
//
This file pertains to Question 7. TAll committed changes made during the execution of the program, should be stored
and used to update the relevant files, when the application terminates.
//

*/

package domain;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
// FileManager class handles file operations such as reading, writing, updating and deleting records in a file
public class FileManager {
    // Logger to log information and errors
    private static final Logger logger = Logger.getLogger(FileManager.class.getName());
    // Method to check the status of a file
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
    // Method to write data to a file
    public static void writeToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to the file: " + e.getMessage(), e);
        }
    }
    // Method to check if a record exists in a file
    public static boolean doesRecordExist(String fileName, String searchString) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchString)) {
                    return true;
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + fileName, e);
        }
        return false;
    }
    // Method to search for a record in a file
    public static String searchForRecord(String fileName, String searchString) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchString)) {
                    return line;
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + fileName, e);
        }
        return null;
    }
    // Method to update a record in a file
    public static void updateRecord(String fileName, String searchString, String newData) {
        try {
            File inputFile = new File(fileName);
            File tempFile = new File("tempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // Check if the current line contains the search string
                if (currentLine.contains(searchString)) {
                    // Replace the existing record with the new data
                    writer.write(newData + System.getProperty("line.separator"));
                } else {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }

            writer.close();
            reader.close();

            // Delete the original file
            if (inputFile.delete()) {
                // Rename the temporary file to the original file name
                if (!tempFile.renameTo(inputFile)) {
                    logger.log(Level.SEVERE, "Error renaming the temporary file.");
                }
            } else {
                logger.log(Level.SEVERE, "Error deleting the original file.");
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error updating record in file: " + fileName, e);
        }
    }
          // Method to delete a record from a file
    public static void deleteRecord(String fileName, String searchString) {
        try {
            File inputFile = new File(fileName);
            File tempFile = new File("tempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // Check if the current line contains the search string
                if (!currentLine.contains(searchString)) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }

            writer.close();
            reader.close();

            // Delete the original file
            if (inputFile.delete()) {
                // Rename the temporary file to the original file name
                if (!tempFile.renameTo(inputFile)) {
                    logger.log(Level.SEVERE, "Error renaming the temporary file.");
                }
            } else {
                logger.log(Level.SEVERE, "Error deleting the original file.");
            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error deleting record from file: " + fileName, e);
        }
    }
          // Method to view all records in a file
    public static ArrayList<String> viewAllRecords(String fileName) {
        ArrayList<String> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + fileName, e);
        }

        return records;
    }



}
