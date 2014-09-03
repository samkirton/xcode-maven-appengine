package com.memtrip.xcode.appengine.utils;

import java.util.ArrayList;

/**
 * @author memtrip
 */
public class StringUtils {
    
    /**
     * Covert the provided array list to a string
     * @param	arrayList	The array list to convert to a string
     * @return	The arrayList as a string
     */
    public static String arrayListOut(ArrayList<String> arrayList) {
    	StringBuilder sb = new StringBuilder();
    	for (String string : arrayList)
    		sb.append(string);	

    	return sb.toString();
    }
}
