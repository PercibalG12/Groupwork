import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class FileManager {
    public static void writeToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    public static String readFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
            return fileContent.toString();
        } catch (IOException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
        }
        return null;
    }

    public static boolean isCodeUniqueInFile(String code, String fileName) {
        String data = readFromFile(fileName);

        if (data == null) {
            return true; // Handle the case where data is null (e.g., file read error).
        }

        String[] records = data.split("\n");

        for (int i = 0; i < records.length; i += 4) {
            String existingCode = records[i];
            if (existingCode.equals(code)) {
                return false; // Code already exists
            }
        }

        return true; // Code is unique
    }

    public static String findRecordByCode(String code, String fileName) {
        String data = readFromFile(fileName);

        if (data == null) {
            return null; // Handle the case where data is null (e.g., file read error).
        }

        String[] records = data.split("\n");

        for (int i = 0; i < records.length; i += 4) {
            if (records[i].equals(code)) {
                // Return the matching record
                return String.join("\n", Arrays.copyOfRange(records, i, i + 4));
            }
        }

        // Return null if record not found
        return null;
    }

    public static boolean updateRecord(String fileName, String targetCode, String updatedData) {
        String data = readFromFile(fileName);

        if (data == null) {
            return false; // Handle the case where data is null (e.g., file read error).
        }

        String[] records = data.split("\n");

        boolean updated = false;

        for (int i = 0; i < records.length; i += 4) {
            if (records[i].equals(targetCode)) {
                records[i] = updatedData;
                updated = true;
                break;
            }
        }

        if (updated) {
            // Update the file with the updated records
            String updatedFileData = String.join("\n", records);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(updatedFileData);
            } catch (IOException e) {
                System.err.println("Error updating the file: " + e.getMessage());
                return false;
            }
            return true;
        } else {
            return false; // Record not found
        }
    }
}