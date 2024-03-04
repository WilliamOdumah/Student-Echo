package comp3350.student_echo.business;

import java.util.List;

import comp3350.student_echo.objects.ReviewableItem;

public interface AccessReviewableItems {
    List<ReviewableItem> getItems();
    List<ReviewableItem> filter(String input, List<ReviewableItem> items);
}