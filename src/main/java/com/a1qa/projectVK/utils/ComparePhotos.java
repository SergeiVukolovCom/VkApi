package com.a1qa.projectVK.utils;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class ComparePhotos {

    static {
        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
    }

    public boolean compareTwoPhoto() {
        boolean flag = true;
        try {
            Mat image1 = Highgui.imread("src/test/resources/avatar.png");
            Mat image2 = Highgui.imread("src/test/resources/screenshot.png");
            Mat grayImage1 = new Mat();
            Mat grayImage2 = new Mat();
            Imgproc.cvtColor(image1, grayImage1, Imgproc.COLOR_BGR2GRAY);
            Imgproc.cvtColor(image2, grayImage2, Imgproc.COLOR_BGR2GRAY);
            Mat diffImage = new Mat();
            Core.absdiff(grayImage1, grayImage2, diffImage);
            Mat threshImage = new Mat();
            Imgproc.threshold(diffImage, threshImage, 0, 255, Imgproc.THRESH_BINARY);
            double sum = Core.sumElems(threshImage).val[0];
            double threshold = 10000.0;
            if (!(sum < threshold)) {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
