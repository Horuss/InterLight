package pl.edu.agh.kis.interlight.fx.model.transform;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

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

public class InterfaceMapperTest {

	private static final double X = 1.0;
	private static final double Y = 2.0;
	private static final double H = 3.0;
	private static final double W = 4.0;
	private static final double L = 5.0;
	private static final double RX = 6.0;
	private static final double RY = 7.0;
	private static final int R = 8;
	private static final boolean WS = true;
	private static final String S = "xxx";
	private static final int P = 9;
	private static final int D = 10;

	@BeforeClass
	public static void setUpClass() {
		GuiHelper.SCALE_PX_TO_M = 2.0;
		GuiHelper.SCALE_M_TO_PX = 0.5;
	}

	@Test
	public void mapCuboidTest() {

		// given
		ICuboid iCuboid = new ICuboid(new IPoint(X, Y), H, W, L, R, WS);

		// when
		Cuboid cuboid = InterfaceMapper.unmap(iCuboid);
		ICuboid iCuboid2 = InterfaceMapper.map(cuboid);

		// then
		Assert.assertEquals(iCuboid, iCuboid2);
	}

	@Test
	public void mapCylinderTest() {

		// given
		ICylinder iCylinder = new ICylinder(new IPoint(X, Y), RX, RY, H, R, WS);

		// when
		Cylinder cylinder = InterfaceMapper.unmap(iCylinder);
		ICylinder iCylinder2 = InterfaceMapper.map(cylinder);

		// then
		Assert.assertEquals(iCylinder, iCylinder2);
	}

	@Test
	public void mapLightPointTest() {

		// given
		ILightPoint iLightPoint = new ILightPoint(new IPoint(X, Y), H);

		// when
		LightPoint lightPoint = InterfaceMapper.unmap(iLightPoint);
		ILightPoint iLightPoint2 = InterfaceMapper.map(lightPoint);

		// then
		Assert.assertEquals(iLightPoint, iLightPoint2);
	}

	@Test
	public void mapLightSourceTest() {

		// given
		ILightSource iLightSource = new ILightSource(S, P, D);

		// when
		LightSource lightSource = InterfaceMapper.unmap(iLightSource);
		ILightSource iLightSource2 = InterfaceMapper.map(lightSource);

		// then
		Assert.assertEquals(iLightSource, iLightSource2);
	}

	@Test
	public void mapRoomTest() {

		// given
		IRoom iRoom = new IRoom(H);
		iRoom.setPoints(Arrays.asList(new IPoint(X, Y), new IPoint(W, L)));

		// when
		Room room = InterfaceMapper.unmap(iRoom);
		IRoom iRoom2 = InterfaceMapper.map(room);

		// then
		Assert.assertEquals(iRoom, iRoom2);
	}

	@Test
	public void mapSceneTest() {

		// given
		IScene iScene = new IScene(S, W, L);

		// when
		SceneModel scene = InterfaceMapper.unmap(iScene);
		IScene iScene2 = InterfaceMapper.map(scene);

		// then
		Assert.assertEquals(iScene, iScene2);
	}

}
