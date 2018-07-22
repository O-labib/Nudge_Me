package labib.com.nudgememvp.ui.main;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import labib.com.nudgememvp.Logy;
import labib.com.nudgememvp.R;
import labib.com.nudgememvp.data.db.Nudge;
import labib.com.nudgememvp.ui.base.BaseActivity;
import labib.com.nudgememvp.ui.base.BaseDialog;
import labib.com.nudgememvp.ui.main.inputFragment.InputFragment;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements
        MainContract.View, Adapter.Callback, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener,BaseDialog.Callback {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.newNudgeFAB)
    FloatingActionButton newNudgeFAB;

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Inject
    Adapter adapter;

    @Inject
    FragmentManager fragmentManager;

    Snackbar snackbar;
    ItemTouchHelper.SimpleCallback recyclerItemTouchHelper;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.clear:
                getPresenter().clearData();
                break;
            case R.id.settings:

                break;
            case R.id.activateService:
                break;
            case R.id.dectivateService:
                break;
            case R.id.showStatus:
                break;
        }
        return true;
    }

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        getPresenter().queryInitialData();

        recyclerItemTouchHelper =
                new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(recyclerItemTouchHelper).attachToRecyclerView(recyclerView);


    }

    @Override
    protected void injectDependencies() {
        getComponent().inject(this);
    }


    @OnClick(R.id.newNudgeFAB)
    public void newNudge() {
        InputFragment.newInstance().show(fragmentManager);
        //   getPresenter().insertData();

    }


    @Override
    public void initRecyclerView(ArrayList<Nudge> data) {
        adapter.setCallBack(this);
        adapter.setData(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void updateRecyclerView(ArrayList<Nudge> data) {
        adapter.swapData(data);
    }


    @Override
    public void onItemClicked(long id) {
        //  getPresenter().deleteOne(id);
    }

    @Override
    public void onEmptyClicked() {

        // button popout
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.2f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.2f);

        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(newNudgeFAB, pvhX, pvhY);
        objectAnimator.setRepeatCount(3);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.start();
    }


    @Override
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction, final int position) {
        if (snackbar != null && snackbar.isShown()) {
            new Logy("Shown");
            snackbar.dismiss();
            new Logy("dismiss upp");
        }

        new Logy("here up ----");
        adapter.removeItem(position);

        final Nudge nudge = (Nudge) viewHolder.itemView.getTag();
        snackbar = Snackbar.make(coordinatorLayout, "A nudge has been removed", Snackbar.LENGTH_LONG);

        final Snackbar.Callback callback = new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                new Logy("dismissed");
                getPresenter().deleteOne(((Nudge) viewHolder.itemView.getTag()).getId());
            }

            @Override
            public void onShown(Snackbar sb) {
            }
        };
        snackbar.addCallback(callback);

        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.restoreItem(nudge, position);
                snackbar.removeCallback(callback);
            }
        });
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();

    }

    @Override
    public void onFragmentDetached(String tag) {
        getPresenter().queryData();
    }
}
