package ej1;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Result;
import javax.xml.transform.Source;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Main {
	private static ArrayList<Alumno> studentList = new ArrayList<>();
	private static DocumentBuilderFactory fnaf;
	private static DocumentBuilder constructor;
	private static DOMImplementation implement;
	private static Document doc;

    /*
    Objetivo:
      <Alumnos>
         <Alumno>
             <nombre>Marcos</nombre>
             <apellidos>Peladin</apellidos>
             <ciclo>Informatica</ciclo>
             <curso>1</curso>
             <grupo>A</grupo>
             <nia>123456</nia>
             <genero>M</genero>
             <fechadenacimiento>2000-01-01</fechadenacimiento>
         </Alumno>
         x5
      </Alumnos>
    */
	public static void createElements(Alumno a) {
		Element alumno = doc.createElement("Alumno");
		createChildElement(alumno, "nombre", a.getNombre());
		createChildElement(alumno, "apellidos", a.getApellidos());
		createChildElement(alumno, "ciclo", a.getCiclo());
		createChildElement(alumno, "curso", a.getCurso());
		createChildElement(alumno, "grupo", a.getGrupo());
		createChildElement(alumno, "nia", String.valueOf(a.getNia()));
		createChildElement(alumno, "genero", String.valueOf(a.getGenero()));
		createChildElement(alumno, "fechadenacimiento", a.getFechadenacimiento().toString());
		doc.getDocumentElement().appendChild(alumno);
	}

	public static void createChildElement(Element parent, String name, String contenido) {
		Element element = doc.createElement(name);
		element.appendChild(doc.createTextNode(contenido));
		parent.appendChild(element);
	}

	public static void assignHierarchiesElements(Element Alumno, Element nombre, Element apellidos, Element ciclo,
			Element curso, Element grupo, Element nia, Element genero, Element fechadenacimiento) {
		Alumno.appendChild(nombre);
		Alumno.appendChild(apellidos);
		Alumno.appendChild(ciclo);
		Alumno.appendChild(curso);
		Alumno.appendChild(grupo);
		Alumno.appendChild(nia);
		Alumno.appendChild(genero);
		Alumno.appendChild(fechadenacimiento);
		doc.getDocumentElement().appendChild(Alumno);
	}

	private static void createDocument() {
	    try {
	        fnaf = DocumentBuilderFactory.newInstance(); 
	        constructor = fnaf.newDocumentBuilder();
	        implement = constructor.getDOMImplementation();
	        doc = implement.createDocument(null, "Alumnos", null);
	        doc.setXmlVersion("1.0");
	    } catch (ParserConfigurationException e) {
	        e.printStackTrace();
	    }
	}


	private static void createFile(String name) {
		try {
			Source sauce = new DOMSource(doc);
			Result result = new StreamResult(new File(name));
			Transformer optimus = TransformerFactory.newInstance().newTransformer();
			optimus.transform(sauce, result);
		} catch (TransformerException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		studentList = CreacionDeAlumnos.create5students();
		createDocument();
		for (Alumno a : studentList) {
			createElements(a);
		}
		createFile("resultado.xml");
	}
}
