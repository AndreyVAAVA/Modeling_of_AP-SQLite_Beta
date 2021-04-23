package com.example.modeling_of_ap;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.util.Xml;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.example.modeling_of_ap.DB.DataThreads.DataThread;
import com.example.modeling_of_ap.DB.DataThreads.DataThreadGet;
import com.example.modeling_of_ap.DB.DataThreads.DataThreadUpdate;
import com.example.modeling_of_ap.DB.Foods;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class DialogsAddData extends DialogFragment {
    Foods food;
    static DialogsAddData newInstance(){
        return new DialogsAddData();
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogsAddDataTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogs_add_data, container, false);
        Bundle bundle = this.getArguments();
        TextInputLayout textInputLayout1 = view.findViewById(R.id.name);
        TextInputLayout textInputLayout2 = view.findViewById(R.id.calories);
        TextInputLayout textInputLayout3 = view.findViewById(R.id.proteins);
        TextInputLayout textInputLayout4 = view.findViewById(R.id.fats);
        TextInputLayout textInputLayout5 = view.findViewById(R.id.carbohydrates);
        TextInputLayout textInputLayout6 = view.findViewById(R.id.sugars);
        ExtendedFloatingActionButton btn = view.findViewById(R.id.adding);
        ExtendedFloatingActionButton btn_close = view.findViewById(R.id.returning_back);
        boolean state = (bundle != null);
        if (state){
            /*btn.setIcon(VectorDrawable.createFromPath("drawable/ic_baseline_white_update_24.xml"));*/
            // TODO FIX Problem with uncompiled XML
            /*Drawable drawable = btn.getIcon();
            Resources res = getResources();
            Drawable drawable1 = VectorDrawable.createFromPath("drawable/ic_baseline_white_update_24.xml");
            btn.setIcon(drawable);*/
            /*Drawable shape = ResourcesCompat.getDrawable(res, R.drawable.ic_add_white_24dp, getTheme());*/
            btn.setIconGravity(MaterialButton.ICON_GRAVITY_END);
            btn.setText("Обновить");
            DataThreadGet threadGet = new DataThreadGet();
            String items = bundle.getString("foodKey");
            food = threadGet.getFood(items);
            textInputLayout1.getEditText().setText(food.getName());
            textInputLayout2.getEditText().setText(String.valueOf(food.getCaloriesOn100G()));
            textInputLayout3.getEditText().setText(String.valueOf(food.getProteinsOn100G()));
            textInputLayout4.getEditText().setText(String.valueOf(food.getFatsOn100G()));
            textInputLayout5.getEditText().setText(String.valueOf(food.getCarbohydratesOn100G()));
            textInputLayout6.getEditText().setText(String.valueOf(food.getSugarsOn100G()));
        }
        btn.setOnClickListener(v -> {
            if (state){
                food.setName(textInputLayout1.getEditText().getText().toString());
                food.setCaloriesOn100G(Double.parseDouble(textInputLayout2.getEditText().getText().toString()));
                food.setProteinsOn100G(Double.parseDouble(textInputLayout3.getEditText().getText().toString()));
                food.setFatsOn100G(Double.parseDouble(textInputLayout4.getEditText().getText().toString()));
                food.setCarbohydratesOn100G(Double.parseDouble(textInputLayout5.getEditText().getText().toString()));
                food.setSugarsOn100G(Double.parseDouble(textInputLayout6.getEditText().getText().toString()));
                DataThreadUpdate threadUpdate = new DataThreadUpdate();
                threadUpdate.updateOne(food);
            } else {
                DataThread thread = new DataThread();
                thread.setAllData(textInputLayout1.getEditText().getText().toString(), Double.parseDouble(textInputLayout2.getEditText().getText().toString()),
                        Double.parseDouble(textInputLayout3.getEditText().getText().toString()),
                        Double.parseDouble(textInputLayout4.getEditText().getText().toString()),
                        Double.parseDouble(textInputLayout5.getEditText().getText().toString()),
                        Double.parseDouble(textInputLayout6.getEditText().getText().toString()));
            }
        });
        btn_close.setOnClickListener(v -> {
            dismiss();
        });
        return view;
    }
}
