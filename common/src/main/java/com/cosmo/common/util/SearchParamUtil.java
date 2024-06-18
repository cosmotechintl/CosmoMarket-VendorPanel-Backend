package com.cosmo.common.util;

import com.cosmo.common.model.SearchParam;

import java.time.Instant;
import java.util.Date;

public class SearchParamUtil {

    public static String getString(SearchParam searchParam, String keyName) {
        return (String) searchParam.getParam().get(keyName);
    }

    public static Date getDate(SearchParam searchParam, String keyName, String dateFormat) {
        String stringDate = getString(searchParam, keyName);
        if (stringDate != null && !stringDate.isBlank()) {
            Instant instant = Instant.parse(stringDate);

            Date date = Date.from(instant);

            return DateUtility.getDateFromDate(date, dateFormat);
        }
        return null;
    }
    public static Long getLong(SearchParam searchParam, String keyName) {
        Object value = searchParam.getParam().get(keyName);
        if (value instanceof Number) {
            return ((Number) value).longValue();
        } else if (value instanceof String) {
            try {
                return Long.parseLong((String) value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
