package cs20viewcontroller;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Write methods in this class for displaying data in the DrawnView.
 *
 * You can use all the public instance variables you defined in AllModelsForView
 * and DrawnView as though they were part of this class! This is due to the
 * magic of subclassing (i.e. using the extends keyword).
 *
 * The methods for displaying data in the DrawnView are written as methods in
 * this class.
 *
 * Make sure to use these methods in the ViewUserActions class though, or else
 * they will be defined but never used!
 *
 * @author cheng
 */
public class ViewOutputs extends DrawnView {

    public void updateDisplay() {

        String aMaxStr;
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        if (aTimerModel.getCPhase() == true) {
            if (aTimerModel.getMaxTimeC() < 10) {
                aMaxStr = "0" + aTimerModel.getMaxTimeC() + ":00";
            } else {
                aMaxStr = aTimerModel.getMaxTimeC() + ":00";
            }
        } else if (aTimerModel.getAPhase() == true) {
            if (aTimerModel.getMaxTime() < 10) {
                aMaxStr = "00:0" + aTimerModel.getMaxTime();
            } else {
                aMaxStr = "00:" + aTimerModel.getMaxTime();
            }
        } else { // if (aTimerModel.getRPhase() == true) {
            if (aTimerModel.getMaxTimeR() < 10) {
                aMaxStr = "00:0" + aTimerModel.getMaxTimeR();
            } else {
                aMaxStr = "00:" + aTimerModel.getMaxTimeR();
            }
        }

        int minTimePart = (int) (aTimerModel.getTime() / 60);
        int secTimePart = aTimerModel.getTime() % 60;
        String STPStr;
        String MTPStr;
        if (secTimePart < 10) {
            STPStr = "0" + secTimePart;
        } else {
            STPStr = secTimePart + "";
        }
        if (minTimePart < 10) {
            MTPStr = "0" + minTimePart;
        } else {
            MTPStr = minTimePart + "";
        }
        this.timeLabel.setText(MTPStr + ":" + STPStr);
        this.maxLabel.setText(aMaxStr);
        this.progressBar.setValue(aTimerModel.getTime());
        if (aTimerModel.getCPhase() == true) {
            this.progressBar.setString(nf.format((double) aTimerModel.getTime() / (double) (aTimerModel.getMaxTimeC() * 60) * 100) + "%");
        } else if (aTimerModel.getRPhase() == true) {
            this.progressBar.setString(nf.format((double) aTimerModel.getTime() / (double) aTimerModel.getMaxTimeR() * 100) + "%");
        } else if (aTimerModel.getAPhase() == true) {
            this.progressBar.setString(nf.format(((double) aTimerModel.getTime() / 7) * 100) + "%");
        }
    }

//    public void adjustSound() {
//        aSoundModel.setSoundVolume("alarm", aSoundModel.getVolume());
//    }
    public void updateDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.dateLabel.setText(dtf.format(now));
    }

    //soundA
    public void playSound() {
        if (aSoundModel.getAlarmA() == true) {
            aSoundModel.playSoundRev("alarmA.wav", "alarmA");
        } else if (aSoundModel.getAlarmB() == true) {
            aSoundModel.playSoundRev("alarmB.wav", "alarmB");
        } else if (aSoundModel.getAlarmC() == true) {
            aSoundModel.playSoundRev("alarmC.wav", "alarmC");
        } else {
            aSoundModel.playSoundRev("alarmD.wav", "alarmD");
        }
    }

    public void stopSound() {
        if (aSoundModel.getAlarmA() == true) {
            aSoundModel.stopSound("alarmA");
        } else if (aSoundModel.getAlarmB() == true) {
            aSoundModel.stopSound("alarmB");
        } else if (aSoundModel.getAlarmC() == true) {
            aSoundModel.stopSound("alarmC");
        } else {
            aSoundModel.stopSound("alarmD");
        }
    }

    public synchronized void playTime() {
        Color purple = new Color(106, 40, 189);
        this.getContentPane().setBackground(purple);
        maxTimeC.setBackground(purple);
        maxTimeR.setBackground(purple);
        volumeTextField.setBackground(purple);
        if (aTimerModel.getTimerRunning() == false) {
            Timer timer = new Timer();

            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    aTimerModel.setTimerRunning(true);
                    if (aTimerModel.getReset() == false) {
                        if (aTimerModel.getPause() == false) {
                            if (aTimerModel.getWasPaused() == true && aTimerModel.getAPhase() == true) {
                                playSound();
                                aTimerModel.setWasPaused(false);
                            } else {
                                aTimerModel.setWasPaused(false);
                            }
                            aTimerModel.setTime(aTimerModel.getTime() + 1);
                            if (aTimerModel.getTime() > aTimerModel.getMaxTime()) {

                                if (aTimerModel.getCPhase() == true && aTimerModel.getTime() > aTimerModel.getMaxTimeC() * 60) {
                                    aTimerModel.setCPhase(false);
                                    aTimerModel.setAPhase(true);
                                    aTimerModel.setRPhase(false);
                                    aTimerModel.setMaxTime(7);
                                    aTimerModel.setTime(1);
                                    phaseLabel.setText("Alarm Phase: It's time to switch phases!");
                                    progressBar.setMaximum(7);
                                    progressBar.setValue(0);
//                                descriptionLabel.setText("It's time to switch phases!");
                                    playSound();
                                } else if (aTimerModel.getAPhase() == true) {
                                    aTimerModel.setAPhase(false);
                                    aTimerModel.setTime(1);
                                    if (aTimerModel.getLastBoolC() == true) {
                                        aTimerModel.setRPhase(true);
                                        aTimerModel.setMaxTime(aTimerModel.getMaxTimeR());
                                        phaseLabel.setText("Rest Phase: Look 20 feet away until the alarm rings!");
                                        progressBar.setMaximum(aTimerModel.getMaxTimeR());
                                        progressBar.setValue(0);
//                                    descriptionLabel.setText("Look 20 feet away until the alarm rings!");
                                        aTimerModel.setLastBoolC(false);
                                    } else if (aTimerModel.getLastBoolC() == false) {
                                        aTimerModel.setCPhase(true);
                                        aTimerModel.setMaxTime(aTimerModel.getMaxTimeC());
                                        phaseLabel.setText("Computer Phase: You can be on your device!");
//                                    descriptionLabel.setText("You can be on your device!");
                                        progressBar.setMaximum(aTimerModel.getMaxTimeC() * 60);
                                        progressBar.setValue(0);
                                        aTimerModel.setLastBoolC(true);
                                    }
                                    stopSound();
                                } else if (aTimerModel.getRPhase() == true) {
                                    aTimerModel.setRPhase(false);
                                    aTimerModel.setAPhase(true);
                                    aTimerModel.setCPhase(false);
                                    aTimerModel.setMaxTime(7);
                                    aTimerModel.setTime(1);
                                    phaseLabel.setText("Alarm Phase: It's time to switch phases!");
                                    progressBar.setMaximum(7);
                                    progressBar.setValue(0);
//                                descriptionLabel.setText("It's time to switch phases!");
                                    playSound();
                                }
                            }
                            updateDisplay();
                        } else if (aTimerModel.getPause() == true) {
                            aTimerModel.setTimerRunning(false);
                            aTimerModel.setWasPaused(true);
                            try {
                                stopSound();
                            } catch (NullPointerException e) {

                            }
                            try {
                                timer.wait();
                            } catch (InterruptedException e) {

                            }
                        }
                    } else {
                        if (Integer.parseInt(volumeTextField.getText()) > 100) {
                            volumeTextField.setText("100");
                        }
                        if (Integer.parseInt(volumeTextField.getText()) < 1) {
                            volumeTextField.setText("1");
                        }else if (volumeTextField.getText().equals("")) {
                            volumeTextField.setText("75");
                        }
                        if (Integer.parseInt(maxTimeR.getText()) < 1) {
                            maxTimeR.setText("1");
                        }else if (maxTimeR.getText().equals("")) {
                            maxTimeR.setText("20");
                        }
                        if (Integer.parseInt(maxTimeC.getText()) < 1) {
                            maxTimeC.setText("1");
                        }else if (maxTimeC.getText().equals("")){
                            maxTimeC.setText("20");
                        }
                        try {
                            stopSound();
                        } catch (NullPointerException e) {

                        }
                        aTimerModel.setMaxTime(aTimerModel.getMaxTimeC());
                        aTimerModel.setTime(0);
                        aTimerModel.setReset(false);
                        aTimerModel.setLastBoolC(true);
                        aTimerModel.setAPhase(false);
                        aTimerModel.setRPhase(false);
                        aTimerModel.setCPhase(true);
                        updateDisplay();
                        phaseLabel.setText("Computer Phase: You can be on your device!");
//                    descriptionLabel.setText("You can be on your device!");
//                    timer.purge();
                        progressBar.setMaximum(aTimerModel.getMaxTimeC() * 60);
                        progressBar.setValue(0);
                    }
                }
            },
                    0, 1000);
        }

        Timer dTimer = new Timer();
        dTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateDate();
            }

        },
                0, 1000);
    }
}
