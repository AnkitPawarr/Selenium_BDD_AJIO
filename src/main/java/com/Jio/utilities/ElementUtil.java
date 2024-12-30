package com.Jio.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.Jio.factory.DriverFactory.log;
import static com.Jio.utilities.Constants.*;

public class ElementUtil {

    private WebDriver driver;
    private JavaScriptUtil jsUtil;

    public ElementUtil(WebDriver driver) {
        this.driver = driver;
        jsUtil = new JavaScriptUtil(driver);
    }

    private void nullCheck(String value) {
        if (value == null || value.isEmpty()) {
            log.error("VALUE IS NULL: " + value);
        }
    }

    public void doSendKeys(WebElement element, String value) {
        log.info("Entering value: '" + value + "' in the WebElement: '" + element + "'.");
        nullCheck(value);
        doClick(element);
        element.clear();
        element.sendKeys(value);
    }

    public void doSendKeys(WebElement element, String value, int timeOut) {
        log.info("Entering value: '" + value + "' in the WebElement: '" + element + "', after '" + timeOut + "' seconds.");
        nullCheck(value);
        doClick(element);
        waitForElementToBeVisible(element, timeOut).clear();
        waitForElementToBeVisible(element, timeOut).sendKeys(value);
    }

    public void doClick(WebElement element) {
        log.info("Clicking on WebElement: '" + element + "'.");
        element.click();
    }

    public void doClick(WebElement element, int timeOut) {
        log.info("Clicking on WebElement: '" + element + "', after '" + timeOut + "' seconds.");
        waitForElementToBeVisible(element, timeOut).click();
    }

    public String doGetText(WebElement element) {
        return element.getText();
    }

    public String doGetAttribute(WebElement element, String attrName) {
        log.info("Return me attribute: '" + attrName + "' of WebElement: '" + element + "'.");
        return element.getDomAttribute(attrName);
    }

    public boolean doIsDisplayed(WebElement element) {
        log.info("Check if the WebElement: '" + element + "' is Displayed.");
        try {
            boolean flag = element.isDisplayed();
            log.info("WebElement is displayed.");
            return flag;
        } catch (NoSuchElementException e) {
            log.error("WebElement: '" + element + "' is not displayed");
            return false;
        }
    }

    public boolean isElementDisplayed(List<WebElement> element) {
        log.info("Verifying if the WebElement: '" + element + "' is Displayed.");
        int elementCount = getElementsCount(element);
        if (elementCount == 1) {
            log.info("Single WebElement is displayed.");
            return true;
        } else if (elementCount == 0) {
            log.error("WebElement is not displayed");
            return false;
        } else {
            log.error("Multiple WebElements are displayed.");
            return false;
        }
    }

    public boolean isElementDisplayed(List<WebElement> element, int expectedElementCount) {
        log.info("Verifying if the WebElement: '" + element + "' is Displayed for expected occurence : " + expectedElementCount);
        int elementCount = getElementsCount(element);
        if (elementCount == expectedElementCount) {
            log.info("WebElement: '" + element + "' is displayed, with the occurrence of " + elementCount);
            return true;
        } else {
            log.error("Occurence of WebElement: '" + element + "' is '" + elementCount + "', Which is not as the expected occurence: " + expectedElementCount);
            return false;
        }
    }

    public List<WebElement> getElements(List<WebElement> elements) {
        return elements;
    }

    public int getElementsCount(List<WebElement> elements) {
        return elements.size();
    }

    public List<String> getElementsTextList(List<WebElement> elements) {
        log.info("Return all the Texts in a list for the WebElement: " + elements);
        List<String> eleTextList = new ArrayList<String>();// pc=0, size=0

        for (WebElement e : elements) {
            String text = doGetText(e);
            if (!text.isEmpty()) {
                eleTextList.add(text);
            }
        }
        return eleTextList;
    }

    public List<String> getElementAttributeList(List<WebElement> elements, String attrName) {
        log.info("Return all the Attribute values in a list for the WebElement: " + elements + "' and Attribute: " + attrName);
        List<String> attrList = new ArrayList<String>();

        for (WebElement e : elements) {
            String attrVal = doGetAttribute(e, attrName);
            if (attrVal != null && !attrVal.isEmpty()) {
                attrList.add(attrVal);
            }
        }
        return attrList;
    }

    // ********************** Select drop down utils **************//

    public void doSelectByIndex(WebElement element, int index) {
        log.info("Select WebElement: '" + element + "' by Index: " + index);
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public void doSelectByVisbleText(WebElement element, String visibleText) {
        log.info("Select WebElement: '" + element + "' by Visible text: " + visibleText);
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public void doSelectByValue(WebElement element, String value) {
        log.info("Select WebElement: '" + element + "' by Value: " + value);
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public int getDropDownOptionsCountUsingSelect(WebElement element) {
        log.info("Using Select, return the Options Count for WebElement: " + element);
        Select select = new Select(element);
        return select.getOptions().size();
    }

    public List<String> getDropDownOptionsTextListUsingSelect(WebElement element) {
        log.info("Using Select, return the options Text in list for WebElement: " + element);
        Select select = new Select(element);

        List<WebElement> optionsList = select.getOptions();
        List<String> optionsTextList = new ArrayList<String>();

        for (WebElement e : optionsList) {
            String text = doGetText(e);
            if (!text.isEmpty()) {
                optionsTextList.add(text);
            }
        }
        return optionsTextList;
    }

    public int getDropDownOptionsCount(List<WebElement> elements) {
        log.info("Return Options Count for WebElement: " + elements);
        return elements.size();
    }

    public List<String> getDropDownOptionsTextList(List<WebElement> elements) {
        log.info("Return the Options Text in list for WebElement: " + elements);
        List<String> optionsTextList = new ArrayList<>();
        for (WebElement e : elements) {
            String text = doGetText(e);
            if (!text.isEmpty()) {
                optionsTextList.add(text);
            }
        }

        return optionsTextList;
    }

    public void doClickOnValueInDropDown(List<WebElement> elements, String optionText) {
        log.info("Click on Option: '" + optionText + "' from a dropdown.");
        for (WebElement e : elements) {
            String text = e.getText();
            if (text.equalsIgnoreCase(optionText)) {
                doClick(e);
                break;
            }
        }
    }

    public void doClickOnOtherElement(List<WebElement> options, String optionText, List<WebElement> checkboxes) {
        log.info("Click on another WebElement: '" + checkboxes + "', for an Option having Text as: " + optionText);
        for (int i = 0; i < options.size(); i++) {
            String text = doGetText(options.get(i));
            if (text.equalsIgnoreCase(optionText)) {
                doClick(checkboxes.get(i));
                break;
            }
        }
    }

    public void doClickOnOtherElement(List<WebElement> options, String optionText, List<WebElement> subOptions, String subOptionText, List<WebElement> checkboxes) throws InterruptedException {
        log.info("Finding for a Product of Brand: '" + optionText + "', and item: '" + subOptionText);

        jsUtil.scrollPageDown("8000");
        Thread.sleep(1500);

        for (int i = 0; i < options.size(); i++) {
            String option = doGetText(options.get(i));
            String subOption = doGetText(subOptions.get(i));
            if (option.equalsIgnoreCase(optionText) && subOption.equalsIgnoreCase(subOptionText)) {
                handleParentSubMenu(options.get(i), checkboxes.get(i));
                log.info("Clicked on Product of Brand: '" + optionText + "', and item: '" + subOptionText);
                break;
            }
        }
        //log.error("No such Product found of Brand: '" + optionText + "', and item: '" + subOptionText);
    }

    public void doSearch(WebElement searchField, String searchValue, List<WebElement> suggestions, String value) throws InterruptedException {
        log.info("Enter value as: '" + searchValue + "', in the Search box: '" + searchField);
        doSendKeys(searchField, searchValue);
        Thread.sleep(3000);

        log.info("and now, from the Suggestions, do click on option having value as: " + value);
        List<WebElement> suggList = getElements(suggestions);

        for (WebElement e : suggList) {
            String text = doGetText(e);
            if (text.contains(value)) {
                doClick(e);
                break;
            }
        }
    }

    // *****************Actions utils********************//

    public void handleParentSubMenu(WebElement parentLocator, WebElement childLocator) throws InterruptedException {
        log.info("Using Actions, do click on Child sub-menu: '" + childLocator + "', from Parent menu: " + parentLocator);
        Actions act = new Actions(driver);
        act.moveToElement(parentLocator).perform();
        Thread.sleep(2000);

        doClick(childLocator, SHORT_WAIT);
    }

    public void handleParentSubMenu(List<WebElement> elements, String parentMenu, String childMenu) {
        log.info("Using Actions, do click on Child sub-menu: '" + childMenu + "', from Parent menu: " + parentMenu);
        WebElement p = getDynamicElementByTitle(elements, parentMenu);

        Actions act = new Actions(driver);
        act.moveToElement(p).perform();

        WebElement c = getDynamicElementByTitle(elements, childMenu);
        doClick(c, SHORT_WAIT);
    }

    public void doDragAndDrop(WebElement sourcelocator, WebElement targetLocator) {
        log.info("Using Actions, do Drag from Source: '" + sourcelocator + "' and Drop it to Target: " + targetLocator);
        Actions act = new Actions(driver);
        act.dragAndDrop(sourcelocator, targetLocator).perform();
    }

    public void doActionsSendKeys(WebElement element, String value) {
        log.info("Using Actions, do enter Value: " + value);
        Actions act = new Actions(driver);
        act.sendKeys(element, value).perform();
    }

    public void doActionsClick(WebElement element) {
        log.info("Using Actions, do Click on WebElement: " + element);
        Actions act = new Actions(driver);
        act.click(element).perform();
    }


    // *******************Wait Utils***************//

    public WebElement waitForElementToBeVisible(WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public List<WebElement> waitForVisibilityOfAllElements(List<WebElement> elements, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (Exception e) {
            return List.of();//return empty arraylist
        }
    }

    public WebElement waitForVisibilityOfModal(By locator, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    public void waitForElementToBeClickable(WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public String waitForTitleContains(String titleFraction, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        try {
            if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
                return driver.getTitle();
            }
        } catch (TimeoutException e) {
            System.out.println("title not found");
        }
        return driver.getTitle();
    }

    public String waitForTitleToBe(String titleVal, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        try {
            if (wait.until(ExpectedConditions.titleIs(titleVal))) {
                return driver.getTitle();
            }
        } catch (TimeoutException e) {
            log.error("Title not found in the given time: " + timeOut);
        }
        return driver.getTitle();
    }

    public String waitForURLContains(String urlFraction, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        try {
            if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
                return driver.getCurrentUrl();
            }
        } catch (TimeoutException e) {
            log.error("URL not found in the given time: " + timeOut);
        }
        return driver.getCurrentUrl();
    }

    public String waitForURLToBe(String urlValue, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        try {
            if (wait.until(ExpectedConditions.urlToBe(urlValue))) {
                return driver.getCurrentUrl();
            }
        } catch (TimeoutException e) {
            log.error("URL not found in the given time: " + timeOut);
        }
        return driver.getCurrentUrl();
    }

    public Alert waitForAlert(int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getAlertText(int timeOut) {
        return waitForAlert(timeOut).getText();
    }

    public void acceptAlert(int timeOut) {
        waitForAlert(timeOut).accept();
    }

    public void dismissAlert(int timeOut) {
        waitForAlert(timeOut).dismiss();
    }

    public void alertSendKeys(int timeOut, String value) {
        Alert alert = waitForAlert(timeOut);
        alert.sendKeys(value);
    }

    // *******************wait for iframes/frame*******************

    public void waitForFrameByElement(WebElement frame, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    public void waitForFrameByIndex(int frameIndex, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
    }

    public void waitForFrameByIndex(String frameIDOrName, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIDOrName));
    }

    public boolean waitForTotalWindowsToBe(int totalWindows, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        return wait.until(ExpectedConditions.numberOfWindowsToBe(totalWindows));
    }

    public void isPageLoaded(int timeOut, String page) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'")).toString(); // "true"

        if (Boolean.parseBoolean(flag)) {
            log.info("Page: '" + page + "' is completely loaded");
        } else {
            throw new RuntimeException("page is not loaded");
        }
    }

    // ******************* Child window handling *******************
    public String getParentWindow() {
        String parentWindow = driver.getWindowHandle();
        return parentWindow;
    }

    public void switchToParentWindow() {
        driver.switchTo().window(getParentWindow());
    }

    public List<String> getTitleOfChildWindows() {
        Set<String> windows = driver.getWindowHandles();
        List<String> allWindowsList = new ArrayList<String>(windows);
        List<String> titles = new ArrayList<>();

        for (String window : allWindowsList) {
            String title = driver.switchTo().window(window).getTitle();
            titles.add(title);
        }

        return titles;
    }

    public void switchToChildWindow() {
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();

        String parentWindow = it.next();
        String childWindow = it.next();

        driver.switchTo().window(childWindow);
    }

    // ******************* Miscellaneous *******************
    public Boolean modalToBe(WebElement headerText, String header) {
        String text = doGetText(headerText);
        if (text.equalsIgnoreCase(header)) {
            return true;
        }
        return false;
    }

    public Boolean ifErrorExist(List<WebElement> error) {
        if (isElementDisplayed(error)) {
            String text = doGetText(error.getFirst());
            return !text.isEmpty();
        }
        return false;
    }

    public WebElement getDynamicElementByTitle(List<WebElement> elements, String title) {
        log.info("Trying to find WebElement with Title as: '" + title);
        for (WebElement e : elements) {
            if (doGetText(e).equalsIgnoreCase(title)) {
                return e;
            }
        }
        throw new NoSuchElementException("Element with title '" + title + "' not found");
    }
}