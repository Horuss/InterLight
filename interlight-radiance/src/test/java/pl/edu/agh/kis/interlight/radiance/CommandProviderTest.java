package pl.edu.agh.kis.interlight.radiance;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CommandProviderTest {
	
	private static String osName;
	
	@BeforeClass
	public static void setUpClass() {
		CommandProviderTest.osName = System.getProperty("os.name");
		CommandProvider.isTest = true;
	}
	
	@Before
	public void setUp() {
		System.setProperty("os.name", CommandProviderTest.osName);
	}
	
	@Test
	public void getCommandsPathTest() {
		// given
		CommandProvider commandProvider = new CommandProvider();
		
		// when
		String s = commandProvider.getCommandsPath();
		
		// then
		Assert.assertTrue(s.contains("lib" + File.separator + "radiance"));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void unsupportedOsTest() {
		// given
		System.setProperty("os.name", "macOS");
		CommandProvider commandProvider = new CommandProvider();
		
		// when
		commandProvider.getCommandsPath();
		
		// then throws exception
		System.clearProperty("os.name");
	}
	
	@Test
	public void createCommandTest() {
		// given
		CommandProvider commandProvider = new CommandProvider();
		
		// when
		String s = commandProvider.createCommand("somecommand");
		
		// then
		Assert.assertTrue(s.endsWith(File.separator + "somecommand"));
	}
	
}
