
import java.io.*;
import java.util.*;

import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDF_Creator {

	public static void main(String[] args) {
	
//		System.out.println("Hello World again");
		
//		File file = new File("/home/sourav/Documents/DIC/lab5.pdf");
		
		try {
		    PDDocument document = null;
		    File file = new File("/home/sourav/Documents/Finance/hello.pdf");
		    System.out.println("Hello World again");
		    document = PDDocument.load(file);
		    System.out.println("Hello World again");
		    
		    document.getClass();
		    if (!document.isEncrypted()) {
		        PDFTextStripper stripper = new PDFTextStripper();
		        stripper.setSortByPosition(true);
		        PDFTextStripper Tstripper = new PDFTextStripper();
		        String st = Tstripper.getText(document);
		        System.out.println("Text:" + st);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}

	}

}
