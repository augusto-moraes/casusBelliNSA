import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Execute{

	public static void main(String[] args) {
		Terrain  p = 	new Terrain(1500,700) ;

		 JFrame f = new JFrame();
		f.setResizable (false);
		 f.setLayout(new BorderLayout());

        p.setPreferredSize(new Dimension(1500, 700));
        			

        f.add(p);
        JButton t = new JButton("caca");
        JButton c = new JButton("caca");

        f.add(t, BorderLayout.PAGE_START);
		f.add(c, BorderLayout.PAGE_END);


        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.addMouseListener(p);

	}
}
