package comp3350.student_echo.presentation;

import static comp3350.student_echo.presentation.ViewCourseActivity.EDIT_REVIEW_REQUEST_CODE;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.business.AccessReviews;
import comp3350.student_echo.objects.Course;
import comp3350.student_echo.objects.Instructor;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.StudentAccount;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private List<? extends Review> reviews;
    private Course course;
    private Instructor instructor;
    private AccessReviews accessReviews;
    private boolean isCourse = false;
    private StudentAccount currentUser;
    public ReviewsAdapter(List<? extends Review> reviews, StudentAccount currentUser, Object type) {
        accessReviews = new AccessReviews();
        this.reviews = reviews;
        this.currentUser = currentUser;
        if (type instanceof Course){
            this.course = (Course) type;
            isCourse = true;
        }
        else {
            this.instructor= (Instructor) type;
        }
    }


    // Method to update the list of reviews
    public void setReviews(List<? extends Review> newReviews, StudentAccount currentUser) {
        this.reviews = newReviews;
        this.currentUser =currentUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.reviewCommentTextView.setText(review.getComment());
        holder.reviewRatingBar.setRating((float) review.getOverallRating());
        // Show or hide the edit and delete buttons based on the user ID
        if (review.getWrittenBy().equals(currentUser.getUsername())) {
            holder.editIcon.setVisibility(View.VISIBLE);
            holder.deleteIcon.setVisibility(View.VISIBLE);
        } else {
            holder.editIcon.setVisibility(View.GONE);
            holder.deleteIcon.setVisibility(View.GONE);
        }
        holder.editIcon.setOnClickListener(v -> {
            // Get the position of the clicked item
            int editPosition = holder.getAdapterPosition();

            // Get the Review object at this position
            Review editReview = reviews.get(editPosition);

            // Intent to start an activity to edit the review, passing the review details
            Intent editIntent = new Intent(v.getContext(), EditReviewActivity.class);
            editIntent.putExtra("REVIEW_ID", editReview.getId()); // Pass the review's unique ID
            if (isCourse){
                editIntent.putExtra("REVIEW_TYPE",course);
            }
            else {
                editIntent.putExtra("REVIEW_TYPE",instructor);
            }
            // Start the activity with a request code
            ((Activity) v.getContext()).startActivityForResult(editIntent, EDIT_REVIEW_REQUEST_CODE);
        });

        holder.deleteIcon.setOnClickListener(v -> {
            // Get the position of the clicked item
            int deletePosition = holder.getAdapterPosition();
            if (deletePosition != RecyclerView.NO_POSITION) {
                // Confirm before deleting
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Delete Review")
                        .setMessage("Are you sure you want to delete this review?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            // Delete the review from the database
                            Review reviewToDelete = reviews.get(deletePosition);
                            if (isCourse) {
                                accessReviews.deleteCourseReview(reviewToDelete.getId());
                            }
                            else {
                                accessReviews.deleteInstructorReview(reviewToDelete.getId());
                            }

                            // Remove the review from the adapter's list
                            reviews.remove(deletePosition);
                            Toast.makeText(v.getContext(), "Review deleted", Toast.LENGTH_SHORT).show();
                            // Notify the adapter of the item removal
                            notifyItemRemoved(deletePosition);
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView reviewCommentTextView;
        public RatingBar reviewRatingBar;
        public ImageView editIcon;
        public ImageView deleteIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            reviewCommentTextView = itemView.findViewById(R.id.reviewCommentTextView);
            reviewRatingBar = itemView.findViewById(R.id.reviewRatingBar);
            editIcon = itemView.findViewById(R.id.editIcon);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
        }
    }
}
