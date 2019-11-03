package ASSN1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import java.util.*;
		

//read file
import java.io.*;

public class main extends Application {
			
			private static Scanner sc;
			private static TextField stdname,mtcnum,cid,spvname;
			private static Button buttonC1;
			private static ArrayList<String> cucibleIDBuffer = new ArrayList<String>();
			private static String[] cucibleID = new String[100],name  = new String[100],matricNo  = new String[100],date  = new String[100],spvName  = new String[100];
			public static Stage guiStage;
			public static Scene scene1,scene2,scene3,scene4,scene5,scene6,sceneChoose;
			
			
			Button button1,button2,button3,buttonN,buttonP;
			Label label1,label2,label3,label4;
			Label info1,info2,info3,info4,info5;
			Label error1;
			
			//To store the file location as variable
			private static File file;
			
			
			public static void openFile() {
				
				try {
					
					file = new File("StudentData.txt");
				
					//Create a new file if doesn't exist
					if(!file.exists()) {
						file.createNewFile();
					}
					
				}catch(Exception e) {
					System.out.println("Create txt error");
				}
			}
			
			
			public static void main(String[] args) {
				openFile();
				readFile();
				launch(args);
			}
			
			
			//Writing data to the text file
			public static void writeFile() {
				
					try {
						
						PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
						
						writeFile.print(AlertBox.cucibleID +"\t"+ stdname.getText()+"\t"+mtcnum.getText()+"\t"+cid.getText()+
								"\t"+spvname.getText()+"\r\n");
						
						writeFile.close();
					}catch(Exception e){
						e.printStackTrace();
				}
			}
			
			
			//Reading data from the text file
			public static void readFile() {

				
				try {
					sc = new Scanner(file);
					int i =0;
					
					while(sc.hasNext()) {
						String readLine = sc.nextLine();
						
						String[] splitted = readLine.split("\t");
							
							cucibleID[i] = splitted[0];
							name[i] = splitted[1];
							matricNo[i] = splitted[2];
							date[i] = splitted[3];
							spvName[i] = splitted[4];
							
							System.out.println(cucibleID[i] +" "+ name[i]);
							
							i++;
						}
						
					
				}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
			
		
			
			
			@Override
			public void start(Stage primaryStag) throws Exception{
				
				guiStage = primaryStag;
				
				//scene1 information column for student
				guiStage.setTitle("INFORMATION STORING CENTRE");
				label1=new Label("Do you a student?");
				GridPane.setConstraints(label1,10,0);
				button1=new Button("Yes");
				GridPane.setConstraints(button1,10,1);
				button2=new Button("No");
				GridPane.setConstraints(button2,10,2);
				button1.setOnAction(e ->guiStage.setScene(sceneChoose));
				button2.setOnAction(e ->guiStage.setScene(scene3));
				GridPane layout=new GridPane();
				layout.setPadding(new Insets(10,10,10,10));
				layout.setVgap(5);
				layout.setHgap(10);
				layout.getChildren().addAll(label1,button1,button2);
				scene1=new Scene(layout,350,120);
				scene1.getStylesheets().add(getClass().getResource("application.css").toString());
				guiStage.setScene(scene1);
				guiStage.show();
				
				
				
				//Choose Floor Plan
				TabPane layoutChooseTab = new TabPane();
				Tab tab1 = new Tab("1-Service Computing");

				GridPane gridChoose1=new GridPane();
				gridChoose1.setPadding(new Insets(10,10,10,10));
				gridChoose1.setVgap(8);
				gridChoose1.setHgap(10);
				
				for(int i = 1; i<=12; i++) {

					int y= 65 + i-1;
					char x = (char)y;

					for(int j = 1; j<=5; j++) {
						
						int z = 0;
						boolean check = true;
						String seatID = Integer.toString(1) + String.valueOf(x) + Integer.toString(j);
						
						while(cucibleID[z]!= null) {
							
							
							if(seatID.equals(cucibleID[z])) {
								
								System.out.println("Occupied");
								
								Button buttonx=new Button("X");
								buttonx.setMaxWidth(100);
								
								String name1 = name[z];
								String matricNo1 = matricNo[z];
								String date1 = date[z];
								String spvName1 = spvName[z];
								
								buttonx.setOnAction(e -> 
								{
									
								//Trigger the AlertBox for not available spaces	
								AlertBox.displayOccupied(seatID, name1, matricNo1, date1, spvName1);
								
								});
								

								GridPane.setConstraints(buttonx,j+5,i+1);

								gridChoose1.getChildren().add(buttonx);
								check = false;
								break;
							}
							
							z++;
						}
						
						if(!check)
							continue;

						Button buttonC1=new Button(seatID);
						buttonC1.setMaxWidth(100);
						
						//Trigger the Alert Box to let user book the place
						buttonC1.setOnAction(e -> AlertBox.display(buttonC1.getText()));

						GridPane.setConstraints(buttonC1,j+5,i+1);

						gridChoose1.getChildren().add(buttonC1);

					};
				}

				tab1.setContent(gridChoose1);
				layoutChooseTab.getTabs().add(tab1);
				
				
				Tab tab2 = new Tab("2-Enabling Technology and Infrastructures");
				
				GridPane gridChoose2=new GridPane();
				gridChoose2.setPadding(new Insets(10,10,10,10));
				gridChoose2.setVgap(8);
				gridChoose2.setHgap(10);
				
				
				for(int i = 1; i<=12; i++) {
					
//					char x = 'A';
					int y= 65 + i-1;
					char x = (char)y;
					
					for(int j = 1; j<=5; j++) {
						
						Button buttonC2=new Button( Integer.toString(1) + String.valueOf(x) + Integer.toString(j));
						buttonC2.setMaxWidth(100);
						buttonC2.setOnAction(e ->guiStage.setScene(scene1));
						
						GridPane.setConstraints(buttonC2,j+5,i+1);
						
						gridChoose2.getChildren().add(buttonC2);
						
					}
					
					;
				}
				
				tab2.setContent(gridChoose2);
				layoutChooseTab.getTabs().add(tab2);
				
				
				Tab tab3 = new Tab("3-Data to Knowledge");
				
				layoutChooseTab.getTabs().add(tab3);
				
				sceneChoose = new Scene(layoutChooseTab,800,800);
				
				
				
				
				
				
				
				//scene 2 if the person entered is student
				GridPane grid=new GridPane();
				grid.setPadding(new Insets(10,10,10,10));
				grid.setVgap(8);
				grid.setHgap(10);
				

				label2=new Label ("You are a student.");
				GridPane.setConstraints(label2,0,0);
				label3=new Label ("Please add your student information");
				GridPane.setConstraints(label3,0,1);
				
				
				info2=new Label("Student Name: ");
				
				
				GridPane.setConstraints(info2,0,2);
				stdname=new TextField();
				stdname.setPromptText("STUDENT NAME");
				GridPane.setConstraints(stdname, 1, 2);
				
				info3=new Label("Matric Name: ");
				
				GridPane.setConstraints(info3,0,3);
				mtcnum=new TextField();
				mtcnum.setPromptText("MATRIC NAME");
				GridPane.setConstraints(mtcnum, 1, 3);
				
				info4=new Label("Check In Date: ");
				
				GridPane.setConstraints(info4,0,4);
				cid=new TextField();
				cid.setPromptText("CHECK IN DATE");
				GridPane.setConstraints(cid, 1, 4);
				
				info5=new Label("Supervisor Name: ");
				
				GridPane.setConstraints(info5,0,5);
				spvname=new TextField();
				spvname.setPromptText("SUPERVISOR NAME");
				GridPane.setConstraints(spvname, 1, 5);
				
				buttonN=new Button("Next");
				
				//buttonN.setOnAction(e ->primaryStage.setScene(scene4));
				
				buttonN.setOnAction(e ->
					{writeFile();
					//cucibleIDBuffer.add(AlertBox.cucibleID);
					guiStage.setScene(scene6);
					stdname.clear();
					mtcnum.clear();
					cid.clear();
					spvname.clear();
					});
				
				GridPane.setConstraints(buttonN,1,6);
				buttonP=new Button("Previous");
				buttonP.setOnAction(e ->guiStage.setScene(sceneChoose));
				GridPane.setConstraints(buttonP,1,7);
				
				
				grid.getChildren().addAll(label2,label3,info2,info3,info4,info5,spvname,mtcnum,stdname,cid,buttonN,buttonP);
				scene2=new Scene(grid,500,300);
				
				//scene 3 if the person entered is not a student and return to the main menu
				GridPane layout3=new GridPane();
				layout3.setPadding(new Insets(10,10,10,10));
				layout3.setVgap(8);
				layout3.setHgap(10);
				error1=new Label("You are not the student.");
				GridPane.setConstraints(error1,7,0);
				Label error2= new Label("Please return to the information confirm menu or exit.");
				GridPane.setConstraints(error2,7,1);
				button3=new Button("Return");
				GridPane.setConstraints(button3,7,2);
				button3.setOnAction(e ->guiStage.setScene(scene1));
				Button buttonE1= new Button("Exit");
				GridPane.setConstraints(buttonE1,7,3);
				buttonE1.setOnAction(e ->guiStage.setScene(scene6));
				layout3.getChildren().addAll(error1,error2,button3,buttonE1);
				scene3=new Scene(layout3,450,150);
				
				//scene 4 if the person continue entering the information and its the turn to enter cubic id
				
				label4=new Label("Now you are requiring to enter the cubic id information");
				GridPane.setConstraints(label4,0,0);
				Label label5=new Label("This is the laboratory ID consisted inside the cubic id: ");
				GridPane.setConstraints(label5,0,1);
				Label label6=new Label("1. Service Computing");
				GridPane.setConstraints(label6, 0, 2);
				Label label7=new Label("2. Enabling Technologies And Infrastructures");
				GridPane.setConstraints(label7,0,3);
				Label label8=new Label("3. Data To Knowledge");
				GridPane.setConstraints(label8,0,4);
				
                Label info6=new Label("Laboratory ID: ");
				
				GridPane.setConstraints(info6,0,5);
				TextField labnum=new TextField();
				labnum.setPromptText("Laboratory ID");
				GridPane.setConstraints(labnum, 1, 5);
				
                Label info7=new Label("The row of table: ");
				
				GridPane.setConstraints(info7,0,6);
				TextField rowtable=new TextField();
				rowtable.setPromptText("Row Of Table");
				GridPane.setConstraints(rowtable, 1, 6);
				
                Label info8=new Label("The column of table: ");
				
				GridPane.setConstraints(info8,0,7);
				TextField columntable=new TextField();
				columntable.setPromptText("Column Of Table");
				GridPane.setConstraints(columntable, 1, 7);
				
				Button buttonN2=new Button("Next");
	            buttonN2.setOnAction(e ->guiStage.setScene(scene5));
				GridPane.setConstraints(buttonN2,1,8);
				Button buttonP2=new Button("Previous");
				buttonP2.setOnAction(e ->guiStage.setScene(scene2));
				GridPane.setConstraints(buttonP2,1,9);
				
				
				GridPane layout4=new GridPane();
				layout4.setPadding(new Insets(10,10,10,10));
				layout4.setVgap(8);
				layout4.setHgap(10);
				layout4.getChildren().addAll(label4,label5,label6,label7,label8,info6,info7,info8,labnum,rowtable,columntable,buttonN2,buttonP2);
				scene4=new Scene(layout4,500,350);
				
				Label label9=new Label("You have complete the information storing");
				GridPane.setConstraints(label9,0,0);
				Label label10=new Label("Click the Return button to go back the main menu for another entry");
				GridPane.setConstraints(label10,0,1);
				Label label11=new Label("Click the Exit button to end the process");
				GridPane.setConstraints(label11,0,2);
				Button buttonR=new Button("Return");
				buttonR.setOnAction(e ->guiStage.setScene(scene1));
				GridPane.setConstraints(buttonR,0,3);
				Button buttonE=new Button("Exit");
				buttonE.setOnAction(e ->{
					sc.close();
					guiStage.setScene(scene6);
				});
				GridPane.setConstraints(buttonE,0,4);
				GridPane layout5=new GridPane();
				layout5.setPadding(new Insets(10,10,10,10));
				layout5.setVgap(8);
				layout5.setHgap(10);
				layout5.getChildren().addAll(label9,label10,label11,buttonR,buttonE);
				scene5=new Scene(layout5,500,200);
				
				Label label12=new Label("Your process is complete .Thanks for cooperation");
				StackPane layout6=new StackPane();
				layout6.getChildren().add(label12);
				scene6=new Scene(layout6,400,200);
				
				
				}
			
			
		}