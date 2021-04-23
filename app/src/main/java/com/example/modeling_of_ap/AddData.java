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
import android.widget.Button;

import com.example.modeling_of_ap.DB.DataThreads.DataThreadDelete;
import com.example.modeling_of_ap.DB.DataThreads.DataThreadGet;
import com.example.modeling_of_ap.DB.Foods;
import com.example.modeling_of_ap.DB.Recycle.RecycleAdapter;
import com.example.modeling_of_ap.DB.Recycle.RecycleViewShow;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;
import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddData#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddData extends Fragment implements RecycleAdapter.OnRecyclerViewItemListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<RecycleViewShow> recycleViewShowList;
    View view;
    RecyclerView recyclerView;
    RecycleAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddData() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddData.
     */
    // TODO: Rename and change types and number of parameters
    public static AddData newInstance(String param1, String param2) {
        AddData fragment = new AddData();
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
        view = inflater.inflate(R.layout.fragment_add_data, container, false);
        TextInputLayout textInputLayout = view.findViewById(R.id.outlinedTextField);
        recyclerView = view.findViewById(R.id.list);
        recycleViewShowList = new ArrayList<>();
        DataThreadGet threadGet = new DataThreadGet();
        threadGet.getList().forEach(x -> recycleViewShowList.add(new RecycleViewShow(x.getName())));
        adapter = new RecycleAdapter(getLayoutInflater(), recycleViewShowList, this);
        recyclerView.setAdapter(adapter);
        textInputLayout.setEndIconOnClickListener(v -> {
            Snackbar.make(view, textInputLayout.getEditText().getText(), Snackbar.LENGTH_SHORT).show();
        });
        ExtendedFloatingActionButton btn = view.findViewById(R.id.add_product);
        btn.setOnClickListener(v -> {
            DialogFragment dialogFragment = DialogsAddData.newInstance();
            dialogFragment.show(getFragmentManager(), "tag");
        });
        return view;
    }

    @Override
    public void onItemClick(int position) {
        String food_elem_name = recycleViewShowList.get(position).getName();
        new MaterialAlertDialogBuilder(getContext()).setTitle("Что вы хотите сделать?").setNegativeButton("Удалить", (dialog, which) -> {
            DataThreadGet threadGet = new DataThreadGet();
            Foods food = threadGet.getFood(food_elem_name);
            DataThreadDelete threadDelete = new DataThreadDelete();
            threadDelete.deleteOne(food);
            recycleViewShowList.remove(position);
            recyclerView.removeViewAt(position);
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position, recycleViewShowList.size());
        }).setNeutralButton("Изменить", (dialog, which) -> {
            DialogFragment dialogFragment = DialogsAddData.newInstance();
            // Add provided argument to Bundles
            Bundle args = new Bundle();
            args.putString("foodKey", food_elem_name);
            dialogFragment.setArguments(args);
            dialogFragment.show(getFragmentManager(), "tag");
        }).setPositiveButton("Добавить", (dialog, which) -> {
            Intent intent = new Intent(this.getActivity(), MainActivity2.class);
            intent.putExtra("foodKey", food_elem_name);
            startActivity(intent);
            /*DialogFragment dialogFragment = DialogsAddTotal.newInstance();
            Bundle args = new Bundle();
            args.putString("foodKey", food_elem_name + "," + position);
            dialogFragment.setArguments(args);
            dialogFragment.show(getFragmentManager(), "tag");*/
        }).show();
    }
}