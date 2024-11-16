package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;


public interface Interacoes extends DadosBS {

    public static AppiumDriver driver = Utils.getDriver();

    /**
     * Aguarda presença do elemento By metodo de selecao(xpath, css, ...) estar presente na tela por quantidade de segundos informados por parametro
     *
     * @param  by
     * @param  timeOutInSeconds
     * @return RemoteWebElement
     */
    default boolean waitForElement(By by, int timeOutInSeconds) {
        RemoteWebElement remoteWebElement;
        try {
            Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(driver).withTimeout(Duration.ofSeconds(Long.valueOf(timeOutInSeconds)))
                    .pollingEvery(Duration.ofMillis(200))
                    .ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                    .ignoring(TimeoutException.class);
            remoteWebElement = (RemoteWebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            return false;
        }
        return remoteWebElement != null;
    }

    /**
     * Aguarda presença do elemento informado por parametro estar presente na tela pela quantidade de segundos informada por parametro
     *
     * @param  element
     * @param  timeOutInSeconds
     * @return boolean element != null
     */
    default boolean waitForElement(RemoteWebElement element, int timeOutInSeconds) {
        RemoteWebElement remoteWebElement;
        try {
            Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(driver).withTimeout(Duration.ofSeconds(Long.valueOf(timeOutInSeconds)))
                    .pollingEvery(Duration.ofMillis(200))
                    .ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                    .ignoring(TimeoutException.class);
            remoteWebElement = (RemoteWebElement) wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            return false;
        }
        return remoteWebElement != null;
    }


    /**
     * Aguarda presença do elemento informado por parametro estar presente na tela pela quantidade de segundos informada por parametro
     *
     * @param  element
     * @param  timeOutInSeconds
     * @return boolean element != null
     */
    default boolean waitForElement(List<WebElement> element, int timeOutInSeconds) {
        RemoteWebElement remoteWebElement;
        try {
            Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(driver).withTimeout(Duration.ofSeconds(Long.valueOf(timeOutInSeconds)))
                    .pollingEvery(Duration.ofMillis(200))
                    .ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
                    .ignoring(TimeoutException.class);
            remoteWebElement = (RemoteWebElement) wait.until(ExpectedConditions.visibilityOf(element.get(0)));
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            return false;
        }
        return remoteWebElement != null;
    }

    /**
     * Faz com que a thread atual durma por quantidade de milisegundos do parametro
     *
     * @param timeOutInMilliseconds
     */
    default void waitMilliseconds(int timeOutInMilliseconds) {
        try {
            Thread.sleep(timeOutInMilliseconds);
        } catch (InterruptedException e) {
            checkError("Erro ao executar wait(int milliseconds)");
        }
    }

    /**
     * Faz com que a thread atual durma por quantidade de segundos do parametro
     *
     * @param timeOutInSeconds
     */
    default void waitSeconds(int timeOutInSeconds) {
        try {
            Thread.sleep(timeOutInSeconds * 1000);
        } catch (InterruptedException e) {
            checkError("Erro ao executar wait(int seconds)");
        }
    }

    /**
     * Aguarda, dentro da tempo informado, que elemento informado por parametro possua o valor informado por parametro apos input
     *
     * @param  element
     * @param  text
     * @param  timeOutInSeconds
     * @return boolean
     */
    default boolean waitUntilElementHasValue(RemoteWebElement element, String text, int timeOutInSeconds) {
        boolean isElementHasText = false;
        try {
            waitMilliseconds(500);
            Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(driver).withTimeout(Duration.ofSeconds(Long.valueOf(timeOutInSeconds)))
                    .pollingEvery(Duration.ofMillis(1)).ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class);
            isElementHasText = wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
            return isElementHasText;
        } catch (Exception e) {
            return isElementHasText;
        }
    }

    /**
     * Verifica se o elemento obtido por metodo de selecao (xpath, css, ...) informado por parametro esta presente no driver
     * Se esta presente, verifica se o elemento eh exibido na tela
     *
     * @param by By
     * @return boolean isPresent && isDisplayed
     */
    default boolean isElementDisplayed(By by) {
        boolean isElementPresent = false;
        boolean isElementDisplayed = false;
        try {
            isElementPresent = !driver.findElements(by).isEmpty();
            if (isElementPresent) {
                isElementDisplayed = driver.findElement(by).isDisplayed();
            }
            return isElementPresent && isElementDisplayed;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *
     * Utiliza o botão voltar nativo do telefone para voltar para a tela anterior
     *
     */
    default void back(){
        driver.navigate().back();
    }
}
