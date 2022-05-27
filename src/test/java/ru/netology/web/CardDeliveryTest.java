package ru.netology.web;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.domain.RegistrationInfo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {


    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void shouldSuccessfullySendForm() {
        String formattedString = generateDate(4);
        String reformattedString = generateDate(5);
        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(formattedString);
        $("[data-test-id='name'] input").setValue(DataGenerator.Registration.generateInfo("ru").getName());
        $("[data-test-id='phone'] input").setValue(DataGenerator.Registration.generateInfo("ru").getPhone());
        $("[class='checkbox__box']").click();
        $("[class='button__text']").click();
        $(Selectors.withText("Встреча успешно")).shouldBe(hidden, Duration.ofMillis(10000));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(reformattedString);
        $("[class='button__text']").click();
        $(Selectors.withText("Перепланировать")).click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + reformattedString), Duration.ofSeconds(15));
    }
}