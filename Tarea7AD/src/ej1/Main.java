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

	public static void createElements(Alumno a) {
		Element Alumno = doc.createElement("Alumno");
		
		Element nombre = doc.createElement("nombre");
		nombre.appendChild(doc.createTextNode(a.getNombre()));
		
		Element apellidos = doc.createElement("apellidos");
		apellidos.appendChild(doc.createTextNode(a.getApellidos()));
		
		Element ciclo = doc.createElement("ciclo");
		ciclo.appendChild(doc.createTextNode(a.getCiclo()));

		Element curso = doc.createElement("curso");
		curso.appendChild(doc.createTextNode(a.getCurso()));

		Element grupo = doc.createElement("grupo");
		grupo.appendChild(doc.createTextNode(a.getGrupo()));

		Element nia = doc.createElement("nia");
		nia.appendChild(doc.createTextNode(String.valueOf(a.getNia())));

		Element genero = doc.createElement("genero");
		genero.appendChild(doc.createTextNode(String.valueOf(a.getGenero())));

		Element fechadenacimiento = doc.createElement("fechadenacimiento");
		fechadenacimiento.appendChild(doc.createTextNode(a.getFechadenacimiento().toString()));

		assignHierarchiesElements(Alumno, nombre, apellidos, ciclo, curso, grupo, nia, genero, fechadenacimiento);
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

	public static void main(String[] args) {
		studentList = CreacionDeAlumnos.create5students();
		try {
			fnaf = DocumentBuilderFactory.newInstance();
			constructor = fnaf.newDocumentBuilder();
			implement = constructor.getDOMImplementation();
			doc = implement.createDocument(null, "Alumnos", null);
			doc.setXmlVersion("1.0");

			for (Alumno a : studentList) {
				createElements(a);
			}

			Source sauce = new DOMSource(doc);
			Result result = new StreamResult(new File("resultado.xml"));
			Transformer optimus = TransformerFactory.newInstance().newTransformer();
			optimus.transform(sauce, result);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
