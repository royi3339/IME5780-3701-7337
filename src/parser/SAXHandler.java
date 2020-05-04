package parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
    boolean inFirstNameElement = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("firstName") { inFirstNameElement = true; }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("firstName")) { inFirstNameElement = false; }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(inFirstNameElement) {
            // do something with the characters in the <firstName> element
        }
    }
}
