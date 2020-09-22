// Generated by data binding compiler. Do not edit!
package com.lkrh.storescontrol.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.ArrivalHeadBean;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityPrintBinding extends ViewDataBinding {
  @NonNull
  public final Button bPrint;

  @NonNull
  public final EditText etCInvCName;

  @NonNull
  public final EditText etCInvStd;

  @NonNull
  public final EditText etCbatch;

  @NonNull
  public final EditText etCinvcode;

  @NonNull
  public final EditText etCvenabbname;

  @NonNull
  public final EditText etGys;

  @NonNull
  public final EditText etIquantity;

  @NonNull
  public final EditText etPage;

  @NonNull
  public final ImageView ivCode;

  @NonNull
  public final RelativeLayout rlTag;

  @Bindable
  protected ArrivalHeadBean mArrivalHeadBean;

  protected ActivityPrintBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button bPrint, EditText etCInvCName, EditText etCInvStd, EditText etCbatch,
      EditText etCinvcode, EditText etCvenabbname, EditText etGys, EditText etIquantity,
      EditText etPage, ImageView ivCode, RelativeLayout rlTag) {
    super(_bindingComponent, _root, _localFieldCount);
    this.bPrint = bPrint;
    this.etCInvCName = etCInvCName;
    this.etCInvStd = etCInvStd;
    this.etCbatch = etCbatch;
    this.etCinvcode = etCinvcode;
    this.etCvenabbname = etCvenabbname;
    this.etGys = etGys;
    this.etIquantity = etIquantity;
    this.etPage = etPage;
    this.ivCode = ivCode;
    this.rlTag = rlTag;
  }

  public abstract void setArrivalHeadBean(@Nullable ArrivalHeadBean arrivalHeadBean);

  @Nullable
  public ArrivalHeadBean getArrivalHeadBean() {
    return mArrivalHeadBean;
  }

  @NonNull
  public static ActivityPrintBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_print, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityPrintBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityPrintBinding>inflateInternal(inflater, R.layout.activity_print, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityPrintBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_print, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityPrintBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityPrintBinding>inflateInternal(inflater, R.layout.activity_print, null, false, component);
  }

  public static ActivityPrintBinding bind(@NonNull View view) {
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
  public static ActivityPrintBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityPrintBinding)bind(component, view, R.layout.activity_print);
  }
}
