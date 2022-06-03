package ru.netology.web;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import ru.netology.domain.AdminCenter;
import ru.netology.domain.RegistrationInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DataGenerator {

    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generateInfo(String locale) {
            AdminCenter adminCenter = new AdminCenter();
            int randomCity = (int) (Math.random() * adminCenter.getCities().length);
            Faker faker = new Faker(new Locale(locale));
            String date = (LocalDate.now().plusDays(faker.number().numberBetween(3, 9))).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            return new RegistrationInfo(adminCenter.getCities()[randomCity], faker.name().fullName(), faker.phoneNumber().phoneNumber(), date, date);

        }
    }
}