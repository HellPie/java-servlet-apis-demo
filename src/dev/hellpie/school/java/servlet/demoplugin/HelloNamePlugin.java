package dev.hellpie.school.java.servlet.demoplugin;

import dev.hellpie.school.java.servlet.enums.HTTPCode;
import dev.hellpie.school.java.servlet.enums.HTTPVersion;
import dev.hellpie.school.java.servlet.models.HTTPPacket;
import dev.hellpie.school.java.servlet.models.RequestHTTPPacket;
import dev.hellpie.school.java.servlet.models.ResponseHTTPPacket;
import dev.hellpie.school.java.servlet.plugins.IPlugin;

public class HelloNamePlugin implements IPlugin {

	@Override
	public boolean supports(RequestHTTPPacket requestHTTPPacket) {
		return requestHTTPPacket.getPath().endsWith("dynamic/Hello");
	}

	@Override
	public HTTPPacket serve(RequestHTTPPacket requestHTTPPacket) {
		String name = requestHTTPPacket.getQuery("name");
		if(name == null) name = "Anonymous";

		byte[] body = ("<html><body><h1>Hello " + name + "!</h1></body></html>").getBytes();

		return new ResponseHTTPPacket.Builder()
				.withVersion(HTTPVersion.HTTP_1_1)
				.withCode(HTTPCode.SUCCESS_200)
				.withBody(body)
				.addHeader("Content-Length", String.valueOf(body.length))
				.build();
	}
}
