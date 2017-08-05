package com.semnome.banco;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * Inicializa o banco de dados caso ele não exista
 * 
 * @author Daniel Ferraz
 * @since 4 de ago de 2017
 *
 */
@Component
public class BuildDataBase {

	@PostConstruct
	public void init() {
		Connection c = null;
		Statement stmt = null;
		String databasePath = System.getProperty("java.io.tmpdir") + "semnome.db";
		File dataBaseFile = new File(databasePath);
		if (!dataBaseFile.exists()) {
			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
				System.out.println("Opened database successfully");

				stmt = c.createStatement();
				String sql = "CREATE TABLE PESSOA " + "(ID INT PRIMARY KEY   NOT NULL," + " NOME  TEXT  NOT NULL, "
						+ " DOCUMENTO	TEXT   NOT NULL, " + " TIPO  CHAR(2) NOT NULL )";
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				dataBaseFile.delete();
				System.exit(0);
			}
			System.out.println("Table created successfully");
		}
	}

}
