package gettingstarted;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.util.Locale;

public class RegsiterNewUser {
    public static void main(String[] args) {
        try {
            Browser browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://freelance-learn-automation.vercel.app/login");
            page.getByText("New user? Signup").click();
            page.pause();
            PlaywrightAssertions.assertThat(page.locator(".submit-btn")).isDisabled();
            Faker faker = new Faker(new Locale("en-IND"));
            page.locator("#name").fill(new Faker().name().fullName());
            page.getByPlaceholder("Email").fill(faker.name().firstName() + "_" + faker.name().lastName() + "@gmail.com");
            page.getByPlaceholder("Password").fill("Gopal@1234");
            page.getByText("AWS").click();
            PlaywrightAssertions.assertThat(page.locator("xpath=//label[text()='AWS']//preceding::input[1]")).isChecked();
            page.locator("xpath=//input[@value='Female']").click();
            PlaywrightAssertions.assertThat(page.locator("xpath=//input[@value='Female']")).isChecked();
            page.locator("#state").selectOption("Tamil Nadu");
            String[] hobbies = {"Playing", "Swimming"};
            page.locator("#hobbies").selectOption(hobbies);
            PlaywrightAssertions.assertThat(page.locator(".submit-btn")).isEnabled();
            page.locator(".submit-btn").click();
//        page.waitForTimeout(5000);

            page.close();
            browser.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }


    }
}
