package ru.yandex.praktikum.model;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageSamokat {
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final By ACCORDION_LIST = By.className("Home_FAQ__3uVm4");
    private static final By COOKIE_BUTTON = By.id("rcc-confirm-button");
    private final WebDriver driver;

    public MainPageSamokat(WebDriver driver) {
        this.driver = driver;
    }

    public MainPageSamokat open() {
        driver.get(PAGE_URL);
        driver.manage().window().maximize();
        driver.findElement(COOKIE_BUTTON).click();
        return this;
    }

    public MainPageSamokat scrollToAccordionList() {
        WebElement element = driver.findElement(ACCORDION_LIST);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    public MainPageSamokat clickAccordionButton(By accordionButtonHeading) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(accordionButtonHeading));
        driver.findElement(accordionButtonHeading).click();
        return this;
    }

    public MainPageSamokat isTextFromAccordionDisplayedAfterClick(By accordionButtonPanel) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(accordionButtonPanel));
        boolean isDisplayed = driver.findElement(accordionButtonPanel).isDisplayed();
        Assert.assertTrue(isDisplayed);
        return this;
    }

    public String getTextFromAccordionAfterClick(By accordionButtonPanel) {
        return driver.findElement(accordionButtonPanel).getText();
    }

    public MainPageSamokat scrollToOrderButton(By orderButton) {
        WebElement element = driver.findElement(orderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    public void clickOrderButton(By orderButton) {
        driver.findElement(orderButton).click();
    }
}
