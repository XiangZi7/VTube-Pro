package com.vtube;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@Slf4j
@SpringBootApplication
public class AdminApplication implements ApplicationListener<WebServerInitializedEvent> {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

	@SneakyThrows
	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		WebServer server = event.getWebServer();
		WebServerApplicationContext context = event.getApplicationContext();
		Environment env = context.getEnvironment();
		String ip = InetAddress.getLocalHost().getHostAddress();
		int port = server.getPort();
		String contextPath = env.getProperty("server.servlet.context-path");
		if (contextPath == null) {
			contextPath = "";
		}

		log.info("\n---------------------------------------------------------\n" +
				"\tApplication is running! Access address:\n" +
				"\tLocal:\t\thttp://localhost:{}" +
				"\n\tExternal:\thttp://{}:{}{}" +
				"\n\t管理员接口文档:\thttp://localhost:{}/swagger-ui/index.html#/" +
				"\n---------------------------------------------------------\n", port, ip, port, contextPath, port);
	}
}
