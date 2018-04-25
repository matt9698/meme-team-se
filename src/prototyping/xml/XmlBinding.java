/*
 * Meme Team Software Engineering Project
 * Property Tycoon
 */
package prototyping.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author matth
 */
public class XmlBinding
{
    public static void main(String[] args) throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance("prototyping.xml");
    }
    
    public static class BindingTestObject
    {
        @XmlAttribute
        private int x;
        
        @XmlAttribute
        private int y;
    }
}
