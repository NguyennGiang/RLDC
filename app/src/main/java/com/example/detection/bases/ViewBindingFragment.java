package com.example.detection.bases;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import com.example.detection.R;
import com.example.detection.Utils.LogUtil;

import java.util.Objects;

public abstract class ViewBindingFragment <VB extends ViewBinding> extends Fragment {

    public VB binding;

    public abstract VB getViewBinding(@NonNull LayoutInflater inflater, @NonNull ViewGroup viewGroup);

    private AlertDialog alertDialog = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoadingDialog();
        LogUtil.d("OPEN_FRAGMENT: " + this.getClass().getSimpleName());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = getViewBinding(inflater, container);
        return Objects.requireNonNull(this.binding).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            initViewOnViewCreated(view, savedInstanceState);
            observeViewModelEvent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void observeViewModelEvent() {

    }

    protected abstract void initViewOnViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState);

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }

    protected void openFragment(int destination) {
        try {
            Navigation.findNavController(binding.getRoot()).navigate(destination);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void openFragment(int destination, Bundle bundle) {
        try {
            Navigation.findNavController(binding.getRoot()).navigate(destination, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void openDialog(DialogFragment dialogFragment, String tag) {
        dialogFragment.show(getChildFragmentManager(), tag);
    }

    protected void backToPreviousScreen() {
        try {
            Navigation.findNavController(binding.getRoot()).popBackStack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void showError(@Nullable String message) {
        Toast toast = Toast.makeText(requireContext(), message != null ? message : getString(R.string.error_common), Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void showMessage(String message) {
        Toast toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }


    protected void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    private void initLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity(), R.style.DialogStyle);
        builder.setCancelable(false);
        builder.setView(R.layout.layout_loading);

        alertDialog = builder.create();
    }

    protected void showLoading() {
        if (alertDialog != null && !alertDialog.isShowing() && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            try {
                alertDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void hideLoading() {
        if (alertDialog != null && alertDialog.isShowing() && getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            try {
                alertDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
