import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
/* TODO
- add ImageLibrary passed inside of here for diceCarousel usage
- update methods with ImageFiles for Player image

// next friday 8 days (✅)
// - Player Count Screen ✅
// - Player Names Screen ✅
// - Board Screen
        // - 


*/
public class Prompt extends JFrame{
    
    JPanel panelA, panelB, cardPanel;
    
    CardLayout cards;

    String message;
    boolean gettingCount;
    boolean gettingNames;
    
    GameData gd;
    ImageLibrary il;

    int playerCount;
    int currPlayer;

    Player[] players;

    boolean gameReady;
    // x and y refer to Window Size, not rows and cols
    public Prompt(int x, int y, GameData gd, ImageLibrary il) {
        super("Deadwood Game"); // window name
        
        // rows by cols
        Dimension dim = new Dimension(x,y);
        setSize(dim);
        int rows = 3;
        int cols = 4;
        this.message = "";
        this.playerCount = 2;
        this.currPlayer = 1;

        this.gd = gd;
        this.il = il;

        this.panelA = new JPanel();
        this.panelB = new JPanel();
        this.cardPanel = new JPanel();
        this.cards = new CardLayout();

        this.cardPanel.setLayout(this.cards); 
        
       
        this.cardPanel.add(this.panelA);
        this.cardPanel.add(this.panelB);

        
        this.add(cardPanel);

        // encase everything in CardLayout...
        promptPlayerCount(rows, cols);
        promptPlayerNames();
        
        
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

   Player[] getPlayers() {
    return this.players;
   }
    // Question Player Amount Phase
    // 3 x 4
    // post:
    // - determines amount of players (also determines rules)
    void promptPlayerCount(int rows, int cols) {
       // Container pane = getContentPane();
        
        panelA.setLayout(new GridLayout(rows,cols,1,1));
        String questionAmount = "<html>How many players are playing?</html>"; // html tag allows for wrapping text
        String validInputA = "<html>Click on a button to figure out rules</html>";
        String validInputB = "<html>press OK to proceed to name selection</html>";
   
        panelA.add(new JPanel());

        // all need wrapping capabilities:
        JLabel q = new JLabel(questionAmount);
        panelA.add(q);

        JLabel a = new JLabel(validInputA);
        panelA.add(a);

        JLabel b = new JLabel(validInputB);
        panelA.add(b);

        panelA.add(new JPanel());

        // instantiated early, placed last
        JLabel info = new JLabel("Game only lasts 3 days");
        
        // draw number buttons + actionListener
        for(int i=2;i<=8;i++) {
            JButton button = new JButton(Integer.toString(i));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    updatePromptPlayerCountDialog(Integer.parseInt(
                        button.getText()
                    ));

                    playerCount = Integer.parseInt(button.getText());
                    

                    info.setText(message);  
                }
            });
            panelA.add(button);
        }

        // draw OK button
        JButton button = new JButton("OK");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               players = new Player[playerCount];
               cards.next(cardPanel);
            }
        });
        panelA.add(button);

        // draw info area
        panelA.add(info);

    }
    
    // update Dialog-promptPlayerNames
    // displays rules based on amount selected
    void updatePromptPlayerCountDialog(int num) {
        if(num < 4) {
            message = "Game only lasts 3 days";
        } else if (num == 4) {
            message = "Game is unchanged";
        } else if(num == 5) {
            message = "Each player gets 2 credits";
        } else if(num == 6) {
            message = "Each player gets 4 credits";
        } else {
            message = "Each player starts with rank of 2";
        }
    }

    //
    boolean nameUntaken(String name, Player[] list) {
        if(name.equals("")) return false;
        for(Player p: list) {
            if(p != null && p.name.equals(name)) {
                return false;
            }
        }
       
        return true;
    }

    // 4 x 1
    // player class now includes dice image
    void promptPlayerNames() {
        panelB.setLayout(new GridLayout(5,1,1,1));

        // dialog per player number
        JLabel dialog = new JLabel();
        panelB.add(dialog);

        // textfield to choose name
        JTextField text = new JTextField();
        panelB.add(text);

        // playericon hardcoded for now (colored die)
        //ImageFile img = new ImageFile("img");
        // dice carousel
        // add dice carousel
        JPanel carousel = il.diceCarousel();
        panelB.add(new JLabel("Player Icon Chosen:"));
        panelB.add(carousel);

        // next/start button (for entry into player array)
        JButton next = new JButton("Next");
        next.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(nameUntaken(text.getText(), players)) {
                    ImageFile removedDie = il.diceUsed.get(il.currentDie);
                    players[currPlayer-1] = new Player(text.getText(),
                                            removedDie);
                
                    text.setText("");

                    /// below works but is janky
                    il.cardHolder.remove(0);
                    il.diceUsed.remove(il.currentDie);
                    il.cardLayout.next(il.cardHolder);

                    if(currPlayer == playerCount) {

                        //il.cardHolder.remove(0);
                        //il.cardLayout.next(il.cardHolder);

                        // go next stage
                        
                        BoardWindow bw = new BoardWindow(players, il);
                        dispose();
                        //cardPanel.add(bw);
                        //cards.next(cardPanel);
                        //System.exit(0);
   
                    }
                    
                    currPlayer++;
                    dialog.setText("What is player " + currPlayer + "'s name?");
                } else {
                    JOptionPane notification = new JOptionPane();
                    notification.showMessageDialog(panelB, "Choose a different name, name is already taken or blank!");
                    
                }
            
                
            }
        });
        panelB.add(next);
        
        
        dialog.setText("What is player " + currPlayer + "'s name?");

         
    }
}
