package comp3350.student_echo.business.access;

import java.util.List;

import comp3350.student_echo.objects.reviewableItems.ReviewableItem;

public interface AccessReviewableItems {
    List<ReviewableItem> getItems();
    List<ReviewableItem> filter(String input, List<ReviewableItem> items);
}