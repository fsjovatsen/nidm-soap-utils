package com.github.idm.drivers

import spock.lang.Specification


class ConfigSpec extends Specification {

    def "Test config object"() {
        when:
        def config = new Config("param1=val1 param2=val2")

        then:
        config.get("param1") == "val1"
        config.get("param2") == "val2"
    }
}
