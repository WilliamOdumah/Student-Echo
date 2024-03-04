package comp3350.student_echo.presentation.displayReviews;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import comp3350.student_echo.R;
import comp3350.student_echo.objects.Review;
import comp3350.student_echo.objects.StudentAccount;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {
    private List<Review> reviews;
    private StudentAccount currentUser;
    private final ReviewModificationListener reviewModificationListener;

    public ReviewsAdapter(List<Review> reviews, StudentAccount currentUser, ReviewModificationListener rml) {
        this.reviews = reviews;
        this.currentUser = currentUser;
        this.reviewModificationListener = rml;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        displayReview(holder, reviews.get(position));
        holder.editIcon.setOnClickListener(v -> editReviewAction(holder, v));
        holder.deleteIcon.setOnClickListener(v -> deleteReviewAction(holder, v));
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    private void displayReview(ViewHolder holder, Review review) {
        holder.reviewCommentTextView.setText(review.getComment());
        holder.reviewRatingBar.setRating((float) review.getOverallRating());
        // Show or hide the edit and delete buttons based on the user ID
        if (review.getAuthorUsername().equals(currentUser.getUsername())) {
            holder.editIcon.setVisibility(View.VISIBLE);
            holder.deleteIcon.setVisibility(View.VISIBLE);
        } else {
            holder.editIcon.setVisibility(View.GONE);
            holder.deleteIcon.setVisibility(View.GONE);
        }
    }

    private void editReviewAction(ViewHolder holder, View v) {
        // Get the position of the clicked item
        int editPosition = holder.getAdapterPosition();

        // Get the Review object at this position
        Review editReview = reviews.get(editPosition);

        // go to EditReviewActivity page and perform edit
        Intent editIntent = new Intent(v.getContext(), EditReviewActivity.class);
        editIntent.putExtra("REVIEW", editReview);
        ((Activity) v.getContext()).startActivityForResult(editIntent, 0);
    }

    private void deleteReviewAction(ViewHolder holder, View v) {
        // Get the position of the clicked item
        int deletePosition = holder.getAdapterPosition();
        if (deletePosition != RecyclerView.NO_POSITION) {
            // Confirm before deleting
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete Review")
                    .setMessage("Are you sure you want to delete this review?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // Notify listener of the item removal
                        reviewModificationListener.onReviewDeletion(deletePosition);
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
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
