package controller;

import model.Highscore;
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


    public static Highscore readHighscore() {
	Highscore highscore = new Highscore();
	Pattern pattern = Pattern.compile(":");
        //Temp variables to add highscore
	try {
	    BufferedReader bufferedReader = new BufferedReader(new FileReader("highscore.txt"));
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
			highscore.addHighscore(userName, score);
           	 } while ( !tmpString.isEmpty());
	    } finally {
	    	    bufferedReader.close();
	    	}
        } catch (IOException e) {
    		e.printStackTrace();
	    	return highscore;
     	}

    	return highscore;
    }


    public static void writeHighscoreOnFile(Highscore highscore) {
	try {
	    FileWriter fileWriter = new FileWriter("highscore.txt");
	    try {
		for (HighscorePair pair : highscore.getHighscoreList()) {
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
