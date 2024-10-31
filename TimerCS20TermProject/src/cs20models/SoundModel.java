/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs20models;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Hashtable;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author Eric
 */
public class SoundModel {

    private boolean soundA = true;
    private boolean soundB = false;
    private boolean soundC = false;
    private boolean soundD = false;
    private int volume = 75;

    private static java.util.Dictionary<String, Clip> sounds = new Hashtable<String, Clip>();

    public synchronized void playSoundRev(final String path, String name) { // Plays and loads sound to be looped
        try {
            loadSound(path, name);
            Clip clip = sounds.get(name);
            setSoundVolume("alarm", volume);
            clip.start();
        } catch (Exception e) {
          
        }
    }

    public void loadSound(final String path, final String name) { // Loads sound into sounds hashtable
        try {
            Clip clip = AudioSystem.getClip();
            InputStream audioSrc = SoundModel.class.getResourceAsStream(path);
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
            clip.open(audioStream);
            sounds.put(name, clip);
        } catch (Exception e) {
            
        }
    }

    public void setSoundVolume(String name, int value) { // Sets volume of loaded sound
        try {
            value = (value > 100) ? 100 : value;
            value = (value < 0) ? 0 : value;
            Clip clip = sounds.get(name);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = volume.getMaximum() - volume.getMinimum();
            float gain = (range * ((float) value / 100.f)) + volume.getMinimum();
            volume.setValue(gain);
        } catch (Exception e) {

        }
    }

    public void stopSound(String name) { // Stops playing sound
        try {
            Clip clip = sounds.get(name);
            clip.stop();
        } catch (Exception e) {

        }
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int l) {
        this.volume = l;
    }

    
    public boolean getAlarmA() {
        return this.soundA;
    }
    public void setAlarmA(boolean b) {
        this.soundA = b;
    }
    
    
    public boolean getAlarmB() {
        return this.soundB;
    }

    public void setAlarmB(boolean b) {
        this.soundB = b;
    }
    
        public boolean getAlarmC() {
        return this.soundC;
    }

    public void setAlarmC(boolean b) {
        this.soundC = b;
    }
    
        public boolean getAlarmD() {
        return this.soundD;
    }

    public void setAlarmD(boolean b) {
        this.soundD = b;
    }
    
}
