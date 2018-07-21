package labib.com.nudgememvp.ui.base;

import labib.com.nudgememvp.data.DataManager;

public class BasePresenter<V extends BaseMvpView> implements BaseMvpPresenter<V> {

    private V view;
    private DataManager dataManager;

    public BasePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    @Override
    public void attach(V view) {
        this.view = view;
        dataManager.toString();
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public boolean isAttached() {
        return this.view != null;
    }

    public V getView() {
        return this.view;
    }
}

