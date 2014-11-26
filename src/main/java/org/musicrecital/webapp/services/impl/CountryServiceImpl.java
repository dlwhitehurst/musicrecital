/**
 * Copyright 2014 David L. Whitehurst
 * 
 * Licensed under the Apache License, Version 2.0 
 * (the "License"); You may not use this file except 
 * in compliance with the License. You may obtain a 
 * copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific 
 * language governing permissions and limitations under the 
 * License.
 * 
 */

package org.musicrecital.webapp.services.impl;

import org.apache.tapestry5.ioc.services.ThreadLocale;
import org.musicrecital.webapp.services.CountryService;
import org.slf4j.Logger;

import java.text.Collator;
import java.util.*;

/**
 * Implementation of CountryService
 *
 * @author Serge Eby
 */
public class CountryServiceImpl implements CountryService {
    private final Logger logger;
    private final ThreadLocale threadLocale;
    private final Map<String, String> sortedCountries;

    public CountryServiceImpl(Logger logger, ThreadLocale threadLocale) {
        this.logger = logger;
        this.threadLocale = threadLocale;

        Map<String, String> countries = initialize();
        sortedCountries = new TreeMap<String, String>
                (new CountryComparator(countries, this.threadLocale.getLocale()));
        sortedCountries.putAll(countries);
    }

    public Map<String, String> getAvailableCountries() {
        return sortedCountries;
    }

    private Map<String, String> initialize() {
        Map<String, String> countries = new HashMap<String, String>();
        Locale currentLocale = threadLocale.getLocale();
        final String EMPTY = "";
        Locale[] availableLocales = Locale.getAvailableLocales();

        for (Locale locale : availableLocales) {
            String name = locale.getDisplayCountry(currentLocale);
            String iso = locale.getCountry();
            if (!EMPTY.equals(name) && !EMPTY.equals(iso)) {
                countries.put(iso, name);
            }
        }
        logger.debug("Number of countries added: " + countries.size());

        return countries;

    }

    /**
     * Class to compare LabelValues using their labels with locale-sensitive
     * behaviour.
     */
    private class CountryComparator implements Comparator<String> {
        private Collator c;
        private Map<String, String> unsortedMap;

        /**
         * Creates a new CountryComparator object.
         *
         * @param map    of country and locale
         * @param locale The Locale used for localized String comparison.
         */
        public CountryComparator(Map<String, String> map, Locale locale) {
            unsortedMap = map;
            c = Collator.getInstance(locale);
        }

        /**
         * Compares the localized labels of two LabelValues.
         *
         * @param rhs The first String to compare.
         * @param lhs The second String to compare.
         * @return The value returned by comparing the localized labels.
         */

        public final int compare(String lhs, String rhs) {
            String lvalue = unsortedMap.get(lhs);
            String rvalue = unsortedMap.get(rhs);
            return c.compare(lvalue, rvalue);
        }
    }
}
