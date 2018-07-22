package labib.com.nudgememvp.ui.main;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.database.Cursor;
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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import labib.com.nudgememvp.R;
import labib.com.nudgememvp.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View, Adapter.Callback, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

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

    ItemTouchHelper.SimpleCallback recyclerItemTouchHelper;

    // wire the menu file
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    // handle on menu item click
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
//        InputFragment.newInstance().show(fragmentManager);
        getPresenter().insertData();

    }


    @Override
    public void initRecyclerView(Cursor cursor) {
        adapter.setCallBack(this);
        adapter.setDataCursor(cursor);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void updateRecyclerView(Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public void onItemClicked(long id) {
        getPresenter().deleteOne(id);
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
    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction, int position) {

        Snackbar snackbar = Snackbar.make(coordinatorLayout, "has removed", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // undo is selected, restore the deleted item
                getPresenter().queryData();
            }
        });

        snackbar.addCallback(new Snackbar.Callback() {

            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                //         getPresenter().deleteOne((Long) viewHolder.itemView.getTag());

            }

            @Override
            public void onShown(Snackbar snackbar) {

            }
        });
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();

    }
}
