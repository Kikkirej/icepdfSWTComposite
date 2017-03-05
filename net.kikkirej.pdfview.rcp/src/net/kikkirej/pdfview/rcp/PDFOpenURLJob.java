package net.kikkirej.pdfview.rcp;

import java.net.URL;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.icepdf.ri.common.SwingController;

class PDFOpenURLJob extends Job {

	private SwingController controller;
	private URL url;

	public PDFOpenURLJob(SwingController controller, Boolean init, URL url) {
		super("Datei öffnen");
		this.controller = controller;
		this.url = url;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		controller.openDocument(url);
		controller.setDocumentToolMode(4); // Set single page continuous as view mode 
		controller.setPageViewMode(2, true); // Get the document library used with named destinations support 
		return Status.OK_STATUS; 
	}

}
