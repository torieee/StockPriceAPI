
package StockTickerFinal;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Victoria Thacker
 * This class is designed to allow the programmer to generate a customized 
 * stock ticker GUI for the VOO stock. The user will specify colors, fonts, and 
 * sizes of different aspects of the GUI. The Jsoup API is required for this 
 * program to run properly. 
 * 
 *  *Docs = Thacker_API_StockTicker_Documentation.docx
 */
public class API_StockTicker extends Application {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    //To be overridden in the demo class
    @Override
    public void start(Stage stage) throws IOException {
        
    }
   
    
    /**
     * showScene() creates a scene with a given height and weight. 
     * If the user enters a width or height less than 150, the window will
     * default to 500 x 500
     * 
     * @param pane BorderPane used in the StockTicker GUI
     * @param w Desired width of the GUI (must be greater than 150)
     * @param h Desired height of the GUI (must be greater than 150)
     * @return Returns a scene of the specified width and height
     */
    
    public Scene showScene(BorderPane pane, int w, int h)
    {
        int[] sizeArr = {w, h};
        
        if(w < 150 || h < 150)
        {
            System.err.println("Specifed height and weight too small. "
                    + "Default 500 x 500 size has been set.");
        }
        
        Scene scene = new Scene(pane, sizeArr[0], sizeArr[1]);
        return scene;
    }
    
    
    /**
     * backgroundColor() sets the color of the BorderPane to the user's preferred
     * hexadecimal color.
     * For String hexColor parameter, only use the hexadecimal color code 
     * (ex: #FFFFFF). 
     * 
     * @param pane BorderPane
     * @param hexColor CSS hexadecimal color (ex: "#FFFFFF")
     */
    public void backgroundColor(BorderPane pane, String hexColor)
    {
        pane.setStyle("-fx-background-color: " + hexColor);
    }
    
    
    /**
     * setTitleFont() allows the user to specify the font/color of the title 
     * text.
     * The font specifications will only apply to the title. Use setPriceFont()
     * to specify the font of the ticker price and opening price. This method 
     * will also add the title to the correct position in the GUI.
     * 
     * @param titlePane VBox used in the StockTicker GUI
     * @param font Font name (Arial, etc.)
     * @param size Desired size of the title font
     * @param c Desired color of the title font
     * @return Returns the Text of the Title with given font specifications 
     */
    
    public Text setTitleFont(VBox titlePane, String font, int size, Color c)
    {
        Text title = new Text("Vanguard S&P 500 ETF");
        title.setFont(Font.font(font, FontWeight.BOLD, size));
        titlePane.getChildren().add(title);
        title.setFill(c);
        
        return title;
    }
    
    /**
     * displayOpen() allows the user to specify the font of the opening price of
     * the stock.
     * The font specifications will only apply to the text displaying the 
     * opening price. This method will add the opening price to the correct 
     * position in the GUI.
     * 
     * @param titlePane VBox
     * @param font Font name
     * @param size Desired size of the title font
     * @param c Desired color of the title font
     * @return Returns the Text of the opening price with given font 
     * specifications 
     * @throws java.io.IOException 
     */
    
    public Text displayOpen(VBox titlePane, String font, int size, Color c) 
            throws IOException
    {
        StockReaderOpen open = SRO();
        Text openText = new Text("Open: $" + open.toString());
        openText.setFont(Font.font(font, FontWeight.MEDIUM, size));
        openText.setFill(c);
        titlePane.getChildren().add(openText);
        
        return openText;
    }
    
    /**
     * displayInitialQuote() displays the current quote of VOO.
     * There are no alterations the user can make to this method.
     * 
     * @param center StackPane
     * @return Returns Text of the current price of VOO. The color of the 
     * current price will either be green or red depending on if the current
     * price is higher or lower than the opening price, respectively.
     * @throws IOException 
     */
    
    public Text displayInitialQuote(StackPane center) throws IOException
    {
        Text initialQuote = quoteText();
        center.getChildren().add(initialQuote);
        
        return initialQuote;
    }
    
    
    /**
     * displayUpdateButton() displays the “Update Quote” button with the user’s
     * chosen color and adds functionality of the button. 
     * For String hexColor parameter, only use the hexadecimal color code 
     * (ex: #FFFFFF). 
     * 
     * @param pane BorderPane
     * @param center StackPane
     * @param hexColor CSS hexadecimal color (ex: "#FFFFFF")
     * @return  Returns the button with chosen color and predefined 
     * functionality.
     */
    
    public Button displayUpdateButton(BorderPane pane, StackPane center, 
            String hexColor)
    {
        Button refresh = new Button ("Update Quote");
        BorderPane.setAlignment(refresh, Pos.BOTTOM_CENTER);
        pane.setBottom(refresh);
        refresh.setStyle("-fx-background-color: " + hexColor);
        
        refresh.setOnAction((ActionEvent e) -> {
            try 
            {
                Text updatedQuote = quoteText(); 
                center.getChildren().clear(); //clears StackPane so quotes are not overlayed
                center.getChildren().add(updatedQuote);
            } 
            catch (IOException ex) 
            {
                System.err.println("Cannot find quote.");
            }
        });
        
        return refresh;
    }

    /*
    THE FOLLOWING ARE PRIVATE METHODS THAT CREATE THE FUNCTIONALITY OF THE GUI.
    THEY ARE NOT ACCESSIBLE TO THE USER OF THE GUI AND SHOULD NOT BE CHANGED.
    */
    
    //METHODS FOR SRO AND SRC OBJECTS (See below for SRO and SRC classes)
    private StockReaderClient SRC() throws IOException
    {
        StockReaderClient newQuote = new StockReaderClient();
        
        return newQuote;
    }
    
    private StockReaderOpen SRO() throws IOException
    {
        StockReaderOpen open = new StockReaderOpen();
        
        return open;
    }
    
    //METHOD FINDS DIFFERENCE BETWEEN CURRENT AND OPENING PRICE
    private double priceDifference() throws IOException
    {
        StockReaderClient current = SRC();
        StockReaderOpen open = SRO();
        
        double currentVal = Double.parseDouble(current.toString());
        double openVal = Double.parseDouble(open.toString());
        
        double difference = currentVal - openVal;
        
        return difference;
    }
    
    //METHOD RETURNS THE CURRENT PRICE (TEXT COLOR BASED ON PRICE DIFFERENCE)
    private Text quoteText () throws IOException
    {
        StockReaderClient quote = SRC();
        String cq = quote.currentQuote();
        System.out.println("Current Quote = " + cq);
        Text quoteText = new Text ("$" + cq);
        quoteText.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 50));
                
        //If current price is higher than open, green text | if lower, red text
        double difference = priceDifference();
        if(difference >= 0)
        {
            quoteText.setFill(Color.GREEN);
        }
        else
        {
            quoteText.setFill(Color.RED);
        }
        System.out.println("New quote");
        
        return quoteText;
    }
} //eo API CLASS


/*
THE FOLLOWING TWO CLASSES UTILIZE JSOUP TO SCRAPE THE CNBC WEBSITE TO FIND THE
CURRENT AND OPENING PRICES OF VOO. OBJECTS OF THESE CLASSES ARE CREATED AND 
USED IN METHODS OF THE API_StockTicker MAIN CLASS. 
*/

//Class uses Jsoup to scrape CNBC site to get the daily opening price for VOO 
class StockReaderOpen {
    
    private String open;
    
    public StockReaderOpen() throws IOException 
    {
        this.open = getOpen();
    }
    
    public static String getOpen() throws IOException
    {
        String open = "";
        Document doc = Jsoup.connect(
                "https://www.cnbc.com/quotes/voo").get();
        
        Elements divs = doc.select("div[class=Summary-subsection]");
        
        for (Element e : divs) 
        {
            open = e.getElementsByClass("Summary-value").text().substring(0, 6);
            System.out.println(open);
            break;
        }
        
        return open;
    }
    
    public String currentOpen()
    {
        return this.open;
    }
    
    @Override
    public String toString()
    {
        return open + "";
    }
} //eo SRO CLASS


//Class uses Jsoup to scrape CNBC site to get the current price of VOO 
class StockReaderClient {
    
     private String quote;
    
    public StockReaderClient () throws IOException
    {
        this.quote = getQuote();
    }
    
    public String getQuote() throws IOException 
    {
        String quote = "";
        Document doc = Jsoup.connect(
                "https://www.cnbc.com/quotes/voo").get();
        
        Elements divs = doc.select("div[class=QuoteStrip-lastPriceStripContainer]");
        
        for (Element e : divs) 
        {
            quote = e.getElementsByClass("QuoteStrip-lastPrice").text();
            System.out.println(quote);
        }
        
        return quote;
    }
    
    public String currentQuote() 
    {
        return this.quote;
    }
    
    @Override
    public String toString()
    {
        return quote + "";
    }
} //eo SRC CLASS


