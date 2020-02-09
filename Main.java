public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("Main Started:");

        MovieRunnerSimilarRatings movieRunnerSimilarRatings = new MovieRunnerSimilarRatings("big");
        System.out.println("number of movies: " + MovieDatabase.size() + " number of raters: " + RaterDatabase.size());
        String director = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";

        movieRunnerSimilarRatings.printSimilarRatings("337", 10, 3
                , 1);

        movieRunnerSimilarRatings.printSimilarRatingsByGenre("964", 20, 5
                , "Mystery", 1);

        movieRunnerSimilarRatings.printSimilarRatings("71", 20, 5
                , 1);

        movieRunnerSimilarRatings.printSimilarRatingsByDirector("120", 10, 2,
                director, 1);

        movieRunnerSimilarRatings.printSimilarRatingsByGenreAndMinutes("168", 10, 3
                , "Drama", 80, 160, 1);

        movieRunnerSimilarRatings.printSimilarRatingsByYearAfterAndMinute("314", 10, 5
                , 1975, 70, 200, 1);

        RecommendationRunner recommendationRunner = new RecommendationRunner();
        recommendationRunner.printRecommendationsFor("65");
    }

}
