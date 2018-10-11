package org.traccar.protocol;

import org.junit.Test;
import org.traccar.ProtocolTest;
import org.traccar.model.Command;

public class BitrekProtocolEncoderTest extends ProtocolTest {

    @Test
    public void testEncode() throws Exception {

        BitrekProtocolEncoder encoder = new BitrekProtocolEncoder();

        Command command = new Command();
        command.setDeviceId(1);
        command.setType(Command.TYPE_CUSTOM);
        command.set(Command.KEY_DATA, "setdigout 11");

        verifyCommand(encoder, command, binary("00000000000000160C01050000000E7365746469676F75742031310D0A010000E258"));

    }
}
