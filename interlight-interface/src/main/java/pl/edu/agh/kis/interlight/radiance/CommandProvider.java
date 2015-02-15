package pl.edu.agh.kis.interlight.radiance;

import java.io.File;
import java.nio.file.Paths;

public class CommandProvider {

	private String systemDir;

	public CommandProvider() {
		this.systemDir = createSystemDir();
	}

	public String getCommandsPath() {
		return Paths
				.get(System.getProperty("user.dir") + "/lib/radiance/"
						+ systemDir + "/").toAbsolutePath().toString();
	}

	public String createCommand(String command) {
		return getCommandsPath() + File.separator + command;
	}

	private String createSystemDir() {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("win")) {
			return "win";
		} else if (osName.contains("linux")) {
			if (System.getProperty("os.arch").contains("64")) {
				return "linux64";
			} else {
				return "linux32";
			}
		} else {
			throw new UnsupportedOperationException("Unsupported OS");
		}
	}

}
