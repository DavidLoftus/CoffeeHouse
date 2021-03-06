package coffeehouse.gui;

import coffeehouse.ClientApplication;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.UnknownHostException;

public class LoginController {
	@FXML
	private TextField serverIpField;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private Button loginButton;

	private ClientApplication clientApp;

	@FXML
	public void initialize() {
		loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String ip = serverIpField.getText();
				String username = usernameField.getText();

				// TODO: Check if ip and username are valid format

				int port = 3000;

				// TODO: if ip contains port (e.g. 192.168.0.10:3000) use that instead

				loginButton.setText("Clicked"); // Something to show that this was run

				try {
					clientApp.login(ip, port, username);
				} catch(UnknownHostException e) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Unknown Address!");
					alert.setHeaderText("Please enter a valid server address.");

					alert.showAndWait();
				} catch(Exception e) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Unknown error!");
					alert.setHeaderText("An error occurred connecting to server.");
					alert.setContentText(e + " thrown while attempting to login to server. See logs for details.");

					e.printStackTrace();

					alert.showAndWait();
				}
			}
		});
	}

	public void setClientApp(ClientApplication clientApp) {
		this.clientApp = clientApp;
	}


}
