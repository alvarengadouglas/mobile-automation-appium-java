package pages.home;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import utils.BasePageAppium;
import java.util.List;

public class HomePageElementMap extends BasePageAppium {

    @AndroidFindBy(accessibility = "Search Wikipedia")
    protected RemoteWebElement searchElement;


    @AndroidFindBy(id = "org.wikipedia.alpha:id/search_src_text")
    protected RemoteWebElement insertTextElement;

    @AndroidFindBy(className = "android.widget.TextView")
    List<WebElement> itensList;
}
