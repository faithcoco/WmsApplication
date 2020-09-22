// Generated by data binding compiler. Do not edit!
package com.lkrh.storescontrol.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.lkrh.storescontrol.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityProductionOrderBinding extends ViewDataBinding {
  @NonNull
  public final Button bSubmit;

  @NonNull
  public final EditText etDepcode;

  @NonNull
  public final EditText etInvcode;

  @NonNull
  public final EditText etMemo;

  @NonNull
  public final EditText etQuantity;

  @NonNull
  public final RadioButton rbOrderclass1;

  @NonNull
  public final RadioButton rbOrderclass2;

  @NonNull
  public final RadioButton rbOrderclass3;

  @NonNull
  public final RadioButton rbOrderclass4;

  @NonNull
  public final RadioButton rbOrderclass5;

  @NonNull
  public final RadioButton rbOrderclass6;

  @NonNull
  public final RadioButton rbStandard;

  @NonNull
  public final RadioButton rbUnstandard;

  @NonNull
  public final RadioGroup rgOrderclass;

  @NonNull
  public final RadioGroup rgOrdertype;

  @NonNull
  public final RelativeLayout rlMemo;

  @NonNull
  public final RelativeLayout rlQuantity;

  @NonNull
  public final TextView tvMemo;

  @NonNull
  public final TextView tvOrderclass;

  @NonNull
  public final TextView tvOrdertype;

  @NonNull
  public final TextView tvQuantity;

  @NonNull
  public final TextView tvTitle1;

  @NonNull
  public final TextView tvTitle2;

  protected ActivityProductionOrderBinding(Object _bindingComponent, View _root,
      int _localFieldCount, Button bSubmit, EditText etDepcode, EditText etInvcode, EditText etMemo,
      EditText etQuantity, RadioButton rbOrderclass1, RadioButton rbOrderclass2,
      RadioButton rbOrderclass3, RadioButton rbOrderclass4, RadioButton rbOrderclass5,
      RadioButton rbOrderclass6, RadioButton rbStandard, RadioButton rbUnstandard,
      RadioGroup rgOrderclass, RadioGroup rgOrdertype, RelativeLayout rlMemo,
      RelativeLayout rlQuantity, TextView tvMemo, TextView tvOrderclass, TextView tvOrdertype,
      TextView tvQuantity, TextView tvTitle1, TextView tvTitle2) {
    super(_bindingComponent, _root, _localFieldCount);
    this.bSubmit = bSubmit;
    this.etDepcode = etDepcode;
    this.etInvcode = etInvcode;
    this.etMemo = etMemo;
    this.etQuantity = etQuantity;
    this.rbOrderclass1 = rbOrderclass1;
    this.rbOrderclass2 = rbOrderclass2;
    this.rbOrderclass3 = rbOrderclass3;
    this.rbOrderclass4 = rbOrderclass4;
    this.rbOrderclass5 = rbOrderclass5;
    this.rbOrderclass6 = rbOrderclass6;
    this.rbStandard = rbStandard;
    this.rbUnstandard = rbUnstandard;
    this.rgOrderclass = rgOrderclass;
    this.rgOrdertype = rgOrdertype;
    this.rlMemo = rlMemo;
    this.rlQuantity = rlQuantity;
    this.tvMemo = tvMemo;
    this.tvOrderclass = tvOrderclass;
    this.tvOrdertype = tvOrdertype;
    this.tvQuantity = tvQuantity;
    this.tvTitle1 = tvTitle1;
    this.tvTitle2 = tvTitle2;
  }

  @NonNull
  public static ActivityProductionOrderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_production_order, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityProductionOrderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityProductionOrderBinding>inflateInternal(inflater, R.layout.activity_production_order, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityProductionOrderBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_production_order, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityProductionOrderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityProductionOrderBinding>inflateInternal(inflater, R.layout.activity_production_order, null, false, component);
  }

  public static ActivityProductionOrderBinding bind(@NonNull View view) {
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
  public static ActivityProductionOrderBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ActivityProductionOrderBinding)bind(component, view, R.layout.activity_production_order);
  }
}
