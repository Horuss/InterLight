package pl.edu.agh.kis.interlight.datamodel;

import java.util.HashMap;
import java.util.Map;

public class ISolution {

	private Double energySavings;
	private Double exploitationCostsSavings;
	private Double operatingCostsSavings;
	private Double simplePaybackPeriod;
	private Double NPV;
	private Map<ILightPoint, ILightSource> lightMap;

	public ISolution(Double energySavings, Double exploitationCostsSavings,
			Double operatingCostsSavings, Double simplePaybackPeriod, Double NPV) {
		this.energySavings = energySavings;
		this.exploitationCostsSavings = exploitationCostsSavings;
		this.operatingCostsSavings = operatingCostsSavings;
		this.simplePaybackPeriod = simplePaybackPeriod;
		this.NPV = NPV;
		this.lightMap = new HashMap<ILightPoint, ILightSource>();
	}

	public Double getEnergySavings() {
		return energySavings;
	}

	public void setEnergySavings(Double energySavings) {
		this.energySavings = energySavings;
	}

	public Double getExploitationCostsSavings() {
		return exploitationCostsSavings;
	}

	public void setExploitationCostsSavings(Double exploitationCostsSavings) {
		this.exploitationCostsSavings = exploitationCostsSavings;
	}

	public Double getOperatingCostsSavings() {
		return operatingCostsSavings;
	}

	public void setOperatingCostsSavings(Double operatingCostsSavings) {
		this.operatingCostsSavings = operatingCostsSavings;
	}

	public Double getSimplePaybackPeriod() {
		return simplePaybackPeriod;
	}

	public void setSimplePaybackPeriod(Double simplePaybackPeriod) {
		this.simplePaybackPeriod = simplePaybackPeriod;
	}

	public Double getNPV() {
		return NPV;
	}

	public void setNPV(Double NPV) {
		this.NPV = NPV;
	}

	public Map<ILightPoint, ILightSource> getLightMap() {
		return lightMap;
	}

	public void setLightMap(Map<ILightPoint, ILightSource> lightMap) {
		this.lightMap = lightMap;
	}

}
