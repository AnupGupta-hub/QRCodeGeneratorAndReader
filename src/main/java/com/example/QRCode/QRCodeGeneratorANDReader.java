package com.example.QRCode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Anup Gupta Application to generate and validate QR Code
 *
 */
@SpringBootApplication
@ComponentScan("com.example.*")
public class QRCodeGeneratorANDReader {
	public static void main(String[] args) {
		SpringApplication.run(QRCodeGeneratorANDReader.class, args);
	}
}
