package com.eeit87t3.tickiteasy.image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Chuan
 */
@Component
public class ImageUtil {
	
	/**
	 * 將使用者上傳的圖片存入檔案系統。<br><br>
	 * 備註：請限制使用者上傳的圖片格式為 jpg、jpeg、png、gif 其中之一。<br>
	 * 備註：檔名不可有中文與空白。
	 * 
	 * @param imageDirectory ImageDirectory：Enum，請輸入對應名稱。
	 * @param imageFile MultipartFile：使用者上傳的圖片。
	 * @param baseName String：要儲存的基本檔名（不含副檔名）。
	 * @return sqlPathString - String：可放入資料庫的路徑字串。
	 * @throws IllegalStateException MultipartFile 已被處理或轉移。
	 * @throws IOException 儲存為檔案的過程發生錯誤。
	*/
	public String saveImage(ImageDirectory imageDirectory, MultipartFile imageFile, String baseName) throws IllegalStateException, IOException {
		String fullFileName = baseName + getFileExtension(imageFile.getOriginalFilename());
		File destinationFile = new File(imageDirectory.getDirectory(), fullFileName);
		imageFile.transferTo(destinationFile);
		
		return imageDirectory.getFileNamePrefix() + fullFileName;
	}
	
	/**
	 * 將圖片從檔案系統裡刪除。
	 * 
	 * @param imageDirectory ImageDirectory：Enum，請輸入對應名稱。
	 * @param sqlPathString String：資料庫儲存的路徑字串。
	 * @return result - Boolean：是否刪除成功；false 代表指定檔案原本就不存在。
	*/
	public Boolean deleteImage(ImageDirectory imageDirectory, String sqlPathString) {
		Boolean result = false;
		File imageFile = new File(imageDirectory.getDirectory(), sqlPathString);
		if (imageFile.exists() && imageFile.isFile()) {
			imageFile.delete();
			result = true;
		}
		return result;
	}
	
	/**
	 * 取得圖片的 byte 陣列。
	 * 
	 * @param imageDirectory ImageDirectory：Enum，請輸入對應名稱。
	 * @param sqlPathString String：資料庫儲存的路徑字串。
	 * @return imageByteArray - byte[]：<code>null</code> 代表指定檔案不存在。
	 * @throws IOException 讀取指定檔案的過程發生錯誤。
	*/
	public byte[] getImageByteArray(ImageDirectory imageDirectory, String sqlPathString) throws IOException {
		byte[] imageByteArray = null;
		File imageFile = new File(imageDirectory.getDirectory(), sqlPathString);
		if (imageFile.exists() && imageFile.isFile()) {
			imageByteArray = Files.readAllBytes(imageFile.toPath());
		}
		return imageByteArray;
	}
	
	/**
	 * 取得檔名中的副檔名。<br>
	 * 由 ChatGPT 生成。
	 * 
	 * @param filename String：檔名。
	 * @return fileExtension - String：副檔名；<code>（空字串）</code> 代表沒有找到副檔名。
	*/
    public String getFileExtension(String filename) {
        int lastIndex = filename.lastIndexOf('.');
        if (lastIndex == -1 || lastIndex == filename.length() - 1) {
            return "";  // 沒有找到副檔名
        }
        return filename.substring(lastIndex + 1);  // 回傳副檔名
    }
}
