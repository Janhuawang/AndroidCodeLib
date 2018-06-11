package me.main.demo.firebase;

import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.common.FirebaseVisionPoint;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetectorOptions;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceLandmark;

import java.util.List;

import baselib.activity.BaseUINavigateActivity;
import baselib.util.ToastUtil;
import me.main.R;

/**
 * 面部识别
 * 作者：wjh on 2018/6/6 16:34
 */
public class FaceDetectorActivity extends BaseUINavigateActivity {
    private final static int OVERLAY_PERMISSION_REQ_CODE = 1;

    private FirebaseVisionFaceDetector detector; // 图片监测器

    @Override
    public int getViewId() {
        return R.layout.activity_demo_face_detector;
    }

    @Override
    public int getMenuRes() {
        return 0;
    }

    @Override
    public void onCreateMenu(Menu menu) {

    }

    @Override
    public boolean onMenuItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initText() {
        setToolbarTile("人脸识别测试");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        initFaceDetector();

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
    }

    @Override
    public void onIntentEvent(@NonNull Intent intent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            Uri uri = data.getData();
            analyseImage(FirebaseVisionImageUtil.createFireImage(this, uri));
        }
    }

    /**
     * 初始化检测器
     */
    private void initFaceDetector() {
        detector = FirebaseVision.getInstance()
                .getVisionFaceDetector(getFaceDetectorOptions());
    }

    /**
     * 人脸识别的默认参数设定
     *
     * @return
     */
    private FirebaseVisionFaceDetectorOptions getFaceDetectorOptions() {
        return new FirebaseVisionFaceDetectorOptions.Builder()
                .setModeType(FirebaseVisionFaceDetectorOptions.ACCURATE_MODE) // 识别模式 取决于您在人脸识别中是喜欢速度或准确性
                .setLandmarkType(FirebaseVisionFaceDetectorOptions.ALL_LANDMARKS)//识别特征点 是否尝试识别面部“特征点”：眼睛，耳朵，鼻子，脸颊，嘴巴
                .setClassificationType(FirebaseVisionFaceDetectorOptions.ALL_CLASSIFICATIONS) // 辨认脸部 是否将人脸分类为“微笑”和“睁眼”等类别。
                .setMinFaceSize(0.2f) // 脸部最小识别 最小识别多大的脸部，与照片大小有关 默认: 0.1f
                .setTrackingEnabled(true) // 允许人脸追踪 是否分配人脸 ID ，可用于跟踪图像中的人脸 默认false
                .build();
    }


    /**
     * 分析一张图片
     *
     * @param image
     */
    private void analyseImage(FirebaseVisionImage image) {
        Task<List<FirebaseVisionFace>> result =
                detector.detectInImage(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<List<FirebaseVisionFace>>() {
                                    @Override
                                    public void onSuccess(List<FirebaseVisionFace> faces) {// 任务成功
                                        ToastUtil.showText("任务成功--" + faces.size());
                                        for (FirebaseVisionFace face : faces) {
                                            Rect bounds = face.getBoundingBox();
                                            float rotY = face.getHeadEulerAngleY();  // 头部转向右rotY角
                                            float rotZ = face.getHeadEulerAngleZ();  // 头部转向上rotZ角

                                            // 如果特征点识别开启了（嘴，耳，眼，脸颊，还有鼻子可以检测）：
                                            FirebaseVisionFaceLandmark leftEar = face.getLandmark(FirebaseVisionFaceLandmark.LEFT_EAR);
                                            if (leftEar != null) {
                                                FirebaseVisionPoint leftEarPos = leftEar.getPosition();
                                            }

                                            // 如果辨认功能开启了:
                                            if (face.getSmilingProbability() != FirebaseVisionFace.UNCOMPUTED_PROBABILITY) {
                                                float smileProb = face.getSmilingProbability();
                                            }
                                            if (face.getRightEyeOpenProbability() != FirebaseVisionFace.UNCOMPUTED_PROBABILITY) {
                                                float rightEyeOpenProb = face.getRightEyeOpenProbability();
                                            }

                                            // 如果脸部追踪开启了:
                                            if (face.getTrackingId() != FirebaseVisionFace.INVALID_ID) {
                                                int id = face.getTrackingId();
                                            }
                                        }
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // 任务失败并且报错
                                        ToastUtil.showText("任务失败并且报错--" + e.getMessage());
                                    }
                                });
    }
}
