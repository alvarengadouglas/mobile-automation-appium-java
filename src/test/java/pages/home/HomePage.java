package pages.home;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends HomePageElementMap {
    public HomePage(){
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }


    public void searchClick(){
        waitForElement(searchElement, 10);
        searchElement.click();
    }

    public void findInSearchBar(){
        waitForElement(insertTextElement, 10);
        insertTextElement.sendKeys("BrowserStack");
    }

    public boolean isListGratherThanOne(){
        waitForElement(itensList, 10);
        return !itensList.isEmpty();
    }
}
