/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MediaPlayerGUI;

import Media.Player.BLL.MediaPlayerClass;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

/**
 *
 * @author Casey
 */
public class CaseysMPGUI extends JPanel {
    private static MediaPlayerClass mpc = new MediaPlayerClass();
    private static JTable myTable = new JTable();
    private static JPanel buttonPanel = new JPanel();
    private static JPanel buttonPanel2 = new JPanel();
    
    public void CaseysMediaPlayerGUI() {
        setSize(WIDTH, HEIGHT);
        

        JLabel music = new JLabel("Music:");
        

        JButton play = new JButton("Play");
        JButton stop = new JButton("Stop");
        JButton pause = new JButton("Pause");
        JButton next = new JButton("Next");
        JButton prev = new JButton("Prev");

        buttonPanel.add(music);
        buttonPanel.add(play);
        buttonPanel.add(stop);
        buttonPanel.add(pause);
        buttonPanel.add(next);
        buttonPanel.add(prev);
        
        JLabel playList = new JLabel("Play List:");
        

        JButton add = new JButton("Add");
        JButton delete = new JButton("Delete");

        buttonPanel2.add(playList);
        buttonPanel2.add(add);
        buttonPanel2.add(delete);

        myTable = new JTable(4, 4);
        JScrollPane myPane = new JScrollPane(myTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(myPane);
        myTable.setPreferredScrollableViewportSize(new Dimension(500, 70));

        

    }
    
   
    public static void main(String[] args) throws CannotReadException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100, 100);
        JList list = new JList();
        frame.getContentPane().add(new JScrollPane(list));
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        contentPane.add(myTable, BorderLayout.CENTER);
        contentPane.add(buttonPanel2, BorderLayout.WEST);
        frame.setVisible(true);
        mpc.constructTable();
    }
}
class FrameListener extends WindowAdapter{
    public void windowClosing(WindowEvent e){
        System.exit(0);
    }
}