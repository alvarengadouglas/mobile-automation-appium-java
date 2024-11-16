package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import pages.home.HomePage;

import static utils.Utils.*;

public class HomeSteps {
    HomePage hp;

    @Dado("acesso o aplicativo no dispositivo {int}")
    public void acesso_o_aplicativo_no_dispositivo(Integer int1) {
        acessarCloudApp(String.valueOf(int1));
    }

    @Quando("realizo o filtro")
    public void realizo_o_filtro() {
        hp = new HomePage();
        hp.searchClick();
        hp.findInSearchBar();
    }

    @Entao("os resultados sao exibidos")
    public void os_resultados_sao_exibidos() {
        Assert.assertTrue("Validando que a lista de pesquisa retornou algum resultado", hp.isListGratherThanOne());
    }
}
