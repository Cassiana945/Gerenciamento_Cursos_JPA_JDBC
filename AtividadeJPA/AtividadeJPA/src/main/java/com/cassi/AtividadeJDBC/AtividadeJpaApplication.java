package com.cassi.AtividadeJDBC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.cassi.AtividadeJPA.JpaRepository")
@EnableJdbcRepositories(basePackages = "com.cassi.AtividadeJDBC.repository")
@EntityScan(basePackages = "com.cassi.AtividadeJPA.JpaModel")
public class AtividadeJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtividadeJpaApplication.class, args);
	}

}
