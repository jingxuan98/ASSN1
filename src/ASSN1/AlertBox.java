package ASSN1;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {

	public static void display(String title) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label();
		label.setText("Are you sure that you want to select this place (" + title + ") ?");
		
		
		GridPane.setConstraints(label,10,0);
		Button button1=new Button("Yes");
		GridPane.setConstraints(button1,10,1);
		Button button2=new Button("No");
		GridPane.setConstraints(button2,10,2);
		button1.setOnAction(e -> {main.writeFile();window.close();});
		button2.setOnAction(e -> window.close());
		
		GridPane layout=new GridPane();
		layout.setPadding(new Insets(10,10,10,10));
		layout.setVgap(5);
		layout.setHgap(10);
		layout.getChildren().addAll(label,button1,button2);
		Scene scene  =new Scene(layout,500,125);
		
		window.setScene(scene);
		window.showAndWait();
	}
	
}
