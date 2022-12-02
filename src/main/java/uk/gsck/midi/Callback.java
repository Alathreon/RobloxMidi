package uk.gsck.midi;

import javax.sound.midi.MidiMessage;

public interface Callback {
    public void callback(MidiMessage message, long timestamp);
}
