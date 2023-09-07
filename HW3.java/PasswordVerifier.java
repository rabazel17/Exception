package Exceptions.HW3;

import java.util.Scanner;

public class PasswordVerifier {
    public static void verifyPassword(String password) throws InvalidPasswordException {
        if (password.length() < 8) {
            throw new InvalidPasswordException("Пароль должен быть не менее 8 символов.");
        }

        boolean hasDigit = false;
        boolean hasUpperCase = false;

        for (char ch : password.toCharArray()) {
            if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (Character.isUpperCase(ch)) {
                hasUpperCase = true;
            }

            if (hasDigit && hasUpperCase) {
                return;
            }
        }

        throw new InvalidPasswordException("Пароль должен содержать хотя бы одну цифру и одну заглавную букву.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        try {
            verifyPassword(password);
            System.out.println("Пароль прошел проверку.");
        } catch (InvalidPasswordException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}