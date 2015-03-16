package pl.edu.agh.kis.interlight.fx.model;

import java.nio.file.Path;

public class Ies {

	private Path iesFile;

	public Ies(Path iesFile) {
		this.iesFile = iesFile;
	}

	@Override
	public String toString() {
		return iesFile.getFileName().toString();
	}

	public Path getIesFile() {
		return iesFile;
	}

}
