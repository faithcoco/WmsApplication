package com.lkrh.storescontrol.bean

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GxbgBean (var Resultcode:String, var ResultMessage:String,var cStatus:String, var data:ArrayList<Data>) :
    Parcelable {
    @Parcelize
    data class Data(var InvCode:String,var InvName:String,var InvStd:String,var iquantity:String,var rowno:String) :
        Parcelable
}
data class ProcessBean (var Resultcode:String, var ResultMessage:String,var cStatus:String, var data:ArrayList<Data>)  {
    data class Data(var rowno:String,var ccode:String,var invcode:String,var invname:String,
                           var invstd:String,var iQuantity:String,var ProcessNo:String,var ProcessDes:String,
                           var quantitysBG:String,var quantitysWBG:String)
}
