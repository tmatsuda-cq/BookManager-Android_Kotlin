package com.android.bookmanager_kotlin.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.setFragmentResultListener
import com.android.bookmanager_kotlin.R
import com.android.bookmanager_kotlin.util.AlertDialogUtils.showAlertDialog
import com.android.bookmanager_kotlin.util.DatePickerUtils
import com.android.bookmanager_kotlin.util.FragmentUtils
import com.android.bookmanager_kotlin.util.ValidationUtils
import java.lang.Exception

class EditBookFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_edit_book, container, false)

        (activity as? AppCompatActivity)?.supportActionBar?.let {
            it.setTitle(R.string.app_edit_book)
            it.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)

        // 書籍一覧から渡されたデータをviewに表示する
        setFragmentResultListener("bookData") { _, bundle ->
            view.findViewById<EditText>(R.id.input_book_name).setText(bundle.getString("bookName"))
            view.findViewById<EditText>(R.id.input_book_price).setText(bundle.getInt("bookPrice").toString())
            view.findViewById<EditText>(R.id.input_book_purchase_date).setText(bundle.getString("bookPurchaseDate"))
        }

        return view
    }

    // onCreate() -> onCreateView() -> onActivityCreated() -> onStart() -> onResume()
    // onCreateView()で下記処理を実装するとnullポになってしまうため onStart()で実装
    override fun onStart() {
        super.onStart()

        // 画像取得処理
        view?.findViewById<Button>(R.id.edit_book_image_button)?.setOnClickListener {
            selectBookImage()
        }

        view?.findViewById<EditText>(R.id.input_book_purchase_date)?.setOnClickListener {
                DatePickerUtils.showDatePicker(requireContext(), it.findViewById(R.id.input_book_purchase_date))
        }
    }

    private fun selectBookImage() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }

        val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
            if (result?.resultCode == Activity.RESULT_OK) {
                result.data?.let { data: Intent ->
                    try {
                        data?.data?.also { uri ->
                            val inputStream = activity?.contentResolver?.openInputStream(uri)
                            val image = BitmapFactory.decodeStream(inputStream)
                            view?.findViewById<ImageView>(R.id.book_image)?.setImageBitmap(image)
                        }
                    } catch (e: Exception) {
                        requireActivity().showAlertDialog(R.string.dialog_title,R.string.error_insert_book_image)
                    }
                }
            }
        }
        launcher.launch(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu)
    }

    // アクションバー戻るボタンクリックでフラグメント切り替え
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                FragmentUtils.showFragment(BookListFragment(), parentFragmentManager, R.id.activity_book_list_layout)
                return true
            }
            R.id.bt_save -> {
                val name = view?.findViewById<EditText>(R.id.input_book_name)?.text.toString()
                val price = view?.findViewById<EditText>(R.id.input_book_price)?.text.toString()
                val purchaseDate = view?.findViewById<EditText>(R.id.input_book_purchase_date)?.text.toString()

                @StringRes
                val errorMessage = ValidationUtils.validationCheckBookData(name, price, purchaseDate)

                // バリデーションに引っかかっているかをnullかどうかで判断している
                if (errorMessage == null) {
                    FragmentUtils.showFragment(BookListFragment(), parentFragmentManager, R.id.activity_book_list_layout)
                } else {
                    requireActivity().showAlertDialog(R.string.dialog_title,errorMessage)
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
