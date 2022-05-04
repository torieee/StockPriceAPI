
package StockTickerFinal;

import java.io.IOException;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * @author Victoria Thacker
 * 
 * Lines for programmer to change are labeled with TODO:
 * Review API instructions - API_StockTicker.java - for how to use these methods
 */

public class StockTickerTemplate extends API_StockTicker {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        //Object of API_StockTicker class
        API_StockTicker ticker = new API_StockTicker();
        
        //Pane specifications needed for API_StockTicker methods
        //BorderPane Specifications (main pane)
        BorderPane pane = new BorderPane();
        BorderPane.setAlignment(pane, Pos.CENTER);
        pane.setPadding(new Insets (5, 20, 20, 20));
        
        //StackPane Specifications (center of GUI)
        StackPane center = new StackPane();
        center.setPadding(new Insets(10, 10, 10, 10));
        pane.setCenter(center);
        
        //VBox Specifications (top of GUI - title space)
        VBox titlePane = new VBox(); 
        pane.setTop(titlePane);
        titlePane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(titlePane, Pos.TOP_CENTER);
        
        // TODO: Show scene | ticker.showScene()
        // Scene scene = 
        
        
        // TODO: Choose background color | ticker.backgroundColor()
        // ticker.backgroundColor(pane, "#FFFFFF");
        
        
        // TITLE (part of titlePane VBox)
        // TODO: Display title and specify font | ticker.setTitleFont()
        // Text title = 
        
        
        // OPENING PRICE (part of titlePane VBox)
        // TODO: Display opening price and specify font | ticker.displayOpen()
        // Text open = 
        
        
        // STOCK QUOTE (part of center StackPane)
        // TODO: Display initial quote | ticker.displayInitialQuote()
        // Text initQuote = 
        
        
        // UPDATE QUOTE BUTTON (bottom of BorderPane pane)
        // TODO: Display "Update quote" button and specify color | ticker.displayUpdateButton()
        // Button updateQuote = 
        
        
        
        primaryStage.setTitle("Stock Ticker");
       
        // TODO: Uncomment the following line (syntax error until previous methods are called):
        // primaryStage.setScene(scene); 
        
        primaryStage.show();
        
    } //eo start()

}
