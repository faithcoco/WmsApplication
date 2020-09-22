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
import com.lkrh.storescontrol.bean.GxbgBean;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemGxbgBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout lInput;

  @NonNull
  public final RelativeLayout rlLayout;

  @NonNull
  public final TextView tvInvCode;

  @NonNull
  public final TextView tvInvName;

  @NonNull
  public final TextView tvInvStd;

  @NonNull
  public final TextView tvIquantity;

  @NonNull
  public final TextView tvRowno;

  @NonNull
  public final TextView tvTag;

  @Bindable
  protected GxbgBean.Data mData;

  protected ItemGxbgBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout lInput, RelativeLayout rlLayout, TextView tvInvCode, TextView tvInvName,
      TextView tvInvStd, TextView tvIquantity, TextView tvRowno, TextView tvTag) {
    super(_bindingComponent, _root, _localFieldCount);
    this.lInput = lInput;
    this.rlLayout = rlLayout;
    this.tvInvCode = tvInvCode;
    this.tvInvName = tvInvName;
    this.tvInvStd = tvInvStd;
    this.tvIquantity = tvIquantity;
    this.tvRowno = tvRowno;
    this.tvTag = tvTag;
  }

  public abstract void setData(@Nullable GxbgBean.Data data);

  @Nullable
  public GxbgBean.Data getData() {
    return mData;
  }

  @NonNull
  public static ItemGxbgBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_gxbg, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemGxbgBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup root,
      boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemGxbgBinding>inflateInternal(inflater, R.layout.item_gxbg, root, attachToRoot, component);
  }

  @NonNull
  public static ItemGxbgBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_gxbg, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemGxbgBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemGxbgBinding>inflateInternal(inflater, R.layout.item_gxbg, null, false, component);
  }

  public static ItemGxbgBinding bind(@NonNull View view) {
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
  public static ItemGxbgBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemGxbgBinding)bind(component, view, R.layout.item_gxbg);
  }
}
