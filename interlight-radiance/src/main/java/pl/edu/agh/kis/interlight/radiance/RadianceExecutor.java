package pl.edu.agh.kis.interlight.radiance;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RadianceExecutor {

	public static final String COMMAND_OCONV = "oconv";
	public static final String COMMAND_RTRACE = "rtrace";
	public static final String COMMAND_RCALC = "rcalc";
	public static final String COMMAND_IES2RAD = "ies2rad";
	public static final String COMMAND_RVU = "rvu";

	private CommandProvider commandProvider = new CommandProvider();

	public Path createResultLuminance(Path destination, Path resultRgb)
			throws IOException {
		if (!Files.exists(destination)) {
			Files.createFile(destination);
		}

		List<String> commands = new ArrayList<String>();
		commands.add(commandProvider
				.createCommand(RadianceExecutor.COMMAND_RCALC));
		commands.add("-e");
		commands.add("$1=$1;$2=$2;$3=$3;$4=47.4*$4+120*$5+11.6*$6");
		ProcessBuilder builder = new ProcessBuilder(commands);
		builder.directory(new File(commandProvider.getCommandsPath()));
		builder.redirectErrorStream(true);
		builder.redirectInput(resultRgb.toFile());
		builder.redirectOutput(destination.toFile());
		Process process = builder.start();
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return destination;
	}

	public Path createTempResultRgb(Path octalTree, Path sampleVectors)
			throws IOException {
		Path tempFileRgb = Files.createTempFile("interlight-temp", ".rgb");
		tempFileRgb.toFile().deleteOnExit();

		List<String> commands = new ArrayList<String>();
		commands.add(commandProvider
				.createCommand(RadianceExecutor.COMMAND_RTRACE));
		commands.add("-opv");
		commands.add("-h");
		commands.add(octalTree.toAbsolutePath().toString());

		ProcessBuilder builder = new ProcessBuilder(commands);
		builder.directory(new File(commandProvider.getCommandsPath()));
		builder.redirectErrorStream(true);
		builder.redirectInput(sampleVectors.toFile());
		builder.redirectOutput(tempFileRgb.toFile());
		Process process = builder.start();
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return tempFileRgb;
	}

	public Path createTempOctalTree(Path radianceScene) throws IOException {
		Path tempFile = Files.createTempFile("interlight-temp", ".oct");
		tempFile.toFile().deleteOnExit();

		List<String> commands = new ArrayList<String>();
		commands.add(commandProvider
				.createCommand(RadianceExecutor.COMMAND_OCONV));
		commands.add("-");

		final ProcessBuilder builder = new ProcessBuilder(commands);
		builder.directory(new File(commandProvider.getCommandsPath()));
		builder.redirectErrorStream(true);
		builder.redirectInput(radianceScene.toFile());
		builder.redirectOutput(tempFile.toFile());
		Process process = builder.start();
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return tempFile;
	}

	public Path createTempSampleVectors(Double width, Double length,
			Double height) throws IOException {
		Path tempFile = Files.createTempFile("interlight-temp", ".vecSamples");
		//tempFile.toFile().deleteOnExit();
		OutputStream out = Files.newOutputStream(tempFile,
				StandardOpenOption.APPEND);
		DecimalFormat df = new DecimalFormat("#.#",
				DecimalFormatSymbols.getInstance(Locale.US));
		for (Double d1 = 0.0; d1 <= length; d1 += 0.1) {
			for (Double d2 = 0.0; d2 <= width; d2 += 0.1) {
				out.write((df.format(d1) + " " + df.format(d2) + " " + height + " 0 0 -1\n")
						.getBytes());
			}
		}
		out.close();
		return tempFile;
	}

	public Path createRadDirFromIes(Path destinationDir, Path ies)
			throws IOException {
		if (!Files.exists(destinationDir)) {
			Files.createDirectory(destinationDir);
		}

		List<String> commands = new ArrayList<String>();
		commands.add(commandProvider
				.createCommand(RadianceExecutor.COMMAND_IES2RAD));
		commands.add("-o");
		commands.add(destinationDir.toAbsolutePath().toString()
				+ File.separator + ies.getFileName().toString().split("\\.")[0]);
		commands.add(ies.toAbsolutePath().toString());
		ProcessBuilder builder = new ProcessBuilder(commands);
		builder.directory(new File(commandProvider.getCommandsPath()));
		builder.redirectErrorStream(true);
		Process process = builder.start();
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return destinationDir;
	}
	
	//stub
	public void visualization() throws IOException {
		List<String> commands = new ArrayList<String>();
		commands.add(commandProvider
				.createCommand(RadianceExecutor.COMMAND_RVU));
		//observer point
		commands.add("-vp");
		commands.add("0");
		commands.add("0");
		commands.add("2.725");
		//observer direction
		commands.add("-vd");
		commands.add("0.5");
		commands.add("0.5");
		commands.add("-0.5");
		commands.add("room.oct");
		ProcessBuilder builder = new ProcessBuilder(commands);
		builder.directory(new File(commandProvider.getProjectPath("SampleProject")));
		builder.redirectErrorStream(true);
		Process process = builder.start();
		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
