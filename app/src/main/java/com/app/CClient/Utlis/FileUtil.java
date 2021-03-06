package com.app.CClient.Utlis;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

/**
 * Created by raomengyang on 15/01/2017.
 */

public class FileUtil {
    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    private String filetype;
    public static void saveYuvToSdCardStorage(byte[] imageData) {
        File imageFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (imageFile == null) {
            return;
        }

        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(imageData);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getOutputMediaFile(int type) {
        File imageFileDir = new File(Environment.getExternalStorageDirectory(), "yuv");

        if (!imageFileDir.exists()) {
            if (!imageFileDir.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File imageFile;
        if (type == MEDIA_TYPE_IMAGE) {
            imageFile = new File(imageFileDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            imageFile = new File(imageFileDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }
        return imageFile;
    }
}
