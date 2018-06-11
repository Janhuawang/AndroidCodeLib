package me.main.demo.firebase;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.camera2.CameraAccessException;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 创造一个FirebaseVisionImage
 * 作者：wjh on 2018/6/6 16:57
 */
public class FirebaseVisionImageUtil {

    /**
     * 从Bitmap对象创建FirebaseVisionImage 对象
     *
     * @param bitmap
     * @return
     */
    public static FirebaseVisionImage createFireImage(Bitmap bitmap) {
        return FirebaseVisionImage.fromBitmap(bitmap);
    }

    /**
     * 从media.Image对象创建FirebaseVisionImage对象
     *
     * @param image
     * @param cameraId
     * @param activity
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static FirebaseVisionImage createFireImage(Image image, String cameraId, Activity activity) {
        try {
            return FirebaseVisionImage.fromMediaImage(image, RotationUtil.getRotationCompensation(cameraId, activity, activity));
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从ByteBuffer创建一个FirebaseVisionImage对象
     *
     * @param buffer
     * @param width
     * @param height
     * @param cameraId
     * @param activity
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static FirebaseVisionImage createFireImage(ByteBuffer buffer, int width, int height, String cameraId, Activity activity) {
        FirebaseVisionImageMetadata metadata = null;
        try {
            metadata = new FirebaseVisionImageMetadata.Builder()
                    .setWidth(width)
                    .setHeight(height)
                    .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_NV21)
                    .setRotation(RotationUtil.getRotationCompensation(cameraId, activity, activity))
                    .build();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


        return FirebaseVisionImage.fromByteBuffer(buffer, metadata);
    }

    /**
     * 从字节数组创建一个FirebaseVisionImage对象
     *
     * @param array
     * @param width
     * @param height
     * @param cameraId
     * @param activity
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static FirebaseVisionImage createFireImage(byte[] array, int width, int height, String cameraId, Activity activity) {
        //FirebaseVisionImage.fromByteArray(byteArray, metadata);
        return createFireImage(ByteBuffer.wrap(array), width, height, cameraId, activity);
    }

    /**
     * 从文件创建FirebaseVisionImage对象
     *
     * @param context
     * @param uri
     * @return
     */
    public static FirebaseVisionImage createFireImage(Context context, Uri uri) {
        FirebaseVisionImage image = null;
        try {
            image = FirebaseVisionImage.fromFilePath(context, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

}
