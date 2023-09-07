package Exceptions.HW3;

import java.io.*;

public class FileManager {

    public static String readFile(String filePath) throws IOException, FileNotFoundException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    public static void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }

    public static void copyFile(String sourcePath, String destinationPath) throws IOException, FileNotFoundException {
        try (InputStream inputStream = new FileInputStream(sourcePath);
             OutputStream outputStream = new FileOutputStream(destinationPath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }

    public static void main(String[] args) {
        try {
            String content = readFile("input.txt");
            System.out.println("Содержимое файла:\n" + content);

            writeFile("output.txt", "Привет, мир!");

            copyFile("input.txt", "copy.txt");
            System.out.println("Файл успешно скопирован.");
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Произошла ошибка ввода-вывода: " + e.getMessage());
        }
    }
}