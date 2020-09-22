package com.lkrh.storescontrol.databinding;
import com.lkrh.storescontrol.R;
import com.lkrh.storescontrol.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityGxbgBindingImpl extends ActivityGxbgBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new androidx.databinding.ViewDataBinding.IncludedLayouts(13);
        sIncludes.setIncludes(0, 
            new String[] {"item_gxbg", "item_process"},
            new int[] {1, 2},
            new int[] {com.lkrh.storescontrol.R.layout.item_gxbg,
                com.lkrh.storescontrol.R.layout.item_process});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_title1, 3);
        sViewsWithIds.put(R.id.iv_scan, 4);
        sViewsWithIds.put(R.id.et_code, 5);
        sViewsWithIds.put(R.id.tv_cStatus, 6);
        sViewsWithIds.put(R.id.b_start, 7);
        sViewsWithIds.put(R.id.tv_inventory, 8);
        sViewsWithIds.put(R.id.tv_process, 9);
        sViewsWithIds.put(R.id.et_count, 10);
        sViewsWithIds.put(R.id.et_user, 11);
        sViewsWithIds.put(R.id.b_ok, 12);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityGxbgBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds));
    }
    private ActivityGxbgBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.Button) bindings[12]
            , (android.widget.Button) bindings[7]
            , (android.widget.EditText) bindings[5]
            , (android.widget.EditText) bindings[10]
            , (android.widget.EditText) bindings[11]
            , (android.widget.ImageView) bindings[4]
            , (com.lkrh.storescontrol.databinding.ItemGxbgBinding) bindings[1]
            , (com.lkrh.storescontrol.databinding.ItemProcessBinding) bindings[2]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[3]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
        }
        lGxbgInfo.invalidateAll();
        lGxbgProcess.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (lGxbgInfo.hasPendingBindings()) {
            return true;
        }
        if (lGxbgProcess.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.bean == variableId) {
            setBean((com.lkrh.storescontrol.bean.GxbgBean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setBean(@Nullable com.lkrh.storescontrol.bean.GxbgBean Bean) {
        this.mBean = Bean;
    }

    @Override
    public void setLifecycleOwner(@Nullable androidx.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        lGxbgInfo.setLifecycleOwner(lifecycleOwner);
        lGxbgProcess.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeLGxbgInfo((com.lkrh.storescontrol.databinding.ItemGxbgBinding) object, fieldId);
            case 1 :
                return onChangeLGxbgProcess((com.lkrh.storescontrol.databinding.ItemProcessBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeLGxbgInfo(com.lkrh.storescontrol.databinding.ItemGxbgBinding LGxbgInfo, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeLGxbgProcess(com.lkrh.storescontrol.databinding.ItemProcessBinding LGxbgProcess, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
        executeBindingsOn(lGxbgInfo);
        executeBindingsOn(lGxbgProcess);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): lGxbgInfo
        flag 1 (0x2L): lGxbgProcess
        flag 2 (0x3L): bean
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}