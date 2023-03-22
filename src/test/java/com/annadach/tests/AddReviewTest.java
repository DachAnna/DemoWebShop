package com.annadach.tests;

import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import com.annadach.tests.config.CredentialsConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class AddReviewTest extends TestBase {

    public static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);

    @Test
    @DisplayName("Successful add review (API + UI)")
    void addReview() {
        step("Get cookie by api and set it to browser", () -> {
            String authorizationCookie =
                    given()
                            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                            .formParam("Email", config.mail())
                            .formParam("Password", config.password())
                            .when()
                            .post("https://demowebshop.tricentis.com/login")
                            .then()
                            .statusCode(302)
                            .extract()
                            .cookie("NOPCOMMERCE.AUTH");

            open("/Themes/DefaultClean/Content/images/logo.png");

            getWebDriver().manage().addCookie(
                    new Cookie("NOPCOMMERCE.AUTH", authorizationCookie));

            step("Check added review", () -> {
                Response response =
                        given()
                                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                                .body("AddProductReview.Title=" + config.title() + "&" +
                                        "AddProductReview.ReviewText=" + config.reviewText() + "&" +
                                        "AddProductReview.Rating=5&" +
                                        "add-review=Submit+review")
                                .cookie("NOPCOMMERCE.AUTH", authorizationCookie)
                                .when()
                                .post("https://demowebshop.tricentis.com/productreviews/31")
                                .then()
                                .statusCode(200)
                                .extract().response();

                open("https://demowebshop.tricentis.com/productreviews/31");
                $$(".product-review-item").last()
                        .shouldHave(text(config.title()))
                        .shouldHave(text(config.reviewText()));
            });
        });
    }
}
