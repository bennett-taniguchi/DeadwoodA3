import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

public class BoardWindow extends JFrame{
     // JPanel cards = diceCarousel();
        // JFrame frame = new JFrame();
        // frame.setDefaultCloseOperation(0);
        // frame.add(cards);
        // frame.pack();
        // frame.setVisible(true);

    // exists within a Jframe in a card layout

    /*  |   Board   | Options |
     *  |-----------|-------- |
     *  |   Players | History |
     */
    // Layout:
    // 1|2
    // 3|4

    // gridlayout rows, cols, hgaps, vgaps <- might be necessary

    // +card data, and +board data (to render)
   
    ImageLibrary il;
    ImageFile boardImage;
   
    JPanel board;
    
    BoardWindow(Player[] players, ImageLibrary il) {
        super("Deadwood Game");
        this.il = il;
        
        this.setLayout(new FlowLayout());

        this.setSize(3000,2500);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.boardImage = il.getImage("board");
      

        // add board, options, players, history

        // board
        
        
        // options (Actions)
       // this.add(new JButton("Actions"));

        // players
        //this.add(new JButton("3"));

        // history
       // this.add(new JLabel("History"));

        this.setVisible(true);
    }

   
    // should work for drawing
    void placeIcon(ImageFile img, int x, int y, int width, int height) {
        
    }

   
}
