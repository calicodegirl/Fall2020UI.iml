package webtables;

import cucumber.api.java.ca.I;
import gherkin.lexer.Th;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Driver;

import java.util.List;

public class PracticeWebTables {
    WebDriver driver;

    @Before
    public void setUp() {
        driver = Driver.getDriver();
    }

    @After
    public void tearDown() {
        Driver.closeDriver();
    }

    @Test
    public void test1() {
        driver.navigate().to("https://chandanachaitanya.github.io/selenium-practice-site/?languages=Java&enterText=");
        //third record 1 row
        //1 - we will treat it as a static table
        //and we can hardcode the row where Dan Brown is displayed

        //bring the table into the view
        Actions actions = new Actions(driver);
        WebElement buttonToScrollTo = driver.findElement(By.id("win1"));
        actions.moveToElement(buttonToScrollTo).perform();

        WebElement table = driver.findElement(By.xpath("//table[@id='BooksAuthorsTable']"));

        //how to get the number of rows
        List<WebElement> rows = table.findElements(By.xpath(".//tr")); //will contain tr web elements from table
        int numberOfRows = rows.size();
        System.out.println("The number of rows in the table: " + numberOfRows);

        //how to get the number of columns
        List<WebElement> columns = table.findElements(By.xpath(".//tr/th"));
        int numberOfColumns = columns.size();
        System.out.println("The number of columns in the table: " + numberOfColumns);

        //print out all the books written by Dan Brown
        List<WebElement> DanBrownRow = table.findElements(By.xpath(".//tr[4]/td"));
        for (int i = 1; i < DanBrownRow.size(); i++) {
            System.out.println(DanBrownRow.get(i).getText());
        }
    }

    @Test
    public void test2() {
        driver.navigate().to("https://chandanachaitanya.github.io/selenium-practice-site/?languages=Java&enterText=");

        //bring the table into the view
        Actions actions = new Actions(driver);
        WebElement buttonToScrollTo = driver.findElement(By.id("win1"));
        actions.moveToElement(buttonToScrollTo).perform();
        WebElement table = driver.findElement(By.xpath("//table[@id='BooksAuthorsTable']"));

        //we will be working with dynamic table
        //1. we need to find out which column contains authors
        //2. we need to locate a row that stores Dan Brown books
        //we will print out the books
        List<WebElement> columnNames = table.findElements(By.xpath(".//th")); //Book1, Book2, ...., Author
        int indexOfAuthorColumn = 0;
        for (WebElement columnName : columnNames) {
            ++indexOfAuthorColumn;
            if (columnName.getText().contains("Author")) break;
        }

        List<WebElement> authors = table.findElements(By.xpath(".//tr/td[" + indexOfAuthorColumn + "]"));
        int indexOfDanBrownRow = 1;
        for (WebElement author : authors) {
            ++indexOfDanBrownRow;
            if (author.getText().trim().equals("Dan Brown")) break;
        }

        int numOfColumns = columnNames.size();
        String booksXpath = "//tr[" + indexOfDanBrownRow + "]/td";
        for (int i = 1; i <= numOfColumns; i++) {
            if (i == indexOfAuthorColumn) continue;
            WebElement book = table.findElement(By.xpath(booksXpath + "[" + i + "]"));
            System.out.println(book.getText());
        }
    }

    @Test
    public void test3() throws InterruptedException {
        //print all of the author names, but first find the correct column
        driver.navigate().to("https://chandanachaitanya.github.io/selenium-practice-site/?languages=Java&enterText=");

        //bring the table into the view
        Actions actions = new Actions(driver);
        WebElement buttonToScrollTo = driver.findElement(By.id("win1"));
        actions.moveToElement(buttonToScrollTo).perform();
        Thread.sleep(3000);
        // WebElement table = driver.findElement(By.xpath("//table[@id='BooksAuthorsTable']"));

        List<WebElement> columns = driver.findElements(By.xpath("//table[@id='BooksAuthorsTable']//th")); //Book1, Book2, ...., Author
        int authorsIndex = 0;
        for (WebElement columnName : columns) {
            authorsIndex++;
            if (columnName.getText().trim().equals("Author")) break;
        }
        List<WebElement> authors = driver.findElements(By.xpath("//table[@id='BooksAuthorsTable']//tr/td[" + authorsIndex + "]"));
        for (WebElement author : authors) {
            System.out.println(author.getText());
        }
    }
    @Test
    public void practiceTest(){
        driver.navigate().to("https://chandanachaitanya.github.io/selenium-practice-site/?languages=Java&enterText=");
        Actions actions = new Actions(driver);
        WebElement buttonToScroll = driver.findElement(By.id("win1"));
        actions.moveToElement(buttonToScroll).perform();

        WebElement table = driver.findElement(By.xpath("//table[@id='BooksAuthorsTable']"));
        List<WebElement> columnNames = table.findElements(By.xpath(".//th"));
        int columnOfAuthor=0;
        for (WebElement column: columnNames){
            columnOfAuthor++;
            if (column.getText().equals("Author"))break;
        }

        List<WebElement> authorNames = table.findElements(By.xpath(".//tr/td["+columnOfAuthor+"]"));
        for (WebElement author: authorNames){
            System.out.println(author.getText());
        }
    }

    @Test
    public void test4() {
        driver.navigate().to("https://www.w3schools.com/html/html_tables.asp");
        int countryColumn = 3;
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='customers']//tr"));
        int mexicoRow = 1; //pointing to headings row

        for (int i = 2; i <= rows.size(); i++) {
            mexicoRow++;
            WebElement countryCell = driver.findElement(By.xpath("//table[@id='customers']//tr[" + i + "]/td[3]"));
            if (countryCell.getText().trim().equals("Mexico")) break;
        }
        int companyColumn = 1;
        WebElement mexicoCompany = driver.findElement(By.xpath("//table[@id='customers']//tr[" + mexicoRow + "]/td[" + companyColumn + "]"));
        System.out.println(mexicoCompany.getText());
    }

    @Test
    public void practiceTest4(){
        driver.navigate().to("https://www.w3schools.com/html/html_tables.asp");
        //find country Mexico
        //print out Company from Mexico

        WebElement table = driver.findElement(By.xpath("//table[@id='customers']"));
        int indexOfCountry=0;
        List<WebElement> columns = table.findElements(By.xpath(".//th"));
        for (WebElement column: columns){
            indexOfCountry++;
            if (column.getText().trim().equals("Country"))break;
        }
        int row =1;
        List<WebElement> rows = table.findElements(By.xpath(".//tr"));
    }


}
