package com.example.baseprojectandroid.src.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.compoments.OnItemSelectedListenner;
import com.example.baseprojectandroid.cores.room.table.RevenueExpenditureTable;
import com.example.baseprojectandroid.databinding.FragmentDialogAddRevenueBinding;
import com.example.baseprojectandroid.models.callback.CallbackToRevenueExpenditure;
import com.example.baseprojectandroid.models.data_display.DataDisplay;
import com.example.baseprojectandroid.models.spinner_model.SpinnerModel;
import com.example.baseprojectandroid.src.viewmodel.revenue_expenditure_viewmodel.RevenueExpenditureViewmodel;
import com.example.baseprojectandroid.utils.Constain;
import com.example.baseprojectandroid.utils.Helpers;

import java.util.List;

public class FragmentDialogRevenueExpenditure extends DialogFragment
        implements CallbackToRevenueExpenditure<FragmentDialogRevenueExpenditure> {
    private FragmentDialogAddRevenueBinding binding;

    //variable
    private RevenueExpenditureViewmodel mRevenueExpenditureViewmodel;
    private SpinnerAdapter mSpinnerAdapter;
    private SpinnerModel mSpinnerModel;
    private String mType;
    private Dialog mDialog;
    private RevenueExpenditureTable mRevenueExpenditureTable;
    private String TAG = "FragmentDialogRevenueExpenditure";
    private static FragmentDialogRevenueExpenditure instance;
    private DataDisplay dataDisplay = new DataDisplay();

    private FragmentDialogRevenueExpenditure(){

    }

    /**
     * các phương thức này trả về Dữ liệu chính Class trên -> FragmentDialogRevenueExpenditure.getInstance().setTitleCategory().setTitleDate() ...vv
     * thuộc dạng Buider Pattern
     */

    @Override
    public FragmentDialogRevenueExpenditure setTitleCategory(int title){
        dataDisplay.setTitleCategory(title);
        return this;
    }

    @Override
    public FragmentDialogRevenueExpenditure setTitleDate(int title){
        dataDisplay.setTitleDate(title);
        return this;
    }

    @Override
    public FragmentDialogRevenueExpenditure setTitlePrice(int title){
        dataDisplay.setTitlePrice(title);
        return this;
    }

    @Override
    public FragmentDialogRevenueExpenditure setTitleNote(int title){
        dataDisplay.setTitleNote(title);
        return this;
    }

    @Override
    public FragmentDialogRevenueExpenditure setTitleSave(int title){
        dataDisplay.setTitleSave(title);
        return this;
    }

    @Override
    public FragmentDialogRevenueExpenditure setTitleCancel(int title){
        dataDisplay.setTitleCancel(title);
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDialogAddRevenueBinding.inflate(inflater);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setData(dataDisplay);

        //khởi tạo viewmodel
        initViewModel();

        //khởi tạo view
        init();

        //lắng nghe sự kiện onClicked view
        listenerOnclicked();

        return binding.getRoot();
    }

    //lắng nghe sự kiện oclicked view
    private void listenerOnclicked() {
        //get item spinner
        binding.spinnerDialog.setOnItemSelectedListener(new OnItemSelectedListenner() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                super.onItemSelected(parent, view, position, id);
                mSpinnerModel = (SpinnerModel) parent.getItemAtPosition(position);
            }
        });

        //date picker dialog
        binding.imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helpers.showDatePickerDialog(getContext(), FragmentDialogRevenueExpenditure.this);
            }
        });

        //save node
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()) {
                    final RevenueExpenditureTable model = new RevenueExpenditureTable(mType, mSpinnerModel.getmContent(), mSpinnerModel.getmImageIcon(), Integer.parseInt(binding.edtPrice.getText().toString()), binding.edtNode.getText().toString(), binding.edtDateTime.getText().toString(), null);
                    mDialog = Helpers.showLoadingDialog(getActivity());
                    mDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (mRevenueExpenditureTable != null) {
                                mRevenueExpenditureTable.setmTitle(mSpinnerModel.getmContent());
                                mRevenueExpenditureTable.setmImage(mSpinnerModel.getmImageIcon());
                                mRevenueExpenditureTable.setmPrice(Integer.parseInt(binding.edtPrice.getText().toString()));
                                mRevenueExpenditureTable.setmContent(binding.edtNode.getText().toString());
                                mRevenueExpenditureTable.setmCreateTime(binding.edtDateTime.getText().toString());
                                //update
                                mRevenueExpenditureViewmodel.updateEvenueExpenditure(mRevenueExpenditureTable);
                            } else {
                                //insert
                                mRevenueExpenditureViewmodel.insertEvenueExpenditure(model);
                            }
                            mDialog.dismiss();
                            Helpers.hideFragmentDialog(FragmentDialogRevenueExpenditure.this, Constain.fragmentDialogRevenueExpenditure);
                        }
                    }, 3000);
                }
            }
        });

        //thoát dialog
        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    //check validation
    private boolean checkValidation() {
        if (binding.edtPrice.getText().toString().equals("")) {
            binding.edtPrice.setError(getString(R.string.lbl_not_null_data));
            return false;
        }
        binding.edtPrice.setError(null);

        if (binding.edtNode.getText().toString().equals("")) {
            binding.edtNode.setError(getString(R.string.lbl_not_null_data));
            return false;
        }
        binding.edtNode.setError(null);
        return true;
    }

    //khởi tạo datta
    private void init() {
        setCancelable(false);

        //khởi tạo adapter spinner
        mSpinnerAdapter = new com.example.baseprojectandroid.src.adapter.spinner_adapter.SpinnerAdapter(getContext(), mRevenueExpenditureViewmodel.getListSpinner(mType).getValue());
        binding.spinnerDialog.setAdapter(mSpinnerAdapter);

        //settime
        binding.edtDateTime.setText(Helpers.getTime());

        //set data edit
        if (mRevenueExpenditureTable != null) {
            binding.edtPrice.setText(mRevenueExpenditureTable.getmPrice() + "");
            binding.edtDateTime.setText(mRevenueExpenditureTable.getmCreateTime());
            binding.edtNode.setText(mRevenueExpenditureTable.getmContent());
            binding.spinnerDialog.setSelection(mRevenueExpenditureViewmodel.getIndexSpinner(mRevenueExpenditureTable.getmTitle()));
        }
    }

    //khởi tạo viewmodel
    private void initViewModel() {
        mRevenueExpenditureViewmodel = ViewModelProviders.of(getActivity()).get(RevenueExpenditureViewmodel.class);

        mRevenueExpenditureViewmodel.getListSpinner(mType).observe(getViewLifecycleOwner(), new Observer<List<SpinnerModel>>() {
            @Override
            public void onChanged(List<SpinnerModel> spinnerModels) {
                mRevenueExpenditureViewmodel.setmListSpinner(spinnerModels);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.colorPickerStyle);
    }

    @Override
    public void onResume() {
        Window window = getDialog().getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        super.onResume();
    }

    //callback
    @Override
    public FragmentDialogRevenueExpenditure getTimePickerDialog(String time) {
        binding.edtDateTime.setText(time);
        return this;
    }

    @Override
    public FragmentDialogRevenueExpenditure getTitleDialog(String title) {
        mType = title;
        return this;
    }

    @Override
    public FragmentDialogRevenueExpenditure getRevenueExpenditureObject(RevenueExpenditureTable revenueExpenditureTable) {
        mRevenueExpenditureTable = revenueExpenditureTable;
        return this;
    }

    //show dialog lên
    public void showFragment(FragmentManager fragmentManager,String TAG){
        if(instance.isVisible()){
            dismiss();
        }else{
            show(fragmentManager,TAG);
        }
    }

    //getInstance() Design Pattern SingleTone
    public static FragmentDialogRevenueExpenditure getInstance(){
        if(instance == null){
            instance = new FragmentDialogRevenueExpenditure();
        }
        return instance;
    }
}
