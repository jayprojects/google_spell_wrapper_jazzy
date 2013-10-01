<%@page import="com.github.jayprojects.spelcheck.MySpellChecker"%>
<%@ page import="org.w3c.dom.*" %>
<%@ page import="javax.xml.parsers.*" %>


<%!
public static String getCharacterDataFromElement(Element e) {
	Node child = e.getFirstChild();
	if (child instanceof CharacterData) {
		CharacterData cd = (CharacterData) child;
		return cd.getData();
	}
	return "";
}

public static String getText(HttpServletRequest request)
{
	StringBuffer sb = new StringBuffer();
	try{
		ServletInputStream is = request.getInputStream(); 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder = factory.newDocumentBuilder(); 
		Document doc = builder.parse(is); 

		NodeList nodes = doc.getElementsByTagName("spellrequest");
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);
			NodeList name = element.getElementsByTagName("text");
			Element line = (Element) name.item(0);
			sb.append(getCharacterDataFromElement(line));
		}
	}
	catch(Exception e)
	{
		return "";
	}
	return sb.toString();
}
%>

<%
	ServletContext      context  = getServletConfig().getServletContext();
	String dictFile = context.getRealPath("/WEB-INF/dict/english.0");
	String phonetFile = context.getRealPath("/WEB-INF/dict/phonet.en");
	
	String text = getText(request);
	MySpellChecker sc = new MySpellChecker(text, dictFile, phonetFile);
	
	String returnText = sc.getReturnText();
	out.println(returnText);
%>