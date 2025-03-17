package gettingstarted;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FirstPlayWrightTest {
    public static void main(String[] args) {
        Playwright pw = Playwright.create();
        BrowserType browseType = pw.chromium();
//        BrowserType browseType = pw.webkit();
//        default PlayWright will be in Headless mode.
        Browser browser = browseType.launch(new BrowserType.LaunchOptions().setHeadless(false));
        browseType.launch(new BrowserType.LaunchOptions().setChannel("chrome"));
        Page page = browser.newPage();
        page.navigate("https://playwright.dev/java/docs/intro");
        String title = page.title();
        System.out.println(title);
        page.close();
        browser.close();
        pw.close();

    }
}
