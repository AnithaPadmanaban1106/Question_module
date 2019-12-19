package com.revature.revaturequestion.appconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.revaturequestion.connection.ConnectionUtil;

@Configuration
public class AppConfig {
	//@Bean
	public ConnectionUtil connectionUtil() {
		return new ConnectionUtil();
	}
}