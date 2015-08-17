package pl.edu.agh.kis.interlight.fx.model.transform;

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
import pl.edu.agh.kis.interlight.fx.model.LightPoint;
import pl.edu.agh.kis.interlight.fx.model.LightSource;
import pl.edu.agh.kis.interlight.fx.model.Room;
import pl.edu.agh.kis.interlight.fx.model.SceneModel;
import pl.edu.agh.kis.interlight.ies.IesProfile;

public class InterfaceMapper {

	public static ICuboid map(Cuboid cuboid) {
		return new ICuboid(new IPoint(GuiHelper.SCALE_PX_TO_M
				* cuboid.getRectangle().getLayoutX(), GuiHelper.SCALE_PX_TO_M
				* cuboid.getRectangle().getLayoutY()), cuboid.getHeight(),
				GuiHelper.SCALE_PX_TO_M * cuboid.getRectangle().getWidth(),
				GuiHelper.SCALE_PX_TO_M * cuboid.getRectangle().getHeight(),
				(int) cuboid.getRectangle().getRotate(), cuboid.getWorkspace());
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
				* cylinder.getEllipse().getLayoutX(), GuiHelper.SCALE_PX_TO_M
				* cylinder.getEllipse().getLayoutY()), GuiHelper.SCALE_PX_TO_M
				* cylinder.getEllipse().getRadiusX(), GuiHelper.SCALE_PX_TO_M
				* cylinder.getEllipse().getRadiusY(), cylinder.getHeight(),
				(int) cylinder.getEllipse().getRotate(),
				cylinder.getWorkspace());
	}

	public static Cylinder unmap(ICylinder iCylinder) {
		Cylinder cylinder = new Cylinder();
		cylinder.setHeight(iCylinder.getHeight());
		cylinder.setWorkspace(iCylinder.getWorkspace());
		Ellipse ellipse = new Ellipse(GuiHelper.SCALE_M_TO_PX
				* iCylinder.getRadiusX(), GuiHelper.SCALE_M_TO_PX
				* iCylinder.getRadiusY());
		ellipse.setLayoutX(GuiHelper.SCALE_M_TO_PX
				* iCylinder.getPointBaseCenter().getX());
		ellipse.setLayoutY(GuiHelper.SCALE_M_TO_PX
				* iCylinder.getPointBaseCenter().getY());
		ellipse.setRotate(iCylinder.getRotation());
		cylinder.setEllipse(ellipse);
		return cylinder;
	}

	public static ILightPoint map(LightPoint lightPoint) {
		return new ILightPoint(lightPoint.getId(), new IPoint(GuiHelper.SCALE_PX_TO_M
				* lightPoint.getCircle().getLayoutX(), GuiHelper.SCALE_PX_TO_M
				* lightPoint.getCircle().getLayoutY()), lightPoint.getHeight());
	}

	public static LightPoint unmap(ILightPoint iLightPoint) {
		return new LightPoint(iLightPoint.getHeight(), iLightPoint.getPoint()
				.getX(), iLightPoint.getPoint().getY());
	}

	public static ILightSource map(LightSource lightSource) {
		return new ILightSource(lightSource.getIes().getName(),
				lightSource.getIes().getPower());
	}

	public static LightSource unmap(ILightSource iLightSource) {
		LightSource lightSource = new LightSource();
		lightSource.setIes(new IesProfile(iLightSource.getIes(), iLightSource.getPower()));
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
				points.add(new IPoint(GuiHelper.SCALE_PX_TO_M * x,
						GuiHelper.SCALE_PX_TO_M * new Double(d)));
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
			polygon.getPoints().addAll(GuiHelper.SCALE_M_TO_PX * point.getX(),
					GuiHelper.SCALE_M_TO_PX * point.getY());
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
