import edu.duke.*;
import java.io.*;

/**
 * Write a description of Grayscale here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grayscale {
	public ImageResource makeGray(ImageResource inImage){
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		
		for(Pixel pixel : outImage.pixels()){
			Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
			int average = (inPixel.getRed()+inPixel.getGreen()+inPixel.getBlue())/3;
		
			pixel.setRed(average);
			pixel.setGreen(average);
			pixel.setBlue(average);
		}		
		return outImage;
	}
	
	public void selectAndConvert(){
		DirectoryResource dr = new DirectoryResource();
		for(File f: dr.selectedFiles()){
			ImageResource inImage = new ImageResource(f);
			ImageResource gray = makeGray(inImage);
			gray.draw();
			
			String fname = f.getName();
			String newName = "copy-"+fname;
			gray.setFileName(newName);
			gray.save();
		}
	}
}
