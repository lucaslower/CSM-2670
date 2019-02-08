/*
CSM 2670 -- Homework 3
Lucas Lower
02/07/2019
Exercise3.java
Displays information about the popularity of a name over time.
Usage: Exercise3 (interactive)

Note: I realized after this was mostly complete that "Rank" would be a better popularity comparison,
because the percentages are always pretty small. I couldn't really figure out how to fit that into
my code without an easy way to sort hashmaps by value.
*/
import java.util.*;
import java.io.*;

public class Main {

    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {

        // print welcome
	    System.out.println("Welcome to NPA (Name Popularity Analyzer). This program analyzes a name's changing popularity over time.");
	    System.out.println("Enter a name and a gender, and a table of the popularity over time will be output.");
	    System.out.println("Note: \"popularity\" is represented as the percentage of population of the chosen gender with that name for a given year.");
	    System.out.println();

	    // get options
        String name = get_input("Enter a name");
        String gender = "";
        while(!gender.equals("M") && !gender.equals("F")){
            gender = get_input("Enter a gender:");
            gender = gender.toUpperCase();
        }

        // print wait message so they don't get antsy
        System.out.println("Analyzing popularity . . . ");
        System.out.println();

        // run popularity analysis
        popularity_analysis(name, gender);

    }

    // note: I just throw the error below because I get the file name from a listFiles() call, so it *should* exist
    // unless it gets deleted WHILE we're running
    private static void popularity_analysis(String user_name, String user_gender) throws FileNotFoundException{
        // get files
        File cur_dir = new File("./");
        File[] file_list = cur_dir.listFiles();
        // init ArrayList of years, and hashmap for (year => percentage)
        ArrayList<Integer> file_years = new ArrayList<>();
        Map<Integer, Float> year_map = new HashMap<>();
        // loop through files and find data files
        for(File f : file_list){
            String f_name = f.getName();
            // check if file name is in the data file format
            if(f_name.matches("^yob[0-9][0-9][0-9][0-9][.]txt$")){
                // here we are just assuming that if the file name specifically matched the above regex, it's most likely
                // a *good* data file. Of course, a nefarious user could fake the name and still pass in bad data.

                // scanner to read the file
                Scanner reader = new Scanner(f);
                // parse year from file name
                int file_year = Integer.valueOf(f_name.substring(3,7));
                // init percentage vars
                float total_pop = 0;
                float user_pop = 0;
                // loop through each line and find occurrences of the name
                int line_count = 1;
                while(reader.hasNextLine()){
                    // read in entire line
                    String line = reader.nextLine();
                    // check for line validity against the following regex, which matches:
                    // <a string of letters, any length>,<M or F>,<a string of numbers, any length>
                    if(line.matches("[a-zA-z]+[,][M|F][,][0-9]+")){
                        // new scanner to parse the line
                        Scanner line_reader = new Scanner(line).useDelimiter(",");
                        // parse components
                        String line_name = line_reader.next();
                        String line_gender = line_reader.next();
                        int line_pop = line_reader.nextInt();
                        // only parse names of given gender
                        if(line_gender.equals(user_gender)){
                            // save user_name's count
                            if(line_name.toLowerCase().equals(user_name.toLowerCase())){
                                user_pop = line_pop;
                            }
                            // add to overall population count
                            total_pop += line_pop;
                        }
                    }
                    else{
                        System.err.println("ERROR: Bad data found in file \""+f_name+"\" on line "+line_count);
                        System.exit(1);
                    }
                    // increment line count
                    line_count++;
                }// END line loop
                // file has been read, calculate percentage
                float pop_percent = (user_pop/total_pop)*100;
                // add year to list
                file_years.add(file_year);
                // add year to map
                year_map.put(file_year, pop_percent);
            }
        }// END file loop
        // print table of percentages
        print_table(file_years, year_map);
        // print max and min info
        print_max_min(year_map);
    }

    private static void print_max_min(Map<Integer, Float> year_map){
        // init max entry
        Map.Entry<Integer, Float> max_entry = null;
        // loop to find max entry
        for(Map.Entry<Integer, Float> entry : year_map.entrySet()){
            if(max_entry == null || entry.getValue().compareTo(max_entry.getValue()) > 0){
                max_entry = entry;
            }
        }
        // init min entry
        Map.Entry<Integer, Float> min_entry = max_entry;
        // loop to find min entry
        for(Map.Entry<Integer, Float> entry : year_map.entrySet()){
            if(min_entry == null || entry.getValue().compareTo(min_entry.getValue()) < 0){
                min_entry = entry;
            }
        }
        System.out.println();
        System.out.printf("Maximum popularity: %d, %.2f%%\n", max_entry.getKey(), max_entry.getValue());
        System.out.printf("Minimum popularity: %d, %.2f%%\n", min_entry.getKey(), min_entry.getValue());
    }

    private static void print_table(ArrayList<Integer> years, Map<Integer, Float> percentages){
        // sort year list
        Collections.sort(years);
        // print table headers
        System.out.printf("%-6s|%9s\n", "Year", "Percent");
        System.out.println("----------------");
        // loop through each
        for(int year : years){
            System.out.printf("%-6d|%8.2f%%\n", year, percentages.get(year));
        }
    }

    private static String get_input(String prompt){
        System.out.println(prompt);
        System.out.print(">  ");
        String input = "";
        if(INPUT.hasNext()){
            input = INPUT.next();
        }
        System.out.println();
        return input;
    }

}
