package gettingstarted;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.util.regex.Pattern;

public class LoginLogout {
    public static void main(String[] args) {

        Browser browser = null;
        Page page = null;
        try {
//            browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            browser = Playwright.create().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//            browser = Playwright.create().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false);
            page = browser.newPage();
            page.navigate("https://freelance-learn-automation.vercel.app/login");
            PlaywrightAssertions.assertThat(page).hasTitle("Learn Automation Courses");


            page.locator("#email1").fill("admin@email.com");
            page.getByPlaceholder("Enter Password").fill("admin@123");
            page.locator(".submit-btn").click();
            PlaywrightAssertions.assertThat(page.locator(".welcomeMessage")).containsText("Welcome");

            page.getByAltText("menu").click();
            page.getByText("Sign out").last().click();

            PlaywrightAssertions.assertThat(page).hasURL(Pattern.compile("login"));


        } finally {
            page.close();
            browser.close();
        }


    }
}
