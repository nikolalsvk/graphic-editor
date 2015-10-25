package app.tools;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DiagramFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		return (f.isDirectory() || f.getName().toLowerCase().endsWith(".gpf"));
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "GE Project Files (*.gpf)";
	}

}
