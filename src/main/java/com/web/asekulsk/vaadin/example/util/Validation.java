package com.web.asekulsk.vaadin.example.util;

/**
 * Static validation helper class utility.
 *
 * @author Andreas Sekulski
 */
public class Validation {

    /**
     * String validation if is not empty, not null or only contains characters.
     *
     * @param string String to check.
     * @return TRUE if string is valid otherwise FALSE.
     */
    public static boolean validateString(String string) {
        return string != null && !string.isEmpty() && !string.trim().isEmpty();
    }

}
