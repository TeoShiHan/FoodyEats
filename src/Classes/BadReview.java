package Classes;

public class BadReview extends Review{
    private String desc;    

    public BadReview(String desc) {
        this.desc = desc;
    }

    @Override
    public void displayReviewImage(){
        // display bad review Image
    }
}