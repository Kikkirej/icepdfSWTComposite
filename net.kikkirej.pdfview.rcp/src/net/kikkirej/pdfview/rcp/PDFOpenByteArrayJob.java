package net.kikkirej.pdfview.rcp;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.icepdf.core.pobjects.Document;
import org.icepdf.ri.common.SwingController;

class PDFOpenByteArrayJob extends Job {


	private SwingController controller;
	private String pathOrUrl;
	private byte[] byteArray;

	public PDFOpenByteArrayJob(SwingController controller, byte[] byteArray, String pathOrUrl) {
		super("Datei öffnen");
		this.controller = controller;
		this.byteArray = byteArray;
		this.pathOrUrl = pathOrUrl;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			Document document = new Document();
			document.setByteArray(byteArray, 0, byteArray.length, pathOrUrl);
			controller.openDocument(document, pathOrUrl);
			return Status.OK_STATUS;
		} catch (Exception e) {
			
		}
		return Status.OK_STATUS;
	}

}
