package Exceptions.HW3;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

class UserDataFormatException extends Exception {
    UserDataFormatException(String message) {
        super(message);
    }
}

public class UserDataApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите данные: Фамилия Имя Отчество Дата_рождения Номер_телефона Пол");
            String input = scanner.nextLine();
            String[] userData = input.split("\\s+");

            if (userData.length != 6) {
                throw new UserDataFormatException("Неверное количество данных. Требуется 6 параметров.");
            }

            String lastName = userData[0];
            String firstName = userData[1];
            String middleName = userData[2];
            String birthDate = userData[3];
            String phoneNumber = userData[4];
            String gender = userData[5];

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            dateFormat.setLenient(false);
            try {
                if (!birthDate.matches("\\d{2}.\\d{2}.\\d{4}") || !birthDate.equals(dateFormat.format(dateFormat.parse(birthDate)))) {
                    throw new UserDataFormatException("Неверный формат даты рождения.");
                }
            } catch (ParseException e) {
                throw new UserDataFormatException("Неверный формат даты рождения.");
            }

            if (!Pattern.matches("\\d+", phoneNumber)) {
                throw new UserDataFormatException("Неверный формат номера телефона.");
            }

            if (!gender.equals("f") && !gender.equals("m")) {
                throw new UserDataFormatException("Неверный формат пола.");
            }

            String fileName = lastName + ".txt";
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write(lastName + " " + firstName + " " + middleName + " " + birthDate + " " +
                    phoneNumber + " " + gender + "\n");
            fileWriter.close();

            System.out.println("Данные успешно записаны в файл " + fileName);
        } catch (UserDataFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при записи данных в файл: " + e.getMessage());
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }
}