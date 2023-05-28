import java.awt.Image;

import javax.swing.ImageIcon;

public class GetRessource {
	
	public Image GetImage(String img) {
		Image img_path = new ImageIcon(this.getClass().getResource(img)).getImage();
		return img_path;
	}

}
