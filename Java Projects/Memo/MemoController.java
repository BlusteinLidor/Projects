import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

/**
 * Maman14EX2
 * @author Lidor Blustein
 * id 314993460
 *
 */

public class MemoController {

    @FXML
    private ComboBox<String> dayCombo;

    @FXML
    private ComboBox<String> monthCombo;

    @FXML
    private TextArea textArea;

    @FXML
    private ComboBox<String> yearCombo;
    
    private HashMap<Date, String> hm;
    
    public void initialize() {
    	hm = new HashMap<Date, String>();
    	initComboBox();
    }
    
    private void initComboBox() {
    	final int DAYS = 31, MONTHS = 12, START_YEAR = 2019,
    			END_YEAR = 2029;
    	for(int i = 1; i <= DAYS; i++) {
    		dayCombo.getItems().add(i + "");
    	}
    	for(int i = 1; i <= MONTHS; i++) {
    		monthCombo.getItems().add(i + "");
    	}
    	for(int i = START_YEAR; i <= END_YEAR; i++) {
    		yearCombo.getItems().add(i + "");
    	}
    	dayCombo.setValue("1");
    	monthCombo.setValue("1");
    	yearCombo.setValue(START_YEAR + "");
    }

    @FXML
    void loadMemo(ActionEvent event) {
    	Date d = new Date(Integer.parseInt(dayCombo.getValue()),
    			Integer.parseInt(monthCombo.getValue()),
    			Integer.parseInt(yearCombo.getValue()));
    	String s = hm.get(d);
    	textArea.setText(s);
    }

    @FXML
    void saveMemo(ActionEvent event) {
    	Date d = new Date(Integer.parseInt(dayCombo.getValue()),
    			Integer.parseInt(monthCombo.getValue()),
    			Integer.parseInt(yearCombo.getValue()));
    	hm.put(d, textArea.getText());
    }
    
    @FXML
    void loadFile(ActionEvent event) {
    	File file = getFile();
    	if(file != null) {
    		try {
    			FileInputStream fi = new FileInputStream(file);
    			ObjectInputStream ois = new ObjectInputStream(fi);
    			hm = (HashMap<Date, String>)ois.readObject();
    			ois.close();
    			fi.close();
    		}
    		catch(IOException e) {
    			alert("Error");
    		}
    		catch(ClassNotFoundException e) {
    			alert("Error");
    		}
    	}
    }
    
    @FXML
    void saveFile(ActionEvent event) {
    	File file = getFile();
    	try {
    		FileOutputStream fo = new FileOutputStream(file);
        	ObjectOutputStream out = new ObjectOutputStream(fo);
        	out.writeObject(hm);
        	out.close();
        	fo.close();
    	}
    	catch(IOException e) {
    		alert("Error");
    	}
    }
    
    private void alert(String txt) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Error");
		alert.setHeaderText("Error");
		alert.setContentText(txt);
		alert.showAndWait();
    }
    
    private File getFile() {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Select a file");
    	fileChooser.setInitialDirectory(new File("."));
    	return fileChooser.showOpenDialog(null);
    }

}
