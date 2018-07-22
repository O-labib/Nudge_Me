package labib.com.nudgememvp.ui.main.inputFragment;

import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.ui.base.BasePresenter;

public class InputPresenter extends BasePresenter<InputContract.View> implements InputContract.Presenter {

    public InputPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
