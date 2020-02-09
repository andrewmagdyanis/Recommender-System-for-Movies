import java.util.ArrayList;

public class MovieRunnerAverage {
    public void printAverageRatings(){
        //SecondRatings secondRatings = new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
        SecondRatings secondRatings = new SecondRatings();

        int numOfMovies = secondRatings.getMovieSize();
        int numOfRaters = secondRatings.getRaterSize();
        System.out.println("number of movies: "+numOfMovies+" number of raters: "+numOfRaters);
        System.out.println("Sorting by rating ...");
        double movieRate = 0.0;
        String movieTitle = "";
        ArrayList<Rating> avgRatingsList = secondRatings.getAverageRatings(11);
        avgRatingsList.sort(Rating::compareTo);
        for(Rating rating :avgRatingsList){
            movieTitle = secondRatings.getTitle(rating.getItem());
            movieRate = rating.getValue();
            System.out.println( movieRate+", "+movieTitle);
        }
    }
    public void getAverageRatingOneMovie(){
        String MovieTitle  = "Vacation";
        double MovieRating = 0.0;
        //SecondRatings secondRatings = new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
        SecondRatings secondRatings = new SecondRatings();
        ArrayList<Rating> avgRatingsList = secondRatings.getAverageRatings(11);
        for(Rating rating:avgRatingsList){
            if(MovieTitle.equals(secondRatings.getTitle(rating.getItem()))){
                MovieRating = rating.getValue();
            }
        }
        System.out.println("Rating for the film "+MovieTitle+" is: "+MovieRating);
    }

}
