package id.co.ardilobrt.shopsnotes.ui.insert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import id.co.ardilobrt.shopsnotes.R
import id.co.ardilobrt.shopsnotes.databinding.ActivityNoteAddUpdateBinding
import id.co.ardilobrt.shopsnotes.entity.Notes
import id.co.ardilobrt.shopsnotes.helper.MyHelper
import id.co.ardilobrt.shopsnotes.helper.ViewModelFactory

class NoteAddUpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteAddUpdateBinding
    private lateinit var viewModel: NoteAddUpdateViewModel
    private lateinit var actionBarTitle: String
    private lateinit var btnTitle: String
    private lateinit var dialogTitle: String
    private lateinit var dialogMessage: String
    private var isEdit = false
    private var notes: Notes? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = obtainViewModel(this)

        notes = intent.getParcelableExtra(EXTRA_NOTE)
        if (notes != null) isEdit = true else notes = Notes()

        setUi()
    }

    private fun setUi() = with(binding) {
        if (isEdit) {
            actionBarTitle = getString(R.string.change)
            btnTitle = getString(R.string.update).uppercase()
            if (notes != null) {
                notes?.let { note ->
                    edtName.setText(note.name)
                    edtBarcode.setText(note.barcode)
                    edtUnit.setText(note.unit)
                    edtStock.setText(note.stock.toString())
                    edtBuyPrice.setText(note.buyPrice.toString())
                    edtSellPrice.setText(note.sellPrice.toString())
                }
            }
        } else {
            actionBarTitle = getString(R.string.add)
            btnTitle = getString(R.string.save).uppercase()
        }
        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        btnSubmit.text = btnTitle

        binding.btnSubmit.setOnClickListener { setButtonListener() }
    }

    private fun setButtonListener() = with(binding) {
        val inputName = edtName.text.toString().trim()
        val inputBarcode = edtBarcode.text.toString().trim()
        val inputUnit = edtUnit.text.toString().trim()

        when {
            inputName.isEmpty() -> edtName.error = getString(R.string.empty)
            inputBarcode.isEmpty() -> edtBarcode.error = getString(R.string.empty)
            inputUnit.isEmpty() -> edtUnit.error = getString(R.string.empty)
            inputUnit.any { !it.isLetter() } -> edtUnit.error = getString(R.string.must_letter)
            else -> showButtonResult(inputName, inputBarcode, inputUnit)
        }
    }

    private fun showButtonResult(inputTitle: String, inputDesc: String, inputUnit: String) =
        with(binding) {
            val inputStock = edtStock.text.toString().trim()
            val inputBuyPrice = edtBuyPrice.text.toString().trim()
            val inputSellPrice = edtSellPrice.text.toString().trim()
            notes.let {
                it?.name = inputTitle
                it?.barcode = inputDesc
                it?.unit = inputUnit
                it?.stock = inputStock.toInt()
                it?.buyPrice = inputBuyPrice.toInt()
                it?.sellPrice = inputSellPrice.toInt()
                it?.date = MyHelper.getCurrentDate()
            }
            if (isEdit) {
                viewModel.update(notes as Notes)
                showToast(getString(R.string.changed))
            } else {
                viewModel.insert(notes as Notes)
                showToast(getString(R.string.added))
            }
            finish()
        }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (isEdit) menuInflater.inflate(R.menu.menu_form, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE

        if (isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogMessage = getString(R.string.message_delete)
            dialogTitle = getString(R.string.delete)
        }

        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                if (!isDialogClose) {
                    viewModel.delete(notes as Notes)
                    showToast(getString(R.string.deleted))
                }
                finish()
            }
            setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun obtainViewModel(activity: AppCompatActivity): NoteAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        val viewModel: NoteAddUpdateViewModel by viewModels { factory }
        return viewModel
    }

    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }
}