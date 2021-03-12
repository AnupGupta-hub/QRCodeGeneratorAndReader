package com.example.QRCode.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.QRCode.service.QRCodeService;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

/**
 * @author Anup Gupta Controller class to generate QR code and validate
 *
 */
@RestController
@RequestMapping(value = "v1/qrcode")
public class QRCodeController {

	@Autowired
	QRCodeService qrCodeService;

	@RequestMapping(method = RequestMethod.POST, value = "/generate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getMockDetails()
			throws IOException, ParseException, WriterException {
		try {
			return ResponseEntity.ok().body(qrCodeService.generateQRCode());
		} catch (IOException e) {
			return ResponseEntity.notFound().build();

		}
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(method = RequestMethod.GET, value = "/validate/{generatedCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> postMockDetails(@PathVariable String generatedCode, HttpServletRequest request)
			throws IOException, ParseException, NotFoundException, WriterException {
		try {
			String res = qrCodeService.validateQRCode(generatedCode);
			if (!StringUtils.isEmpty(res) && !res.equalsIgnoreCase("badRequest")) {
				return ResponseEntity.ok().body(res);
			}
			return ResponseEntity.badRequest().body(res);
		} catch (IOException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
