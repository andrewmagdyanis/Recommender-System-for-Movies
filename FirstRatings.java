import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> MoviesList = new ArrayList<Movie>();
        File file = new File("data/"+filename);
        FileResource fr = new FileResource(file);
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser){
            Movie movie = new Movie(record.get("id"),record.get("title"),record.get("year"),record.get("country"),record.get("genre"),record.get("director"),Integer.parseInt(record.get("minutes")) );
            //System.out.println(movie);
            MoviesList.add(movie);
        }
        return MoviesList;
    }

    public void testLoadMovies(String filename){
        int comedyMoviesCounter = 0;
        int longMoviesCounter = 0;
        HashMap <String,Integer> directorsMap = new HashMap<String, Integer>();
        ArrayList<Movie> list = loadMovies(filename);
        System.out.println("number of movies: "+list.size());

        for(int i =0;i <list.size();i++){
            if(list.get(i).getGenres().contains("Comedy")){
                comedyMoviesCounter++;
            }
            if(list.get(i).getMinutes()>150){
                longMoviesCounter++;
            }
            String director = list.get(i).getDirector();
            if(! directorsMap.containsKey(director) ){
                directorsMap.put(director,1);
            }
            else{
                directorsMap.put(director,directorsMap.get(director)+1);
            }
        }
        //System.out.println("the map: "+directorsMap);
        int max = 0;
        String directorWithMaxRates ="";
        for(String director :directorsMap.keySet()){
            if(directorsMap.get(director)>max) {
                max = directorsMap.get(director);
                directorWithMaxRates = director;
            }
        }
        System.out.println("max number of movies by any diector is: "+max +" and that director is "+directorWithMaxRates +" and the number of all directors are: "+ directorsMap.size());
        System.out.println("number of comedy movies: "+comedyMoviesCounter);
        System.out.println("number of long movies: "+longMoviesCounter);
    }

    public ArrayList<EfficientRater> loadRaters(String filename){
        ArrayList<EfficientRater> raterList = new ArrayList<EfficientRater>();
        ArrayList<String> rater_idList = new ArrayList<>();
        File file = new File("data/"+filename);
        FileResource fr = new FileResource(file);
        CSVParser parser = fr.getCSVParser();
        EfficientRater rater = new EfficientRater("1");

        for(CSVRecord record : parser){
            String rater_id = record.get("rater_id");

            if(!rater_idList.contains(rater_id)) {
                rater = new EfficientRater(rater_id);
                rater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")) );
                raterList.add(rater);
                rater_idList.add(rater_id);
                //System.out.println("new rater"+ rater_id);
            }
            else{
                raterList.get(raterList.size()-1).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                //System.out.println("old rater" + rater_id);
            }
        }
        //System.out.println("raterList : "+raterList);
        return raterList;
    }

    public void testLoadRaters(String filename) {
        int comedyMoviesCounter = 0;
        int longMoviesCounter = 0;
        HashMap <String,Integer> ratersMap = new HashMap<String, Integer>();

        ArrayList<EfficientRater> ratersList = loadRaters(filename);
        System.out.println("number of raters: " + ratersList.size());
        /*
        for (Rater rater : ratersList) {
            System.out.println("rater id: " + rater.getID() + " number of rating he did: " + rater.numRatings());
            System.out.println("movie id: "+rater.getItemsRated() );
        }
         */
        int max=0;
        String raterIdWithMaxNumOfRatings ="";
        for(int i =0;i<ratersList.size();i++){
            if(ratersList.get(i).numRatings()>max){
                max = ratersList.get(i).numRatings();
                raterIdWithMaxNumOfRatings = ratersList.get(i).getID();
            }
        }
        System.out.println("Max number of rating by any rater is: "+max+" and that is for rater "+raterIdWithMaxNumOfRatings);

        String rater_id ="193";
        EfficientRater specifiedRater = new EfficientRater(rater_id) ;
        String movie_id ="1798709";
        int movieRatedCounter = 0;
        int numOfMoviesRated = 0;
        HashMap<String ,Integer> moviesMap = new HashMap<>();
        for(EfficientRater plainRater : ratersList){
            if(plainRater.getID().equals(rater_id)){
                specifiedRater = plainRater;
            }
            if(plainRater.hasRating(movie_id)){
                movieRatedCounter++;
            }

            for(String movieKey: plainRater.getItemsRated()) {
                if (!moviesMap.containsKey(movieKey)) {
                    moviesMap.put(movieKey, 1);
                } else {
                    moviesMap.put(movieKey, moviesMap.get(movieKey) + 1);
                }
            }
        }
        System.out.println("number of ratings of rater "+rater_id+" is: "+ specifiedRater.numRatings());
        System.out.println("number of rates for the movie: "+movie_id+" is: "+movieRatedCounter);
        System.out.println("number of different movies rated is: "+moviesMap.size());
    }
}





