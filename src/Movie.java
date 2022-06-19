import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Movie {
	public Play IPlay;
	public String title;
	public int runtime;
	public String category;
	public ArrayList<String> scenes = new ArrayList<String>();
	// this method returns the movie name

	public String getTitle() {
		return title;
	}

	// this method returns the movie runtime
	public int getRuntime() {
		return runtime;
	}

	// this method returns the total no of scenes in a movie
	public int getSceneLength() {
		return scenes.size() - 1;
	}

	public void printInfo() {
		System.out.println(this.title);
		System.out.println("Category: " + category);
		System.out.println("Runtime: " + runtime + "minutes");
	}

	public void printScenes() {
		// looping through the arraylist of a screen and printing all screens
		for (int i = 0; i < scenes.size(); i++) {
			System.out.println(i + "  " + scenes.get(i));
		}
	}

	public void play() {
		IPlay.play(scenes);
	}

}

interface Play {
	public void play(List<String> Scenes);
}

class VHS extends Movie implements Play {
	public int currentTime = 0;

	// this constructor is instantly called whenever an object of VHS type is
	// created.
	public VHS(String title, int runtime, ArrayList<String> scenes, int currentTime, String category) {
		super();
		this.title = title;
		this.runtime = runtime;
		this.scenes = scenes;
		this.currentTime = currentTime;
		this.category = category;
		this.IPlay = this;

	}

	// this method returns the currenttime
	public int getCurrentTime() {
		return currentTime;
	}

	// this method sets the currenttime to 0
	public void rewind() {
		currentTime = 0;
	}

	@Override
	public void play(List<String> Scenes) {

		System.out.println("Scene" + ": " + scenes.get(currentTime));
		currentTime++;
		// if currenttime is greater then scene length rewind method is called
		if (currentTime >= scenes.size())
			rewind();

	}
}

class DVD extends Movie implements Play {
	static Scanner input = new Scanner(System.in);

	// this constructor is instantly called whenever an object of DVD type is
	// created.
	public DVD(String title, int runtime, ArrayList<String> scenes, String category) {
		super();
		this.title = title;
		this.runtime = runtime;
		this.scenes = scenes;
		this.category = category;
		this.IPlay = this;

	}

	@Override
	public void play(List<String> Scenes) {
		int size = scenes.size() - 1;
		System.out.println("Which scene would you like to watch?");
		printScenes();
		System.out.println("Would you like to watch? Select 0 to " + size + ":");
		int scene = input.nextInt();
		input.nextLine();
		// printing the intended scene
		System.out.println("Scene " + scene + ": " + scenes.get(scene));

	}

	public static void main(String[] args) {
		// creating an arraylist of scenes
		ArrayList<String> sc = new ArrayList<String>();
		sc.add("a");
		sc.add("b");
		sc.add("c");
		sc.add("d");
		// creating 6 movies and adding them to an arraylist
		ArrayList<Movie> movies = new ArrayList<>() {
			{
				add(new DVD("DVD1", 30, sc, "category"));
				add(new DVD("DVD2", 60, sc, "category"));
				add(new DVD("DVD3", 90, sc, "category"));
				add(new VHS("VHS1", 30, sc, 0, "category"));
				add(new VHS("VHS2", 60, sc, 0, "category"));
				add(new VHS("VHS3", 90, sc, 0, "category"));

			}
		};
		System.out.println("Welcome to GC Blockbuster!\n" + "Please Select a Movie from the list:");
		for (int i = 0; i < movies.size(); i++) {
			System.out.print(i + 1 + ". ");
			// printing the movie name
			System.out.println(movies.get(i).getTitle());
		}
		while (true) {

			System.out.println("Please select a movie you want to watch: ");
			int movieIndex = input.nextInt();
			input.nextLine();
// printing all the infos of a movie
			movies.get(movieIndex - 1).printInfo();
//this loops keeps on going until the user inputs the correct input(Y/N)
			while (true) {
				System.out.print("Do you want to watch the movie? Y/N  ");

				String response = input.nextLine();
				if (response.equals("N")) {
					System.out.println("Bye!");
					break;

				} else if (response.equals("Y")) {

					// playing the scene
					movies.get(movieIndex - 1).play();

				}

			}

		}
	}

}
