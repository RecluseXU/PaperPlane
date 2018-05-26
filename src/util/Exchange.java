package util;

import java.util.ArrayList;
import java.util.List;

public final class Exchange {
	
	public Exchange(){}
	
	public List<String> strs_to_strlist(String... strs)
	{
		List<String> result = new ArrayList<String>();
        for (String str : strs) {  
            result.add(str);
        }
        return result;
	}
}
