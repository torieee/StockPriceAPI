
package StockTickerFinal;

import java.io.IOException;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 * @author Victoria Thacker
 * 
 * Lines for programmer to change are labeled with TODO:
 * Review API instructions - API_StockTicker.java - for how to use these methods
 */

public class StockTickerDemo extends API_StockTicker {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        API_StockTicker ticker = new API_StockTicker();
        
        BorderPane pane = new BorderPane();
        BorderPane.setAlignment(pane, Pos.CENTER);
        pane.setPadding(new Insets (5, 20, 20, 20));
        
        StackPane center = new StackPane();
        center.setPadding(new Insets(10, 10, 10, 10));
        pane.setCenter(center);
        
        VBox titlePane = new VBox();
        pane.setTop(titlePane);
        titlePane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(titlePane, Pos.TOP_CENTER);
        
        // TODO: Show scene | ticker.showScene()
        Scene scene = ticker.showScene(pane, 500, 500);
        
        //TODO: Choose background color | ticker.backgroundColor() - VOID
        ticker.backgroundColor(pane, "#000000");
        
        
        //TITLE  (part of titlePane VBox)
        // TODO: Display title and specify font | ticker.setTitleFont()
        Text title = ticker.setTitleFont(titlePane, "Times", 30, Color.WHITE);
        
        //OPENING PRICE (part of titlePane VBox)
        // TODO: Display opening price and specify font | ticker.displayOpen()
        Text open = ticker.displayOpen(titlePane, "Times", 10, Color.WHITE);
        
        //STOCK QUOTE (part of center StackPane)
        // TODO: Display initial quote | ticker.displayInitialQuote()
        Text initQuote = ticker.displayInitialQuote(center);
        
        
        //UPDATE QUOTE BUTTON (bottom of BorderPane pane)
        // TODO: Display "Update quote" button and specify color | ticker.displayUpdateButton()
        Button updateQuote = ticker.displayUpdateButton(pane, center, "#FFFFFF");
        
        
        primaryStage.setTitle("Stock Ticker");
        primaryStage.setScene(scene); //Red line will disappear when TODO items are completed
        primaryStage.show();
        
    } //eo start()

}
