import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class ImageLibrary{
    // give constructor relative path, and it creates object with 
    // everything encased
    
    // below used by carousel
    // null if ImageLibrary were initialized and fields attempted to be accessed
    // either implement getters or protect
    ArrayList<ImageFile> images;
   

    JPanel cardHolder;
    CardLayout cardLayout;

    ArrayList<ImageFile> diceUsed;
    int currentDie;
    ImageLibrary (String path) {
       super();
       this.cardLayout = new CardLayout();
       this.cardHolder = new JPanel();
       this.cardHolder.setLayout(cardLayout);
       this.currentDie = 0;
       this.cardHolder.addMouseListener(new MouseAdapter() {
       
        @Override
        public void mouseClicked(MouseEvent e) {
            //cardLayout.next(cardHolder);
            currentDie++;
            
            
            if(currentDie == diceUsed.size()) {
                currentDie = 0;
            }
            cardLayout.next(cardHolder);
            //cardLayout.show(cardHolder, (currentDie) + ""); doesnt work
            
        }
       });

     
     
       images  = new ArrayList<>();

        // for each lambda splits on . and / ->, selects [2] which is fileName
        // img/cards/01.png -> new imageFile(
        //                      name : "01",
        //                       img : 01.png,
        //                          parent : "cards")
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths
                .filter(Files::isRegularFile)
                .forEach(t -> images.add(new ImageFile(new ImageIcon(t.toString()), 
                t.toString().split("\\.|\\\\")[2],
                t.toString().split("\\.|\\\\")[1])));
                
                      
        } catch (IOException e) {
            e.printStackTrace();
        }

        // images.forEach(t->System.out.println(t.name));
        // quick render test below

        // JPanel cards = diceCarousel();
        // JFrame frame = new JFrame();
        // frame.setDefaultCloseOperation(0);
        // frame.add(cards);
        // frame.pack();
        // frame.setVisible(true);

    }

    // pre: given array of ImageFiles to utilize in returned Jpanel
    // post: returns JPanel component that can cycle thru Die for character selection
    // ** Should be utilizable for any player icons that have the format:
    //  "name"1-6.jpg
    // ** implements MouseAdapter for interaction

    // TODO get it to cycle thru all dice with "Icon:" displayed at top
    JPanel diceCarousel() {
        
        
        // panel of all labels, layout, insert layout into panel
        

       
        
        
        //this = new JPanel(cardLayout);
        //this.cards.addFocusListener(this);
        // spaghetti but goes from blue, cyan, green, orange, purple, red, violet, yellow
        // HARDCODED for now, could be passed search for each initial die in directory
        diceUsed = getImages("b1");
        diceUsed.add(getImage("c1"));
        diceUsed.add(getImage("g1"));
        diceUsed.add(getImage("o1"));
        diceUsed.add(getImage("p1"));
        diceUsed.add(getImage("r1"));
        diceUsed.add(getImage("v1"));
        diceUsed.add(getImage("w1"));
        diceUsed.add(getImage("y1"));

        // label for each image
        JLabel imgHolders[] = new JLabel[diceUsed.size()];
        
        
        // add imageCards to component
        for(int i=0; i<diceUsed.size(); i++) {
            imgHolders[i] = new JLabel(diceUsed.get(i));
            imgHolders[i].setName(i+"");
           
            System.out.println(imgHolders[i].getName());
            //////////////////imgHolders[i].addFocusListener(this);
            cardHolder.add(imgHolders[i]);
            
        }
        

  
        return cardHolder;
    }

    // returns incremented image if one exists
    // otherwise is null
    ImageFile incrementImage(ImageFile curr) {
        if(curr == null) return null;
        int len = curr.name.length();
        String minusLast = curr.name.substring(0,len-1);
        String incrementedName = minusLast + (curr.name.charAt(len-1)+1);
        
        if(getImage(incrementedName) != null) {
            return(getImage(incrementedName));
        } 
        return null;
    }

    // for both below change to lowercase matching if necessary but both commands are for implementation anyway
    // for getting individual image by name only
    ImageFile getImage(String query) {
        
        for(ImageFile i: images) {
            if(i.name.equals(query)) {
                return i;
            }
        }

        return null;
    }

    // // elementwise comparison, returns images that match search terms
    // matches on exact or directory exact
    ArrayList<ImageFile> getImages(String query) {
        ArrayList<ImageFile> res = new ArrayList<>();
        
        for(ImageFile i: images) {
            if(i.name.equals(query) || i.parent.equals(query)) {
                res.add(i);
            }
        }
        return res;
    }


   
  


}
