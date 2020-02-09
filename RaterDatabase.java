
/**
 * Write a description of RaterDatabase here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class RaterDatabase {
    private static HashMap<String, Rater> ourRaters;
     
	private static void initialize() {
	    // this method is only called from addRatings 
		if (ourRaters == null) {
			ourRaters = new HashMap<String, Rater>();
            addRatings("ratings.csv");
            /*
            System.out.println("ourRaters:\n "+ourRaters);
            for(String raterID : ourRaters.keySet()){
                System.out.println(ourRaters.get(raterID).getItemsRated());
            }

             */
        }
	}

    public static void initialize(String filename) {
 		if (ourRaters == null) {
 			ourRaters= new HashMap<String, Rater>();
 			addRatings(filename);
            /*
            System.out.println("ourRaters:\n "+ourRaters);
 			for(String raterID : ourRaters.keySet()){
                System.out.println(ourRaters.get(raterID).getItemsRated());
            }

             */
 		}
 	}	
 	
    public static void addRatings(String filename) {
        initialize(); 
        FileResource fr = new FileResource("data/"+filename);
        CSVParser csvp = fr.getCSVParser();
        for(CSVRecord rec : csvp) {
                String id = rec.get("rater_id");
                String item = rec.get("movie_id");
                String rating = rec.get("rating");
                addRaterRating(id,item,Double.parseDouble(rating));
        } 
    }
    
    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize();
        Rater rater = null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID);
        } else {
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID, rater);
        }
        rater.addRating(movieID, rating);
    }

	         
    public static Rater getRater(String id) {
    	initialize();
    	
    	return ourRaters.get(id);
    }
    
    public static ArrayList<Rater> getRaters() {
    	initialize();
    	ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());
    	
    	return list;
    }
 
    public static int size() {
	    return ourRaters.size();
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
        
}
