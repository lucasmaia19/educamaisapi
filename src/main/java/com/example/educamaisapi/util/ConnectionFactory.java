package com.example.educamaisapi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ConnectionFactory {

		@Autowired
		public Environment env;

		public Connection getConexao() {

			try {

				return DriverManager.getConnection(
						env.getProperty("spring.datasource.url"), 
						env.getProperty("spring.datasource.username"), 
						env.getProperty("spring.datasource.password"));

			} catch(SQLException e) {
				throw new RuntimeException(e);
			}

		}

		public Connection getConexao(String url, String username, String password) {

			try {

				return DriverManager.getConnection(url, username, password); 

			} catch(SQLException e) {
				throw new RuntimeException(e);
			}

		}
}
