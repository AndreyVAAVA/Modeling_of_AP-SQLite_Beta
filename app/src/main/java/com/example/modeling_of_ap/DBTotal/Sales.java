package com.example.sales.DB;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*import com.example.sales.DATA.Data;
import com.example.sales.DB.DataThreads.DataThreadGet;
import com.example.sales.DB.Recycle.RecycleAdapter;
import com.example.sales.DB.Recycle.RecycleViewShow;
import com.example.sales.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import yahoofinance.Stock;

import static android.content.ContentValues.TAG;

*//**
 * A simple {@link Fragment} subclass.
 * Use the {@link Sales#newInstance} factory method to
 * create an instance of this fragment.
 *//*
public class Sales extends Fragment implements RecycleAdapter.OnRecyclerViewItemListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    ArrayList<RecycleViewShow> recycleViewShowList;
    // TODO: Rename and change types of parameters
    private String typeof;
    TextInputLayout search;
//    TextInputEditText srch;

    @SuppressLint("StaticFieldLeak")
    AsyncTask<Stocks, Fragment, Fragment> task = new AsyncTask<Stocks, Fragment, Fragment>(){

        @Override
        protected Fragment doInBackground(Stocks... stocks) {
            Bundle bundle = new Bundle();
            bundle.putString("item", stocks[0].getName());
            Fragment fragment = Data.newInstance("From Sales");
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        protected void onPostExecute(Fragment fragment) {
            getParentFragmentManager().
                    beginTransaction()
                    .replace(R.id.fragment_container_view, fragment)
                    .commit();
        }
    };

    public Sales() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Sales newInstance(String param1) {
        Sales fragment = new Sales();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            typeof = getArguments().getString(ARG_PARAM1);
        }
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sales, container, false);
        Bundle bundle = this.getArguments();
        String nameOfIt = null;
        DataThreadGet threadGet = new DataThreadGet();
        threadGet.start();
        try {
            threadGet.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RecyclerView recyclerView = v.findViewById(R.id.list);
        recycleViewShowList = new ArrayList<>();
        if (bundle != null) {
            nameOfIt = bundle.getString("type");
            if (nameOfIt.equalsIgnoreCase("Sales List"))
                threadGet.getList().forEach(x -> recycleViewShowList.add(new RecycleViewShow(x.getName(), x.getPrice())));
            else if (nameOfIt.equalsIgnoreCase("Favourite"))
                threadGet.getFavouriteList().forEach(x -> recycleViewShowList.add(new RecycleViewShow(x.getName(), x.getPrice())));
        } else {
            Log.e(TAG, "Sales.java, line 70, because of null, i randomly selected fragment(to not crash the program), please fix bug");
            threadGet.getList().forEach(x -> recycleViewShowList.add(new RecycleViewShow(x.getName(), x.getPrice())));
        }
        RecycleAdapter adapter = new RecycleAdapter(getLayoutInflater(), recycleViewShowList, this);
        recyclerView.setAdapter(adapter);

        *//*srch = v.findViewById(R.id.srch);*//*
        search = v.findViewById(R.id.search);
        search.setEndIconOnClickListener(v1 -> {
            String readed = search.getEditText().getText().toString();
            DataThreadGet getComp = new DataThreadGet();
            DataThreadGet getCompByTicker = new DataThreadGet();
            Stocks comp = null;
            if (readed != null)
                comp = getComp.getCompany(readed);
            if (comp != null) {
                task.execute(comp);
            } else {
                comp = getCompByTicker.getCompanyByTicker(readed);
                if (comp != null){
                    task.execute(comp);
                } else {
                    Snackbar.make(v1, "This company doesn't exist in List", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onItemClick: " + recycleViewShowList.get(position).getName());
        //TODO rewrite code without using AsyncTask
        new AsyncTask<Void, Fragment, Fragment>(){

            @Override
            protected Fragment doInBackground(Void... voids) {
                Bundle bundle = new Bundle();
                bundle.putString("item",recycleViewShowList.get(position).getName());
                Fragment fragment = Data.newInstance("From Sales");
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            protected void onPostExecute(Fragment fragment) {
*//*                search.clearFocus();
                search.clearChildFocus(fragment.getView());
                srch.clearFocus();
                srch.clearComposingText();
                srch.clearAnimation();*//*
                getParentFragmentManager().
                        beginTransaction()
                        .replace(R.id.fragment_container_view, fragment)
                        .commit();
            }
        }.execute();

    }
}*/