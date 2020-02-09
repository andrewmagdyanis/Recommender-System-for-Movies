import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FourthRatings {
    /*here we will use the Movie database and Rater Database instead of instance variables*/

    private double getAverageByID(String id, int minimalRaters){
        double ans = 0.0;
        Movie specifiedMovie ;
        int numOfRatesToThisFilm = 0;
        //RaterDatabase raterDatabase = new RaterDatabase();
        for(Rater rater : RaterDatabase.getRaters()){
            if(rater.hasRating(id)){
                numOfRatesToThisFilm++;
                ans += rater.getRating(id);
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
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
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
        System.out.println("Movie list is filtered... ");
        for(String movieID: moviesIDList){
            double avgRating = getAverageByID(movieID,minimalRaters);
            if(avgRating>0.0) {
                Rating rating = new Rating(movieID, avgRating);
                avgRatingsByFilter.add(rating);
            }
        }
        return avgRatingsByFilter;
    }
    public double dotProduct(Rater me , Rater r){
        ArrayList<String> myList = me.getItemsRated();
        // System.out.println("myList: "+myList);
        ArrayList<String> raterList = r.getItemsRated();;
        //System.out.println("raterList: "+raterList);
        ArrayList<Double> productList = new ArrayList<Double>();
        double product = 0.0;
        for(int i =0;i<myList.size();i++){
            String filmID = myList.get(i);
            if(r.hasRating(filmID)) { //if the rater rated tat film i Rated
                productList.add((me.getRating(filmID) - 5) * (r.getRating(filmID) - 5));
                product += (me.getRating(filmID) - 5) * (r.getRating(filmID) - 5);
                //System.out.println("part of product: "+(me.getRating(filmID) - 5) * (r.getRating(filmID) - 5));
            }
        }
        //System.out.println("Product List: \n"+productList);
        //System.out.println("Product: \n"+product);
        return product;
    }

    public ArrayList<Rating> getSimilarities(String raterID){
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        for(Rater rater: RaterDatabase.getRaters()){
            if(rater.getID().equals(raterID)){
                continue;
            }
            double product = dotProduct(RaterDatabase.getRater(raterID),rater);
            if(product>0.0) {// take only the +ve dot products
                Rating rating = new Rating(rater.getID(), product);
                similarities.add(rating);
            }
        }
        Collections.sort(similarities, Collections.reverseOrder());
        //System.out.println("similarites: \n"+ similarities);
        return similarities;
    }
/*
    public ArrayList<Rating> getSimilarRatings(String raterID,int numSimilarRaters, int minimalRaters){
        ArrayList <Rating> similarities = new ArrayList<Rating>( getSimilarities(raterID).subList(0,numSimilarRaters) );
        //ArrayList <Rating> similarities = new ArrayList<Rating>( getSimilarities(raterID) );

        ArrayList<Rating> ret = new ArrayList<Rating>();
        double total = 0.0;
        int numOfRaters = 0;
        for(String movieID : MovieDatabase.filterBy(new TrueFilter())){
            if (RaterDatabase.getRater(raterID).hasRating(movieID)){
                continue;
            }
            for(Rating r : similarities){// Rating is (RaterID,dotProduct)
                Rater rater = RaterDatabase.getRater(r.getItem());
                double similarityRating = r.getValue();
                double currentRaterRating = rater.getRating(movieID);
                if(currentRaterRating != -1) {
                    total += (similarityRating * currentRaterRating);
                    numOfRaters ++; // num of raters who rated that movie
                }
            }
            if(numOfRaters>=minimalRaters &&  total !=0) {
                ret.add(new Rating(movieID,total/numOfRaters));
            }
            numOfRaters =0;
            total =0.0;
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
    */
    public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters){
        return getSimilarRatingsByFilter(raterID,numSimilarRaters,minimalRaters,new TrueFilter());
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String raterID, int numSimilarRaters, int minimalRaters, Filter filter){
        ArrayList<Rating> similarities = new ArrayList<>();
        if(numSimilarRaters> getSimilarities(raterID).size()){
            similarities = ( getSimilarities(raterID));
        }
        else {
            similarities = new ArrayList<Rating>(getSimilarities(raterID).subList(0, numSimilarRaters));
        }
        ArrayList<Rating> ret = new ArrayList<Rating>();
        double total = 0.0;
        int numOfRaters = 0;
        for(String movieID : MovieDatabase.filterBy(filter)){
            if (RaterDatabase.getRater(raterID).hasRating(movieID)){
                continue;
            }
            for(Rating r : similarities){// Rating is (RaterID,dotProduct)
                Rater rater = RaterDatabase.getRater(r.getItem());
                double similarityRating = r.getValue();
                double currentRaterRating = rater.getRating(movieID);
                if(currentRaterRating != -1) {
                    total += (similarityRating * currentRaterRating);
                    numOfRaters ++; // num of raters who rated that movie
                }
            }
            if(numOfRaters>=minimalRaters &&  total !=0) {
                ret.add(new Rating(movieID,total/numOfRaters));
            }
            numOfRaters =0;
            total =0.0;
        }
        Collections.sort(ret, Collections.reverseOrder());
        if(ret.size()<5){
            ArrayList <String > list = MovieDatabase.filterBy(new TrueFilter());
            Random random = new Random();
            for(int i =0;i<5;i++) {
                int randomNum =random.nextInt(list.size());
                ret.add(  new Rating (list.get(randomNum), 100-((i+1)*20) ) );
            }
            Collections.sort(ret, Collections.reverseOrder());
        }
        return ret;
    }


}
