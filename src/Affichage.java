import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import javax.sound.sampled.*;
import java.io.File;

public class Affichage extends JFrame{
    private JPanel principal;
    private JPanel choseNbPlayers;

    private JTextArea credits;
    private JButton PressStart;
    private Clip audioClipBoucle;
    
    private JButton deuxjoueur;
    private JButton troisjoueur;
    private JButton quatrejoueur;

    private GameManager manager;
  
    public Affichage(GameManager gm, int largeur, int hauteur){
        super("Casus Bellinsa");

        this.manager = gm;
        
        setSize(largeur,hauteur);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        principal = new JPanel(new BorderLayout());
        principal.setBackground(Color.BLACK);
        
        choseNbPlayers = new JPanel();
        choseNbPlayers.setBackground(Color.BLACK);

        credits = new JTextArea();
        credits.setEditable(false);
        credits.setText("CasusBellInsa is a game made by Rhino Entertainement, All rights reserved. Music from Clash Royale. Have fun !");
        
		PressStart = mkButton("Sprites/images/pressStart.jpg");
		deuxjoueur = mkButton("Sprites/images/2joueur.jpg");
		troisjoueur = mkButton("Sprites/images/3joueur.jpg");
		quatrejoueur =mkButton("Sprites/images/4joueur.jpg");
        
        PressStart.addActionListener(new EcouteurLaunch(this, 2)); 
        deuxjoueur.addActionListener(new EcouteurLaunch(this, 2));
        troisjoueur.addActionListener(new EcouteurLaunch(this, 3));
        quatrejoueur.addActionListener(new EcouteurLaunch(this, 4));
               
        principal.add(PressStart);
        
        choseNbPlayers.add(deuxjoueur);
        choseNbPlayers.add(troisjoueur);
        choseNbPlayers.add(quatrejoueur);
        
        PlayMusicEnBoucle("Sprites/music/music_1.wav", 0.70); 
                
        this.add(principal);
        this.add(choseNbPlayers, BorderLayout.NORTH); 
        this.add(credits, BorderLayout.SOUTH);
        setVisible(true);
    }
    
    // méthode pour rendre d'image cliquable
    public JButton mkButton(String url){
        JButton b = new JButton(new ImageIcon(url));
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        return b;
    }
        
    public void PlayMusicEnBoucle(String chemin, double gain) {    // Lance la musique en boucle
        File audio = new File(chemin);
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(audio);
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClipBoucle = (Clip) AudioSystem.getLine(info);
            audioClipBoucle.open(audioInputStream);
            FloatControl gainControl = (FloatControl) audioClipBoucle.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
            audioClipBoucle.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    
    public void clearScreen() {
        this.getContentPane().removeAll();
    }

    public void startGame(int nbJoueurs) {
        this.manager.startGame(nbJoueurs);
    }
}
