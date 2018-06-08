package tech.adrianohrl.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Adriano Henrique Rossette Leite (contact@adrianohrl.tech)
 */
public class AddressUtil {
    
    private static final Logger LOGGER = Logger.getLogger(AddressUtil.class);
    private static final Map<String, String> STATES = new TreeMap<>();
    private static final Map<String, List<String>> CITIES = new TreeMap<>();
    
    static {
        ClassLoader classLoader = AddressUtil.class.getClassLoader();
        try {
            URL url = classLoader.getResource(tech.adrianohrl.util.PropertyUtil.getProperty("data.set.states"));
            System.out.println("Loading the state list from the " + url.getFile() + " file ...");
            File file = new File(url.getFile());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            int counter = 0;
            while (line != null) {
                String[] splitted = line.split(",");
                STATES.put(splitted[1], splitted[0]);
                CITIES.put(splitted[1], new ArrayList<>());
                counter++;
                line = reader.readLine();
            }
            System.out.println(counter + " states were loaded.");
        } catch (FileNotFoundException e) {
            LOGGER.error("FileNotFoundException caught while reading state list: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException caught while reading state list: " + e.getMessage());
        }
        try {
            URL url = classLoader.getResource(tech.adrianohrl.util.PropertyUtil.getProperty("data.set.cities"));
            System.out.println("Loading the city list from the " + url.getFile() + " file ...");
            File file = new File(url.getFile());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            int counter = 0;
            while (line != null) {
                String[] splitted = line.split(",");
                CITIES.get(splitted[0]).add(splitted[1]);
                counter++;
                line = reader.readLine();
            }
            System.out.println(counter + " cities were loaded.");
        } catch (FileNotFoundException e) {
            LOGGER.error("FileNotFoundException caught while reading city list: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException caught while reading city list: " + e.getMessage());
        }        
    }
    
    public static Set<String> getStates() {
        return STATES.keySet();
    }
    
    public static List<String> getStateCities(String state) {
        return CITIES.get(state);
    }
    
    public static String getState(String uf) {
        for (String state : STATES.keySet()) {
            if (STATES.get(state).equalsIgnoreCase(uf)) {
                return state;
            }
        }
        return null;
    }
    
    public static String getUf(String state) {
        return STATES.get(state);
    }

    public static boolean isValid(String state) {
        return STATES.containsKey(state);
    }
    
    public static boolean isValid(String state, String city) {
        return STATES.containsKey(state) && CITIES.get(state).contains(city);
    }
    
    public static void main(String[] args) {
        for (String state : AddressUtil.getStates()) {
            System.out.println("state: " + state);
        }
        for (String city : AddressUtil.getStateCities("Minas Gerais")) {
            System.out.println("city of MG: " + city);
        }
    }
    
}
