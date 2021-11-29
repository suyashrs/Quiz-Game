package scripts;


public class Main {				
	static Game game;			
	
	static String[][] questions = {{"What was the first eraser often used before rubber?", "Which country is Nestle from?", "What is the biggest island in the world?"},
								   {"In what year did 'Star Wars Episode IV: A New Hope' release?", "Which movie features a New York policemen, taking on a gang of criminals in a Los Angeles skyscraper on Christmas Eve?", "Which famous DC Comics trilogy was directed by Chrisopher Nolan?"},
								   {"What movies main star is the rapper himself?", "Which movie is based on the backstory of the rap group NWA?", "Which band currently plays for 'The Tonight Show starring Jimmy Fallon'?"}};
	static String[][] answers = {{"Bread",   "Switzerland",   "Greenland"},   
								 {"1977",   "Die Hard",   "The Dark Knight Trilogy"},   
								 {"8 Mile", "Straight Outta Compton",   "The Roots"}};
	
	
	
public static void shuffleQuestions(String[][] q, String[][] a) {
	for(int r = 0; r < q.length; r++){
		for(int c = 0; c < q[0].length; c++) {
		int rand = (int)(Math.random()*3);
		String temp1 = q[r][c];
		q[r][c] = q[r][rand];
		q[r][rand] = temp1;
		String temp2 = a[r][c];
		a[r][c] = a[r][rand];
		a[r][rand] = temp2;
		}
		}
}
	public static void main(String[] args) {
		String ans;
		do{								
			//Reset the game
			game = new Game();			
			
			//Get number of players (from 1 to 3)
			int numPlayers = game.askForInt("How many players", 1, 3);

			//Add up to 3 players to the game
			for (int i = 0; i < numPlayers; i++) {
				String name = game.askForText("What is player " + i + " name?");
				game.addPlayer(name);				
			}
			
			
			shuffleQuestions(questions, answers);
			 
			int maxRounds = (questions[0].length*questions.length)/numPlayers;
			int numRounds = game.askForInt("Choose number of rounds (Each player gets one question per round)", 1, maxRounds);
			while(numRounds > maxRounds) {
				numRounds = game.askForInt("Choose number of rounds (Each player gets one question per round)", 1, maxRounds);
			}
			int next = 0;
			int cat1 = 0;
			int cat2 = 0;
			int cat3 = 0;
			int cat;
			
			for(int i = 0; i < numRounds; i++) {
				for(int j = 0; j < numPlayers; j++) {			
				game.setCurrentPlayer(j);//draw rectangle around player 0, and currentPlayer = player0
				cat = game.askForInt("Pick a category: 1-General Knowledge, 2-Movies, 3-Music", 1, questions[0].length);
				while(cat > questions[0].length || cat < 1) {
					cat = game.askForInt("Invalid Answer. Pick a category: 1-General Knowledge, 2-Movies, 3-Music", 1, questions[0].length);
				}
				if(cat1==3) {
					while(cat == 1) {
						cat = game.askForInt("This category is complete. Pick another category: 1-General Knowledge, 2-Movies, 3-Music", 1, questions[0].length);
					}
				}
				else if(cat2==3) {
					while(cat == 2) {
						cat = game.askForInt("This category is complete. Pick another category: 1-General Knowledge, 2-Movies, 3-Music", 1, questions[0].length);
					}
				}
				else if(cat3==3) {
					while(cat == 3) {
						cat = game.askForInt("This category is complete. Pick another category: 1-General Knowledge, 2-Movies, 3-Music", 1, questions[0].length);
					}
				}
				

				String answer = game.askForText(questions[cat-1][next]);
				if(answers[cat-1][next].equals(answer)) {
					game.correct();     //display "Correct", increment score, change frame color to green
					next++;
					if(cat == 1) {
						cat1++;
					}
					else if(cat == 2) {
						cat2++;
					}
					else if(cat == 3) {
						cat3++;
					}
				}
				else {
					game.incorrect();}	//display "incorrect", change frame color of player to red
			}
			}	
			
			//Do you want to play again? 
			ans = game.askForText("Play again? (Y/N)"); 
			while(ans != null && !ans.toUpperCase().equals("Y") && !ans.toUpperCase().equals("N"))
				ans = game.askForText("Invalid input. Play again? (Y/N)");
		}while(ans.toUpperCase().equals("Y"));	//play again if the user answers "Y" or "y"

		System.exit(1); 	//This statement terminates the program
		
	}
}
