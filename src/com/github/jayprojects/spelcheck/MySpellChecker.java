package com.github.jayprojects.spelcheck;

import com.swabunga.spell.engine.*;
import com.swabunga.spell.event.*;
import java.io.*;
import java.util.Iterator;
import java.util.List;


public class MySpellChecker implements SpellCheckListener {
	StringBuffer returnTextBuilder;
	
	public MySpellChecker(String text, String dictFile, String phonetFile) {
		try {
			returnTextBuilder = new StringBuffer();
			returnTextBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			returnTextBuilder.append("<spellresult error=\"0\" clipped=\"0\" charschecked=\""+text.length()+"\">\n");
			
			
			SpellDictionary dictionary = new SpellDictionaryHashMap(new File(dictFile), new File(phonetFile));
			SpellChecker spellCheck = new SpellChecker(dictionary);
			spellCheck.addSpellCheckListener(this);
			spellCheck.checkSpelling(new StringWordTokenizer(text));
			returnTextBuilder.append("</spellresult>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void spellingError(SpellCheckEvent event) {
		int position =event.getWordContextPosition();
		int length = event.getInvalidWord().length();
		List suggestions = event.getSuggestions();
		if (suggestions.size() > 0) {
		String seperator="";
		returnTextBuilder.append("<c o=\""+position+"\" l=\""+length+"\" s=\"1\">");
		for (Iterator suggestedWord = suggestions.iterator(); suggestedWord.hasNext();) {
			returnTextBuilder.append(seperator+suggestedWord.next());
			seperator=" ";
		}
		returnTextBuilder.append("</c>\n");
		} else {
			returnTextBuilder.append("<c o=\""+position+"\" l=\""+length+"\" s=\"0\"></c>\n");
		}
	
	}
	
	
	public String getReturnText() {
		return returnTextBuilder.toString();
	}
}
