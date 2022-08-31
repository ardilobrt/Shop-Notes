package id.co.ardilobrt.shopsnotes.helper

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object MyHelper {
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }

    fun setToRupiah(number: Int): String = DecimalFormat("Rp #,###").format(number).toString()
}