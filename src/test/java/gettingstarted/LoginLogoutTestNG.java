package gettingstarted;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Locale;
import java.util.regex.Pattern;

public class LoginLogoutTestNG {
    public static Browser browser = null;
    public static Page page = null;

    @BeforeTest
    public void setUp() {
//        browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//        browser = Playwright.create().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false);
        page = browser.newPage();
        page.navigate("https://freelance-learn-automation.vercel.app/login");
        PlaywrightAssertions.assertThat(page).hasTitle("Learn Automation Courses");
        Assert.assertTrue(page.title().contains("Learn Automation Courses"));
    }

    @Test
    public static void loginTest() {
        try {
//            browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
//            browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//            browser = Playwright.create().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false);
//            page = browser.newPage();


            page.locator("#email1").fill("admin@email.com");
            page.getByPlaceholder("Enter Password").fill("admin@123");
            page.locator(".submit-btn").click();
            PlaywrightAssertions.assertThat(page.locator(".welcomeMessage")).containsText("Welcome");
            page.getByAltText("menu").click();
            page.getByText("Sign out").last().click();
            PlaywrightAssertions.assertThat(page).hasURL(Pattern.compile("login"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public static void RegisterNewUser() {
        try {
//            Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//            Page page = browser.newPage();
//            page.navigate("https://freelance-learn-automation.vercel.app/login");
            page.getByText("New user? Signup").click();
//            page.pause();
            PlaywrightAssertions.assertThat(page.locator(".submit-btn")).isDisabled();
            Faker faker = new Faker(new Locale("en-IND"));
            page.locator("#name").fill(new Faker().name().fullName());
            page.getByPlaceholder("Email").fill(faker.name().firstName() + "_" + faker.name().lastName() + "@gmail.com");
            page.getByPlaceholder("Password").fill("Gopal@1234");
            page.locator("xpath=//*[text()='Java']").click();
            PlaywrightAssertions.assertThat(page.locator("xpath=//label[text()='Java']//preceding::input[1]")).isChecked();
            page.locator("xpath=//input[@value='Female']").click();
            PlaywrightAssertions.assertThat(page.locator("xpath=//input[@value='Female']")).isChecked();
            page.locator("#state").selectOption("Tamil Nadu");
            String[] hobbies = {"Playing", "Swimming"};
            page.locator("#hobbies").selectOption(hobbies);
            PlaywrightAssertions.assertThat(page.locator(".submit-btn")).isEnabled();
            page.locator(".submit-btn").click();
//        page.waitForTimeout(5000);
//            page.close();
//            browser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        page.close();
        browser.close();
    }
}
