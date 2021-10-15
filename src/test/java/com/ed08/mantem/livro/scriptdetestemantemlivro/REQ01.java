package com.ed08.mantem.livro.scriptdetestemantemlivro;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class REQ01 {

    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://ts-scel-web.herokuapp.com/login");
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void ct01cadastrarLivroComSucesso() {
        //dado que o livro não está cadastrado
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("jose");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
        driver.findElement(By.linkText("Livros")).click();
        //Quando um usuário cadastrar um livro
        driver.findElement(By.id("isbn")).click();
        driver.findElement(By.id("isbn")).sendKeys("1245");
        driver.findElement(By.id("autor")).click();
        driver.findElement(By.id("autor")).sendKeys("Tenembaum");
        driver.findElement(By.id("titulo")).click();
        driver.findElement(By.id("titulo")).sendKeys("Sistemas Operacionais Modernos");
        driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
        //então será redirecionado para tela de consulta todos
        assertEquals(driver.findElement(By.id("paginaConsulta")).getText(), ("Lista de livros"));
    }

    @Test
    public void ct02alterarAutorETituloComSucesso() {

        //Dado que o usuário deseja editar o título e autor de um livro já cadastrado
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("jose");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
        driver.findElement(By.linkText("Livros")).click();
        //Quando o usuário editar o título e autor do livro
        driver.findElement(By.linkText("Lista de Livros")).click();
        driver.findElement(By.linkText("Editar")).click();
        driver.findElement(By.cssSelector("body")).click();
        driver.findElement(By.id("autor")).clear();
        driver.findElement(By.id("autor")).sendKeys("Presman");
        driver.findElement(By.cssSelector("body")).click();
        driver.findElement(By.id("titulo")).click();
        driver.findElement(By.id("titulo")).clear();
        driver.findElement(By.id("titulo")).sendKeys("Engenharia");
        driver.findElement(By.cssSelector(".btn")).click();
        //Então o o usuário é redirecionado para lista de livros e o título e autor do livro devem estar alterados
        assertEquals(driver.findElement(By.cssSelector("td:nth-child(3)")).getText(), ("Engenharia"));
        assertEquals(driver.findElement(By.cssSelector("td:nth-child(4)")).getText(), ("Presman"));
    }

    @Test
    public void ct03deletarLivroComSucesso() {
        //Dado que o livro está cadastrado
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("jose");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
        //Quando o usuário acessar a lista de livros e deletar um livro
        driver.findElement(By.linkText("Livros")).click();
        driver.findElement(By.linkText("Lista de Livros")).click();
        vars.put("isbn", driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")).getText());
        driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")).toString();
        driver.findElement(By.linkText("Excluir")).click();
        //Então o livro deve ser deletado com sucesso
        String line = driver.findElement(By.cssSelector("tbody")).getText();
        if (!Objects.equals(line, "")) {
            Assertions.assertNotEquals(vars.get("isbn"), driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")));
        }

    }
}
