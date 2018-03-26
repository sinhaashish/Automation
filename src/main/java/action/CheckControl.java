package action;

import core.Configuration;

/**
 * Created by asi292 on 3/25/2018.
 */
public class CheckControl {

    public static boolean validateStatus(Configuration conf)
    {
        if("Failure".equalsIgnoreCase(conf.getStatus()))
        {
            System.out.println(" Cant continue ");
            return true;
        }
        return false;
    }
}
