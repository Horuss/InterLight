package pl.edu.agh.kis.interlight.fx.parts;

import javafx.scene.canvas.Canvas;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.fx.ChartCanvas;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import pl.edu.agh.kis.interlight.ies.IesProfile;

public class LuminousIntensityPolarDiagram {

	private final Canvas chartViewer;

	public LuminousIntensityPolarDiagram(IesProfile iesProfile) {

		XYSeriesCollection data = new XYSeriesCollection();

		for (int i = 0; i < iesProfile.getHorizontalAngles().length; i++) {
			if (iesProfile.getHorizontalAngles()[i] % 90 == 0) {
				XYSeries series = new XYSeries("Series " + i);
				for (int j = 0; j < iesProfile.getVerticalAngles().length; j++) {
					series.add(iesProfile.getVerticalAngles()[j],
							iesProfile.getLumenValues()[i][j]);
				}
				data.addSeries(series);
			}
		}

		final JFreeChart chart = ChartFactory.createPolarChart(null, data,
				false, false, false);
		chart.setBackgroundPaint(new java.awt.Color(244, 244, 244));

		chartViewer = new ChartCanvas(chart);
		chartViewer.setWidth(195);
		chartViewer.setHeight(200);

	}

	public Canvas getChartViewer() {
		return chartViewer;
	}

}
