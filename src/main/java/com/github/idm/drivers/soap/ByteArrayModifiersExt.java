package com.github.idm.drivers.soap;

import com.github.idm.drivers.Config;
import com.novell.nds.dirxml.driver.soap.util.ByteArrayModifiers;
import com.novell.nds.dirxml.driver.soap.util.StatusException;
import com.novell.nds.dirxml.driver.soap.util.Tracer;

import java.util.Properties;


public class ByteArrayModifiersExt implements ByteArrayModifiers {

    private static final String VERSION = "1.0.0";
    private Tracer tracer = null;
    private Config config = null;
    private String responseElement = null;
    private String secure = null;

    @Override
    public Properties init(String parameterString, Tracer tracer) throws StatusException {

        Properties prop = new Properties();
        this.tracer = tracer;
        config = new Config(parameterString);

        tracer.trace("--- Loading ByteArrayModifiersExt ---");
        tracer.trace("Greetings from Frode Sjovatsen!");
        tracer.trace("fNIdmSOAPUtils v. " + version());
        tracer.trace("Extention config:");
        if (parameterString.isEmpty()) {
            tracer.trace("No extention config. Using defaults.");
        } else {
            tracer.trace(" " + config.configString());
        }
        tracer.trace("Validating config...");
        secure = (config.get("secure").equals("na")) ? "false" : config.get("secure");
        responseElement = (config.get("responseElement").equals("na")) ? "response" : config.get("responseElement");
        tracer.trace(" secure=" + secure);
        tracer.trace(" responseElement=" + responseElement);
        prop.setProperty("secure", secure);
        tracer.trace("Config seems ok!");
        tracer.trace("--- End loading ByteArrayModifiersExt ---");

        return prop;

    }

    @Override
    public byte[] modifySubscriberResponse(byte abyte0[]) {

        String response = new String(abyte0);

        if (response.startsWith("<")) {
            tracer.trace("ByteArrayModifiersExt: The response seems to be XML. No need to do anything.");
        } else {
            tracer.trace("ByteArrayModifiersExt: The response doesn't seem to be XML. Fixing response.");
            response = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><" + responseElement + ">" + response + "</" + responseElement + ">";
            tracer.trace("ByteArrayModifiersExt: New reponse: " + response);
        }

        return response.getBytes();
    }

    @Override
    public byte[] modifySubscriberRequest(byte abyte0[]) {

        return abyte0;
    }

    @Override
    public byte[] modifyPublisherRequest(byte abyte0[]) {

        return abyte0;
    }

    @Override
    public byte[] modifyPublisherResponse(byte abyte0[]) {

        return abyte0;
    }

    public String version() {
        return VERSION;
    }
}