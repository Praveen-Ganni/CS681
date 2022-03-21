package edu.umb.cs681.hw03;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CovidDataCSV {
    @SuppressWarnings("rawtypes")
	public static void main(String[] args) {
        // Create a Path object from a string path name and read the csv file.
        Path path = Paths.get("src/Data" , "Model_12.4_20210113_data.csv");

        try (Scanner scanner = new Scanner(System.in)) {
			// Get county as input.
			System.out.println("Enter county name: Eg: Suffolk, Livingston");
			String county = scanner.nextLine();

			// get state as input.
			System.out.println("Enter state name: Eg: Massachusetts, Missouri, New York");
			String state = scanner.nextLine();

			// Create a stream of lines from the csv file.
			try( Stream<String> lines = Files.lines(path) ){

			    // Convert the stream to a list.
			    List<List<String>> matrix = lines.map( line -> {
			                return Stream.of( line.split(",") )
			                        .map(value->value.substring(0, value.length()))
			                        .collect( Collectors.toList() ); })
			            .collect( Collectors.toList() );

			    // Get all rows with the state name.
			    List my_state = matrix.stream().filter((i) ->
			            i.get(4).contains(state)).collect(Collectors.toList());

			    // check if the state is present in the list.
			    if (my_state.size() == 0) {
			        System.out.println("No data found for the state entered.");
			        return;
			    }

			    // Get the row within the state with the county name.
			    List county_list = matrix.stream().filter((i) ->
			            i.get(4).contains(state)).collect(Collectors.toList()).
			    stream().filter((i) ->
			            i.get(5).contains(county)).collect(Collectors.toList());

			    // check if county exists in the state.
			    if (county_list.size() == 0) {
			        System.out.println("No data found for the county in state "+ state + ".");
			        return;
			    }

			    List my_county = (List) county_list.get(0);

			    System.out.println("\n1. Number of deaths occurred in the '"+ county +"' county of the '"+ state +"' state are: " + my_county.get(7));
			    String deathInState = matrix.stream().filter((i) ->
			                    i.get(4).contains(state))
			            .map((i) -> i.get(7)).reduce("0", (subtotal, element) ->
			                    String.valueOf(Integer.parseInt(subtotal) + Integer.parseInt(element)));
			    System.out.println("\n2. Total number of deaths in State '"+state+"' are: " + deathInState);
			    System.out.println("\n3. Average number of deaths in each county of State '"+state+"' are: " + Integer.parseInt(deathInState)/my_state.size());
			}
			catch(IOException ex) {
			    // Handle the exception.
			    System.out.println("Error: " + ex.getMessage());
			    ex.printStackTrace();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
