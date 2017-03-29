package dev.hellpie.school.java.servlet.demoplugin;

import dev.hellpie.school.java.servlet.enums.HTTPCode;
import dev.hellpie.school.java.servlet.models.HTTPPacket;
import dev.hellpie.school.java.servlet.models.RequestHTTPPacket;
import dev.hellpie.school.java.servlet.models.ResponseHTTPPacket;
import dev.hellpie.school.java.servlet.plugins.IPlugin;

import java.util.Random;

public class RandomNumberPlugin implements IPlugin {

	private Random random = new Random(System.currentTimeMillis());

	@Override
	public boolean supports(RequestHTTPPacket requestHTTPPacket) {
		return requestHTTPPacket.getPath().endsWith("dynamic/Random");
	}

	@Override
	public HTTPPacket serve(RequestHTTPPacket requestHTTPPacket) {
		byte[] body = String.valueOf(random.nextInt(100)).getBytes();
		return new ResponseHTTPPacket.Builder()
				.withCode(HTTPCode.SUCCESS_200)
				.withBody(body)
				.addHeader("Content-Length", String.valueOf(body.length))
				.build();
	}
}
