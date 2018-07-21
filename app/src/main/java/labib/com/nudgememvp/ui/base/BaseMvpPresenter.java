package labib.com.nudgememvp.ui.base;

public interface BaseMvpPresenter<V extends BaseMvpView> {

    void attach(V view);

    void detach();

    boolean isAttached();

}
