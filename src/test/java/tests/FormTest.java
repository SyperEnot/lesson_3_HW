package tests;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormTest {
    //Настройка
    @BeforeAll
    static void adjust() {
        Configuration.browserSize = "1920*1080";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
    }

    //Заполнение формы
    @Test
    void fillFields() {
        //url
        open("/automation-practice-form");

        //Убираем баннеры
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        //Name
        $("#firstName").setValue("Stas");
        $("#lastName").setValue("Erohov");

        //Email
        $("#userEmail").setValue("example@example.com");

        //Gender
        $("#genterWrapper").$(byText("Male")).click();

        //Mobile
        $("#userNumber").setValue("8901234567");

        //Date of Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").sendKeys("1993");
        $(".react-datepicker__month-select").sendKeys("October");
        $(".react-datepicker__day--007").click();
        $("#subjectsContainer input").click();

        //Subjects
        $("#subjectsContainer input").setValue("Arts").pressEnter();

        //Hobbies
        $("#hobbiesWrapper").$(byText("Reading")).click();

        //Picture
        $("#uploadPicture").uploadFromClasspath("Pika-pika.jpg");

        //Current Address
        $("#currentAddress").setValue("Saint-Petersburg, Parfenovsk. street, 7");

        //State and City
        $("#state input").sendKeys("NCR");
        $("#state input").pressEnter();
        $("#city input").sendKeys("Noida");
        $("#city input").pressEnter();
        $("#submit").click();

        //Проверка
        $(".modal-content").shouldHave (text("Thanks for submitting the form"));
        $(".modal-content").shouldHave (text("Stas Erohov"));
        $(".modal-content").shouldHave (text("example@example.com"));
        $(".modal-content").shouldHave (text("Male"));
        $(".modal-content").shouldHave (text("8901234567"));
        $(".modal-content").shouldHave (text("07 October,1993"));
        $(".modal-content").shouldHave (text("Arts"));
        $(".modal-content").shouldHave (text("Reading"));
        $(".modal-content").shouldHave (text("Pika-pika.jpg"));
        $(".modal-content").shouldHave (text("Saint-Petersburg, Parfenovsk. street, 7"));
        $(".modal-content").shouldHave (text("NCR Noida"));
    }
}
