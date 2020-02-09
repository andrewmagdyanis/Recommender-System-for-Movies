import java.util.ArrayList;

public class MovieRunnerSimilarRatings {
    private int minimalRaters;
    public   MovieDatabase movieDatabase;
    public   RaterDatabase raterDatabase;
    public   FourthRatings fourthRatings = new FourthRatings();

    public MovieRunnerSimilarRatings(String smallOrBig, int min) throws IllegalAccessException {
        minimalRaters = min;
        MovieDatabase movieDatabase = new MovieDatabase();
        RaterDatabase raterDatabase = new RaterDatabase();
        //FourthRatings fourthRatings = new FourthRatings();// by default will use the full file
        if(smallOrBig.equals("small")) {
            movieDatabase.initialize("ratedmovies_short.csv");
            raterDatabase.initialize("ratings_short.csv");
        }
        else if(smallOrBig.equals("big")) {
            movieDatabase.initialize("ratedmoviesfull.csv");
            raterDatabase.initialize("ratings.csv");
        }
        else{
            throw new IllegalAccessException("no file given");
        }

    }

    public MovieRunnerSimilarRatings(String smallOrBig) throws IllegalAccessException {
        minimalRaters = 0;
        MovieDatabase movieDatabase = new MovieDatabase();
        RaterDatabase raterDatabase = new RaterDatabase();
        //FourthRatings fourthRatings = new FourthRatings();// by default will use the full file
        if(smallOrBig.equals("small")) {
            movieDatabase.initialize("ratedmovies_short.csv");
            raterDatabase.initialize("ratings_short.csv");
            System.out.println("data bases are initialized with short files");
        }
        else if(smallOrBig.equals("big")) {
            movieDatabase.initialize("ratedmoviesfull.csv");
            raterDatabase.initialize("ratings.csv");
            System.out.println("data bases are initialized with full files");
        }
        else{
            throw new IllegalAccessException("no file given");
        }
    }

    public void setMinimalRaters(int min){
        minimalRaters = min;
    }
    /*
    public void printAverageRatings(){
        int numOfMovies =  movieDatabase.size();
        int numOfRaters = raterDatabase.size();
        System.out.println(" number of movies: "+numOfMovies+" number of raters: "+numOfRaters);
        System.out.println("MovieRunnerSimilarRatings ..");

        double movieRate = 0.0;
        String movieTitle = "";
        ArrayList<Rating> avgRatingsList = fourthRatings.getAverageRatings(minimalRaters);
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
        int numOfRaters = raterDatabase.size();
        System.out.println(" number of movies: "+numOfMovies+" number of raters: "+numOfRaters);
        System.out.println("MovieRunnerSimilarRatings ..");

        double movieRate = 0.0;
        String movieTitle = "";
        int movieYear = 0;
        Filter yearFilter = new YearAfterFilter(year);
        ArrayList<Rating> avgRatingsList = fourthRatings.getAverageRatingsByFilter(minimalRaters,yearFilter);
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
        int numOfRaters = raterDatabase.size();
        System.out.println(" number of movies: "+numOfMovies+" number of raters: "+numOfRaters);
        System.out.println("MovieRunnerSimilarRatings ..");

        double movieRate = 0.0;
        String movieTitle = "";
        int movieYear = 0;
        String movieGenre ="";
        Filter genreFilter = new GenreFilter(genre);
        ArrayList<Rating> avgRatingsList = fourthRatings.getAverageRatingsByFilter(minimalRaters,genreFilter);
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
        int numOfRaters = raterDatabase.size();
        System.out.println(" number of movies: "+numOfMovies+" number of raters: "+numOfRaters);
        System.out.println("MovieRunnerSimilarRatings ..");

        double movieRate = 0.0;
        String movieTitle = "";
        int movieYear = 0;
        String movieGenre ="";
        int movieTime =0;
        Filter MinutesFilter = new MinutesFilter(minMinutes,minMinutes);
        ArrayList<Rating> avgRatingsList = fourthRatings.getAverageRatingsByFilter(minimalRaters,MinutesFilter);
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
        int numOfRaters = raterDatabase.size();
        System.out.println(" number of movies: " + numOfMovies + " number of raters: " + numOfRaters);
        System.out.println("MovieRunnerSimilarRatings ..");

        double movieRate = 0.0;
        String movieTitle = "";
        int movieYear = 0;
        String movieGenre = "";
        int movieTime = 0;
        String movieDirector = "";
        Filter DirectorFilter = new DirectorsFilter(directors);
        ArrayList<Rating> avgRatingsList = fourthRatings.getAverageRatingsByFilter(minimalRaters, DirectorFilter);
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
        int numOfRaters = raterDatabase.size();
        System.out.println(" number of movies: " + numOfMovies + " number of raters: " + numOfRaters);
        System.out.println("MovieRunnerSimilarRatings ..");

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
        ArrayList<Rating> avgRatingsList = fourthRatings.getAverageRatingsByFilter(minimalRaters, yearAfterAndGenreFilter);
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
        int numOfRaters = raterDatabase.size();
        System.out.println(" number of movies: " + numOfMovies + " number of raters: " + numOfRaters);
        System.out.println("MovieRunnerSimilarRatings ..");

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
        ArrayList<Rating> avgRatingsList = fourthRatings.getAverageRatingsByFilter(minimalRaters, yearAfterAndGenreFilter);
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
/************************************************************************************************/
    public  void printSimilarRatings(String raterID,int numSimilarRaters, int minimalRaters, int numOfItemsToBeShown){
        System.out.println("*******printSimilarRatings*******");
        System.out.println(
                "raterID: "+raterID
                        +"\nnumSimilarRaters: "+numSimilarRaters
                        +"\nminimalRaters: "+minimalRaters
                        +"\nnumber of recommendation to be shown: "+numOfItemsToBeShown );

        ArrayList<Rating> list = fourthRatings.getSimilarRatings(raterID,numSimilarRaters,minimalRaters);
        int count = list.size();
        if (list.size() > numOfItemsToBeShown){
            count=numOfItemsToBeShown;
        }
        for(int i =0;i<count;i++) {
            System.out.println(i+" "+list.get(i).getValue()+" "+MovieDatabase.getTitle(list.get(i).getItem()));
        }
        System.out.println("________________________________________________________________");
    }

    public void printSimilarRatingsByGenre(String raterID,int numSimilarRaters, int minimalRaters, String genre, int numOfItemsToBeShown){
        System.out.println("*******printSimilarRatingsByGenre*******");
        System.out.println(
                  "raterID: "+raterID
                +"\nnumSimilarRaters: "+numSimilarRaters
                +"\nminimalRaters: "+minimalRaters
                +"\ngenre: "+genre
                +"\nnumber of recommendation to be shown: "+numOfItemsToBeShown );

        Filter filter = new GenreFilter(genre);
        ArrayList<Rating> list = fourthRatings.getSimilarRatingsByFilter(raterID,numSimilarRaters,minimalRaters,filter);
        int count = list.size();
        if (list.size() > numOfItemsToBeShown){
            count=numOfItemsToBeShown;
        }
        for(int i =0;i<count;i++) {
            System.out.println(i+" "+list.get(i).getValue()+" "+MovieDatabase.getTitle(list.get(i).getItem()) );
            System.out.println(MovieDatabase.getGenres(list.get(i).getItem()));
        }
        System.out.println("________________________________________________________________");
    }

    public  void printSimilarRatingsByDirector(String raterID,int numSimilarRaters, int minimalRaters, String director, int numOfItemsToBeShown){
        System.out.println("*******printSimilarRatingsByDirector*******");
        System.out.println(
                "raterID: "+raterID
                        +"\nnumSimilarRaters: "+numSimilarRaters
                        +"\nminimalRaters: "+minimalRaters
                        +"\ndirector: "+director
                        +"\nnumber of recommendation to be shown: "+numOfItemsToBeShown );

        Filter filter = new DirectorsFilter(director);
        ArrayList<Rating> list = fourthRatings.getSimilarRatingsByFilter(raterID,numSimilarRaters,minimalRaters,filter);
        int count = list.size();
        if (list.size() > numOfItemsToBeShown){
            count=numOfItemsToBeShown;
        }
        for(int i =0;i<count;i++) {
            System.out.println(i+" "+list.get(i).getValue()+" "+MovieDatabase.getTitle(list.get(i).getItem()));
            System.out.println(MovieDatabase.getDirector(list.get(i).getItem()));
        }
        System.out.println("________________________________________________________________");
    }

    public void printSimilarRatingsByGenreAndMinutes(String raterID,int numSimilarRaters, int minimalRaters, String genre, int min , int max, int numOfItemsToBeShown){
        System.out.println("*******printSimilarRatingsByGenreAndMinutes*******");
        System.out.println(
                "raterID: "+raterID
                        +"\nnumSimilarRaters: "+numSimilarRaters
                        +"\nminimalRaters: "+minimalRaters
                        +"\ngenre: "+genre
                        +"\ntime range of film: from "+min+" to "+max
                        +"\nnumber of recommendation to be shown: "+numOfItemsToBeShown );

        Filter genreFilter = new GenreFilter(genre);
        Filter minutesFilter = new MinutesFilter(min,max);
        AllFilters combinedFilter = new AllFilters();
        combinedFilter.addFilter(genreFilter);
        combinedFilter.addFilter(minutesFilter);
        ArrayList<Rating> list = fourthRatings.getSimilarRatingsByFilter(raterID,numSimilarRaters,minimalRaters,combinedFilter);
        int count = list.size();
        if (list.size() > numOfItemsToBeShown){
            count=numOfItemsToBeShown;
        }
        for(int i =0;i<count;i++) {
            System.out.println(i+" "+list.get(i).getValue()+" "+MovieDatabase.getTitle(list.get(i).getItem())
                    +" "+MovieDatabase.getYear( list.get(i).getItem()));
            System.out.println(MovieDatabase.getGenres(list.get(i).getItem()));
        }
        System.out.println("________________________________________________________________");

    }

    public void printSimilarRatingsByYearAfterAndMinute (String raterID,int numSimilarRaters, int minimalRaters, int year, int min , int max, int numOfItemsToBeShown){
        System.out.println("***printSimilarRatingsByYearAfterAndMinute ...");
        System.out.println(
                "raterID: "+raterID
                        + "\nnumSimilarRaters: "+numSimilarRaters
                        +"\nminimalRaters: "+minimalRaters
                        +"\nyear "+year
                        +"\ntime range of film: from "+min+" to "+max
                        +"\nnumber of recommendation to be shown: "+numOfItemsToBeShown );

        Filter yearAfterFilter = new YearAfterFilter(year);
        Filter minutesFilter = new MinutesFilter(min,max);
        AllFilters combinedFilter = new AllFilters();
        combinedFilter.addFilter(yearAfterFilter);
        combinedFilter.addFilter(minutesFilter);
        ArrayList<Rating> list = fourthRatings.getSimilarRatingsByFilter(raterID,numSimilarRaters,minimalRaters,combinedFilter);
        int count = list.size();
        if (list.size() > numOfItemsToBeShown){
            count=numOfItemsToBeShown;
        }
        for(int i =0;i<count;i++) {
            System.out.println(i+" "+list.get(i).getValue()+" "+MovieDatabase.getTitle(list.get(i).getItem())
                    +" "+MovieDatabase.getMinutes( list.get(i).getItem())
                    +" "+MovieDatabase.getYear( list.get(i).getItem()));
        }
        System.out.println("________________________________________________________________");
    }

}
