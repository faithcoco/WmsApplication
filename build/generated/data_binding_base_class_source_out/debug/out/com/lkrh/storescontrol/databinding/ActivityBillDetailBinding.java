// Generated by data binding compiler. Do not edit!
package com.lkrh.storescontrol.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.view.declaration.UnqualifiedListActivity;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityBillDetailBinding extends ViewDataBinding {
  @NonNull
  public final Button bDelete;

  @NonNull
  public final Button bSubmit;

  @NonNull
  public final EditText etCwhcode;

  @NonNull
  public final ImageView ivClear;

  @NonNull
  public final ImageView ivCwhcode;

  @NonNull
  public final ImageView ivHelp;

  @NonNull
  public final RelativeLayout lBottom;

  @NonNull
  public final View lTitle;

  @NonNull
  public final LinearLayout lTop;

  @NonNull
  public final RelativeLayout rlCwhcode;

  @NonNull
  public final RecyclerView rvList;

  @NonNull
  public final TextView tvCwhcode;

  @Bindable
  protected UnqualifiedListActivity.FunctionAdapter mAdapter;

  protected ActivityBillDetailBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button bDelete, Button bSubmit, EditText etCwhcode, ImageView ivClear, ImageView ivCwhcode,
      ImageView ivHelp, RelativeLayout lBottom, View lTitle, LinearLayout lTop,
      RelativeLayout rlCwhcode, RecyclerView rvList, TextView tvCwhcode) {
    super(_bindingComponent, _root, _localFieldCount);
    this.bDelete = bDelete;
    this.bSubmit = bSubmit;
    this.etCwhcode = etCwhcode;
    this.ivClear = ivClear;
    this.ivCwhcode = ivCwhcode;
    this.ivHelp = ivHelp;
    this.lBottom = lBottom;
    this.lTitle = lTitle;
    this.lTop = lTop;
    this.rlCwhcode = rlCwhcode;
    this.rvList = rvList;
    this.tvCwhcode = tvCwhcode;
  }

  public abstract void setAdapter(@Nullable UnqualifiedListActivity.FunctionAdapter adapter);

  @Nullable
  public UnqualifiedListActivity.FunctionAdapter getAdapter() {
    return mAdapter;
  }

  @NonNull
  public static ActivityBillDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_bill_detail, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityBillDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityBillDetailBinding>inflateInternal(inflater, R.layout.activity_bill_detail, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityBillDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_bill_detail, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityBillDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityBillDetailBinding>inflateInternal(inflater, R.layout.activity_bill_detail, null, false, component);
  }

  public static ActivityBillDetailBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivityBillDetailBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityBillDetailBinding)bind(component, view, R.layout.activity_bill_detail);
  }
}
