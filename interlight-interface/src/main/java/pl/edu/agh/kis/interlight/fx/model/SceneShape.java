package pl.edu.agh.kis.interlight.fx.model;

import javafx.scene.control.ListView;

public abstract class SceneShape extends AbstractSceneObject {

	private Boolean workspace;

	public SceneShape() {
		super();
		this.workspace = Boolean.FALSE;
	}

	public Boolean getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Boolean workspace) {
		this.workspace = workspace;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void selectMe(ListView<? extends AbstractSceneObject> listView) {
		((ListView<SceneShape>) listView).getSelectionModel().select(this);
	}

}
