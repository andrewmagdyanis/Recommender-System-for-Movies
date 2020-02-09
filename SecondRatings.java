/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.ArrayList;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;

    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    private double getAverageByID(String id, int minimalRaters){
        double ans = 0.0;
        Movie specifiedMovie ;
        int numOfRatesToThisFilm = 0;
        for(EfficientRater plainRater : myRaters){
            if(plainRater.hasRating(id)){
                numOfRatesToThisFilm++;
                ans += plainRater.getRating(id);
            }
        }
        if(numOfRatesToThisFilm>minimalRaters){
            ans = ans/(double) numOfRatesToThisFilm;
        }
        else {
            ans=0.0;
        }
        return ans;
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratingList = new ArrayList<>();
        for(Movie movie: myMovies){
            String movieID = movie.getID();
            double avgRating = getAverageByID(movieID,minimalRaters);
            if(avgRating>0.0) {
                Rating rating = new Rating(movieID, avgRating);
                ratingList.add(rating);
            }
        }
        //System.out.println("rating list: "+ratingList);
        return ratingList;
    }

    public String getTitle (String movieId){
        String title ="title is not found";
        for(Movie movie: myMovies){
            if(movie.getID().equals(movieId)){
                title = movie.getTitle();
            }
        }
        return  title;
    }

    public String getID (String movieTitle){
        String ID ="NO SUCH TITLE";
        for(Movie movie: myMovies){
            if(movie.getTitle().equals(movieTitle)){
                ID = movie.getID();
            }
        }
        return  ID;
    }


}
