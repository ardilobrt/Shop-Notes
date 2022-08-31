package id.co.ardilobrt.shopsnotes.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Notes")
@Parcelize
data class Notes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "barcode")
    var barcode: String? = null,

    @ColumnInfo(name = "unit")
    var unit: String? = null,

    @ColumnInfo(name = "stock")
    var stock: Int = 0,

    @ColumnInfo(name = "buy price")
    var buyPrice: Int = 0,

    @ColumnInfo(name = "sell price")
    var sellPrice: Int = 0,

    @ColumnInfo(name = "date")
    var date: String? = null
) : Parcelable