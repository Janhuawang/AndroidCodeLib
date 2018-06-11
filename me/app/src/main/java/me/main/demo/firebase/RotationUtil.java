package me.main.demo.firebase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.util.SparseIntArray;
import android.view.Surface;

import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;

import static android.content.Context.CAMERA_SERVICE;

/**
 * 图片调整的角度
 * 作者：wjh on 2018/6/6 17:03
 */
public class RotationUtil {

    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    /**
     * 得到当前图像需要补偿的角度
     */
    @SuppressLint("NewApi")
    public static int getRotationCompensation(String cameraId, Activity activity, Context context)
            throws CameraAccessException {
        // 得到设备当前与原始的角度的旋转差值
        // 然后照片一定要旋转回去相对的差值
        int deviceRotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int rotationCompensation = ORIENTATIONS.get(deviceRotation);

        // 在大多数的设备上，传感器的方向是90度。但是对于
        // 少数设备，这个值是270度。那么对于这些270度的设备
        // 必须让照片旋转额外的180 ((270 + 270) % 360) 度.
        CameraManager cameraManager = (CameraManager) context.getSystemService(CAMERA_SERVICE);
        int sensorOrientation = cameraManager
                .getCameraCharacteristics(cameraId)
                .get(CameraCharacteristics.SENSOR_ORIENTATION);
        rotationCompensation = (rotationCompensation + sensorOrientation + 270) % 360;

        // 返回相关的FirebaseVisionImageMetadata rotation值。
        int result;
        switch (rotationCompensation) {
            case 0:
                result = FirebaseVisionImageMetadata.ROTATION_0;
                break;
            case 90:
                result = FirebaseVisionImageMetadata.ROTATION_90;
                break;
            case 180:
                result = FirebaseVisionImageMetadata.ROTATION_180;
                break;
            case 270:
                result = FirebaseVisionImageMetadata.ROTATION_270;
                break;
            default:
                result = FirebaseVisionImageMetadata.ROTATION_0;
        }
        return result;
    }
}
