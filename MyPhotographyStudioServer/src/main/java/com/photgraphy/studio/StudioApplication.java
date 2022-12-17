package com.photgraphy.studio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class StudioApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(StudioApplication.class, args);
//        log.info("\n-------------------------------------------------------------------\n\t " +
//                "Application StudioApplication is running! " +
//                "\n\t-----------------------------------------------------------------");
//    }

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(StudioApplication.class);

		Environment env = app.run(args).getEnvironment();
		logApplicationStartup(env);
	}

	private static void logApplicationStartup(Environment env) {
		String protocol = "http";
		if(env.getProperty("server.ssl.key-store") != null){
			protocol = "https";
		}
		String serverPort = env.getProperty("server.port");
		String contextPath = env.getProperty("server.servlet.context-path");
		if(StringUtils.isBlank(contextPath)){
			contextPath = "/";
		}
		String hostAddress = "localhost";
		try{
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		}catch (UnknownHostException e){
			log.warn("The host name could not be determined using localhost as fallback");
			e.printStackTrace();
		}
		log.info("\n-------------------------------------------------------------------\n\t"
				+ "Application '{}' is running! Access Urls: \n\t"
				+ "Local: \t\t{}://localhost:{}{}\n\t"
				+ "External: \t{}://{}:{}{}\n\t\t\n-----------------------------------------------------------------",
				env.getProperty("spring.application.name"),
				protocol,
				serverPort,
				contextPath,
				protocol,
				hostAddress,
				serverPort,
				contextPath);
	}
}
