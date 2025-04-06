package com.leang.springminiproject.controller;

import com.leang.springminiproject.model.entity.FileMetaData;
import com.leang.springminiproject.model.response.ApiResponse;
import com.leang.springminiproject.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.Instant;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {
	private final FileService fileService;

	@PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponse<FileMetaData>> uploadFile(@RequestParam MultipartFile file){
		FileMetaData fileMetaData = fileService.saveFile(file);
		ApiResponse<FileMetaData> response = ApiResponse.<FileMetaData>builder()
				.success(true)
				.message("File uploaded successfully")
				.status(HttpStatus.CREATED)
				.payload(fileMetaData)
				.timestamps(Instant.now())
				.build();
		return ResponseEntity.ok(response);
	}

	@GetMapping("/preview-file/{file-name}")
	public ResponseEntity<?> getFileByFileName(@PathVariable("file-name") String fileName) throws IOException {
		InputStream inputStream = fileService.getFileByFileName(fileName);
		String contentType = URLConnection.guessContentTypeFromName(fileName);

		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.parseMediaType(contentType))
				.body(inputStream.readAllBytes());
	}

}