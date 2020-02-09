import java.util.ArrayList;
import java.util.Random;

public class RecommendationRunner implements Recommender{

    public ArrayList<String> getItemsToRate (){
        //System.out.println("start************");
        ArrayList<String> tobeShowenMoviesList = new ArrayList<>();
        ArrayList<String> allMoviesList = MovieDatabase.filterBy(new TrueFilter());
        Random random = new Random();
        int numOfItemsToBeShowed = 20;
        for(int i =0;i<numOfItemsToBeShowed;i++) {
            int randomNum =random.nextInt(allMoviesList.size());
            tobeShowenMoviesList.add(allMoviesList.get(randomNum));
        }
        return tobeShowenMoviesList;
    }

    public void printRecommendationsFor (String webRaterID) {
        StringBuilder html = new StringBuilder("<html>"
                +"<body>"
                +table(webRaterID)
                +"<body>");
        System.out.println(html.toString());

    }
    private String table (String webRaterID){
        FourthRatings fourthRatings = new FourthRatings();
        ArrayList<Rating> recommendedMovieList = fourthRatings.getSimilarRatings(webRaterID, 4, 2);
        String rowcolor = (" <tr bgcolor=\"yellow\">");
        StringBuilder tableHtml = new StringBuilder("<table>");

        tableHtml.append("<tr>"
                +"<th>Number</th>"
                +"<th>Film name</th>"
                +"<th>Rate</th>"
                +"<th>Year</th>"
                +"<th>Genre</th>"
                +"<th>Time</th>"
                +"<th>Director</th>"
                +"<th>Country</th>"
                +"</tr>");
        int i =0;
        for(Rating rating: recommendedMovieList) {
            String movieID = rating.getItem();
            tableHtml.append("<tr>"
                    + "<td>" + i + "</td>"
                    + "<td>"+MovieDatabase.getTitle(movieID)+"</td>"
                    + "<td>"+rating.getValue()+"</td>"
                    + "<td>"+MovieDatabase.getYear(movieID)+"</td>"
                    + "<td>"+MovieDatabase.getGenres(movieID)+"</td>"
                    + "<td>"+MovieDatabase.getMinutes(movieID)+"</td>"
                    + "<td>"+MovieDatabase.getDirector(movieID)+"</td>"
                    + "<td>"+MovieDatabase.getCountry(movieID)+"</td>"
                    + "</tr>");
            i++;
            if(i>=20){
                break;
            }
        }
        tableHtml.append("</table>");
        return  tableHtml.toString();
    }

}



