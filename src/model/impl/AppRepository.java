package model.impl;

import exceptions.*;
import model.MyRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class AppRepository implements MyRepository {

    @Override
    public void checkSizeValue(String receivedValue) {
        int check = Integer.compare(receivedValue.split(" ").length, 6);
        switch (check) {
            case -1 -> throw new WrongSizeOfIncomeException("not enough data");
            case 1 -> throw new WrongSizeOfIncomeException("a lot of data");
        }
    }

    @Override
    public String[] getArrayForWriter(String[] valueArray) {
        checkName(valueArray);
        String secondName = valueArray[0];
        String name = valueArray[1];
        String surName = valueArray[2];
        LocalDate birthDay = getBirthDay(valueArray[3]);
        long phoneNumber = getPhone(valueArray[4]);
        char gender = getGender(valueArray[5]);
        String[] resultArray = new String[valueArray.length];
        resultArray[0] = secondName;
        resultArray[1] = name;
        resultArray[2] = surName;
        resultArray[3] = birthDay.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        resultArray[4] = String.valueOf(phoneNumber);
        resultArray[5] = String.valueOf(gender);
        return resultArray;
    }

    private void checkName(String[] array) throws RuntimeException {
        String result = "Wrong";
        boolean firstElem = Pattern.matches("[a-zA-Zа-яА-Я]+", array[0]);
        boolean secondElem = Pattern.matches("[a-zA-Zа-яА-Я]+", array[1]);
        boolean thirdElem = Pattern.matches("[a-zA-Zа-яА-Я]+", array[2]);
        if (!firstElem) result += " second name";
        if (!secondElem) result += " name";
        if (!thirdElem) result += " surname";
        if (!(firstElem && secondElem && thirdElem)) throw new WrongNameException(result);
    }

    private LocalDate getBirthDay(String value) throws WrongDateException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            return LocalDate.parse(value, dateFormatter);
        } catch (DateTimeParseException e) {
            throw new WrongDateException("Wrong date format");
        }
    }

    private long getPhone(String value) {
        if (value.length() < 11) throw new WrongPhoneNumberException("too small number");
        if (value.length() > 11) throw new WrongPhoneNumberException("too big number");
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new WrongPhoneNumberException("foreign characters in the number");
        }
    }

    private char getGender(String value) {
        if (value.charAt(0) != 'f' && value.charAt(0) != 'm' || value.length() > 1)
            throw new WrongGenderException("Wrong gender");
        return value.charAt(0);
    }

    @Override
    public void writeResultArray(String[] resultArray) {
        try (FileWriter writer = new FileWriter(resultArray[0], true)) {
            writer.write(resultArray[0] + " "
                    + resultArray[1] + " "
                    + resultArray[2] + " "
                    + resultArray[3] + " "
                    + resultArray[4] + " "
                    + resultArray[5] + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
