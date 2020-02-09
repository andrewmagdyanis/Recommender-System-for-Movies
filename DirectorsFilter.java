public class DirectorsFilter implements  Filter{
    private String directorslistAsString;

    public DirectorsFilter(String directors) {
        directorslistAsString = directors;
    }

    @Override
    public boolean satisfies(String id) {
        String[] directorsList ;
        directorsList = directorslistAsString.split(",");
        for(String director : directorsList){
            if(MovieDatabase.getDirector(id).contains(director)){
                return true;
            }
        }
        return false;
    }


}
