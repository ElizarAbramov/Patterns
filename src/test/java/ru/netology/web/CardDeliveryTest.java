package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.domain.RegistrationInfo;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTest {


    RegistrationInfo info;

    @BeforeEach
    public void setUp() {
        info = DataGenerator.Registration.generateInfo("ru");
        open("http://localhost:9999/");
    }

    @Test
    public void shouldSuccessfullySendForm() {

        $("[data-test-id='city'] input").setValue("Кемерово");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(info.getFirstDate());
        $("[data-test-id='name'] input").setValue(info.getName());
        $("[data-test-id='phone'] input").setValue(info.getPhone());
        $("[class='checkbox__box']").click();
        $("[class='button__text']").click();
        $(Selectors.withText("Встреча успешно")).shouldHave(Condition.text(info.getFirstDate()), Duration.ofMillis(10000));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(info.getSecondDate());
        $("[class='button__text']").click();
        $(Selectors.withText("Перепланировать")).click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + info.getSecondDate()), Duration.ofSeconds(15));
    }
}