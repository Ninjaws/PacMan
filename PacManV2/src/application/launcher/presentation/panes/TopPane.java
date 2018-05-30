package application.launcher.presentation.panes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;


public class TopPane extends FlowPane {
    public TopPane() {
        setId("top-pane");

        ImageView imageView = new ImageView();
        imageView.setImage(new Image(getClass().getResource("/launcher/presentation_images/pacman.png").toExternalForm(),128,128,false,false));
        this.getChildren().add(imageView);

        Text title = new Text("PacMan");
        title.setId("title");
        this.getChildren().add(title);
    }
}
