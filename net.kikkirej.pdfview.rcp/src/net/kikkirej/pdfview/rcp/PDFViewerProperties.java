package net.kikkirej.pdfview.rcp;

import java.util.Properties;

import org.icepdf.ri.util.PropertiesManager;

public class PDFViewerProperties {

	private Properties props;
	
	
	public PDFViewerProperties(){
		props = new Properties();
		loadDefaultProperties();
	}
	
	private void loadDefaultProperties() {
		showAnnotationOptions(false);
		props.setProperty("application.showLocalStorageDialogs","false");
	}

	Properties getProperties(){
		return props;
	}
	
	public void setProperty(String key, String value){
		props.setProperty(key, value);
	}
	
	public void hideToolbar(Boolean hide){
		setBoolean("application.viewerpreferences.hidetoolbar", hide);
	}
	
	public void showPrint(Boolean showPrint){
		setBoolean("application.toolbar.show.utility.print", showPrint);
	}
	
	public void showSearch(Boolean showSearch){
		setBoolean("application.toolbar.show.utility.search", showSearch);
	}
	
	public void showOpen(Boolean value){
		setBoolean("application.toolbar.show.utility.open", value);
	}
	
	public void showAnnotationOptions(Boolean showAnnotation){
		setBoolean(PropertiesManager.PROPERTY_SHOW_TOOLBAR_ANNOTATION, showAnnotation);
		setBoolean("application.utilitypane.show.annotation", showAnnotation);
	}
	
	public void showStatusBar(Boolean showStatusBar){
		setBoolean("application.statusbar", showStatusBar);
	}
	
	public void showUtilityPane(Boolean value){
		setBoolean("application.toolbar.show.utility.upane", value);
	}
	
	public void showUtility(Boolean value){
		setBoolean("application.toolbar.show.utility", value);
	}
	
	public void showZoom(Boolean value){
		setBoolean("application.toolbar.show.zoom", value);
	}
	
	public void showBookmarks(Boolean value){
		setBoolean("application.utilitypane.show.bookmarks", value);
	}
	
	private void setBoolean(String key, Boolean value) {
		if(value){
			props.setProperty(key, "true");
		}else {
			props.setProperty(key, "false");
		}
	}
	
}
