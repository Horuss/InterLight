package pl.edu.agh.kis.interlight.radiance;

import java.io.File;
import java.nio.file.Paths;

public class CommandProvider {
	
	public static boolean isTest = false;

	private String systemDir;

	public CommandProvider() {
		this.systemDir = createSystemDir();
	}

	public String getCommandsPath() {
		String path = Paths
				.get(System.getProperty("user.dir") + File.separator + "lib"
						+ File.separator + "radiance" + File.separator
						+ systemDir + File.separator).toAbsolutePath()
				.toString();
		if (!path.contains("target" + File.separator + "jfx" + File.separator
				+ "app") && !isTest) {
			path = path.replace("lib" + File.separator + "radiance", "target"
					+ File.separator + "jfx" + File.separator + "app"
					+ File.separator + "lib" + File.separator + "radiance");
		}
		return path;
	}

	public String getProjectPath(String projectName) {
		return Paths
				.get(System.getProperty("user.dir") + File.separator
						+ "projects" + File.separator + projectName)
				.toAbsolutePath().toString();
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
