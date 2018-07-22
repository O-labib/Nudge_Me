package labib.com.nudgememvp.ui.main.inputFragment;

import labib.com.nudgememvp.ui.base.BaseMvpDialog;
import labib.com.nudgememvp.ui.base.BaseMvpPresenter;

public interface InputContract {

    interface View extends BaseMvpDialog {
        void highlightMapBtn();
    }

    interface Presenter extends BaseMvpPresenter<View>{
        void checkData(String message, String place, double lat, double lon);
    }
}
