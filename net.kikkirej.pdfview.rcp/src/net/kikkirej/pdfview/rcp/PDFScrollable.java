package net.kikkirej.pdfview.rcp;

interface PDFScrollable {
	/**
	 * This method is used to scroll the currently opened PDF to a specified
	 * page
	 * 
	 * @param page
	 *            - the page# to scroll to
	 */
	public void scrollToPage(int page);

	/**
	 * This method is used to scroll the currently opened PDF to a specified
	 * named destination
	 * 
	 * @param destination
	 *            - a string representing the name of the destination
	 */
	public void scrollToDestination(String destination);
}