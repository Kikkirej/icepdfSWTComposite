package net.kikkirej.pdfview.rcp;

import java.awt.Frame;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.icepdf.core.pobjects.Destination;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Name;
import org.icepdf.core.util.Library;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.util.PropertiesManager;

public class PDFViewer extends Composite implements PDFScrollable {

	Frame frame;
	SwingViewBuilder factory;
	SwingController controller;
	Library library;
	JPanel viewerComponentPanel;
	int currentPage;
	boolean useCurrentPage;
	Destination currentDestination;
	boolean useCurrentDestination;
	boolean initialized;

	public PDFViewer(Composite parent, int style, PDFViewerProperties pDFViewerProps) {
		super(parent, SWT.EMBEDDED);

		init(pDFViewerProps);

	}

	private void init(PDFViewerProperties pDFViewerProps) {
		currentPage = 1; 
		useCurrentPage = true; 
		useCurrentDestination = false; 
		initialized = false;

		frame = SWT_AWT.new_Frame(this); 
		controller = new SwingController(); // Build a SwingViewFactory configured with the controller 

		controller.getDocumentViewController().setAnnotationCallback( new org.icepdf.ri.common.MyAnnotationCallback(controller.getDocumentViewController())); // Use the factory to build a JPanel that is pre-configured with a complete, active Viewer UI.

		Properties props= pDFViewerProps.getProperties();
		PropertiesManager properties=new PropertiesManager(System.getProperties(),props,ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));

		factory = new SwingViewBuilder(controller, properties); // add interactive mouse link annotation support via callback 

		viewerComponentPanel = factory.buildViewerPanel(); 
		controller.setAnnotationPanel(null);
		frame.add(viewerComponentPanel);
		frame.pack(); 
		frame.setVisible(true); // add copy keyboard command 
		ComponentKeyBinding.install(controller, viewerComponentPanel);
		setVisible(true);
	}

	@Override
	public boolean setFocus() {
		if (initialized) { // Every time the view is opened repaint to keep it updated 
			if (useCurrentPage) { // The viewer counts pages starting with `0`, thus we call page-1 
				controller.showPage(currentPage - 1); System.out.println("Scrolling to page " + currentPage); // Use method to extract current page text to a file located in C:\
			} 
			viewerComponentPanel.updateUI(); 
			System.out.println("Refreshing view"); }
		return true;
	}


	@Override
	public void scrollToPage(int page)
	{
		currentPage = page;
		useCurrentPage = true;
		useCurrentDestination = false;
		System.out.println("Current page set to " + currentPage);
	}

	@Override
	public void scrollToDestination(String destination)
	{
		library = controller.getDocument().getCatalog().getLibrary(); 
		if(library != null){
			currentDestination = new Destination(library, new Object());
			currentDestination.setNamedDestination(new Name(destination));
			useCurrentDestination = true;
			useCurrentPage = false;
			System.out.println("Destination set to " + destination);
		}
	}

	public String[] getText() throws InterruptedException{
		Document document = controller.getDocument();
		int numberOfPages = document.getNumberOfPages();
		String[] seiten = new String[numberOfPages];
		for (int i = 0; i < numberOfPages; i++) {
			seiten[i] = document.getPageText(i+1).toString();
		}
		return seiten;
	}

	public void openDocument(URL docUrl){
		PDFOpenURLJob openJob = new PDFOpenURLJob(controller, initialized, docUrl);
		openJob.schedule();
		initialized = true;
	}
	
	
	/**
	 * Öffnet eine Byte-Array
	 * @param file Das Byte-Array. welches geöffnet werden soll.
	 * @param pathOrUrl 
	 * @return
	 */
	public Job openDocument(byte[] file, String pathOrUrl) {
		Job job = new PDFOpenByteArrayJob(controller, file, pathOrUrl);
		job.schedule();
		return job;
	}
	
	public void closeDocument(){
		controller.closeDocument();
	}
	
	public PDFController getController(){
		return new PDFController(controller);
	}
	
}

