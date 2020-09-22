// Generated by data binding compiler. Do not edit!
package com.lkrh.storescontrol.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public abstract class ActivityInventoryBinding extends ViewDataBinding {
  @NonNull
  public final View lTitle;

  @NonNull
  public final RecyclerView rvGxbgInventory;

  @Bindable
  protected UnqualifiedListActivity.FunctionAdapter mAdapter;

  protected ActivityInventoryBinding(Object _bindingComponent, View _root, int _localFieldCount,
      View lTitle, RecyclerView rvGxbgInventory) {
    super(_bindingComponent, _root, _localFieldCount);
    this.lTitle = lTitle;
    this.rvGxbgInventory = rvGxbgInventory;
  }

  public abstract void setAdapter(@Nullable UnqualifiedListActivity.FunctionAdapter adapter);

  @Nullable
  public UnqualifiedListActivity.FunctionAdapter getAdapter() {
    return mAdapter;
  }

  @NonNull
  public static ActivityInventoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_inventory, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityInventoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityInventoryBinding>inflateInternal(inflater, R.layout.activity_inventory, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityInventoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_inventory, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityInventoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityInventoryBinding>inflateInternal(inflater, R.layout.activity_inventory, null, false, component);
  }

  public static ActivityInventoryBinding bind(@NonNull View view) {
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
  public static ActivityInventoryBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityInventoryBinding)bind(component, view, R.layout.activity_inventory);
  }
}