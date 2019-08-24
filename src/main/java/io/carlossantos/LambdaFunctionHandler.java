package io.carlossantos;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.net.InetAddress;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {

	String host;

	{
		// Hostname ou IP destino
		host = "host.domain.com";
	}

	@Override
	public String handleRequest(Object input, Context context) {
		LambdaLogger logger = context.getLogger();
		StringBuilder result = new StringBuilder("");

		try {
			if (InetAddress.getByName(host).isReachable(5000))
				result.append("Conectividade AWS para Destino OK: " + host);
			else
				result.append("Conectividade AWS para Destino com falha: " + host);
		} catch (Exception e) {
			result.append("Conectividade AWS para Destino com falha: " + host + " - " + e);
		}

		logger.log(result.toString());
		return result.toString();
	}
}
