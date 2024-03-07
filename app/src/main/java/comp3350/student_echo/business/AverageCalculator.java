package comp3350.student_echo.business;

import android.annotation.SuppressLint;

import java.util.List;

import comp3350.student_echo.objects.Review;

public class AverageCalculator {

    @SuppressLint("DefaultLocale")
    public static double calcAverageOverallRating(List<? extends Review> reviews) {
        if(reviews==null ||reviews.size() == 0) return 0;

        double total = 0;
        for(Review r : reviews) {
            total += r.getOverallRating();
        }
        return total / reviews.size();
    }

    @SuppressLint("DefaultLocale")
    public static double calcAverageDifficultyRating(List<? extends Review> reviews) {
        if(reviews==null || reviews.size() == 0) return 0;

        double total = 0;
        for(Review r : reviews) {
            total += r.getDifficultyRating();
        }
        return  total / reviews.size();
    }
}
