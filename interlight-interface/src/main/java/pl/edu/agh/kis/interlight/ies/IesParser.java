package pl.edu.agh.kis.interlight.ies;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class IesParser {

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
			sc.nextDouble(); // photometric type
			sc.nextDouble(); // units type
			sc.nextDouble(); // width
			sc.nextDouble(); // length
			sc.nextDouble(); // height
			sc.nextDouble(); // ballast factor
			sc.nextDouble(); // future use
			double power = sc.nextDouble();

			double[] verticalAngles = new double[verticalAnglesCount];
			for (int i = 0; i < verticalAnglesCount; i++) {
				verticalAngles[i] = sc.nextDouble();
			}
			double sym = verticalAngles[verticalAngles.length - 1];
			if (verticalAnglesCount == 1) {
				verticalAngles = new double[360];
				for (int i = 0; i < 360; i++) {
					verticalAngles[i] = (double) i;
				}
			} else if (sym != 360.0) {
				double diff = verticalAngles[1] - verticalAngles[0];
				verticalAngles = Arrays.copyOf(verticalAngles,
						(int) Math.round(360 / diff) + 1);
				int i = verticalAnglesCount;
				while (verticalAngles[i - 1] + diff != 360.0) {
					verticalAngles[i] = verticalAngles[i - 1] + diff;
					i++;
				}
				verticalAngles[i] = 360.0;
			}

			double[] horizontalAngles = new double[horizontalAnglesCount];
			for (int j = 0; j < horizontalAnglesCount; j++) {
				horizontalAngles[j] = sc.nextDouble();
			}

			double[][] lumens = new double[horizontalAnglesCount][verticalAngles.length];
			for (int i = 0; i < horizontalAnglesCount; i++) {
				for (int j = 0; j < verticalAnglesCount; j++) {
					lumens[i][j] = sc.nextDouble();
				}
				if (sym == 90.0) {
					for (int j = verticalAnglesCount * 4 - 4, k = 0; verticalAngles[j] >= 270.0; j--, k++) {
						lumens[i][j] = lumens[i][k];
					}
				} else if (sym == 180.0) {
					for (int j = verticalAnglesCount, k = 2; j <= verticalAnglesCount * 2 - 2; j++, k++) {
						lumens[i][j] = lumens[i][verticalAnglesCount - k];
					}
				}
			}

			IesProfile iesProfile = new IesProfile(path.getFileName()
					.toString(), power);
			iesProfile.setHorizontalAngles(horizontalAngles);
			iesProfile.setVerticalAngles(verticalAngles);
			iesProfile.setLumenValues(lumens);
			return iesProfile;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
