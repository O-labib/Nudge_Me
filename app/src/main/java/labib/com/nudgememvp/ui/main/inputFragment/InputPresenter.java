package labib.com.nudgememvp.ui.main.inputFragment;

import labib.com.nudgememvp.data.DataManager;
import labib.com.nudgememvp.ui.base.BasePresenter;

public class InputPresenter extends BasePresenter<InputContract.View> implements InputContract.Presenter {

    public InputPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void checkData(String message, String place, double lat, double lon) {
        if (message.equals("")) {
            getView().showMessage("Please add a message");
            return;
        }
        if (place.equals("")) {
            getView().showMessage("Please add a place title");
            return;
        }
        if (lat == 0 || lon == 0) {
            getView().showMessage("Please pick coordinates from the map");
            getView().highlightMapBtn();
            return;
        }
        long result = getDataManager().insertData(message, place, lat, lon);
        if (result != -1) {
            getView().showMessage("successfully added");
            getView().dismissDialog();
        }
    }
}
