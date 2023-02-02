package ru.yandex.praktikum.model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageSamokat {
    private static final By NAME_INPUT = By.xpath(".//input[@placeholder='* Имя']");
    private static final By SURNAME_INPUT = By.xpath(".//input[@placeholder='* Фамилия']");
    private static final By ADDRESS_INPUT = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private static final By METRO_STATION_STATION = By.xpath(".//input[@placeholder='* Станция метро']");
    private static final By ENTERED_METRO_STATION = By.className("Order_Text__2broi");
    private static final By PHONE_NUMBER_INPUT = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private static final By NEXT_BUTTON = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private static final By DATE_INPUT = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private static final By RENTAL_PERIOD_INPUT = By.className("Dropdown-control");
    public static final By BLACK_COLOR = By.id("black");
    public static final By GREY_COLOR = By.id("grey");
    private static final By COMMENT_INPUT = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private static final By CREATE_ORDER_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private static final By YES_BUTTON = By.xpath(".//div[@class='Order_Modal__YZ-d3']/div[@class='Order_Buttons__1xGrp']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private static final By POPUP_ORDER_WINDOW = By.xpath(".//div[text()='Заказ оформлен']");
    private static final By ORDER_PAGE = By.className("App_App__15LM-");
    private final WebDriver driver;

    public OrderPageSamokat(WebDriver driver) {
        this.driver = driver;
    }

    public void fillName(String name) {
        driver.findElement(NAME_INPUT).sendKeys(name);
    }

    public void fillSurname(String surname) {
        driver.findElement(SURNAME_INPUT).sendKeys(surname);
    }

    public void fillAddress(String address) {
        driver.findElement(ADDRESS_INPUT).sendKeys(address);
    }

    public void fillMetroStation(String metroStation) {
        driver.findElement(METRO_STATION_STATION).sendKeys(metroStation);
        driver.findElement(ENTERED_METRO_STATION).click();
    }

    public void fillPhoneNumber(String phoneNumber) {
        driver.findElement(PHONE_NUMBER_INPUT).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ORDER_PAGE));
    }

    public OrderPageSamokat setDataFirstPageOrder(String name, String surname, String address, String metroStation, String phoneNumber) {
        fillName(name);
        fillSurname(surname);
        fillAddress(address);
        fillMetroStation(metroStation);
        fillPhoneNumber(phoneNumber);
        clickNextButton();
        return this;
    }

    public void fillDate(String date) {
        driver.findElement(DATE_INPUT).sendKeys(date, Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void chooseRentalPeriod(String rentalPeriod) {
        driver.findElement(RENTAL_PERIOD_INPUT).click();
        driver.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    public void chooseColor(By color) {
        driver.findElement(color).click();
    }

    public void writeComment(String comment) {
        driver.findElement(COMMENT_INPUT).sendKeys(comment);
    }

    public void clickCreateOrderButton() {
        driver.findElement(CREATE_ORDER_BUTTON).click();
    }

    public OrderPageSamokat setDataSecondPageOrder(String date, String rentalPeriod, By color, String comment) {
        fillDate(date);
        chooseRentalPeriod(rentalPeriod);
        chooseColor(color);
        writeComment(comment);
        clickCreateOrderButton();
        return this;
    }

    public void clickYesButton() {
        driver.findElement(YES_BUTTON).click();
    }

    public boolean isOrderWindowDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(POPUP_ORDER_WINDOW));
        return driver.findElement(POPUP_ORDER_WINDOW).isDisplayed();
    }
    public void getTextFromPopupOrderWindow() {
        System.out.println(driver.findElement(POPUP_ORDER_WINDOW).getText());
    }
}