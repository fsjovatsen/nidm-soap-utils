package com.github.idm.drivers.soap

import com.novell.nds.dirxml.driver.soap.util.Tracer
import spock.lang.Specification


class ByteArrayModifiersExtSpec extends Specification {

    def "Check if init return property 'secure'"() {
        given:
        def tracer = Mock(Tracer)
        def byteArrayModifiersExt = new ByteArrayModifiersExt()

        when:
        def props = byteArrayModifiersExt.init("secure=na test=test", tracer)

        then:
        props.getProperty("secure") == "false"
        _ * tracer.trace(_ as String)
    }

    def "Modify Subscriber Response if not XML"() {
        given:
        def response = "test"
        def tracer = Mock(Tracer)
        def byteArrayModifiersExt = new ByteArrayModifiersExt(tracer: tracer, responseElement: "response")

        when:
        def modifiedResponse = new String(byteArrayModifiersExt.modifySubscriberResponse(response.bytes))

        then:
        modifiedResponse.startsWith("<")
        _ * tracer.trace(_ as String)
    }

    def "Modify Subscriber Response if XML"() {
        given:
        def response = "<xml>test</xml>"
        def tracer = Mock(Tracer)
        def byteArrayModifiersExt = new ByteArrayModifiersExt(tracer: tracer, responseElement: "response")

        when:
        def modifiedResponse = new String(byteArrayModifiersExt.modifySubscriberResponse(response.bytes))

        then:
        modifiedResponse.contains("<response>") == false
    }
}
