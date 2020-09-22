// Generated by data binding compiler. Do not edit!
package com.lkrh.storescontrol.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.bean.ConfirmlistBean;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemCkkbBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout lInput;

  @NonNull
  public final RelativeLayout rlLayout;

  @NonNull
  public final TextView tvTag;

  @NonNull
  public final TextView tvTitle1;

  @NonNull
  public final TextView tvTitle2;

  @NonNull
  public final TextView tvTitle3;

  @NonNull
  public final TextView tvTitle4;

  @Bindable
  protected ConfirmlistBean mData;

  protected ItemCkkbBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout lInput, RelativeLayout rlLayout, TextView tvTag, TextView tvTitle1,
      TextView tvTitle2, TextView tvTitle3, TextView tvTitle4) {
    super(_bindingComponent, _root, _localFieldCount);
    this.lInput = lInput;
    this.rlLayout = rlLayout;
    this.tvTag = tvTag;
    this.tvTitle1 = tvTitle1;
    this.tvTitle2 = tvTitle2;
    this.tvTitle3 = tvTitle3;
    this.tvTitle4 = tvTitle4;
  }

  public abstract void setData(@Nullable ConfirmlistBean data);

  @Nullable
  public ConfirmlistBean getData() {
    return mData;
  }

  @NonNull
  public static ItemCkkbBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_ckkb, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemCkkbBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemCkkbBinding>inflateInternal(inflater, R.layout.item_ckkb, root, attachToRoot, component);
  }

  @NonNull
  public static ItemCkkbBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_ckkb, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemCkkbBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemCkkbBinding>inflateInternal(inflater, R.layout.item_ckkb, null, false, component);
  }

  public static ItemCkkbBinding bind(@NonNull View view) {
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
  public static ItemCkkbBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemCkkbBinding)bind(component, view, R.layout.item_ckkb);
  }
}
