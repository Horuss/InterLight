package pl.edu.agh.kis.interlight.fx.parts;

import java.awt.Rectangle;

import javafx.scene.canvas.Canvas;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.fx.ChartCanvas;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import pl.edu.agh.kis.interlight.ies.IesProfile;

public class LuminousIntensityPolarDiagram {

	private final ChartCanvas chartCanvas;

	public LuminousIntensityPolarDiagram(IesProfile iesProfile) {

		XYSeriesCollection data = new XYSeriesCollection();

		DefaultPolarItemRenderer renderer = new DefaultPolarItemRenderer();
		renderer.setSeriesShape(0, new Rectangle(1, 1), false);
		renderer.setSeriesShape(1, new Rectangle(1, 1), false);
		renderer.setSeriesShape(2, new Rectangle(1, 1), false);
		renderer.setSeriesShape(3, new Rectangle(1, 1), false);
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
		((PolarPlot) chart.getPlot()).setRenderer(renderer);
		chart.setBackgroundPaint(new java.awt.Color(244, 244, 244));

		chartCanvas = new ChartCanvas(chart) {
			public void chartChanged(ChartChangeEvent event) {
			}
		};
		chartCanvas.setWidth(195);
		chartCanvas.setHeight(200);

	}

	public Canvas getChartViewer() {
		return chartCanvas;
	}

}
