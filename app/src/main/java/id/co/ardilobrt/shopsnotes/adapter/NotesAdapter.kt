package id.co.ardilobrt.shopsnotes.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.ardilobrt.shopsnotes.entity.Notes
import id.co.ardilobrt.shopsnotes.helper.NoteDiffCallback
import id.co.ardilobrt.shopsnotes.ui.insert.NoteAddUpdateActivity
import id.co.ardilobrt.shopsnotes.databinding.ItemNoteBinding
import id.co.ardilobrt.shopsnotes.helper.MyHelper

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val listNotes = ArrayList<Notes>()

    fun setListNotes(listNotes: List<Notes>) {
        val diffCallback = NoteDiffCallback(this.listNotes, listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNotes.clear()
        this.listNotes.addAll(listNotes)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view =
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = listNotes[position]
        holder.itemView.setOnClickListener {
            Intent(it.context, NoteAddUpdateActivity::class.java).also { intent ->
                intent.putExtra(NoteAddUpdateActivity.EXTRA_NOTE, item)
                it.context.startActivity(intent)
            }
        }

        holder.bind(item)
    }

    override fun getItemCount(): Int = listNotes.size

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notes: Notes) = with(binding) {
            tvItemDate.text = notes.date
            tvItemName.text = notes.name
            tvItemBarcode.text = notes.barcode
            tvItemUnit.text = notes.unit
            tvItemStock.text = notes.stock.toString()
            tvItemBuy.text = MyHelper.setToRupiah(notes.buyPrice)
            tvItemSell.text = MyHelper.setToRupiah(notes.sellPrice)
        }
    }
}