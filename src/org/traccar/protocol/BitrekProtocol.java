package org.traccar.protocol;

import org.traccar.BaseProtocol;
import org.traccar.PipelineBuilder;
import org.traccar.TrackerServer;

import java.util.List;

public class BitrekProtocol extends BaseProtocol {

    public BitrekProtocol() {
        super("bitrek");
    }

    @Override
    public void initTrackerServers(List<TrackerServer> serverList) {
        serverList.add(new TrackerServer(false, getName()) {
            @Override
            protected void addProtocolHandlers(PipelineBuilder pipeline) {
                pipeline.addLast("frameDecoder", new BitrekFrameDecoder());
                pipeline.addLast("objectEncoder", new BitrekProtocolEncoder());
                pipeline.addLast("objectDecoder", new BitrekProtocolDecoder(BitrekProtocol.this, false));
            }
        });
        serverList.add(new TrackerServer(true, getName()) {
            @Override
            protected void addProtocolHandlers(PipelineBuilder pipeline) {
                pipeline.addLast("objectEncoder", new BitrekProtocolEncoder());
                pipeline.addLast("objectDecoder", new BitrekProtocolDecoder(BitrekProtocol.this, true));
            }
        });
    }
}
