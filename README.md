# SOAP Driver extention for NetIQ Identity Manager

Implements the `modifySubscriberResponse()` method in the `ByteArrayModifiers` interface.

The extention checks if the response from the SOAP ws is XML. If it is not XML it wraps the response in XML.

For more information see:

 * (NetIQ documentation)[https://www.netiq.com/documentation/idm45drivers/soapdriver/data/chdhjcej.html]
 * (Javadocs for the ByteArrayModifiers interface)[https://www.novell.com/documentation/dirxmldrivers/javadoc/api/]

## Install
* (Download)[] the jar file
* Copy the jar file to the IDM server
* See (documentation)[https://www.netiq.com/documentation/idm45drivers/soapdriver/data/bvpan6m.html] on how to configure the extention in the driver

### Java class parameter
`com.github.idm.drivers.soap.ByteArrayModifiersExt`

### Init parameter

The format should be `secure=false responseElement=response`

| Key             | Description                                                                                                                                            | Default value |
|-----------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|---------------|
| secure          | `true` if SOAP service is running on https. For more information see (documentation)[https://www.novell.com/documentation/dirxmldrivers/javadoc/api/]. | `false`       |
| responseElement | Name of the element to wrap around the text. Not with <>.                                                                                              | `response`    |

## Build

If you want to build it your self:
* Create a directry called ´libs´ in the root of the project
* Copy `SOAPShim.jar` into `libs`
* Run `./gradlew clean build`

