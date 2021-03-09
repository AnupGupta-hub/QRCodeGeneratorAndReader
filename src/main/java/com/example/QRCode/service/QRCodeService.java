package com.example.QRCode.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import com.google.zxing.Result;

@Service
public class QRCodeService {

	@SuppressWarnings("deprecation")
	public String generateQRCode(String data) throws WriterException, IOException {
		String path = System.getProperty("user.dir") + "//resources//QRCode.png";
		BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes("UTF-8"), "UTF-8"),
				BarcodeFormat.QR_CODE, 200, 200);
		MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
		return "success";
	}

	public String validateQRCode() throws WriterException, IOException, NotFoundException {
		String path = System.getProperty("user.dir") + "//resources//QRCode.png";
		BinaryBitmap binaryBitmap = new BinaryBitmap(
				new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))));
		Result rslt = new MultiFormatReader().decode(binaryBitmap);
		return rslt.getText();
	}

}
