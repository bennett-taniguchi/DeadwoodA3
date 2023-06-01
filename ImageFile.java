import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import javax.swing.Icon;
import javax.swing.ImageIcon;

// basically a wrapper class for Icon which has fields useful for describing the icon
// because of this the icon can be anything that is 1-6

public class ImageFile implements Icon{
    
    Image img;
    ImageIcon originalImg;
    String name;
    String parent;
    int width;
    int height;
    ImageObserver imageObserver;

    ImageFile(ImageIcon img, String name, String parent) {
        
        this.originalImg = img;
        this.img = img.getImage();
        
        
        this.name = name;
        this.parent = parent;
        
       
     
        this.width = img.getIconWidth();
        this.height = img.getIconHeight();
       
        
    }

    
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
       
       
        g.drawImage( this.img, x, y, c);
        
    }
    @Override
    public int getIconWidth() {
        return width;
    }
    @Override
    public int getIconHeight() {
        return height;
    }   
}
