package pl.edu.agh.kis.interlight.model;

public class JPoint {
	
	
	private double point_x;
    private double point_y;
   
   
    public double getPoint_X() {
        return point_x;
    }
    
    public void setPoint_X(double point_x) {
        this.point_x = point_x;
    }
    
    public double getPoint_Y() {
        return point_y;
    }
    
    public void setPoint_Y(double point_y) {
        this.point_y = point_y;
    }
   
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Point\n");
        sb.append("Point x"+getPoint_X()+"\n");
        sb.append("Point y"+getPoint_Y()+"\n");
        sb.append("\n");

        return sb.toString();
    }

}
