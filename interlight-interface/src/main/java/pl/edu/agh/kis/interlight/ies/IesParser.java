package pl.edu.agh.kis.interlight.ies;

import java.nio.file.Path;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IesParser {

	private static final Logger log = LogManager.getLogger(IesParser.class
			.getName());

	public static IesProfile parse(Path path) {

		try (Scanner sc = new Scanner(path.toFile())) {

			while (!sc.hasNext("TILT=.*")) {
				sc.nextLine();
			}

			sc.nextLine(); // TILT

			sc.nextDouble(); // number of lamps
			sc.nextDouble(); // lumens per lamp
			sc.nextDouble(); // candela multiplier
			int verticalAnglesCount = (int) Math.round(sc.nextDouble());
			int horizontalAnglesCount = (int) Math.round(sc.nextDouble());
			int photometricType = (int) Math.round(sc.nextDouble());
			sc.nextDouble(); // units type
			sc.nextDouble(); // width
			sc.nextDouble(); // length
			sc.nextDouble(); // height
			sc.nextDouble(); // ballast factor
			sc.nextDouble(); // future use
			double power = sc.nextDouble();
			
			//TODO handle "type" here (symmetry - see IES spec)

			double[] verticalAngles = new double[verticalAnglesCount];
			for (int i = 0; i < verticalAnglesCount; i++) {
				verticalAngles[i] = sc.nextDouble();
			}

			double[] horizontalAngles = new double[horizontalAnglesCount];
			for (int j = 0; j < horizontalAnglesCount; j++) {
				horizontalAngles[j] = sc.nextDouble();
			}

			double[][] lumens = new double[horizontalAnglesCount][verticalAnglesCount];
			for (int i = 0; i < horizontalAnglesCount; i++) {
				for (int j = 0; j < verticalAnglesCount; j++) {
					lumens[i][j] = sc.nextDouble();
				}
			}

			IesProfile iesProfile = new IesProfile(path.getFileName().toString(), power);
			iesProfile.setHorizontalAngles(horizontalAngles);
			iesProfile.setVerticalAngles(verticalAngles);
			iesProfile.setLumenValues(lumens);
			return iesProfile;

		} catch (Exception e) {
			log.error(e);
		}

		return null;
	}

}
