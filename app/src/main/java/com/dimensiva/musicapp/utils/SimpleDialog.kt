package com.dimensiva.musicapp.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.dimensiva.musicapp.R
import com.dimensiva.musicapp.databinding.FragmentSimpleDialogBinding

class SimpleDialog: DialogFragment() {

    private lateinit var fragmentDialogBinding: FragmentSimpleDialogBinding

    companion object {

        const val TAG = "SimpleDialog"

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"

        fun newInstance(title: String, subTitle: String): SimpleDialog {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUBTITLE, subTitle)
            val fragment = SimpleDialog()
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDialogBinding = FragmentSimpleDialogBinding.inflate(inflater, container, false)
        return fragmentDialogBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentDialogBinding = FragmentSimpleDialogBinding.bind(view)

        setupView()
        setupClickListeners()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupView() {

        fragmentDialogBinding.tvTitle.text = arguments?.getString(KEY_TITLE)
        fragmentDialogBinding.tvSubTitle.text = arguments?.getString(KEY_SUBTITLE)
    }

    private fun setupClickListeners() {
        fragmentDialogBinding.btnPositive.setOnClickListener {
            // TODO: Do some task here
            dismiss()
        }
        fragmentDialogBinding.btnNegative.setOnClickListener {
            // TODO: Do some task here
            dismiss()
        }
    }
}