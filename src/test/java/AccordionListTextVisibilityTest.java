import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.model.MainPageSamokat;

import static ru.yandex.praktikum.model.MainPageSamokat.*;

@RunWith(Parameterized.class)
public class AccordionListTextVisibilityTest {

    private final By accordionButtonHeading;
    private final By accordionButtonPanel;
    private final String accordionTextExpected;
    private WebDriver driver;

    public AccordionListTextVisibilityTest(By accordionButtonHeading, String accordionTextExpected, By accordionButtonPanel) {
        this.accordionButtonHeading = accordionButtonHeading;
        this.accordionTextExpected = accordionTextExpected;
        this.accordionButtonPanel = accordionButtonPanel;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {ACCORDION_HEADING_0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", ACCORDION_PANEL_0},
                {ACCORDION_HEADING_1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", ACCORDION_PANEL_1},
                {ACCORDION_HEADING_2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", ACCORDION_PANEL_2},
                {ACCORDION_HEADING_3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", ACCORDION_PANEL_3},
                {ACCORDION_HEADING_4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", ACCORDION_PANEL_4},
                {ACCORDION_HEADING_5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", ACCORDION_PANEL_5},
                {ACCORDION_HEADING_6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", ACCORDION_PANEL_6},
                {ACCORDION_HEADING_7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.", ACCORDION_PANEL_7},
        };
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
// Для тестирования в браузере FireFox Mozilla раскомментируй строки подключения FirefoxDriver, закомментируй подключение ChromeDriver
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();
    }

    @Test
    public void checkAccordionListTest() {
        MainPageSamokat mainPage = new MainPageSamokat(driver)
                .open()
                .scrollToAccordionList()
                .clickAccordionButton(accordionButtonHeading)
                .isTextFromAccordionDisplayedAfterClick(accordionButtonPanel);
        String accordionTextActual = mainPage.getTextFromAccordionAfterClick(accordionButtonPanel);
        Assert.assertEquals("Текст в выпадающем списке отличается от ожидаемого", accordionTextExpected, accordionTextActual);
        System.out.println("Тест пройден");
    }

    @After
    public void teardown() {
        driver.quit();
    }

}