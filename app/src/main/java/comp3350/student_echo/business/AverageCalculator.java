package comp3350.student_echo.business;

import android.annotation.SuppressLint;

import java.util.List;

import comp3350.student_echo.objects.Review;

public class AverageCalculator {

    @SuppressLint("DefaultLocale")
    public static String calcAverageOverallRating(List<? extends Review> reviews) {
        if(reviews.size() == 0) return "0.0";

        double total = 0;
        for(Review r : reviews) {
            total += r.getOverallRating();
        }
        double res = total / reviews.size();

        // format result to single decimal
        return String.format("%.1f", res);
    }

    @SuppressLint("DefaultLocale")
    public static String calcAverageDifficultyRating(List<? extends Review> reviews) {
        if(reviews.size() == 0) return "0.0";

        double total = 0;
        for(Review r : reviews) {
            total += r.getDifficultyRating();
        }
        double res = total / reviews.size();

        // format result to single decimal
        return String.format("%.1f", res);
    }
}
