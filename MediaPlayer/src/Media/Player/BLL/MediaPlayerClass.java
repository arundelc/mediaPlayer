/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Media.Player.BLL;

import MediaPlayerGUI.CaseysMPGUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;



/**
 *
 * @author Casey
 */
public class MediaPlayerClass {
    private File file;
    public String Artist, Title, Genre;
    public double trackLength;
    ArrayList<List<MP3>> songs = new ArrayList<List<MP3>>(10);
    
    private class MP3{
        
        private MP3() {
            for(int i=0;i<10;i++){
                List<MP3> emptySubList = new ArrayList<MP3>();
                songs.add(emptySubList);
            }
        }
    }
    
    //ArrayList me = songs;
    
    
    public void constructTable() {
        final DefaultTableModel dtm = new DefaultTableModel();
        JTable table = new JTable();
        table.setModel(dtm);
        dtm.setColumnIdentifiers(new String[] {"Artist", "Song Title", "Genre", "Track Length"});
        for (MediaPlayerClass.MP3 ai : songs){
            dtm.addRow(new String[]{ai.Artist, ai.Title, ai.Genre, String.valueOf(ai.trackLength)});
          }
    }  
        
    public Long getSongDuration(File file) throws IOException, UnsupportedAudioFileException {
        AudioFileFormat baseFileFormat = AudioSystem.getAudioFileFormat(file);
        Map properties = baseFileFormat.properties();
        Long duration = (Long) properties.get("duration");
        return duration;
    }
}
class ExtensionFileFilter extends FileFilter {
  String description;

  String extensions[];

  public ExtensionFileFilter(String description, String extension) {
    this(description, new String[] { extension });
  }

  public ExtensionFileFilter(String description, String extensions[]) {
    if (description == null) {
      this.description = extensions[0];
    } else {
      this.description = description;
    }
    this.extensions = (String[]) extensions.clone();
    toLower(this.extensions);
  }

  private void toLower(String array[]) {
    for (int i = 0, n = array.length; i < n; i++) {
      array[i] = array[i].toLowerCase();
    }
  }

  public String getDescription() {
    return description;
  }

  public boolean accept(File file) {
    if (file.isDirectory()) {
      return true;
    } else {
      String path = file.getAbsolutePath().toLowerCase();
      for (int i = 0, n = extensions.length; i < n; i++) {
        String extension = extensions[i];
        if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
          return true;
        }
      }
    }
    return false;
  }
}

