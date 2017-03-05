package net.kikkirej.pdfview.rcp;

import java.io.IOException;
import java.io.OutputStream;

import org.icepdf.ri.common.SwingController;

public class PDFController {

	private SwingController controller;

	PDFController() {
		// nur damit es unsichtbar ist.
	}
	
	PDFController(SwingController controller){
		this.controller = controller;
	}
	
	public void zoomIn(){
		controller.zoomIn();
	}
	
	public void zoomOut(){
		controller.zoomOut();
	}
	
	public float getUserZoom() {
		return controller.getUserZoom();
	}
	
	public void rotateLeft(){
		controller.rotateLeft();
	}
	
	public void rotateRight(){
		controller.rotateRight();
	}
	
	public float getUserRotation(){
		return controller.getUserRotation();
	} 
	
	public void updateDocumentsView(){
		controller.updateDocumentView();
	}
	
	public Integer getCurrentPage() {
		return controller.getCurrentPageNumber();
	}
	
	public Integer getPageCount(){
		return controller.getDocument().getNumberOfPages();
	}
	
	public void print(){
		controller.showPrintSetupDialog();
	}
	
	public void searchPanel(){
		controller.showSearchPanel();
	}
	
	public void saveToStream(OutputStream stream) throws IOException{
		controller.getDocument().saveToOutputStream(stream);
	}
	
	public Boolean havePermissionToPrint(){
		return controller.havePermissionToPrint();
	}
}
