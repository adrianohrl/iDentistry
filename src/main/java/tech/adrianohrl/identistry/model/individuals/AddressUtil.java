/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.adrianohrl.identistry.model.individuals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.log4j.Logger;
import tech.adrianohrl.util.PropertyUtil;

/**
 *
 * @author adrianohrl
 */
public class AddressUtil {
    
    private static final Logger logger = Logger.getLogger(AddressUtil.class);
    private static final List<String> states = new ArrayList<>();
    private static final Map<String, List<String>> cities = new TreeMap<>();
    
    static {
        ClassLoader classLoader = AddressUtil.class.getClassLoader();
        try {
            URL url = classLoader.getResource(PropertyUtil.getProperty("data.set.states"));
            System.out.println("Loading the state list from the " + url.getFile() + " file ...");
            File file = new File(url.getFile());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            int counter = 0;
            while (line != null) {
                String[] splitted = line.split(",");
                System.out.println("uf: " + splitted[0] + ", state: " + splitted[1]);
                states.add(splitted[1]);
                counter++;
                line = reader.readLine();
            }
            System.out.println(counter + " states were loaded.");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException caught while reading state list: " + e.getMessage());
        } catch (IOException e) {
            logger.error("IOException caught while reading state list: " + e.getMessage());
        }
        for (String state : states) {
            cities.put(state, new ArrayList<>());
            System.out.println("state: " + state + ", size: " + cities.size());
        }
        try {
            URL url = classLoader.getResource(PropertyUtil.getProperty("data.set.cities"));
            System.out.println("Loading the city list from the " + url.getFile() + " file ...");
            File file = new File(url.getFile());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            int counter = 0;
            while (line != null) {
                String[] splitted = line.split(",");
                System.out.println("state: " + splitted[0] + ", city: " + splitted[1]);
                cities.get(splitted[0]).add(splitted[1]);
                counter++;
                line = reader.readLine();
            }
            System.out.println(counter + " cities were loaded.");
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException caught while reading city list: " + e.getMessage());
        } catch (IOException e) {
            logger.error("IOException caught while reading city list: " + e.getMessage());
        }        
    }
    
    public static List<String> getStates() {
        return states;
    }
    
    public static List<String> getStateCities(String state) {
        return cities.get(state);
    }
    
    public static void main(String[] args) {
        for (String state : AddressUtil.getStates()) {
            System.out.println("state: " + state);
        }
        for (String city : AddressUtil.getStateCities("Minas Gerais")) {
            System.out.println("city of MG: " + city);
        }
    }

    public static boolean isValid(String state) {
        return states.contains(state);
    }
    
    public static boolean isValid(String state, String city) {
        return states.contains(state) && cities.get(state).contains(city);
    }
    
}
