import java.util.ArrayList;

public class MovieRunnerWithFilters {
    private int minimalRaters;
    public MovieDatabase movieDatabase;
    public ThirdRatings thirdRatings = new ThirdRatings("ratings_short.csv");

    public MovieRunnerWithFilters(int min,String smallOrBig)throws IllegalAccessException{
        minimalRaters = min;
        MovieDatabase movieDatabase = new MovieDatabase();
        if(smallOrBig.equals("small")) {
            movieDatabase.initialize("ratedmovies_short.csv");
            thirdRatings.initMyRaters("ratings_short.csv");
            }
        else if(smallOrBig.equals("big")) {
            movieDatabase.initialize("ratedmoviesfull.csv");
            thirdRatings.initMyRaters("ratings.csv");
        }
        else{
            throw new IllegalAccessException("no file given");
        }
    }

    public MovieRunnerWithFilters(String smallOrBig)throws IllegalAccessException{
        minimalRaters = 0;
        MovieDatabase movieDatabase = new MovieDatabase();
        if(smallOrBig.equals("small")) {
            movieDatabase.initialize("ratedmovies_short.csv");
            thirdRatings.initMyRaters("ratings_short.csv");
        }
        else if(smallOrBig.equals("big")) {
            movieDatabase.initialize("ratedmoviesfull.csv");
            thirdRatings.initMyRaters("ratings.csv");
        }
        else{
            throw new IllegalAccessException("no file given");
        }
    }

    public void setMinimalRaters(int min){
        minimalRaters = min;
    }

    public void printAverageRatings(){
        int numOfMovies =  movieDatabase.size();
        int numOfRaters = thirdRatings.getRaterSize();
        System.out.println(" number of movies: "+numOfMovies+" number of raters: "+numOfRaters);
        System.out.println("MovierRunnerWithFilter ...");

        double movieRate = 0.0;
        String movieTitle = "";
        ArrayList<Rating> avgRatingsList = thirdRatings.getAverageRatings(minimalRaters);
        System.out.println("number of rated movies is: " + avgRatingsList.size());
        avgRatingsList.sort(Rating::compareTo);
        for(Rating rating :avgRatingsList){
            movieTitle = movieDatabase.getTitle(rating.getItem());
            movieRate = rating.getValue();
            System.out.println( movieRate+", "+movieTitle);
        }
    }

    public void printAverageRatingsByYear(int year){
        int numOfMovies =  movieDatabase.size();
        int numOfRaters = thirdRatings.getRaterSize();
        System.out.println(" number of movies: "+numOfMovies+" number of raters: "+numOfRaters);
        System.out.println("MovierRunnerWithFilter ...");

        double movieRate = 0.0;
        String movieTitle = "";
        int movieYear = 0;
        Filter yearFilter = new YearAfterFilter(year);
        ArrayList<Rating> avgRatingsList = thirdRatings.getAverageRatingsByFilter(minimalRaters,yearFilter);
        System.out.println("number of movies after filtering is: " + avgRatingsList.size());
        avgRatingsList.sort(Rating::compareTo);
        for(Rating rating :avgRatingsList){
            movieTitle = movieDatabase.getTitle(rating.getItem());
            movieRate = rating.getValue();
            movieYear = movieDatabase.getYear(rating.getItem());
            System.out.println( movieRate+", " + movieYear + ", "+movieTitle);
        }
    }


    public void printAverageRatingsByGenre(String genre){
        int numOfMovies =  movieDatabase.size();
        int numOfRaters = thirdRatings.getRaterSize();
        System.out.println(" number of movies: "+numOfMovies+" number of raters: "+numOfRaters);
        System.out.println("MovierRunnerWithFilter ...");

        double movieRate = 0.0;
        String movieTitle = "";
        int movieYear = 0;
        String movieGenre ="";
        Filter genreFilter = new GenreFilter(genre);
        ArrayList<Rating> avgRatingsList = thirdRatings.getAverageRatingsByFilter(minimalRaters,genreFilter);
        System.out.println("number of movies after filtering is: " + avgRatingsList.size());
        avgRatingsList.sort(Rating::compareTo);
        for(Rating rating :avgRatingsList){
            movieTitle = movieDatabase.getTitle(rating.getItem());
            movieRate = rating.getValue();
            movieYear = movieDatabase.getYear(rating.getItem());
            movieGenre = movieDatabase.getGenres(rating.getItem());
            System.out.println( movieRate+", " +", "+movieTitle+", "+movieGenre );
        }

    }

    public void printAverageRatingsByMinutes(int minMinutes, int maxMinutes){
        int numOfMovies =  movieDatabase.size();
        int numOfRaters = thirdRatings.getRaterSize();
        System.out.println(" number of movies: "+numOfMovies+" number of raters: "+numOfRaters);
        System.out.println("MovierRunnerWithFilter ...");

        double movieRate = 0.0;
        String movieTitle = "";
        int movieYear = 0;
        String movieGenre ="";
        int movieTime =0;
        Filter MinutesFilter = new MinutesFilter(minMinutes,minMinutes);
        ArrayList<Rating> avgRatingsList = thirdRatings.getAverageRatingsByFilter(minimalRaters,MinutesFilter);
        System.out.println("number of movies after filtering is: " + avgRatingsList.size());
        avgRatingsList.sort(Rating::compareTo);
        for(Rating rating :avgRatingsList){
            movieTitle = movieDatabase.getTitle(rating.getItem());
            movieRate = rating.getValue();
            movieYear = movieDatabase.getYear(rating.getItem());
            movieGenre = movieDatabase.getGenres(rating.getItem());
            movieTime = movieDatabase.getMinutes(rating.getItem());
            System.out.println( movieRate+", time:"+movieTime +", **"+movieTitle+"**, "+movieGenre);
        }
    }

    public void printAverageRatingsByDirector(String directors) {
        int numOfMovies = movieDatabase.size();
        int numOfRaters = thirdRatings.getRaterSize();
        System.out.println(" number of movies: " + numOfMovies + " number of raters: " + numOfRaters);
        System.out.println("MovierRunnerWithFilter ...");

        double movieRate = 0.0;
        String movieTitle = "";
        int movieYear = 0;
        String movieGenre = "";
        int movieTime = 0;
        String movieDirector = "";
        Filter DirectorFilter = new DirectorsFilter(directors);
        ArrayList<Rating> avgRatingsList = thirdRatings.getAverageRatingsByFilter(minimalRaters, DirectorFilter);
        System.out.println("number of movies after filtering is: " + avgRatingsList.size());
        avgRatingsList.sort(Rating::compareTo);
        for (Rating rating : avgRatingsList) {
            movieTitle = movieDatabase.getTitle(rating.getItem());
            movieRate = rating.getValue();
            movieYear = movieDatabase.getYear(rating.getItem());
            movieGenre = movieDatabase.getGenres(rating.getItem());
            movieTime = movieDatabase.getMinutes(rating.getItem());
            movieDirector = movieDatabase.getDirector(rating.getItem());
            System.out.println(movieRate + ", time:" + movieTime + ", **" + movieTitle + "**, " + movieGenre+", "+movieDirector);
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(int year, String genre){
        int numOfMovies = movieDatabase.size();
        int numOfRaters = thirdRatings.getRaterSize();
        System.out.println(" number of movies: " + numOfMovies + " number of raters: " + numOfRaters);
        System.out.println("MovierRunnerWithFilter ...");

        double movieRate = 0.0;
        String movieTitle = "";
        int movieYear = 0;
        String movieGenre = "";
        int movieTime = 0;
        String movieDirector = "";
        Filter yearFilter = new YearAfterFilter(year);
        Filter genreFilter = new GenreFilter(genre);
        AllFilters yearAfterAndGenreFilter = new AllFilters();
        yearAfterAndGenreFilter.addFilter(yearFilter);
        yearAfterAndGenreFilter.addFilter(genreFilter);
        ArrayList<Rating> avgRatingsList = thirdRatings.getAverageRatingsByFilter(minimalRaters, yearAfterAndGenreFilter);
        System.out.println("number of movies after filtering is: " + avgRatingsList.size());
        avgRatingsList.sort(Rating::compareTo);
        for (Rating rating : avgRatingsList) {
            movieTitle = movieDatabase.getTitle(rating.getItem());
            movieRate = rating.getValue();
            movieYear = movieDatabase.getYear(rating.getItem());
            movieGenre = movieDatabase.getGenres(rating.getItem());
            movieTime = movieDatabase.getMinutes(rating.getItem());
            movieDirector = movieDatabase.getDirector(rating.getItem());
            System.out.println(movieRate + ", time:" + movieTime + ", **" + movieTitle + "**, " + movieGenre+", "+movieDirector);
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes(String directors, int minMinutes, int maxMinutes){
        int numOfMovies = movieDatabase.size();
        int numOfRaters = thirdRatings.getRaterSize();
        System.out.println(" number of movies: " + numOfMovies + " number of raters: " + numOfRaters);
        System.out.println("MovierRunnerWithFilter ...");

        double movieRate = 0.0;
        String movieTitle = "";
        int movieYear = 0;
        String movieGenre = "";
        int movieTime = 0;
        String movieDirector = "";
        Filter DirectorFilter = new DirectorsFilter(directors);
        Filter MinutesFilter = new MinutesFilter(minMinutes,maxMinutes);
        AllFilters yearAfterAndGenreFilter = new AllFilters();
        yearAfterAndGenreFilter.addFilter(DirectorFilter);
        yearAfterAndGenreFilter.addFilter(MinutesFilter);
        ArrayList<Rating> avgRatingsList = thirdRatings.getAverageRatingsByFilter(minimalRaters, yearAfterAndGenreFilter);
        System.out.println("number of movies after filtering is: " + avgRatingsList.size());
        avgRatingsList.sort(Rating::compareTo);
        for (Rating rating : avgRatingsList) {
            movieTitle = movieDatabase.getTitle(rating.getItem());
            movieRate = rating.getValue();
            movieYear = movieDatabase.getYear(rating.getItem());
            movieGenre = movieDatabase.getGenres(rating.getItem());
            movieTime = movieDatabase.getMinutes(rating.getItem());
            movieDirector = movieDatabase.getDirector(rating.getItem());
            System.out.println(movieRate + ", time:" + movieTime + ", **" + movieTitle + "**, " + movieGenre+", "+movieDirector);
        }
    }

}
