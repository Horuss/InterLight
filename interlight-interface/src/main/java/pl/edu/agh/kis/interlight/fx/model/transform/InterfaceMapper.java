package pl.edu.agh.kis.interlight.fx.model.transform;

import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import pl.edu.agh.kis.interlight.datamodel.ICuboid;
import pl.edu.agh.kis.interlight.datamodel.ICylinder;
import pl.edu.agh.kis.interlight.datamodel.ILightPoint;
import pl.edu.agh.kis.interlight.datamodel.ILightSource;
import pl.edu.agh.kis.interlight.datamodel.IRoom;
import pl.edu.agh.kis.interlight.datamodel.IScene;
import pl.edu.agh.kis.interlight.datamodel.util.IPoint;
import pl.edu.agh.kis.interlight.fx.GuiHelper;
import pl.edu.agh.kis.interlight.fx.model.Cuboid;
import pl.edu.agh.kis.interlight.fx.model.Cylinder;
import pl.edu.agh.kis.interlight.fx.model.Ies;
import pl.edu.agh.kis.interlight.fx.model.LightPoint;
import pl.edu.agh.kis.interlight.fx.model.LightSource;
import pl.edu.agh.kis.interlight.fx.model.Room;
import pl.edu.agh.kis.interlight.fx.model.SceneModel;

//TODO test
public class InterfaceMapper {

	public static ICuboid map(Cuboid cuboid) {
		return new ICuboid(new IPoint(GuiHelper.SCALE_PX_TO_M
				* cuboid.getRectangle().getLayoutX(), GuiHelper.SCALE_PX_TO_M
				* cuboid.getRectangle().getLayoutY()), cuboid.getHeight(),
				GuiHelper.SCALE_PX_TO_M * cuboid.getRectangle().getWidth(),
				cuboid.getRectangle().getHeight(), cuboid.getRectangle()
						.getRotate(), cuboid.getWorkspace());
	}

	public static Cuboid unmap(ICuboid iCuboid) {
		Cuboid cuboid = new Cuboid();
		cuboid.setHeight(iCuboid.getHeight());
		cuboid.setWorkspace(iCuboid.getWorkspace());
		Rectangle rectangle = new Rectangle(GuiHelper.SCALE_M_TO_PX
				* iCuboid.getWidth(), GuiHelper.SCALE_M_TO_PX
				* iCuboid.getLength());
		rectangle.setLayoutX(GuiHelper.SCALE_M_TO_PX
				* iCuboid.getPointBaseTopLeft().getX());
		rectangle.setLayoutY(GuiHelper.SCALE_M_TO_PX
				* iCuboid.getPointBaseTopLeft().getY());
		rectangle.setRotate(iCuboid.getRotation());
		cuboid.setRectangle(rectangle);
		return cuboid;
	}

	public static ICylinder map(Cylinder cylinder) {
		return new ICylinder(new IPoint(GuiHelper.SCALE_PX_TO_M
				* cylinder.getEllipse().getCenterX(), GuiHelper.SCALE_PX_TO_M
				* cylinder.getEllipse().getCenterY()), GuiHelper.SCALE_PX_TO_M
				* cylinder.getEllipse().getRadiusX(), GuiHelper.SCALE_PX_TO_M
				* cylinder.getEllipse().getRadiusY(), cylinder.getHeight(),
				cylinder.getEllipse().getRotate(), cylinder.getWorkspace());
	}

	public static Cylinder unmap(ICylinder iCylinder) {
		Cylinder cylinder = new Cylinder();
		cylinder.setHeight(iCylinder.getHeight());
		cylinder.setWorkspace(iCylinder.getWorkspace());
		Ellipse ellipse = new Ellipse(
				GuiHelper.SCALE_M_TO_PX * iCylinder.getPointBaseCenter().getX(),
				GuiHelper.SCALE_M_TO_PX * iCylinder.getPointBaseCenter().getY(),
				GuiHelper.SCALE_M_TO_PX * iCylinder.getRadiusX(),
				GuiHelper.SCALE_M_TO_PX * iCylinder.getRadiusY());
		ellipse.setRotate(iCylinder.getRotation());
		return cylinder;
	}

	public static ILightPoint map(LightPoint lightPoint) {
		return new ILightPoint(new IPoint(GuiHelper.SCALE_PX_TO_M
				* lightPoint.getCircle().getCenterX(), GuiHelper.SCALE_PX_TO_M
				* lightPoint.getCircle().getCenterY()), lightPoint.getHeight());
	}

	public static LightPoint unmap(ILightPoint iLightPoint) {
		return new LightPoint(iLightPoint.getHeight(), iLightPoint.getPoint()
				.getX(), iLightPoint.getPoint().getY());
	}

	public static ILightSource map(LightSource lightSource) {
		return new ILightSource(lightSource.getIes().getIesFile().getFileName()
				.toString(), lightSource.getPower(), lightSource.getDimming());
	}

	public static LightSource unmap(ILightSource iLightSource) {
		LightSource lightSource = new LightSource();
		lightSource.setPower(iLightSource.getPower());
		lightSource.setDimming(iLightSource.getDimming());
		lightSource.setIes(new Ies(Paths.get(iLightSource.getIes())));
		return lightSource;
	}

	public static IRoom map(Room room) {
		IRoom iRoom = new IRoom(room.getHeight());
		List<IPoint> points = new LinkedList<>();
		Double x = null;
		for (Double d : room.getSceneObject().getPoints()) {
			if (x == null) {
				x = new Double(d);
			} else {
				points.add(new IPoint(x, new Double(d)));
				x = null;
			}
		}
		iRoom.setPoints(points);
		return iRoom;
	}

	public static Room unmap(IRoom iRoom) {
		Room room = new Room();
		room.setHeight(iRoom.getHeight());
		Polygon polygon = new Polygon();
		for (IPoint point : iRoom.getPoints()) {
			polygon.getPoints().addAll(point.getX(), point.getY());
		}
		room.setSceneObject(polygon);
		return room;
	}

	public static IScene map(SceneModel scene) {
		return new IScene(scene.getName(), scene.getSceneWidthM(),
				scene.getSceneLengthM());
	}

	public static SceneModel unmap(IScene iScene) {
		SceneModel scene = new SceneModel();
		scene.setName(iScene.getName());
		scene.setSceneWidthM(iScene.getWidth());
		scene.setSceneLengthM(iScene.getLength());
		return scene;
	}

}
