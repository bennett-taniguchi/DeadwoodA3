import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.GridLayout;

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
    BoardWindow(Player[] players, ImageLibrary il) {

        this.setLayout(new FlowLayout());

        this.setDefaultCloseOperation(0);
        this.setTitle("Deadwood Game");
        this.setSize(3000,2500);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // add board, options, players, history

        // board
        ImageFile boardImage = il.getImage("board");
        this.add(new JLabel(boardImage));

        // options (Actions)
       // this.add(new JButton("Actions"));

        // players
        //this.add(new JButton("3"));

        // history
       // this.add(new JLabel("History"));

        this.pack();
        this.setVisible(true);
    }


    // should 
    void placeIcon(ImageFile img, int x, int y, int width, int height) {
        
    }
}
