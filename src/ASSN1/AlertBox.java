package ASSN1;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {
	
	public static String cucibleID = "";

	public static void display(String title) {
		Stage window = new Stage();
		
		cucibleID = title;
		
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
		button1.setOnAction(e -> 
			{
			//main.writeFile();
			main.guiStage.setScene(main.scene2) ;
			window.close();});
		
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
	
	public static void displayOccupied(String title, String name, String matricNo, String date, String spvName) {
		
		Stage window = new Stage();
		
		cucibleID = title;
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label();
		label.setText("This is occupied by the student with following information:");
		GridPane.setConstraints(label,1,0);
		
		Label labelName = new Label();
		labelName.setText("Name : " + name);
		GridPane.setConstraints(labelName,1,4);
		
		Label labelMatricNo = new Label();
		labelMatricNo.setText("MatricNo : " + matricNo);
		GridPane.setConstraints(labelMatricNo,1,6);
		
		Label labelDate = new Label();
		labelDate.setText("Check in Date : " + date);
		GridPane.setConstraints(labelDate,1,8);
		
		Label labelSpvName = new Label();
		labelSpvName.setText("Supervisor's Name : " + spvName);
		GridPane.setConstraints(labelSpvName,1,10);
		
		GridPane layout=new GridPane();
		layout.setPadding(new Insets(10,10,10,10));
		layout.setVgap(5);
		layout.setHgap(10);
		
		layout.getChildren().addAll(label, labelName, labelMatricNo, labelDate, labelSpvName);
		
		Scene scene  =new Scene(layout,500,200);
		
		window.setScene(scene);
		window.showAndWait();
	}
	
	public static void displayTest(String title, String name3) {
		
		if(name3 !=null) {
		
		Stage window = new Stage();
		
		cucibleID = title;
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label();
		label.setText("Are you sure that you want to select this place (" + title + ") ? by " + name3);
		
		
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
		}else {
			System.out.println("Problem Occured");
		}
	}
	
}
