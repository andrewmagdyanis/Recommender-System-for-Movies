public class MinutesFilter implements Filter {
    private int filmMinMinuets;
    private int filmMaxMinuets;
    public MinutesFilter(int  minMinuets, int MaxMinuets) {
        filmMinMinuets = minMinuets;
        filmMaxMinuets = MaxMinuets;
    }

    @Override
    public boolean satisfies(String id) {
        return (MovieDatabase.getMinutes(id)>=filmMinMinuets && MovieDatabase.getMinutes(id)<=filmMaxMinuets ) ;
    }

}
