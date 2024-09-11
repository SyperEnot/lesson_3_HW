package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenoidRepositorySearch {
        @BeforeAll
        static void beforeAll(){
            Configuration.browserSize = "1920x1080";
            Configuration.pageLoadStrategy = "eager";
            Configuration.baseUrl = "https://github.com";
            Configuration.holdBrowserOpen = true;
        }

        @Test
        void shouldFindCode(){
            open("/selenide/selenide");
            $("#wiki-tab").click();
            $(".wiki-rightbar ul li button").click();
            $(".wiki-rightbar ul").shouldHave(text("SoftAssertions"));
            $(byText("SoftAssertions")).click();
            $("#user-content-3-using-junit5-extend-test-class").sibling(0).shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                    "class Tests {\n" +
                    "  @Test\n" +
                    " void test() {\n" +
                    "    Configuration.assertionMode = SOFT;\n" +
                    "    open(\"page.html\");\n" +
                    "\n" +
                    "    $(\"#first\").should(visible).click();\n" +
                    "    $(\"#second\").should(visible).click();\n" +
                    "  }\n" +
                    "}" ));
        }
    }
