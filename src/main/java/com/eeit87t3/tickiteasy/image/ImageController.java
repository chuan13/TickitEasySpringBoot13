package com.eeit87t3.tickiteasy.image;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StreamUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Chuan
 */
@Controller
public class ImageController {

	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private ImageUtil imageUtil;
	
	// 取得圖片
	@ResponseBody
	@GetMapping("/images/{folderName}/{fileName}")
    public ResponseEntity<?> getImage(
    		@PathVariable String folderName,
    		@PathVariable String fileName) {
		String basePath = System.getProperty("user.dir") + "/images/";
        Resource resource = resourceLoader.getResource("file:" + basePath + folderName + "/" + fileName);
        try {
			byte[] imageFile = StreamUtils.copyToByteArray(resource.getInputStream());
			
			HttpHeaders headers = new HttpHeaders();
			MediaType determinedMediaType = determineMediaType(fileName);
			headers.setContentType(determinedMediaType);
			
			return new ResponseEntity<>(imageFile, headers, HttpStatus.OK);
		} catch (IOException e) {
			System.out.println("在 /images/ 下找不到指定的圖片檔案。");
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }
	
	// 上傳圖片
	@PostMapping("/images/test/upload")
	public String uploadImage(
			@RequestParam MultipartFile imageFile) {
//		String uuidFileName = UUID.randomUUID().toString() + "." +  getFileExtension(imageFile.getOriginalFilename());
//		try {
//			File uploadDirectory = new File(System.getProperty("user.dir") + "/images/test");
//			if (!uploadDirectory.exists()) {
//				uploadDirectory.mkdirs();
//			}
//			
//			File destinationFile = new File(uploadDirectory, uuidFileName);
//			imageFile.transferTo(destinationFile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		String fileName = UUID.randomUUID().toString();
		String sqlPathString = null;
		try {
			sqlPathString = imageUtil.saveImage(ImageDirectory.TEST, imageFile, fileName);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return "redirect:/images/test/" + sqlPathString;
	}
	
	
	
    private MediaType determineMediaType(String filename) {
        String extension = imageUtil.getFileExtension(filename);

        switch (extension.toLowerCase()) {
            case "jpg":
            case "jpeg":
                return MediaType.IMAGE_JPEG;
            case "png":
                return MediaType.IMAGE_PNG;
            case "gif":
                return MediaType.IMAGE_GIF;
            default:
                return MediaType.APPLICATION_OCTET_STREAM; // 回傳二進制 stream
        }
    }
}
