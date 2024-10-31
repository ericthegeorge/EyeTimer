package cs20models;

import java.util.Date;
import java.util.TimerTask;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * A class to model the problem or situation your program solves
 *
 * @author cheng
 */
public class TimerModel extends SoundModel {

    private boolean pause = false;
    private boolean cPhase = true;
    private boolean rPhase = false;
    private boolean aPhase = false;
    private boolean lastBoolC = true;
    private int time = 0;
    private int max = 20;
    private int maxTimeC = 20;
    private int maxTimeR = 20;
    private boolean reset = false;
    private boolean first = true;
    private boolean wasPaused = false;
    private boolean firstTime = true;
    private boolean timerRunning = false;

    //pause
    public boolean getPause() {
        return this.pause;
    }

    public void setPause(boolean b) {
        this.pause = b;
    }

    //cPhase
    public boolean getCPhase() {
        return this.cPhase;
    }

    public void setCPhase(boolean b) {
        this.cPhase = b;
    }

    //rPhase
    public boolean getRPhase() {
        return this.rPhase;
    }

    public void setRPhase(boolean b) {
        this.rPhase = b;
    }

    //aPhase
    public boolean getAPhase() {
        return this.aPhase;
    }

    public void setAPhase(boolean b) {
        this.aPhase = b;
    }

    //lastBoolC
    public boolean getLastBoolC() {
        return this.lastBoolC;
    }

    public void setLastBoolC(boolean b) {
        this.lastBoolC = b;

    }
    
    //reset
    public boolean getReset() {
        return this.reset;
    }
    
    public void setReset(boolean b) {
        this.reset = b;
    }
    
    //first
    public boolean getFirst() {
        return this.first;
    }
    
    public void setFirst(boolean b) {
        this.first = b;
    }
    
    
    //time
    public int getTime() {
        return this.time;
    }

    public void setTime(int b) {
        this.time = b;
    }

    //maxTimeGeneral
    public int getMaxTime() {
        return this.max;
    }

    public void setMaxTime(int b) {
        this.max = b;
    }
    
    //maxTimeC
        public int getMaxTimeC() {
        return this.maxTimeC;
    }

    public void setMaxTimeC(int b) {
        this.maxTimeC = b;
    }
    
    //maxTimeR
        public int getMaxTimeR() {
        return this.maxTimeR;
    }

    public void setMaxTimeR(int b) {
        this.maxTimeR = b;
    }
    
        //soundPlayed
    public boolean getWasPaused() {
        return this.wasPaused;
    }
    
    public void setWasPaused(boolean b) {
        this.wasPaused = b;
    }
        //timeRunning
    public boolean getTimerRunning() {
        return this.timerRunning;
    }
    public void setTimerRunning(boolean b) {
        this.timerRunning = b;
    }
}
