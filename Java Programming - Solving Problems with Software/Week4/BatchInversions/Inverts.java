import java.io.*;
import edu.duke.*;

/**
 * Write a description of Inverts here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inverts {
	public ImageResource makeInverted(ImageResource inImage){
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		
		for(Pixel pixel : outImage.pixels()){
			Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
			
			pixel.setRed(255-inPixel.getRed());
			pixel.setGreen(255-inPixel.getGreen());
			pixel.setBlue(255-inPixel.getBlue());
		}		
		return outImage;
	}
	
	public void selectAndConvert(){
		DirectoryResource dr = new DirectoryResource();
		for(File f: dr.selectedFiles()){
			ImageResource inImage = new ImageResource(f);
			ImageResource invert = makeInverted(inImage);
			invert.draw();
			
			String fname = f.getName();
			String newName = "copy-"+fname;
			invert.setFileName(newName);
			invert.save();
		}
	}
}
