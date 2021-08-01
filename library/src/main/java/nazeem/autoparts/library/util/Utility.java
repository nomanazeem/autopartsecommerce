package nazeem.autoparts.library.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Utility {

    public List<Integer> getYears(){
        List<Integer> years = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        for(Integer i=1980; i<= currentYear; i++){
            years.add(i);
        }
        return years;
    }
}
