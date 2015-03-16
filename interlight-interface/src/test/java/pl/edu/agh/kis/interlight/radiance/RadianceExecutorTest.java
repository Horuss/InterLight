package pl.edu.agh.kis.interlight.radiance;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RadianceExecutorTest {

	private static String userDir;
	private RadianceExecutor radianceExecutor = new RadianceExecutor();

	@BeforeClass
	public static void setUpClass() {
		userDir = System.getProperty("user.dir");
	}

	@Test
	public void createTempSampleVectorsTest() throws IOException {
		// given
		Double width = 1.0;
		Double lenght = 1.0;
		Double height = 1.0;

		// when
		Path result = radianceExecutor.createTempSampleVectors(width, lenght,
				height);

		// then
		Assert.assertTrue(Files.exists(result));
		Assert.assertTrue(Files.size(result) > 0);
		BufferedReader reader = Files.newBufferedReader(result);
		Assert.assertEquals("0 0 1.0 0 0 -1", reader.readLine());
		reader.close();
		Assert.assertEquals(121, Files.readAllLines(result).size());
	}

	@Test
	public void createTempOctalTreeTest() throws IOException {
		// given
		Path path = Paths.get(userDir, "src", "test", "resources",
				"radiance", "room.rad");

		// when
		Path result = radianceExecutor.createTempOctalTree(path);

		// then
		Assert.assertTrue(Files.exists(result));
		Assert.assertTrue(Files.size(result) > 0);
	}

	@Test
	public void createTempResultRgbTest() throws IOException {
		// given
		Path octalTree = radianceExecutor.createTempOctalTree(Paths.get(
				userDir, "src", "test", "resources", "radiance",
				"room.rad"));
		Path sampleVectors = radianceExecutor.createTempSampleVectors(6.0, 6.0,
				2.5);

		// when
		Path result = radianceExecutor.createTempResultRgb(octalTree,
				sampleVectors);

		// then
		Assert.assertEquals(3721, Files.readAllLines(result).size());
	}

	@Test
	public void createResultLuminanceTest() throws IOException {
		// given
		Path octalTree = radianceExecutor.createTempOctalTree(Paths.get(
				userDir, "src", "test", "resources", "radiance",
				"room.rad"));
		Path sampleVectors = radianceExecutor.createTempSampleVectors(6.0, 6.0,
				2.5);
		Path resultRgb = radianceExecutor.createTempResultRgb(octalTree,
				sampleVectors);
		Path destination = Files.createTempFile("interlight-temp",
				".testResult");
		destination.toFile().deleteOnExit();

		// when
		Path result = radianceExecutor.createResultLuminance(destination,
				resultRgb);

		// then
		List<String> lines = Files.readAllLines(result);
		Assert.assertEquals(3721, lines.size());
		String[] split = lines.get(0).split("\t");
		Assert.assertEquals(0.0, Double.parseDouble(split[0]), 0.001);
		Assert.assertEquals(0.0, Double.parseDouble(split[1]), 0.001);
		Assert.assertEquals(0.0, Double.parseDouble(split[2]), 0.001);
		Assert.assertEquals(42.2, Double.parseDouble(split[3]), 0.1);
	}

	@Test
	public void createRadDirFromIesTest() throws IOException {
		// given
		Path ies = Paths.get(userDir, "src", "test", "resources",
				"radiance", "example.ies");
		Path dir = Files.createTempDirectory("interlight-temp");

		// when
		Path result = radianceExecutor.createRadDirFromIes(dir, ies);

		// then
		Path radFile = Paths.get(result.toAbsolutePath().toString(),
				"example.rad");
		Path datFile = Paths.get(result.toAbsolutePath().toString(),
				"example.dat");
		List<String> lines = Files.readAllLines(radFile);
		Assert.assertTrue(lines.size() > 0);
		Assert.assertTrue(lines.get(0).startsWith("#"));
		lines = Files.readAllLines(datFile);
		Assert.assertTrue(lines.size() > 0);
		Assert.assertEquals("1", lines.get(0));

		radFile.toFile().delete();
		datFile.toFile().delete();
		result.toFile().delete();
	}

}
