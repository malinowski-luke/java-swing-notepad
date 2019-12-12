package utils;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParsing {
	private String[] msgArr;
	private String[] statusArr;
	
	public TextParsing(String text){
		String [] msgArr = {
			"your text contains "+count(text,"word")+" words.",
			"your text contains "+count(text,"sentances")+" sentances.",
			"flesch score = "+getFleschScore(text)
		};
		String [] statusArr = {
			"word count : "+count(text,"word"),
			"sentances count : "+count(text,"sentances"),
			"flesch Score : "+getFleschScore(text)
		};
		this.msgArr = msgArr;
		this.statusArr = statusArr;
	}
	public String [] getMsgArr() {return msgArr;}
	public String [] getStatusArr() {return statusArr;}
	private int count(String text,String action) {
		String regexPattern = (action.equalsIgnoreCase("sentances"))?"[^.?!]+":"[\\w]+";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher match = pattern.matcher(text);
		int counter = 0;
		while(match.find()) {
			counter++;
		} return counter;
	}
	public int countSyllables(String text) {
		String pattern = "[aeiouyAEIOUY]";
		Pattern tokenSplitter = Pattern.compile(pattern);
		Matcher match = tokenSplitter.matcher(text);
		String lastToken = "";
		LinkedList list = new LinkedList<Integer>();
		int counter = 0;
		while(match.find()){
			counter++;
			lastToken = match.group();
			list.add(match.start());
			if(list.contains(match.start())&&list.contains(match.start()-1)) {
				counter--;
				list.clear();
			} 
		} 
		if(text.charAt(text.length()-1)=='e'&&lastToken.contentEquals("e")) counter--;
		return counter;
	}
	private double getFleschScore(String text) {
		if(text.equals("")) return 0.0;
		int wordCount = count(text,"words"),
				sentanceCount = count(text,"sentances"),
				syllableCount = countSyllables(text);
		double averageSentanceLength = (double)(wordCount)/sentanceCount,
				averageSyllablesPerWord =  (double)(syllableCount)/wordCount,
				fleschScore = 0.39*(averageSentanceLength)+11.8*(averageSyllablesPerWord)-15.59;
		return (fleschScore>18.0||fleschScore<0.0)
				? 0.0
				:Math.round(fleschScore*10)/10.0;
	}
}
