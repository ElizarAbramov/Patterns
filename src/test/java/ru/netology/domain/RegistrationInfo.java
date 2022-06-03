package ru.netology.domain;

import lombok.Data;

@Data
public class RegistrationInfo {
    private final String city;
    private final String name;
    private final String phone;
    private final String firstDate;
    private final String secondDate;

}