package uk.gsck.midi;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Controller implements Callback{

    @FXML
    private ComboBox MIDIInputDevice;
    @FXML
    private ComboBox MIDIOutputDevice;
    @FXML
    private Label messageType;
    @FXML
    private Label messageNote;
    @FXML
    private Label messageValue;

    private MidiHandler currentInputDevice;
    private MidiHandler currentOutputDevice;

    @Override
    public void callback(MidiMessage message, long timestamp) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String messageTypeString = "";

                if (message.getMessage()[0] == -80) {
                    messageTypeString = "Control Change";
                } else if (message.getMessage()[0] == -112) {
                    messageTypeString = "Note On";
                } else if (message.getMessage()[0] == -128) {
                    messageTypeString = "Note Off";
                } else {
                    messageTypeString = "Unknown (" + message.getMessage()[0] + ")";
                }

                messageType.setText("Type: " + messageTypeString);
                messageNote.setText("Note: " + message.getMessage()[1]);
                messageValue.setText("Value: " + message.getMessage()[2]);
            }
        });
    }

    public Controller() {

    }



    public void MIDIInputSelect() {
        if (currentInputDevice != null) {
            currentInputDevice.Close();
        }
        currentInputDevice = (MidiHandler) MIDIInputDevice.getValue();
        currentInputDevice.Open(this);
        System.out.println("Device opened");
    }
    public void MIDIOutputSelect() {
        if (currentOutputDevice != null) {
            currentOutputDevice.Close();
        }
        currentOutputDevice = (MidiHandler) MIDIOutputDevice.getValue();
        currentOutputDevice.Open(this);
    }

    @FXML
    private void initialize(){
        MidiDevice.Info[] midiDeviceInfo = MidiSystem.getMidiDeviceInfo();

        ArrayList<MidiHandler> midiInputDeviceInfo = new ArrayList<>();
        ArrayList<MidiHandler> midiOutputDeviceInfo = new ArrayList<>();

        for ( int i = 0; i < midiDeviceInfo.length; i++) {
            try {
                MidiDevice currentDevice = MidiSystem.getMidiDevice(midiDeviceInfo[i]);

                System.out.println(midiDeviceInfo[i].getName() + ":" + currentDevice.getMaxReceivers() + ":" + currentDevice.getMaxTransmitters());

                MidiHandler newDevice = new MidiHandler(currentDevice);

                if (currentDevice.getMaxReceivers() != 0) {
                    midiOutputDeviceInfo.add(newDevice);
                }

                if (currentDevice.getMaxTransmitters() != 0) {
                    midiInputDeviceInfo.add(newDevice);
                }

            } catch (MidiUnavailableException | AWTException e) {
                e.printStackTrace();
            }
        }

        MIDIInputDevice.getItems().setAll(
                midiInputDeviceInfo
        );
        MIDIOutputDevice.getItems().setAll(
                midiOutputDeviceInfo
        );
    }
}
