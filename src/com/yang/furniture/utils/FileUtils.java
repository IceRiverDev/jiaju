package com.yang.furniture.utils;

import java.io.File;
import java.time.LocalDateTime;

/**
 * @author 刘洋
 * @date 2022/6/11  7:07 PM
 */
public class FileUtils {
    public static String generateUploadPath() {
        LocalDateTime now = LocalDateTime.now();
        return now.getYear() + File.separator + now.getMonthValue() + File.separator + now.getDayOfMonth();
    }

}
