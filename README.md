google_spell_wrapper_jazzy
======================
A wrapper (Server application) to make Jazzy Spellchecker to mimic google spellcheck interface.


Google has shutdown their spell check service. Oh no! (sky falling emo!)
Well it’s really not sky falling event, science most of the modern browser already has its built-in spellcheck functionality. But if you are concern about poor IE users and you had used google spellcheck then it might not be an easy task to switch all the pages to a new spellchecker.
As it turns out there are not may open source spellchecker out there, on my short search I liked “Jazzy - Java Spell Check API (http://sourceforge.net/projects/jazzy/)” if you too like jazzy as your replacement and don’t want to get your hands dirty to change your current google spellcheck based front end then here is a rescue option for you.

You can deploy this to your server then simply change your google spellcheck url to your own url 

For example 
From “https://www.google.com/tbproxy/spell?lang=” to say “https://yoursite.com/google_spell_wrapper_jazzy/spellcheck.jsp?lang=”

Disclaimer
======================
This is a quick fix, I haven’t looked into Jazzy’s code in depth, also I ignored some of the functionality that google spellchecker had such as (ignoredups, ignoredigits, ignoreallcaps). Not to mention that the dictionary file itself is not as rich.
