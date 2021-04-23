package com.example.modeling_of_ap;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.modeling_of_ap.DB.DataThreads.DataThreadDelete;
import com.example.modeling_of_ap.DB.DataThreads.DataThreadGet;
import com.example.modeling_of_ap.DB.Foods;
import com.example.modeling_of_ap.DB.Recycle.RecycleAdapter;
import com.example.modeling_of_ap.DB.Recycle.RecycleViewShow;
import com.example.modeling_of_ap.DBTotal.DataThreads.TotalDataThread;
import com.example.modeling_of_ap.DBTotal.DataThreads.TotalDataThreadDelete;
import com.example.modeling_of_ap.DBTotal.DataThreads.TotalDataThreadGet;
import com.example.modeling_of_ap.DBTotal.Eated;
import com.example.modeling_of_ap.DBTotal.Recycle.TotalRecycleAdapter;
import com.example.modeling_of_ap.DBTotal.Recycle.TotalRecycleViewShow;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTotal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTotal extends Fragment implements TotalRecycleAdapter.OnRecyclerViewItemListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<TotalRecycleViewShow> recycleViewShowList;
    View view;
    RecyclerView recyclerView;
    TotalRecycleAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddTotal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddTotal.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTotal newInstance(String param1, String param2) {
        AddTotal fragment = new AddTotal();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_total, container, false);
        recyclerView = view.findViewById(R.id.total_list);
        recycleViewShowList = new ArrayList<>();
        TotalDataThreadGet totalDataThreadGet = new TotalDataThreadGet();
        totalDataThreadGet.getList().forEach(x -> recycleViewShowList.add(new TotalRecycleViewShow(x.getName(), x.getDate(), x.getWeight())));
        adapter = new TotalRecycleAdapter(getLayoutInflater(), recycleViewShowList, this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onItemClick(int position) {
        String food_elem_name = recycleViewShowList.get(position).getName();
        String food_elem_date = recycleViewShowList.get(position).getDate();
        double food_elem_weight = recycleViewShowList.get(position).getWeight();
        TotalDataThreadGet totalDataThreadGet = new TotalDataThreadGet();
        Eated eated = totalDataThreadGet.getEated(food_elem_name, food_elem_date, food_elem_weight);
        new MaterialAlertDialogBuilder(getContext()).setTitle("Что вы хотите сделать?").setNegativeButton("Удалить", (dialog, which) -> {
            TotalDataThreadDelete threadDelete = new TotalDataThreadDelete();
            threadDelete.deleteOne(eated);
            recycleViewShowList.remove(position);
            recyclerView.removeViewAt(position);
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position, recycleViewShowList.size());
        }).setPositiveButton("Получить данные", (dialog, which) -> {
            DialogFragment dialogFragment = DialogsAddTotal.newInstance();
            Bundle args = new Bundle();
            args.putString("foodKey", food_elem_name + "," + food_elem_date + "," + food_elem_weight);
            dialogFragment.setArguments(args);
            dialogFragment.show(getFragmentManager(), "tag");
        }).show();
    }
}