package id.co.ardilobrt.shopsnotes.helper

import androidx.recyclerview.widget.DiffUtil
import id.co.ardilobrt.shopsnotes.entity.Notes

class NoteDiffCallback(
    private val oldNotesList: List<Notes>,
    private val newNotesList: List<Notes>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldNotesList.size

    override fun getNewListSize(): Int = newNotesList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNotesList[oldItemPosition].id == newNotesList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNotes = oldNotesList[oldItemPosition]
        val newNotes = newNotesList[newItemPosition]
        return oldNotes.name == newNotes.name
                && oldNotes.barcode == newNotes.barcode
                && oldNotes.unit == newNotes.unit
                && oldNotes.stock == newNotes.stock
                && oldNotes.buyPrice == newNotes.buyPrice
                && oldNotes.sellPrice == newNotes.sellPrice
    }
}