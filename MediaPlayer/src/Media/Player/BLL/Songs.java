/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Media.Player.BLL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
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
public class Songs {

    private String a(String artist) {
        return artist;
    }

    private String t(String title) {
        return title;
    }

    private String g(String genre) {
        return genre;
    }

    private double tL(double trackLength) {
        return trackLength;
    }
    public String AlbumInfo(String a, String t, String g, double tL) throws CannotReadException, 
            IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException{
         
        AudioFile f = AudioFileIO.read(file);
        Tag tag = f.getTag();
        AudioHeader audioHeader = f.getAudioHeader();
        
        Artist = a(tag.getFirst(FieldKey.ARTIST));
        Title = t(tag.getFirst(FieldKey.TITLE));
        Genre = g(tag.getFirst(FieldKey.GENRE));
        trackLength = tL(audioHeader.getTrackLength());
        return Artist + "," + Title +","+Genre+","+trackLength;
    }

    private static class MP3 {
        
        public MP3(String a, String t,String g,double tl) {
            for(int i=0;i<10;i++){
                List<MP3> emptySubList = new ArrayList<MP3>();
                songs.add(emptySubList);
            }
        }

        private String Mp3(String AlbumInfo) {
            return AlbumInfo;
        }
    }
    private File file = new File(".");
    private static ArrayList<List<MP3>> songs = new ArrayList<List<MP3>>();
    private String Artist, Title, Genre;
    private double trackLength;
    
    
    
    public String[] getFile(int listNr){
        JFileChooser addSong = new JFileChooser();
        
        try{
            FileFilter filter = new ExtensionFileFilter("MP3|MP4|WMA|WAV", 
                new String[] {"MP3", "MP4", "WMA", "WAV"});
            addSong.setFileSelectionMode(
            JFileChooser.FILES_ONLY);
            addSong.setFileFilter(filter);
            int result = addSong.showOpenDialog(null);
            
            // user clicked Cancel button on dialog
            if ( result == JFileChooser.APPROVE_OPTION) {
                
                file = addSong.getSelectedFile();
                BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
                
                String line = br.readLine();
                
                while (line != null){
                    line = br.readLine();
                    line.split("\t");
                    for (int i=0;i<songs.size();i++)
                        songs.get(listNr).add(new MP3(Artist,Title,Genre,trackLength));
//                    
                }
            }
            else {
                file = null;
                return null;
            }
            return null;
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        return null;
    
    }

    /**
     * @return the list
     */
    public ArrayList<List<MP3>> getList() {
        return songs;
    }

    /**
     * @param list the list to set
     */
    public void setList(ArrayList<List<MP3>> songs) {
        this.songs = songs;
    }
    
}
