package pl.edu.agh.kis.interlight.fx.model;

public class LightPointNet {

	private Integer amountX;
	private Integer amountY;
	private Double marginX;
	private Double marginY;
	private Double spacingX;
	private Double spacingY;

	public Integer getAmountX() {
		return amountX;
	}

	public void setAmountX(Integer amountX) {
		this.amountX = amountX;
	}

	public Integer getAmountY() {
		return amountY;
	}

	public void setAmountY(Integer amountY) {
		this.amountY = amountY;
	}

	public Double getMarginX() {
		return marginX;
	}

	public void setMarginX(Double marginX) {
		this.marginX = marginX;
	}

	public Double getMarginY() {
		return marginY;
	}

	public void setMarginY(Double marginY) {
		this.marginY = marginY;
	}

	public Double getSpacingX() {
		return spacingX;
	}

	public void setSpacingX(Double spacingX) {
		this.spacingX = spacingX;
	}

	public Double getSpacingY() {
		return spacingY;
	}

	public void setSpacingY(Double spacingY) {
		this.spacingY = spacingY;
	}

	@Override
	public String toString() {
		return "LightPointNet [amountX=" + amountX + ", amountY=" + amountY
				+ ", marginX=" + marginX + ", marginY=" + marginY
				+ ", spacingX=" + spacingX + ", spacingY=" + spacingY + "]";
	}

}
