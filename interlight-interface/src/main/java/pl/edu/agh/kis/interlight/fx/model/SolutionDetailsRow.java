package pl.edu.agh.kis.interlight.fx.model;

import pl.edu.agh.kis.interlight.datamodel.ILightPoint;
import pl.edu.agh.kis.interlight.datamodel.ILightSource;

public class SolutionDetailsRow {
	private ILightPoint lightPoint;
	private ILightSource lightSource;

	public SolutionDetailsRow(ILightPoint lightPoint, ILightSource lightSource) {
		this.lightPoint = lightPoint;
		this.lightSource = lightSource;
	}

	public ILightPoint getLightPoint() {
		return lightPoint;
	}

	public void setLightPoint(ILightPoint lightPoint) {
		this.lightPoint = lightPoint;
	}

	public ILightSource getLightSource() {
		return lightSource;
	}

	public void setLightSource(ILightSource lightSource) {
		this.lightSource = lightSource;
	}

}
