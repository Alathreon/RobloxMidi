package uk.gsck.midi;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Base64;

import static java.awt.event.KeyEvent.VK_ALT;
import static java.awt.event.KeyEvent.VK_ALT_GRAPH;

public class MidiHandler {
    private MidiDevice device;
    private Callback midiCallback;
    private Receiver receiver;
    private Robot robot;

    public MidiHandler(MidiDevice device) throws AWTException {
        this.device = device;
        this.robot = new Robot();
        this.receiver = new Receiver() {
            @Override
            public void send(MidiMessage message, long timeStamp) {
                midiCallback.callback(message, timeStamp);
                Callback(message, timeStamp);
            }

            @Override
            public void close() {

            }
        };
    }

    @Override
    public String toString() {
        return device.getDeviceInfo().getName();
    }

    public void Open(Callback callback) {
        this.midiCallback = callback;
        try {
            device.getTransmitter().setReceiver(receiver);
            device.open();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void Close() {
        device.close();
    }

    private void Callback(MidiMessage message, long timeStamp) {
        byte[] noteValue = {message.getMessage()[1], message.getMessage()[2]};
        byte[] outputMessage = Base64.getEncoder().encode(noteValue);
        byte[] withoutEnd = { outputMessage[0], outputMessage[1], outputMessage[2]};
        //System.out.println(message.getMessage()[1]);
        //System.out.println(new String(withoutEnd));
        //KeyHandler.pressKey('l', robot);
        robot.keyPress(VK_ALT_GRAPH);
        robot.keyRelease(VK_ALT_GRAPH);
        KeyHandler.sendKeys(robot, new String( withoutEnd ));
    }
}
