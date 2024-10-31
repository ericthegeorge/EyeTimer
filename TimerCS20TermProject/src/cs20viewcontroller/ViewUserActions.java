/*
 * The controller classes (like the ViewUserActions class) provides actions
 * that the user can trigger through the view classes.  Those actions are 
 * written in this class as private inner classes (i.e. classes 
 * declared inside another class).
 *
 * You can use all the public instance variables you defined in AllModelsForView, 
 * DrawnView, and ViewOutputs as though they were part of this class! This is 
 * due to the magic of subclassing (i.e. using the extends keyword).
 */
package cs20viewcontroller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import javax.swing.JRadioButton;

/**
 * ViewUserActions is a class that contains actions users can trigger.
 *
 * User actions are written as private inner classes that implements the
 * ActionListener interface. These actions must be "wired" into the DrawnView in
 * the ViewUserActions constructor, or else they won't be triggered by the user.
 *
 * Actions that the user can trigger are meant to manipulate some model classes
 * by sending messages to them to tell them to update their data members.
 *
 * Actions that the user can trigger can also be used to manipulate the GUI by
 * sending messages to the view classes (e.g. the DrawnView class) to tell them
 * to update themselves (e.g. to redraw themselves on the screen).
 *
 * @author cheng
 */
public class ViewUserActions extends ViewOutputs {

    /*
     * Step 1 of 2 for writing user actions.
     * -------------------------------------
     *
     * User actions are written as private inner classes that implement
     * ActionListener, and override the actionPerformed method.
     *
     * Use the following as a template for writting more user actions.
     */
//    private class CopyUserText implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent ae) {
//            String userText = userTextField.getText(); // get text from view
//            aDeepThoughtModel.setThought(userText); // update model
//            updateThoughtDisplayed(); // tell view to update
//        }
//    }
    private class StartStopAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (aTimerModel.getFirst() == true) {
                playTime();
                aTimerModel.setFirst(false);
                startButton.setBackground(Color.green);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                textArea.append("\nStart: " + dtf.format(now));
            } else {
                if (aTimerModel.getPause() == true) {
                    aTimerModel.setPause(false);
                    playTime();
                    startButton.setBackground(Color.green);
                    textArea.append("\nStart: " + dateLabel.getText());
                } else {
                    aTimerModel.setPause(true);
                    startButton.setBackground(Color.red);
                    textArea.append("\nPause: " + dateLabel.getText());
                }
            }
        }
    }

    private class ResetAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            aTimerModel.setReset(true);
            textArea.append("\nReset: " + dateLabel.getText());
        }
    }

    private class ApplyAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            aTimerModel.setMaxTimeC(Integer.parseInt(maxTimeC.getText()));
            aTimerModel.setMaxTimeR(Integer.parseInt(maxTimeR.getText()));
            aSoundModel.setVolume(Integer.parseInt(volumeTextField.getText()));
            aTimerModel.setReset(true);
            try {
                stopSound();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Enumeration buttonEnum = soundGroup.getElements();
            while (buttonEnum.hasMoreElements()) {
                JRadioButton jrb = (JRadioButton) buttonEnum.nextElement();
                String actionCmdStr = jrb.getActionCommand();
                if (jrb.isSelected()) {
                    if (actionCmdStr.equals(soundButton1.getActionCommand())) {
                        aSoundModel.setAlarmA(true);
                        aSoundModel.setAlarmB(false);
                        aSoundModel.setAlarmC(false);
                        aSoundModel.setAlarmD(false);
                    } else if (actionCmdStr.equals(soundButton2.getActionCommand())) {
                        aSoundModel.setAlarmA(false);
                        aSoundModel.setAlarmB(true);
                        aSoundModel.setAlarmC(false);
                        aSoundModel.setAlarmD(false);
                    } else if (actionCmdStr.equals(soundButton3.getActionCommand())) {
                        aSoundModel.setAlarmA(false);
                        aSoundModel.setAlarmB(false);
                        aSoundModel.setAlarmC(true);
                        aSoundModel.setAlarmD(false);
                    } else {
                        aSoundModel.setAlarmA(false);
                        aSoundModel.setAlarmB(false);
                        aSoundModel.setAlarmC(false);
                        aSoundModel.setAlarmD(true);
                    }
                }
            }
            textArea.append("\nApply: " + dateLabel.getText());
            updateDisplay();
//            adjustSound();
        }
    }

    private class DefaultAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            stopSound();
            aTimerModel.setMaxTimeC(20);
            maxTimeC.setText("20");
            aTimerModel.setMaxTimeR(20);
            maxTimeR.setText("20");
            aSoundModel.setVolume(75);
            //sound auto adjusted by changing volume
            volumeTextField.setText("75");
            aTimerModel.setReset(true);
            textArea.append("\nDefault: " + dateLabel.getText());
        }
    }

    /*
     * ViewUserActions constructor used for wiring user actions to GUI elements
     */
    public ViewUserActions() {
        /*
         * Step 2 of 2 for writing user actions.
         * -------------------------------------
         *
         * Once a private inner class has been written for a user action, you
         * can wire it to a GUI element (i.e. one of GUI elements you drew in
         * the DrawnView class and which you gave a memorable public variable
         * name!).
         *
         * Use the following as a template for wiring more user actions.
         */
        startButton.addActionListener(new StartStopAction());
        resetButton.addActionListener(new ResetAction());
        applyButton.addActionListener(new ApplyAction());
        defaultButton.addActionListener(new DefaultAction());
    }

}
