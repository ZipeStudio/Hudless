package me.zipestudio.hudless.client;

import me.zipestudio.hudless.HLServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HLClient {

    public static Logger LOGGER = LoggerFactory.getLogger(HLServer.MOD_NAME + "/Client");

    public static void onInitializeClient() {
        LOGGER.info("{} Client Initialized", HLServer.MOD_NAME);
    }

}