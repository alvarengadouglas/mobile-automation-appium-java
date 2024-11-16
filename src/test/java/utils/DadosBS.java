package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.JavascriptExecutor;

import static utils.Utils.driver;

public interface DadosBS {
    //Cria um log de informacoes no Browser Stack
    default void checkInfo(String message) {
        JavascriptExecutor jse = driver;
        jse.executeScript("browserstack_executor: {\"action\": \"annotate\", \"arguments\": {\"data\":\"" + message + "\", \"level\": \"info\"}}");
    }

    //Cria um log pass no Browser Stack
    default void checkPass(String message) {
        JavascriptExecutor jse = driver;
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"" + message + "\"}}");
    }

    //Cria um log de erro no Browser Stack, não falha o teste
    default void checkError(String message) {
        JavascriptExecutor jse = driver;
        jse.executeScript("browserstack_executor: {\"action\": \"annotate\", \"arguments\": {\"data\":\"" + message + "\", \"level\": \"error\"}}");
    }

    //Cria um log de erro no Browser Stack e marca o teste como falhado no Browser Stack
    default void checkFail(String message) {
        JavascriptExecutor jse = driver;
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"" + message + "\"}}");
    }

    // Printa o Build ID e Session ID que esta rodando no Browser Stack
    default void printBuildAndSession() {
        JavascriptExecutor jse = driver;
        Object response = jse.executeScript("browserstack_executor: {\"action\": \"getSessionDetails\"}");
        JsonObject jsonResponse = (JsonObject) new JsonParser().parse((String) response);
        String sessionID = jsonResponse.get("hashed_id").getAsString();
        String buildID = jsonResponse.get("build_hashed_id").getAsString();
        System.out.println("sessionID =>" + sessionID);
        System.out.println("buildID =>" + buildID);
        //System.out.println(response);
    }
}
