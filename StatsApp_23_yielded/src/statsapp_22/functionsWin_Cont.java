/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statsapp_22;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author SosKode
 */
public class functionsWin_Cont implements Initializable {
    
    //Communication 
    central_tendencies ctObj = new central_tendencies();
    dispersion_variation dispOB = new dispersion_variation();
    //Original array and copyArr
    double[] originalArr= new double[100];
    double[] weightArr= new double[100];
    double[] copyArr ;
    double[] copyWeightArr ;
    double ANSWER;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TIME_LBLE.setText(LocalDate.now().toString());
        //tooltips
        tips();
        
    }    
    
        Tooltip tip = new Tooltip();
    private void tips(){
        tip.setStyle("-fx-background-color:#EFE4B0; -fx-padding: 2,2,2,2");
        tip.setText("Click to accepts data values in input field");  
        acceptBtn.setTooltip(tip);
        tip.setText("Enter major data values here");    
        inputTF.setTooltip(tip);    
        
    }
    
    @FXML private Menu file;
    @FXML private MenuItem closeMI;
    @FXML private void closingApp(){
        Platform.exit();
    }
    
    //Edit Menu
    @FXML private MenuItem saveFile;
    TextField filename = new TextField();
    @FXML private void saveCurrentWork(){
        Stage stg = new Stage();
        Label scr = new Label();
        scr.setPrefSize(330, 140);
        scr.setWrapText(true);
        Button sav = new Button("Save");
        sav.setOnAction(savtxt->{
            try {
                stringSaver();
                scr.setText("Your work was save successfully as "+filename.getText()+" directory:\n"+
                        "C:\\Users\\SosKode\\OneDrive\\Documents\\NetBeansProjects\\student polls\\src\\text\\"+filename.getText()+".txt");
            } catch (IOException ex) {
                Logger.getLogger(functionsWin_Cont.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Button cancel = new Button("Close");
        cancel.setOnAction(canceling->{
            stg.close();
        });
        HBox controlsHB = new HBox(30);
        controlsHB.getChildren().addAll(sav,cancel);
        controlsHB.setAlignment(Pos.CENTER);
        filename.setMaxSize(189, 30);
        filename.setPromptText("Specify name of file");
        filename.setFocusTraversable(true);
        Label enterNameLbl = new Label();
        enterNameLbl.setText("Type filename to be saved");
        
        VBox root = new VBox();
        root.setPadding(new Insets(2,3,2,3));
        root.setStyle("-fx-background-color:#E6F2F1");
        VBox sub = new VBox(44);
        sub.setStyle("-fx-background-color:#F2EFE6");
        root.getChildren().addAll(sub,scr);
        root.setAlignment(Pos.CENTER);
        sub.getChildren().addAll(enterNameLbl,filename,controlsHB);
        sub.setAlignment(Pos.CENTER);
        Scene scen = new Scene(root,400,400);
        stg.setScene(scen);
        stg.centerOnScreen();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setTitle("Save screen work to textfile");
        stg.showAndWait();
        
    }
    //saving screen txt
    private void stringSaver() throws FileNotFoundException, IOException{
        StringBuilder sB=new StringBuilder();
        File fil= new File("C:\\Users\\SosKode\\OneDrive\\Documents\\NetBeansProjects\\student polls\\src\\text\\"+filename.getText()+".txt");
        sB.append(mainScreen.getText()+"\n"+LocalDate.now().toString());
        try (FileWriter fw = new FileWriter(fil)) {
            fw.write(sB.toString());
        }
    }
    
    
    
    
    @FXML private Menu help;
    
    @FXML
    private Label TIME_LBLE;
    @FXML
    private Label ansLbl;
    @FXML
    private Text ansTxt;
    @FXML
    private Label valCounter;
    @FXML
    private Label cur_fx;
    @FXML
    private Button computeBtn;
    @FXML
    private Button clearBtn;
    
    @FXML
    private Button acceptBtn;
    int count=0, weightCount=0;
    @FXML private void acceptInput(){
            
        if(centralTend_SM.getText().equals(weighted_meanMI.getText())){
            OTHER_VALUES_TF.setVisible(true);
            if(OTHER_VALUES_TF.getText().isEmpty()||inputTF.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Null input detected");
                alert.setHeaderText("Check that no input field is empty");
                alert.showAndWait();
            }else{
                double weight = Double.parseDouble(OTHER_VALUES_TF.getText());
                double Input=Double.parseDouble(inputTF.getText());
                originalArr[count]=Input;
                weightArr[weightCount]=weight; System.out.println(""+weightCount); System.out.println(weightArr[count]);
                count++;
                weightCount++;
                computeBtn.setDisable(false); 
                inputTF.setFocusTraversable(true);
            }
        }else{
            if(inputTF.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Null input detected");
                alert.setHeaderText("Check that input field is not empty");
                alert.showAndWait();
            }else{
                double Input=Double.parseDouble(inputTF.getText());
                originalArr[count]=Input;   //feeding array
                computeBtn.setDisable(false);
                count++;
                inputTF.setFocusTraversable(true);
            }

        }
        inputTF.clear();OTHER_VALUES_TF.clear();
    }
    @FXML
    private Button showSteps;
    
    @FXML
    private SplitMenuButton centralTend_SM;
    @FXML private MenuItem meanMI;
    @FXML private void meanFinder(){    //                                                                                  Mean
        
        //formatting window
        centralTend_SM.setText(meanMI.getText());OTHER_INPUT_CONTROLS_HB.setVisible(false);
        cur_fx.setText(meanMI.getText());
        computeBtn.setOnAction(findMean->{
            //copy originalArr into a new sizeable array
            for(int i =0; i<count; i++){
                System.out.println(originalArr[i]);
            }
            //initialize copyArr
            copyArr = new double[count];
            System.arraycopy(originalArr, 0, copyArr, 0, count);

            
            ctObj.mean(copyArr, count,mainScreen);
            mainScreen.appendText("\n"); 
            mainScreen.setVisible(false);
            ansLbl.setVisible(true);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<count; i++){
                sb.append("  "+originalArr[i]);
            }
            ansLbl.setText(sb+"\nMean = "+ctObj.CT_Ans);
             
        });
    }
    
    
    //formatting with computeBtn clicked
    @FXML private void formattingWindowWith_ComputeBtnClicked(){
        acceptBtn.setDisable(true);inputTF.setDisable(true);
        lineVisibilityControl(compLine);
        computeBtn.setDisable(false);
    }
    
    private void promptArrayNull(){
        if(copyArr.length==0){
            Alert nullArr = new Alert(Alert.AlertType.ERROR);
            nullArr.setTitle("Null data ");
            nullArr.setHeaderText("You need to load data to use");
            nullArr.show();
        }
    }
    
    
    
    @FXML private MenuItem medianMI;
    @FXML private void medianMeth(){  //                                                                                      Median                                                          
        //formatting...
        centralTend_SM.setText(medianMI.getText());OTHER_INPUT_CONTROLS_HB.setVisible(false);
        cur_fx.setText(medianMI.getText());
        
        
        computeBtn.setOnAction(Med->{
            //activating instance of copyArr
            copyArr=new double[count];
            //Copy originalArr into copyArr
            System.arraycopy(originalArr, 0, copyArr, 0, count);
            
            ctObj.median(copyArr, count, mainScreen);
            ansLbl.setVisible(true);
            mainScreen.setVisible(false);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<count; i++){
                sb.append("  "+originalArr[i]);
            }
            ansLbl.setText(sb+"\nMedian = "+ctObj.CT_Ans);
        });
    }
    
    
    @FXML private MenuItem modeMI;
    @FXML private void modeMeth(){
        centralTend_SM.setText(modeMI.getText());OTHER_INPUT_CONTROLS_HB.setVisible(false);
        cur_fx.setText(modeMI.getText());
    }
    
    double percentileValue;
    @FXML private TextField OTHER_VALUES_TF;
    @FXML private MenuItem weighted_meanMI;
    @FXML private void weightedMeth(){//                                                                                        Weighted mean
        
        //formatting window
        centralTend_SM.setText(weighted_meanMI.getText());OTHER_INPUT_CONTROLS_HB.setVisible(false);
        cur_fx.setText(weighted_meanMI.getText());
        
        inputTextFieldsHB.getChildren().remove(OTHER_VALUES_TF);
        inputTextFieldsHB.getChildren().add(OTHER_VALUES_TF);
        OTHER_VALUES_TF.setVisible(true);  OTHER_VALUES_TF.setPromptText("Weight");
        computeBtn.setOnAction(findWmean->{
            //instantiate copyArr
            copyArr= new double[count];
            copyWeightArr= new double[count];
            //copy originalArr inot copyArr
            System.arraycopy(originalArr, 0, copyArr, 0, count);
            //Copy weightArr into new copyWeightArr;
            System.arraycopy(weightArr, 0, copyWeightArr, 0, count);
            //importing weighted mean method from central tendencies class
            for(int i=0; i<count; i++){
                mainScreen.appendText("\n"+copyWeightArr[i]);

            }
            ctObj.weightedMean(copyArr, copyWeightArr, count, mainScreen);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<count; i++){
                sb.append("  "+originalArr[i]);
            }
            ansLbl.setText(sb+"\nWM = "+ctObj.CT_Ans);
            mainScreen.setVisible(false);
            ansLbl.setVisible(true);
        });
        
        
    }
    
    @FXML
    private SplitMenuButton dispersion_SM;
    @FXML private MenuItem mean_dev_mi;
    @FXML private void meanDevMeth(){//                                                                                     Mean deviation
        dispersion_SM.setText(mean_dev_mi.getText());OTHER_INPUT_CONTROLS_HB.setVisible(false);
        cur_fx.setText(mean_dev_mi.getText());
         acceptBtn.setText("New data");
        mainScreen.appendText("\n\n------------------------------------------------\n");
    }
    
    @FXML private MenuItem std_dev_mi;
    @FXML private void stdDevMeth(){
        
        //formatting...
        dispersion_SM.setText(std_dev_mi.getText());OTHER_INPUT_CONTROLS_HB.setVisible(false);
        cur_fx.setText(std_dev_mi.getText());
        
        
       computeBtn.setOnAction(var->{
            //preparing copyArr for onward process
            copyArr = new double[count];

            //copying first count of items from originalArr into copyArr
            System.arraycopy(originalArr, 0, copyArr, 0, count);
           double vari =dispOB.varianceMeth(copyArr, count, mainScreen);
           mainScreen.appendText("\n\nThe variance is = "+vari);
           mainScreen.appendText("\n\nMeanwhile standard deviation is the square root of variance, hence, :\n\n("+vari+")^0.5 \nTherefore standard = "+Math.sqrt(vari));
           mainScreen.appendText("\n\n------------------------------------------------");
           StringBuilder sb = new StringBuilder();
            for(int i=0; i<count; i++){
                sb.append("  "+originalArr[i]);
            }
           ansLbl.setText(sb+"\nStd Dev. = "+Math.sqrt(vari));
           mainScreen.setVisible(false);
            ansLbl.setVisible(true);
       });
    }
    
    @FXML private MenuItem variance_mi;
    @FXML private void varianceMeth(){
        
        //formatting window
        OTHER_INPUT_CONTROLS_HB.setVisible(false);
        dispersion_SM.setText(variance_mi.getText());
        cur_fx.setText(variance_mi.getText());
       
       computeBtn.setOnAction(var->{
            //preparing copyArr for onward process
            copyArr = new double[count];

            //copying first count of items from originalArr into copyArr
            System.arraycopy(originalArr, 0, copyArr, 0, count);
           mainScreen.appendText("\nTherefore the variance is = "+dispOB.varianceMeth(copyArr, count, mainScreen));
            mainScreen.appendText("\n\n------------------------------------------------\n");
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<count; i++){
                sb.append("  "+originalArr[i]);
            }
            ansLbl.setText(sb+"\nVariance = "+dispOB.DIS_Ans);
            mainScreen.setVisible(false);
            ansLbl.setVisible(true);
       });
    }
    
    @FXML private MenuItem percentile_mi;
    @FXML private Button OTHER_VALUES_BTN;
    @FXML private Label thisValueSelectedLbl;
    @FXML private HBox OTHER_INPUT_CONTROLS_HB;
    
    double PercentileQuery;
    @FXML private void percentileMeth(){                                                                                        //PERCENTILE
        
        //formatting window
        dispersion_SM.setText(percentile_mi.getText());
        cur_fx.setText(percentile_mi.getText());
        
//        inputsVB.getChildren().add(OTHER_INPUT_CONTROLS_HB);
        OTHER_INPUT_CONTROLS_HB.setVisible(true);
        OTHER_INPUT_CONTROLS_HB.getChildren().add(OTHER_VALUES_TF);
        OTHER_VALUES_TF.setVisible(true);
        OTHER_VALUES_BTN.setText("Guage value"); 
        
        computeBtn.setOnAction(per->{
            //preparing copyArr for onward process
            copyArr = new double[count];

            //copying first count of items from originalArr into copyArr
            System.arraycopy(originalArr, 0, copyArr, 0, count);
            dispOB.percentile(copyArr, PercentileQuery, mainScreen);
            mainScreen.appendText("\n\n------------------------------------------------\n");
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<count; i++){
                sb.append("  "+originalArr[i]);
            }
            ansLbl.setText(sb+"\nPercentile = "+dispOB.DIS_Ans);
            mainScreen.setVisible(false);
            ansLbl.setVisible(true);
        });
       
    }
    
    @FXML private void percentileValueSelector(){//                                                                     PERCENTILE VALUE SELECTION
        if(OTHER_VALUES_TF.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NULL INPUT");
            alert.setHeaderText("No value input detected");
            alert.showAndWait();
        }else{
            PercentileQuery=Double.parseDouble(OTHER_VALUES_TF.getText());
            valCounter.setText(OTHER_VALUES_TF.getText()); 
        }
    }
    
    
    @FXML private MenuItem LQ_mi;
    @FXML private void lowerQuartileMeth(){//                                                                                               LOWER QUARTILE
        dispersion_SM.setText(LQ_mi.getText());OTHER_INPUT_CONTROLS_HB.setVisible(false);
        cur_fx.setText(LQ_mi.getText());
        computeBtn.setOnAction(lq->{
             //preparing copyArr for onward process
            copyArr = new double[count];

            //copying first count of items from originalArr into copyArr
            System.arraycopy(originalArr, 0, copyArr, 0, count);
            mainScreen.appendText("\nTherefore the lower quartile is\t"+dispOB.lowerQuartile(copyArr, mainScreen));
            mainScreen.appendText("\n\n------------------------------------------------\n");
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<count; i++){
                sb.append("  "+originalArr[i]);
            }
            ansLbl.setText(sb+"\nLQ = "+dispOB.DIS_Ans);
            mainScreen.setVisible(false);
            ansLbl.setVisible(true);
        });
       
    }
    
    
    @FXML private MenuItem mid_Q_mi;
    @FXML private void midQuartileMeth(){//                                                                                         SECOND QUARTILE
        dispersion_SM.setText(mid_Q_mi.getText());OTHER_INPUT_CONTROLS_HB.setVisible(false);
        cur_fx.setText(mid_Q_mi.getText());
         //preparing copyArr for onward process
        
        computeBtn.setOnAction(secQ->{
            mainScreen.appendText("\nSecond quartile is the same as the median\nTherefore the median:\n");
            medianMeth();
            mainScreen.appendText("\n\n------------------------------------------------\n");
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<count; i++){
                sb.append("  "+originalArr[i]);
            }
            ansLbl.setText(sb+"\nSec. Quartile = "+dispOB.DIS_Ans);
            mainScreen.setVisible(false);
            ansLbl.setVisible(true);
        });
    }
    
    
    @FXML private MenuItem UQ_mi;
    @FXML private void upperQuartileMeth(){ //                                                                              UPPER QUARTILE
        dispersion_SM.setText(UQ_mi.getText());OTHER_INPUT_CONTROLS_HB.setVisible(false);
        cur_fx.setText(UQ_mi.getText());
        computeBtn.setOnAction(UQ->{
             //preparing copyArr for onward process
            copyArr = new double[count];
            //copying first count of items from originalArr into copyArr
            System.arraycopy(originalArr, 0, copyArr, 0, count);
            mainScreen.appendText("\nThe upper quartile is "+dispOB.upperQuartile(copyArr, mainScreen)+"\n");
            mainScreen.appendText("\n\n------------------------------------------------\n");
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<count; i++){
                sb.append("  "+originalArr[i]);
            }
            ansLbl.setText(sb+"\nUpper Quartile = "+dispOB.DIS_Ans);
            mainScreen.setVisible(false);
            ansLbl.setVisible(true);
        });
    }
    
    
    @FXML private MenuItem range_mi;
    @FXML private void rangeMeth(){//                                                                                           RANGE
        dispersion_SM.setText(range_mi.getText());OTHER_INPUT_CONTROLS_HB.setVisible(false);
        cur_fx.setText(range_mi.getText());
        
       computeBtn.setOnAction(ranging->{
             //preparing copyArr for onward process
            copyArr = new double[count];
            //copying first count of items from originalArr into copyArr
            System.arraycopy(originalArr, 0, copyArr, 0, count);
           dispersion_variation.rangeFinder(copyArr, mainScreen);
            mainScreen.appendText("\n\n------------------------------------------------\n");
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<count; i++){
                sb.append("  "+originalArr[i]);
            }
            ansLbl.setText(sb+"\nRange = "+dispOB.DIS_Ans);
            mainScreen.setVisible(false);
            ansLbl.setVisible(true);
       });
    }
    
    
    
    @FXML private MenuItem inter_Q_mi;
    @FXML private void interQuartileMeth(){ //                                                                                          INTER-QUARTILE RANGE
        dispersion_SM.setText(inter_Q_mi.getText());OTHER_INPUT_CONTROLS_HB.setVisible(false);
        cur_fx.setText(inter_Q_mi.getText());
       computeBtn.setOnAction(interquartileRange->{
             //preparing copyArr for onward process
            copyArr = new double[count];

            //copying first count of items from originalArr into copyArr
            System.arraycopy(originalArr, 0, copyArr, 0, count);
            mainScreen.appendText("The inter-quartile range is the difference between the upper-quartile and the lower-quartile:"
                    + "\n"+(dispOB.upperQuartile(copyArr, mainScreen)+" - "+dispOB.lowerQuartile(copyArr, mainScreen))+""
                            + "\n\t= "+(dispOB.upperQuartile(copyArr, mainScreen)-dispOB.lowerQuartile(copyArr, mainScreen))+"\n");
            mainScreen.appendText("\n\n------------------------------------------------\n");
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<count; i++){
                sb.append("  "+originalArr[i]);
            }
            ansLbl.setText(sb+"\nInt. Quartile range = "+(dispOB.upperQuartile(copyArr, mainScreen)-dispOB.lowerQuartile(copyArr, mainScreen)));
            mainScreen.setVisible(false);
            ansLbl.setVisible(true);
       });
    }
    
    
    private void lineVisibilityControl(Line line){                                                                  //          Lines visibility
        compLine.setVisible(false);
        clearLine.setVisible(false);
        resetLine.setVisible(false);
        showWorkLine.setVisible(false);
        line.setVisible(true); 
    }
    
    
    @FXML
    private Line compLine;
    @FXML
    private Line showWorkLine;
    @FXML
    private Line clearLine;
    @FXML
    private Line resetLine;
    @FXML 
    private ImageView hamburgerImgV; 
    int changeType=0;
    @FXML private void changeGroup(){
        if(centralTend_SM.isVisible()){
            
            centralTend_SM.setVisible(false);
            dispersion_SM.setVisible(true); dispersion_SM.setText("Dispersion"); OTHER_VALUES_TF.setVisible(false);
            cur_fx.setText("Select dispersion measure");
        }
        else{
            dispersion_SM.setVisible(false);centralTend_SM.setVisible(true); centralTend_SM.setText("Central tendancy");
            cur_fx.setText("Select tendency measure");
            OTHER_INPUT_CONTROLS_HB.setVisible(false);
        }
            
    }
    
    @FXML 
    private StackPane disp_And_Cent_StckP;
    
    @FXML
    private TextField inputTF;
    @FXML 
    private HBox inputTextFieldsHB;
    @FXML 
    private VBox inputsVB;
    @FXML 
    private TextArea mainScreen;
    
    @FXML 
    private Button logOutBtn;
    
    //Clearing fields
    @FXML private void clearFields(){
        inputTF.clear();
        mainScreen.clear();
        valCounter.setText("");
        lineVisibilityControl(clearLine);
        ansTxt.setText(ansLbl.getText());
        ansLbl.setText("");
    }
    
    @FXML 
    private Button resetBtn;
    @FXML private void resetData(){
        lineVisibilityControl(resetLine);
        acceptBtn.setDisable(false);inputTF.setDisable(false);
        computeBtn.setDisable(true);
        //clearing arrays
        count=0;
        copyArr= new double[100];
        originalArr= new double[100];
        mainScreen.setText("Ok. Load new data to go.");
        ansTxt.setText(ansLbl.getText());
        ansLbl.setText("");
    }
    
    //Show work
    int showWorkCount=0;
    @FXML private void showWork(){
        lineVisibilityControl(showWorkLine);
        mainScreen.setVisible(true);
        ansTxt.setText(ansLbl.getText());
        ansLbl.setVisible(false); 
        
        //debug
        System.out.println("The copied array items");
        copyArr=new double[count];
        //Copying the old array into a new one
        //the last int specified is the number of elements to copy up to
        //the first 0 is the beginning of the old arry and the second 0 is the starting location of the second array
        System.arraycopy(originalArr, 0, copyArr, 0, count);  
        for(int i=0; i<count; i++){
            System.out.println(copyArr[i]);
        }
    }
    
    
    
    @FXML private void loggingOut() throws IOException{
        //Displaying a loggin page
        Stage stage ;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LOGIN.fxml"));
        root= loader.load();
        stage= (Stage) logOutBtn.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    
    
    
    // The end
}

