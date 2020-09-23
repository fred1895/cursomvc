package com.coursespringboot.workshop.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.coursespringboot.workshop.services.exceptions.FileException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String filename = multipartFile.getOriginalFilename();
			InputStream inputStream;
			String contentType = multipartFile.getContentType();
			inputStream = multipartFile.getInputStream();
			return uploadFileAWS(filename, inputStream, contentType);
		} catch (IOException e) {
			throw new FileException("Erro ao buscar arquivo " + e.getMessage());
		}
	}

	private URI uploadFileAWS(String filename, InputStream inputStream, String contentType) {

		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(contentType);

			log.info("Iniciando upload");
			s3Client.putObject(new PutObjectRequest(bucketName, filename, inputStream, metadata));
			log.info("Upload finalizado");

			return s3Client.getUrl(bucketName, filename).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URI" + e.getMessage());
		}
	}
}
