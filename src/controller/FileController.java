package controller;

import model.HighscoreList;
import model.HighscorePair;

import java.io.*;
import java.util.regex.Pattern;

/**
 * FileController is used to read and write to our highscore.txt file in which we store all scores.
 */
public final class FileController {

    //Make sure there are no other instances
    private FileController() {
    }


    public static HighscoreList readHighscore() {
	HighscoreList highscoreList = new HighscoreList();
	Pattern pattern = Pattern.compile(":");
        //Temp variables to add highscorelist
	try {
	    BufferedReader bufferedReader = new BufferedReader(new FileReader("highscorelist.txt"));
	    try {
		String tmpString;
	   	 do  {
		     tmpString = bufferedReader.readLine();
		     if(tmpString == null){
			 break;
		     }
                	//Format Name:Score
			String[] tmpArray = pattern.split(tmpString);
			String userName = tmpArray[0];
			Integer score = Integer.parseInt(tmpArray[1]);
			highscoreList.addHighscore(userName, score);
           	 } while ( !tmpString.isEmpty());
	    } finally {
	    	    bufferedReader.close();
	    	}
        } catch (IOException e) {
    		e.printStackTrace();
	    	return highscoreList;
     	}

    	return highscoreList;
    }


    public static void writeHighscoreOnFile(HighscoreList highscoreList) {
	try {
	    FileWriter fileWriter = new FileWriter("highscorelist.txt");
	    try {
		for (HighscorePair pair : highscoreList.getHighscoreList()) {
		    String tmpString = pair.getName() + ":" + pair.getScore();
		    fileWriter.write(tmpString + "\n");
		}
		fileWriter.flush();
	    } finally {
		fileWriter.close();
	    }
	} catch (IOException e) {
		e.printStackTrace();
	}
    }
}
