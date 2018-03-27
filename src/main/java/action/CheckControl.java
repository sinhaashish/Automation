package action;

import core.Configuration;

/**
 * Created by ashishsinha on 3/25/2018.
 */

/**
 * A Utility class to check the status
 *
 */
public class CheckControl {
    /**
     * Utiltity method to check the status.     *
     * @param conf Configuration Class object
     *
     *
     */

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
