import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;

    public ThirdRatings() {
        // default constructor
        this( "ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsfile);
    }

    public void initMyRaters (String ratingsfile){
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsfile);
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
        if(numOfRatesToThisFilm>=minimalRaters){
            ans = ans/(double) numOfRatesToThisFilm;
        }
        else {
            ans=0.0;
        }
        return ans;
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratingList = new ArrayList<>();
        ArrayList<String> moviesIDList = MovieDatabase.filterBy(new TrueFilter());
        for(String movieID: moviesIDList){
            double avgRating = getAverageByID(movieID,minimalRaters);
            if(avgRating>0.0) {
                Rating rating = new Rating(movieID, avgRating);
                ratingList.add(rating);
            }
        }
        //System.out.println("rating list: "+ratingList);
        return ratingList;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating>  avgRatingsByFilter = new ArrayList<>();
        ArrayList<String> moviesIDList = MovieDatabase.filterBy(filterCriteria);
        for(String movieID: moviesIDList){
            double avgRating = getAverageByID(movieID,minimalRaters);
            if(avgRating>0.0) {
                Rating rating = new Rating(movieID, avgRating);
                avgRatingsByFilter.add(rating);
            }
        }
        return avgRatingsByFilter;
    }
}
