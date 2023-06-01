public class Main {

    // A3
    // most of focus on GUI, don't worry too much about Design
    // think about:
    //      showing things properly (Observer)
    //      interacting properly with GUI (User->Observer)
    //      Report for A3 on OOD practices, rationlize decisions
    
    // analogous to Deadwood.java

    /*  Roadmap:
       
        - remove die when chosen
        - finalize imageLibrary+Prompt (make display a bit prettier)

        - either create new class for GameWindow or call thru Prompt
        - GameWindow class handles GUI for game
    
     */
    public static void main(String[] args) {
        GameData gd = new GameData(); // supposed to be Model for Game

        // parseImages directly here
        ImageLibrary il = new ImageLibrary("img/"); // points to folder of images

        // imageParse class?
        Prompt win = new Prompt(1000, 750, gd, il); // will finalize with Player names, and Images, and Order
        //launch GameWindow 
  
    }
}